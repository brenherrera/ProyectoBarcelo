package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Gym;
import model.Spa;

public class GymGestion {

    private static final String SQL_GETRESERVACIONESG = "SELECT * FROM hotelmaribu.gym";
    private static final String SQL_GETRESERVACIONG = "SELECT * FROM hotelmaribu.gym where id=? and numHabit=?";
    private static final String SQL_INSERTARRESERVACIONG = "insert into hotelmaribu.gym(numHabit,nombre,apellido1,apellido2,horaIngr) values (?,?,?,?,?)";
    private static final String SQL_MODIFICARRESERVACIONG = "update hotelmaribu.gym set nombre=?,apellido1=?,apellido2=?,horaIngr=? where id=? and numHabit=?";
    private static final String SQL_ELIMINARRESERVACIONG = "Delete FROM hotelmaribu.gym where id=? and numHabit=?";

    public static ArrayList<Gym> getReservacionesG() {
        ArrayList<Gym> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESERVACIONESG);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Gym(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(GymGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Gym getReservacionG(int id, int numHabit) {
        Gym gym = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESERVACIONG);
            sentencia.setInt(1, id);
            sentencia.setInt(2, numHabit);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                gym = new Gym(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(GymGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return gym;
    }

    public static boolean insertaReservacionG(Gym gym) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARRESERVACIONG);
            sentencia.setInt(1, gym.getNumHabit());
            sentencia.setString(2, gym.getNombre());
            sentencia.setString(3, gym.getApellido1());
            sentencia.setString(4, gym.getApellido2());
            sentencia.setString(5, gym.getHoraIngr());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GymGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarReservacionG(Gym gym) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARRESERVACIONG);
            sentencia.setInt(1, gym.getNumHabit());
            sentencia.setString(2, gym.getNombre());
            sentencia.setString(3, gym.getApellido1());
            sentencia.setString(4, gym.getApellido2());
            sentencia.setString(5, gym.getHoraIngr());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GymGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarReservacionG(Gym gym) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARRESERVACIONG);
            sentencia.setInt(1, gym.getId());
            sentencia.setInt(2, gym.getNumHabit());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(GymGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        Gym gym = null;
        String tiraJson = "";
        String numHabit = "";
        String nombre = "";
        String horaIngr = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETRESERVACIONESG);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                gym = new Gym(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                numHabit = sdf.format(gym.getNumHabit());
                nombre = sdf.format(gym.getNombre());
                horaIngr = sdf.format(gym.getHoraIngr());
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", gym.getId())
                        .add("numHabit", numHabit)
                        .add("nombre", nombre)
                        .add("apellido1", gym.getApellido1())
                        .add("apellido2", gym.getApellido2())
                        .add("horaIngr", horaIngr)
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tiraJson;
    }
}
