package controller;

import gestion.TotalGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Total;

@Named(value = "totalController")
@SessionScoped
public class TotalController extends Total implements Serializable {
    
    public TotalController() {
    }
    
    public List<Total> getTotales() {
        return TotalGestion.getTotales();
    }

        public String editaTotal(int id, int idHuesped) {
        Total t = TotalGestion.getTotal(id, idHuesped);
        if (t != null) {
            this.setId(t.getId());
            this.setIdHuesped(t.getIdHuesped());
            this.setNumHabit(t.getNumHabit());
            this.setNombre(t.getNombre());
            this.setApellido1(t.getApellido1());
            this.setApellido2(t.getApellido2());
            this.setDetalleP(t.getDetalleP());
            this.setSubtotal(t.getSubtotal());
            this.setEncarExtras(t.getEncarExtras());
            this.setPrecioExtras(t.getPrecioExtras());
            this.setTotal(t.getTotal());
            this.setFechaIngr(t.getFechaIngr());
            this.setFechaSalida(t.getFechaSalida());
            this.setNumFactura(t.getNumFactura());
            return "editaT.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaTotalForm:identificacion", msg);
            return "total.xhtml";
        }
    }

    public String insertTotal() {
        if (TotalGestion.insertaTotal(this)) {
            return "total.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el total");
            FacesContext.getCurrentInstance().addMessage("editaTotalForm:identificacion", msg);
            return "nuevoT.xhtml";
        }
    }

    public String updateTotal() {
        if (TotalGestion.ModificarTotal(this)) {
            return "total.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el total");
            FacesContext.getCurrentInstance().addMessage("editaTotalForm:identificacion", msg);
            return "editaT.xhtml";
        }
    }

    public String deleteTotal() {
        if (TotalGestion.eliminarTotal(this)) {
            return "total.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el total");
            FacesContext.getCurrentInstance().addMessage("editaTotalForm:identificacion", msg);
            return "editaT.xhtml";
        }
    }
}
