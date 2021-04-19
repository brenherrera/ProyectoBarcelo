package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Proovedores;

public class ProovedoresGestion {
    private static final String SQL_GETPROOVEDORES = "SELECT * FROM hotelmaribu.proveedores ";
    private static final String SQL_GETPROOVEDOR = "SELECT * FROM hotelmaribu.proveedores where id=? and codProv=?";
    private static final String SQL_INSERTARPROVEDOR = "insert into hotelmaribu.proveedores(codProv,nombre,apellido1,apellido2,telefono,correo,tipoProd) values (?,?,?,?,?,?,?)";
    private static final String SQL_MODIFICARPROVEDOR = "update hotelmaribu.proveedores  set nombre=?,apellido1=?,apellido2=?,telefono=?,correo=?,tipoProd=? where id=? and codProv=?";
    private static final String SQL_ELIMINARPROVEDOR = "Delete FROM hotelmaribu.proveedores where id=? and codProv=?";

    public static ArrayList<Proovedores> getProovedores() {
        ArrayList<Proovedores> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPROOVEDORES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Proovedores(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                        ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProovedoresGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Proovedores getProovedor(int id, int codProv) {
        Proovedores proovedores = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPROOVEDOR);
            sentencia.setInt(1, id);
            sentencia.setInt(2, codProv);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                proovedores = new Proovedores(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                        );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProovedoresGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return proovedores;
    }

    public static boolean insertaProovedores(Proovedores proovedores) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTARPROVEDOR);
            sentencia.setInt(1, proovedores.getCodProv());
            sentencia.setString(2, proovedores.getNombre());
            sentencia.setString(3, proovedores.getApellido1());
            sentencia.setString(4, proovedores.getApellido2());
            sentencia.setString(5, proovedores.getTelefono());
            sentencia.setString(6, proovedores.getCorreo());
            sentencia.setString(7, proovedores.getTipoProd());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProovedoresGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarProovedores(Proovedores proovedores) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICARPROVEDOR);
            sentencia.setString(1, proovedores.getNombre());
            sentencia.setString(2, proovedores.getApellido1());
            sentencia.setString(3, proovedores.getApellido2());
            sentencia.setString(4, proovedores.getTelefono());
            sentencia.setString(5, proovedores.getCorreo());
            sentencia.setString(6, proovedores.getTipoProd()); 
            sentencia.setInt(7, proovedores.getId()); 
            sentencia.setInt(8, proovedores.getCodProv()); 
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProovedoresGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarProovedores(Proovedores proovedores) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINARPROVEDOR);
            sentencia.setInt(1, proovedores.getId());
            sentencia.setInt(2, proovedores.getCodProv());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProovedoresGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
}
