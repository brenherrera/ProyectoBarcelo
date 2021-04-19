package model;

import java.util.Date;

public class Spa {
   private int id;
    private int numHabit;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String horaIngr;

    public Spa() {
    }

    public Spa(int id, int numHabit, String nombre, String apellido1, String apellido2, String horaIngr) {
        this.id = id;
        this.numHabit = numHabit;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.horaIngr = horaIngr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHoraIngr() {
        return horaIngr;
    }

    public void setHoraIngr(String horaIngr) {
        this.horaIngr = horaIngr;
    } 
}
