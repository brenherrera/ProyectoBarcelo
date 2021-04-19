package dashboard;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import java.util.ArrayList;
import gestion.HabitacionesGestion;
import model.Habitaciones;

@Named(value = "chartInteractiveView2")
@SessionScoped
public class ChartInteractiveView2 implements Serializable {
    private BarChartModel barModel;
 
    @PostConstruct
    public void init() { 
        createBarModels();
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ArrayList<Habitaciones> list = HabitacionesGestion.getHabitaciones();
        int queenVal = 0;
        int kingVal = 0;
        int twinVal = 0;
        for (Habitaciones item : list) 
        { 
            if(item.getTamaño().equals("Queen")){
                queenVal++;
            }
            if(item.getTamaño().equals("King")){
                kingVal++;
            }
            if(item.getTamaño().equals("Twin")){
                twinVal++;
            }
        }
 
        ChartSeries queen = new ChartSeries();
        queen.setLabel("Queen");
        queen.set("1", queenVal);
 
        ChartSeries king = new ChartSeries();
        king.setLabel("King");
        king.set("1", kingVal);

        ChartSeries twin = new ChartSeries();
        twin.setLabel("Twin");
        twin.set("1", twinVal);
 
        model.addSeries(queen);
        model.addSeries(king);
        model.addSeries(twin);
        
 
        return model;
    }
 
    private void createBarModels() {
        createBarModel();
    }
 
    private void createBarModel() {
        barModel = initBarModel();
 
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Habitaciones");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(30);
    }
 
}