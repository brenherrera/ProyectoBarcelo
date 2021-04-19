package dashboard;

import gestion.HuespedesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import model.YearGender;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
@Named(value = "chartDateView")
@SessionScoped
public class ChartDateView implements Serializable {
    private LineChartModel dateModel;
 
    @PostConstruct
    public void init() {
        createDateModel();
    }
 
    public LineChartModel getDateModel() {
        return dateModel;
    }
 
    private void createDateModel() {
        dateModel = new LineChartModel();
        /*LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set("2014-01-01", 32);
        series2.set("2014-01-06", 73);
        series2.set("2014-01-12", 24);
        series2.set("2014-01-18", 12);
        series2.set("2014-01-24", 74);
        series2.set("2014-01-30", 62);*/
        
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        LineChartSeries series8 = new LineChartSeries();
        LineChartSeries series9 = new LineChartSeries();
        LineChartSeries series10 = new LineChartSeries();
        
        series1.setFill(true);
        series2.setFill(true);
        series3.setFill(true);
        series4.setFill(true);
        series5.setFill(true);
        series6.setFill(true);
        series7.setFill(true);
        series8.setFill(true);
        series9.setFill(true);
        series10.setFill(true);
        
        String label = "N/A";
        String label1 = "N/A";
        String label2 = "N/A";
        String label3 = "N/A";
        String label4 = "N/A";
        String label5 = "N/A";
        String label6 = "N/A";
        String label7 = "N/A";
        String label8 = "N/A";
        String label9 = "N/A";
        
        ArrayList<YearGender> list = HuespedesGestion.getIngresoYearGender();
        int mayor = list.get(0).getTotal();
        
        ArrayList<String> genders = new ArrayList<>();
        
        list.forEach(linea -> {
            genders.add(linea.getApellido1());
        });
        
        List<String> distinctGenders = genders.stream().distinct().collect(Collectors.toList());
        
        for (String s : distinctGenders) {
            if (s.equalsIgnoreCase("A")) {
                label = "Apellido con A";
            }
            if (s.equalsIgnoreCase("B")) {
                label1 = "Apellido con B";
            }
            if (s.equalsIgnoreCase("C")) {
                label2 = "Apellido con C";
            }
            if (s.equalsIgnoreCase("E")) {
                label3 = "Apellido con E";
            }
            if (s.equalsIgnoreCase("G")) {
                label4 = "Apellido con G";
            }
            if (s.equalsIgnoreCase("M")) {
                label5 = "Apellido con M";
            }
            if (s.equalsIgnoreCase("O")) {
                label6 = "Apellido con O";
            }
            if (s.equalsIgnoreCase("P")) {
                label7 = "Apellido con P";
            }
            if (s.equalsIgnoreCase("Q")) {
                label8 = "Apellido con Q";
            }
            if (s.equalsIgnoreCase("S")) {
                label9 = "Apellido con S";
            }
        }
        
        series1.setLabel(label);
        series2.setLabel(label1);
        series3.setLabel(label2);
        series4.setLabel(label3);
        series5.setLabel(label4);
        series6.setLabel(label5);
        series7.setLabel(label6);
        series8.setLabel(label7);
        series9.setLabel(label8);
        series10.setLabel(label9);
        
        for (YearGender row : list) {
            if (row.getApellido1().equalsIgnoreCase("A")) {
                series1.set(row.getYear(), row.getTotal());                
            }
            if (row.getApellido1().equalsIgnoreCase("B")) {
                series2.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("C")) {
                series3.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("E")) {
                series4.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("G")) {
                series5.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("M")) {
                series6.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("O")) {
                series7.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("P")) {
                series8.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("Q")) {
                series9.set(row.getYear(), row.getTotal());
            }
            if (row.getApellido1().equalsIgnoreCase("S")) {
                series10.set(row.getYear(), row.getTotal());
            }
            if (mayor < row.getTotal()) {
                mayor = row.getTotal();
            }
        }
        
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
        dateModel.addSeries(series3);
        dateModel.addSeries(series4);
        dateModel.addSeries(series5);
        dateModel.addSeries(series6);
        dateModel.addSeries(series7);
        dateModel.addSeries(series8);
        dateModel.addSeries(series9);
        dateModel.addSeries(series10);
 
        dateModel.setTitle("Apellidos de Huespedes");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Date");
        DateAxis axis = new DateAxis("Letter");
        axis.setTickAngle(-50);
        //axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");
 
        dateModel.getAxes().put(AxisType.X, axis);
    }
}