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
import model.Spa;

public class SpaGestion {
    private static final String SQL_GETSPAS = "SELECT * FROM hotelmaribu.spa";
    private static final String SQL_GETSPA = "SELECT * FROM hotelmaribu.spa where id=? and numHabit=?";
    private static final String SQL_INSERTARSPA = "insert into hotelmaribu.spa(numHabit,nombre,apellido1,apellido2,horaIngr) values (?,?,?,?,?)";
    private static final String SQL_MODIFICARSPA = "update hotelmaribu.spa set nombre=?,apellido1=?,apellido2=?,horaIngr=? where id=? and numHabit=?";
    private static final String SQL_ELIMINARSPA = "Delete FROM hotelmaribu.spa where id=? and numHabit=?";

    public static ArrayList<Spa> getSpas() {
        ArrayList<Spa> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETSPAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Spa(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                        ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Spa getSpa(int id, int numHabit) {
        Spa spa = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETSPA);
            sentencia.setInt(1, id);
            sentencia.setInt(2, numHabit);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                spa = new Spa(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                        );
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return spa;
    }

    public static boolean insertaSpa(Spa spa) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARSPA);
            sentencia.setInt(1, spa.getNumHabit());
            sentencia.setString(2, spa.getNombre());
            sentencia.setString(3, spa.getApellido1());
            sentencia.setString(4, spa.getApellido2());
            sentencia.setString(5, spa.getHoraIngr());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarSpa(Spa spa) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARSPA);
            sentencia.setString(1, spa.getNombre());
            sentencia.setString(2, spa.getApellido1());
            sentencia.setString(3, spa.getApellido2());
            sentencia.setString(4, spa.getHoraIngr());
            sentencia.setInt(5, spa.getNumHabit());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarSpa(Spa spa) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARSPA);
            sentencia.setInt(1, spa.getId());
            sentencia.setInt(2, spa.getNumHabit());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(SpaGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
    
    
        public static String generarJson() {
        Spa spa = null;
        String tiraJson = "";
        String numHabit = "";
        String nombre = "";
        String horaIngr=""; 
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETSPAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                spa = new Spa(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)                       
                );

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                numHabit = sdf.format(spa.getNumHabit());
                nombre = sdf.format(spa.getNombre());
                horaIngr = sdf.format(spa.getHoraIngr());
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", spa.getId())
                        .add("numHabit", numHabit)
                        .add("nombre", nombre)
                        .add("apellido1", spa.getApellido1())
                        .add("apellido2", spa.getApellido2())
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
