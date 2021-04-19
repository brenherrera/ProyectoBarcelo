package model;

public class Restaurante {

    private int id;
    private int numHabit;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int numMesa;
    private int cantPersonas;

    public Restaurante() {
    }

    public Restaurante(int id, int numHabit, String nombre, String apellido1, String apellido2, int numMesa, int cantPersonas) {
        this.id = id;
        this.numHabit = numHabit;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numMesa = numMesa;
        this.cantPersonas = cantPersonas;
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

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }
}
