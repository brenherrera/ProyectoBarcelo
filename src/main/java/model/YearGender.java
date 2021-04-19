package model;
public class YearGender {
    private int year;
    private String apellido1;
    private int total;

    public YearGender() {
    }

    public YearGender(int year, String apellido1, int total) {
        this.year = year;
        this.apellido1 = apellido1;
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
