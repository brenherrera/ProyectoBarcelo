package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Habitaciones;

public class HabitacionesGestion {
    private static final String SQL_GETHABITACIONES = "SELECT * FROM habitaciones";
    private static final String SQL_GETHABITACION = "SELECT * FROM habitaciones  where numHabit=?";
    private static final String SQL_INSERTARHABITACION = "insert into habitaciones (tamaño,precio) values (?,?)";
    private static final String SQL_MODIFICARHABITACION = "update habitaciones set tamaño=?,precio=? where numHabit=?";
    private static final String SQL_ELIMINARHABITACION = "Delete FROM habitaciones where numHabit=?";

    public static ArrayList<Habitaciones> getHabitaciones() {
        ArrayList<Habitaciones> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETHABITACIONES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Habitaciones(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));

            }
            //System.out.println("lIST COUNT----------"+list.size());
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Habitaciones getHabitacion(int numHabit, String tamaño) {
        Habitaciones habitaciones = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETHABITACION);
            sentencia.setInt(1, numHabit);
            //sentencia.setString(2, tamaño);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                habitaciones = new Habitaciones(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return habitaciones;
    }

    public static boolean insertaHabitacion(Habitaciones habitaciones) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARHABITACION);
            sentencia.setString(1, habitaciones.getTamaño());
            sentencia.setString(3, habitaciones.getPrecio());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(HabitacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarHabitacion(Habitaciones habitaciones) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARHABITACION);
            sentencia.setString(1, habitaciones.getTamaño());
            sentencia.setString(2, habitaciones.getPrecio());
            sentencia.setInt(3, habitaciones.getNumHabit());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(HabitacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarHabitacion(Habitaciones habitaciones) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARHABITACION);
            sentencia.setInt(1, habitaciones.getNumHabit());
            sentencia.setString(2, habitaciones.getTamaño());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
}
