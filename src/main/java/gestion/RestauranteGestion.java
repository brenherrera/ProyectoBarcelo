package gestion;

import java.io.StringWriter;
import java.math.BigDecimal;
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
import model.Restaurante;
import model.Spa;

public class RestauranteGestion {

    private static final String SQL_GETRESTAURANTE = "SELECT * FROM hotelmaribu.restaurante";
    private static final String SQL_GETRESTAURANTES = "SELECT * FROM hotelmaribu.restaurante where id=? and numHabit=?";
    private static final String SQL_INSERTARRESTAURANTES = "insert into hotelmaribu.restaurante(numHabit,nombre,apellido1,apellido2,numMesa,cantPersonas) values (?,?,?,?,?,?)";
    private static final String SQL_MODIFICARRESTAURANTES = "update hotelmaribu.restaurante set nombre=?,apellido1=?,apellido2=?,numMesa=?,cantPersonas=? where id=? and numHabit=?";
    private static final String SQL_ELIMINARRESTAURANTES = "Delete FROM hotelmaribu.restaurante where id=? and numHabit=?";

    public static ArrayList<Restaurante> getRestaurantes() {
        ArrayList<Restaurante> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESTAURANTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Restaurante(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Restaurante getRestaurante(int id, int numHabit) {
        Restaurante restaurante = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETRESTAURANTE);
            sentencia.setInt(1, id);
            sentencia.setInt(2, numHabit);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                restaurante = new Restaurante(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return restaurante;
    }

    public static boolean insertaRestaurante(Restaurante restaurante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARRESTAURANTES);
            sentencia.setInt(1, restaurante.getNumHabit());
            sentencia.setString(2, restaurante.getNombre());
            sentencia.setString(3, restaurante.getApellido1());
            sentencia.setString(4, restaurante.getApellido2());
            sentencia.setInt(5, restaurante.getNumMesa());
            sentencia.setInt(6, restaurante.getCantPersonas());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarRestaurante(Restaurante restaurante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARRESTAURANTES);
            sentencia.setString(1, restaurante.getNombre());
            sentencia.setString(2, restaurante.getApellido1());
            sentencia.setString(3, restaurante.getApellido2());
            sentencia.setInt(4, restaurante.getNumMesa());
            sentencia.setInt(5, restaurante.getCantPersonas());
            sentencia.setInt(6, restaurante.getNumHabit());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarRestaurante(Restaurante restaurante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARRESTAURANTES);
            sentencia.setInt(1, restaurante.getId());
            sentencia.setInt(2, restaurante.getNumHabit());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static String generarJson() {
        Restaurante rest = null;
        String tiraJson = "";
        String numHabit = "";
        String nombre = "";
        
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETRESTAURANTES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                rest = new Restaurante(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                );

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                numHabit = sdf.format(rest.getNumHabit());
                nombre = sdf.format(rest.getNombre());
               
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", rest.getId())
                        .add("numHabit", numHabit)
                        .add("nombre", nombre)
                        .add("apellido1", rest.getApellido1())
                        .add("apellido2", rest.getApellido2())
                        .add("numMesa", rest.getNumMesa())
                        .add("cantPersonas",rest.getCantPersonas())
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
