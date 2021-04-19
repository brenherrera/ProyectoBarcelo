package model;

public class YearGender2 {

    public String estadoCivil;
    public String idRol;
    public int total;

    public YearGender2(String estadoCivil, String idRol, int total) {
    this.estadoCivil = estadoCivil;
    this.idRol = idRol;
    this.total = total;
    }

    public YearGender2() {
    }
    
    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
