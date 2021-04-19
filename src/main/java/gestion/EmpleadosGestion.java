package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Empleados;
import model.YearGender2;

public class EmpleadosGestion {
    private static final String SQL_GETEMPLEADOS = "SELECT * FROM hotelmaribu.empleados";
    private static final String SQL_GETEMPLEADO = "SELECT * FROM hotelmaribu.empleados  where id=? and idEmpleado=?";
    private static final String SQL_INSERTAREMPLEADO = "insert into hotelmaribu.empleados(idEmpleado,nombre,apellido1,apellido2,idRol,telefono,correo,direccion,fechaNaci,edad,estadoCivil) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_MODIFICAREMPLEADO = "update hotelmaribu.empleados  set nombre=?,apellido1=?,apellido2=?,idRol=?,telefono=?,correo=?,direccion=?,fechaNaci=?,edad=?,estadoCivil=? where id=? and idEmpleado=?";
    private static final String SQL_ELIMINAREMPLEADO = "Delete FROM hotelmaribu.empleados  where id=? and idEmpleado=?";
    private static final String SQL_INGRESO_YEAR_GENDER2 = "SELECT estadoCivil,idRol,Count(*) total FROM hotelmaribu.empleados group by estadoCivil,idRol order by estadoCivil";

    public static ArrayList<Empleados> getEmpleados() {
        ArrayList<Empleados> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Empleados(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getString(12)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }

    public static Empleados getEmpleado(int id, String idEmpleado) {
        Empleados empleados = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETEMPLEADO);
            sentencia.setInt(1, id);
            sentencia.setString(2, idEmpleado);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                empleados = new Empleados(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getString(12)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return empleados;
    }

    public static boolean insertaEmpleados(Empleados empleados) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTAREMPLEADO);
            sentencia.setInt(1, empleados.getIdEmpleado());
            sentencia.setString(2, empleados.getNombre());
            sentencia.setString(3, empleados.getApellido1());
            sentencia.setString(4, empleados.getApellido2());
            sentencia.setString(5, empleados.getIdRol());
            sentencia.setString(6, empleados.getTelefono());
            sentencia.setString(7, empleados.getCorreo());
            sentencia.setString(8, empleados.getDireccion());
            sentencia.setObject(9, empleados.getFechaNaci());
            sentencia.setInt(10, empleados.getEdad());
            sentencia.setString(11, empleados.getEstadoCivil());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean ModificarEmpleados(Empleados empleados) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareCall(SQL_MODIFICAREMPLEADO);
            sentencia.setInt(1, empleados.getIdEmpleado());
            sentencia.setString(2, empleados.getNombre());
            sentencia.setString(3, empleados.getApellido1());
            sentencia.setString(4, empleados.getApellido2());
            sentencia.setString(5, empleados.getIdRol());
            sentencia.setString(6, empleados.getTelefono());
            sentencia.setString(7, empleados.getCorreo());
            sentencia.setString(8, empleados.getDireccion());
            sentencia.setObject(9, empleados.getFechaNaci());
            sentencia.setInt(10, empleados.getEdad());
            sentencia.setString(11, empleados.getEstadoCivil());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean eliminarEmpleados(Empleados empleados) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_ELIMINAREMPLEADO);
            sentencia.setInt(1, empleados.getId());
            sentencia.setInt(2, empleados.getIdEmpleado());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static ArrayList<YearGender2> getIngresoYearGender2() {
        ArrayList<YearGender2> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INGRESO_YEAR_GENDER2);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new YearGender2(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;

    }
}
