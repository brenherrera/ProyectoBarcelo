package dashboard;

import gestion.HuespedesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import model.Huesped;
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
        LineChartSeries series1 = new LineChartSeries();
        ArrayList<Huesped> lista = HuespedesGestion.getHuespedes();
        Hashtable<String,String> dataset = new Hashtable<String,String>();
        
        for (Huesped item : lista) {
            String fecha = item.getFechaIngr().toString().substring(0,10);
            if(!dataset.containsKey(fecha)){
                dataset.put(fecha, "1");
            }
            else{
                int value = Integer.parseInt(dataset.get(fecha));
                value++;
                dataset.put(fecha, String.valueOf(value));
            }
        }
        series1.setLabel("Series 1");
        Set<String> keys = dataset.keySet();
        for(String key: keys){
            series1.set(key, Integer.parseInt(dataset.get(key)));
        }
        
 
//        series1.set("2020-12-05", 51);
//        series1.set("2021-01-03", 22);
//        series1.set("2021-01-18", 65);
//        series1.set("2021-02-06", 74);
//        series1.set("2021-02-25", 24);
//        series1.set("2021-03-01", 51);
 
 
        dateModel.addSeries(series1);
        //dateModel.addSeries(series2);
 
        dateModel.setTitle("Cantidad de Huespedes por dia");
        dateModel.setZoom(false);
        dateModel.getAxis(AxisType.Y).setLabel("Cantidad");
        DateAxis axis = new DateAxis("Fechas");
        axis.setTickAngle(-50);
        axis.setMax("2021-03-07");
        axis.setMin("2020-12-29");
        axis.setTickFormat("%b %#d, %y");
 
        dateModel.getAxes().put(AxisType.X, axis);
    }
}