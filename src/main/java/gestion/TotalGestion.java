package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Total;

public class TotalGestion {
    private static final String SQL_GETTOTALES = "SELECT * FROM hotelmaribu.total";
    private static final String SQL_GETTOTAL = "SELECT * FROM hotelmaribu.total where id=? and idHuesped=?";
    private static final String SQL_INSERTARTOTAL = "insert into hotelmaribu.total(idHuesped,numHabit,nombre,apellido1,apellido2,detalleP,subtotal,encarExtras,precioExtras,total,fechaIngr,fechaSalida,numFactura) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_MODIFICARTOTAL = "update hotelmaribu.total set numHabit=?,nombre=?,apellido1=?,apellido2=?,detalleP=?,subtotal=?,encarExtras=?,precioExtras=?,total=?,fechaIngr=?,fechaSalida=?,numFactura=? where id=? and idHuesped=?";
    private static final String SQL_ELIMINARTOTAL = "Delete FROM hotelmaribu.total where id=? and idHuesped=?";

    public static ArrayList<Total> getTotales() {
        ArrayList<Total> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETTOTALES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Total(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getInt(14)
                        ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TotalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Total getTotal(int id, int idHuesped) {
        Total total = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETTOTAL);
            sentencia.setInt(1, id);
            sentencia.setInt(2, idHuesped);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                total = new Total(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getInt(14)
                        );
            }

        } catch (SQLException ex) {
            Logger.getLogger(TotalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return total;
    }

    public static boolean insertaTotal(Total total) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARTOTAL);
            sentencia.setInt(1, total.getIdHuesped());
            sentencia.setInt(2, total.getNumHabit());
            sentencia.setString(3, total.getNombre());
            sentencia.setString(4, total.getApellido1());
            sentencia.setString(5, total.getApellido2());
            sentencia.setString(6, total.getDetalleP());
            sentencia.setString(7, total.getSubtotal());
            sentencia.setString(8, total.getEncarExtras());
            sentencia.setString(9, total.getPrecioExtras());
            sentencia.setString(10, total.getTotal());
            sentencia.setObject(11, total.getFechaIngr());
            sentencia.setObject(12, total.getFechaSalida());
            sentencia.setInt(13, total.getNumFactura());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TotalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarTotal(Total total) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARTOTAL);
            sentencia.setInt(1, total.getNumHabit());
            sentencia.setString(2, total.getNombre());
            sentencia.setString(3, total.getApellido1());
            sentencia.setString(4, total.getApellido2());
            sentencia.setString(5, total.getDetalleP());
            sentencia.setString(6, total.getSubtotal());
            sentencia.setString(7, total.getEncarExtras());
            sentencia.setString(8, total.getPrecioExtras());
            sentencia.setString(9, total.getTotal());
            sentencia.setObject(10, total.getFechaIngr());
            sentencia.setObject(11, total.getFechaSalida());
            sentencia.setInt(12, total.getNumFactura());
            sentencia.setInt(13, total.getIdHuesped());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TotalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarTotal(Total total) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARTOTAL);
            sentencia.setInt(1, total.getId());
            sentencia.setInt(2, total.getIdHuesped());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TotalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
}
