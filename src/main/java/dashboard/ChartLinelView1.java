package dashboard;

import gestion.EmpleadosGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Empleados;
import model.YearGender2;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "chartLineView1")
@SessionScoped
public class ChartLinelView1 implements Serializable {

    private LineChartModel lineModel1;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        /*ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);*/
        ChartSeries boys = new ChartSeries();
        ChartSeries girls = new ChartSeries();

        String label = "N/A";
        String label1 = "N/A";

        ArrayList<YearGender2> list = EmpleadosGestion.getIngresoYearGender2();
        int mayor = list.get(0).getTotal();

        ArrayList<String> rol = new ArrayList<>();

        list.forEach(linea -> {
            rol.add(linea.getIdRol());
        });

        List<String> distinctRol = rol.stream().distinct().collect(Collectors.toList());
        for (String s : distinctRol) {
            if (s.equalsIgnoreCase("C")) {
                label = "Casado";

            }
            if (s.equalsIgnoreCase("S")) {
                label1 = "Soltera";
            }
        }

        boys.setLabel(label);
        girls.setLabel(label1);

        for (YearGender2 row : list) {
            if (row.getIdRol().equalsIgnoreCase("C")) {
                boys.set(row.getEstadoCivil(), row.getTotal());

            }
            if (row.getIdRol().equalsIgnoreCase("S")) {
                girls.set(row.getEstadoCivil(), row.getTotal());
            }
            if (mayor < row.getTotal()) {
                mayor = row.getTotal();
            }
        }

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Cantidad de Empleados por estado civil y edad");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        Axis xAxis = lineModel1.getAxis(AxisType.X);
        yAxis.setMin(0);
        yAxis.setMax(10);
        xAxis.setMin(22);
        xAxis.setMax(32);
        yAxis.setLabel("Cantidad");
        xAxis.setLabel("Edad");
        //yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        
        ArrayList<Empleados> lista = EmpleadosGestion.getEmpleados();
        
        ArrayList<Empleados> casados = new ArrayList<Empleados>();
        ArrayList<Empleados> solteros = new ArrayList<Empleados>();
        for(Empleados emp : lista){
            if(emp.getEstadoCivil().equals("soltera")){
                solteros.add(emp);
            }
            else{
                casados.add(emp);
            }
        }
        
        Hashtable<String,String> solterosList = new Hashtable<String,String>();
        Hashtable<String,String> casadosList = new Hashtable<String,String>();
        
        
        for(Empleados emp: solteros){
            if(!solterosList.containsKey(String.valueOf(emp.getEdad()))){
                solterosList.put(String.valueOf(emp.getEdad()), "1");
            }
            else{
                int value = Integer.parseInt(solterosList.get(String.valueOf(emp.getEdad())));
                value++;
                solterosList.put(String.valueOf(emp.getEdad()), String.valueOf(value));
            }
        }
        
        for(Empleados emp: casados){
            if(!casadosList.containsKey(String.valueOf(emp.getEdad()))){
                casadosList.put(String.valueOf(emp.getEdad()), "1");
            }
            else{
                int value = Integer.parseInt(casadosList.get(String.valueOf(emp.getEdad())));
                value++;
                casadosList.put(String.valueOf(emp.getEdad()), String.valueOf(value));
            }
        }
        TreeMap<String, String> smap = new TreeMap<String, String>( solterosList );
        TreeMap<String, String> cmap = new TreeMap<String, String>( casadosList );
        
        Set<String> sKeys = smap.keySet();
        Set<String> cKeys = cmap.keySet();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Soltero(a)");

        for(String key: sKeys){
            series1.set(key, Integer.parseInt(solterosList.get(key)));
        }

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Casado(a)");
        
        for(String key: cKeys){
            series2.set(key, Integer.parseInt(casadosList.get(key)));
        }


        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }
}
