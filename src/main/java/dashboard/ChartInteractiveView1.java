package dashboard;

import gestion.HabitacionesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Habitaciones;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "chartInteractiveView1")
@SessionScoped
public class ChartInteractiveView1 implements Serializable {
    private PieChartModel pieModel1;
    private PieChartModel livePieModel;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 
    public PieChartModel getLivePieModel() {
        int random1 = (int) (Math.random() * 1000);
        int random2 = (int) (Math.random() * 1000);
 
        livePieModel.getData().put("Candidate 1", random1);
        livePieModel.getData().put("Candidate 2", random2);
 
        livePieModel.setTitle("Votes");
        livePieModel.setLegendPosition("ne");
 
        return livePieModel;
    }
    private PieChartModel initPieModel() {
        PieChartModel pieModel = new PieChartModel();
        
        return pieModel;
    }
 
     private void createPieModels() {
        createPieModel1();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        ArrayList<Habitaciones> list = HabitacionesGestion.getHabitaciones();
        int queen = 0;
        int king = 0;
        int twin = 0;
        for (Habitaciones item : list) 
        { 
            if(item.getTamaño().equals("Queen")){
                queen++;
            }
            if(item.getTamaño().equals("King")){
                king++;
            }
            if(item.getTamaño().equals("Twin")){
                twin++;
            }
        }
        
        System.out.println("QUEEN COUNT----------"+queen);
        System.out.println("king count----------"+king);
        System.out.println("twin count----------"+twin);
        pieModel1.set("Queen", queen);
        pieModel1.set("King", king);
        pieModel1.set("Twin", twin);
 
        pieModel1.setTitle("Habitaciones");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
 
}
}