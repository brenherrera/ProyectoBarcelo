package model;
public class Habitaciones {
    private int numHabit;
    private String tamaño;
    private String precio;

    public Habitaciones() {
    }

    public Habitaciones(int numHabit, String tamaño, String precio) {
        this.numHabit = numHabit;
        this.tamaño = tamaño;
        this.precio = precio;
    }

    public int getNumHabit() {
        return numHabit;
    }

    public void setNumHabit(int numHabit) {
        this.numHabit = numHabit;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
