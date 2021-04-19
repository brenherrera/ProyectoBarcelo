package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleados {
    private int id;
    private int idEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String idRol;
    private String telefono;
    private String correo;
    private String direccion;
    private Date fechaNaci;
    private int edad;
    private String estadoCivil;

    public Empleados() {
    }

    public Empleados(int id, int idEmpleado, String nombre, String apellido1, String apellido2, String idRol, String telefono, String correo, String direccion, Date fechaNaci, int edad, String estadoCivil) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.idRol = idRol;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaNaci = fechaNaci;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(Date fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha1=format.format(this.fechaNaci);
        return "{\"Empleado\":{\n\"cedula\":\""
                + idEmpleado + "\",\n\"nombre\":\""
                + nombre + "\",\n\"apellido1\":\""
                + apellido1 + "\",\n\"apellido2\":\""
                + apellido2 + "\",\n\"idRol\":\""
                + idRol + "\",\n\"telefono\":\""
                + telefono + "\",\n\"correo\":\""
                + correo + "\",\n\"direccion\":\""
                + direccion + "\",\n\"fechaNacimiento\":\""
                + fecha1 + "\",\n\"edad\":\""
                + edad + "\",\n\"estadoCivil\":\""
                + estadoCivil + "\"\n}\n}";
    }
}
