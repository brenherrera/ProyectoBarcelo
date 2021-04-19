package dashboard;

import gestion.EmpleadosGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.YearGender2;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named(value = "chartLineView2")
@SessionScoped
public class ChartLineView2 implements Serializable {

    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
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
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Información de empleados");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Estado Civil"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }
}
