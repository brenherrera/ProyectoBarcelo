package model;

import java.util.Date;

public class Total {
    private int id;
    private int idHuesped;
    private int numHabit;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String detalleP;
    private String subtotal;
    private String encarExtras;
    private String precioExtras;
    private String total;
    private Date fechaIngr;
    private Date fechaSalida;
    private int numFactura;

    public Total() {
    }

    public Total(int id, int idHuesped, int numHabit, String nombre, String apellido1, String apellido2, String detalleP, String subtotal, String encarExtras, String precioExtras, String total, Date fechaIngr, Date fechaSalida, int numFactura) {
        this.id = id;
        this.idHuesped = idHuesped;
        this.numHabit = numHabit;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.detalleP = detalleP;
        this.subtotal = subtotal;
        this.encarExtras = encarExtras;
        this.precioExtras = precioExtras;
        this.total = total;
        this.fechaIngr = fechaIngr;
        this.fechaSalida = fechaSalida;
        this.numFactura = numFactura;
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

    public String getDetalleP() {
        return detalleP;
    }

    public void setDetalleP(String detalleP) {
        this.detalleP = detalleP;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getEncarExtras() {
        return encarExtras;
    }

    public void setEncarExtras(String encarExtras) {
        this.encarExtras = encarExtras;
    }

    public String getPrecioExtras() {
        return precioExtras;
    }

    public void setPrecioExtras(String precioExtras) {
        this.precioExtras = precioExtras;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }
}
