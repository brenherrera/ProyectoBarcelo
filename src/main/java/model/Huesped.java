package model;

import java.util.Date;

public class Huesped {
  private int id;
    private int idHuesped;
    private int numHabit;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String correo;
    private Date fechaIngr;
    private Date fechaSalida;

    public Huesped() {
    }

    public Huesped(int id, int idHuesped, int numHabit, String nombre, String apellido1, String apellido2, String telefono, String correo, Date fechaIngr, Date fechaSalida) {
        this.id = id;
        this.idHuesped = idHuesped;
        this.numHabit = numHabit;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaIngr = fechaIngr;
        this.fechaSalida = fechaSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public int getNumHabit() {
        return numHabit;
    }

    public void setNumHabit(int numHabit) {
        this.numHabit = numHabit;
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

    public Date getFechaIngr() {
        return fechaIngr;
    }

    public void setFechaIngr(Date fechaIngr) {
        this.fechaIngr = fechaIngr;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }  
    
    public String getNombreCompleto() {
        String texto="";
        texto+=nombre!=null?nombre+" ":"";
        texto+=apellido1!=null?apellido1+" ":"";
        texto+=apellido2!=null?apellido2+" ":"";
        return texto;
    }
}
