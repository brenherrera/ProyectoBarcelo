package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Huesped;
import model.YearGender;

public class HuespedesGestion {
    private static final String SQL_GETHUESPEDES = "SELECT * FROM hotelmaribu.huesped";
    private static final String SQL_GETHUESPED = "SELECT * FROM hotelmaribu.huesped where id=? and idHuesped=?";
    private static final String SQL_GETHUESPEDReporte = "SELECT * FROM hotelmaribu.huesped where  idHuesped=?";
    private static final String SQL_INSERTARHUESPED = "insert into hotelmaribu.huesped (idHuesped,numHabit,nombre,apellido1,apellido2,telefono,correo,fechaIngr,fechaSalida) values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_MODIFICARHUESPED = "update hotelmaribu.huesped  set numHabitHabitacion=?,nombre=?,apellido1=?,apellido2=?,telefono=?,correo=?,fechaIngr=?,fechaSalida=? where id=? and idHuesped=?";
    private static final String SQL_ELIMINARHUESPED = "Delete FROM hotelmaribu.huesped where id=? and idHuesped=?";
    private static final String SQL_INGRESO_YEAR_GENDER ="SELECT YEAR(fechaIngr) as Fecha,apellido1,Count(*) total FROM hotelmaribu.huesped group by YEAR(fechaIngr),apellido1 order by YEAR(fechaIngr)";

    public static ArrayList<Huesped> getHuespedes() {
        ArrayList<Huesped> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETHUESPEDES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Huesped(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Huesped getHuesped(int id, int idHuesped) {
        Huesped huespedes = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETHUESPED);
            sentencia.setInt(1, id);
            sentencia.setInt(2, idHuesped);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                huespedes = new Huesped(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return huespedes;
    }

    public static Huesped buscarHuesped(int idHuesped) {
        Huesped huesped = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETHUESPEDReporte);
            sentencia.setInt(1, idHuesped);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                huesped = new Huesped(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return huesped;
    }
    
    public static ArrayList<YearGender> getIngresoYearGender() {
        ArrayList<YearGender> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INGRESO_YEAR_GENDER);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new YearGender(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static boolean insertaHuesped(Huesped huespedes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARHUESPED);
            sentencia.setInt(1, huespedes.getIdHuesped());
            sentencia.setInt(2, huespedes.getNumHabit());
            sentencia.setString(3, huespedes.getNombre());
            sentencia.setString(4, huespedes.getApellido1());
            sentencia.setString(5, huespedes.getApellido2());
            sentencia.setString(6, huespedes.getTelefono());
            sentencia.setString(7, huespedes.getCorreo());
            sentencia.setObject(8, huespedes.getFechaIngr());
            sentencia.setObject(9, huespedes.getFechaSalida());
            
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarHuesped(Huesped huespedes) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARHUESPED);
            sentencia.setInt(1, huespedes.getIdHuesped());
            sentencia.setInt(2, huespedes.getNumHabit());
            sentencia.setString(3, huespedes.getNombre());
            sentencia.setString(4, huespedes.getApellido1());
            sentencia.setString(5, huespedes.getApellido2());
            sentencia.setString(6, huespedes.getTelefono());
            sentencia.setString(7, huespedes.getCorreo());
            sentencia.setObject(8, huespedes.getFechaIngr());
            sentencia.setObject(9, huespedes.getFechaSalida());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarHuesped(Huesped huespedes) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARHUESPED);
            sentencia.setInt(1,  huespedes.getId());
            sentencia.setInt(2,  huespedes.getIdHuesped());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HuespedesGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
}
