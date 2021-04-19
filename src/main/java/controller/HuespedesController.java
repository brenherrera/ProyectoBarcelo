package controller;

import gestion.HuespedesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Huesped;

@Named(value = "huespedesController")
@SessionScoped
public class HuespedesController extends Huesped implements Serializable {

    public HuespedesController() {
    }
    
    public List<Huesped> getHuespedes() {
        return HuespedesGestion.getHuespedes();
    }

    public String editaHuesped(int id, int idHuesped) {
        Huesped h = HuespedesGestion.getHuesped(id, idHuesped);
        if (h != null) {
            this.setId(h.getId());
            this.setIdHuesped(h.getIdHuesped());
            this.setNombre(h.getNombre());
            this.setApellido1(h.getApellido1());
            this.setApellido2(h.getApellido2());
            this.setTelefono(h.getTelefono());
            this.setCorreo(h.getCorreo());
            this.setFechaIngr(h.getFechaIngr());
            this.setFechaSalida(h.getFechaSalida());
            return "editaH.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaHuespedForm:identificacion", msg);
            return "huesped.xhtml";
        }
    }

    private boolean noImprimir=true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarHuesped(int idHuesped) {
        Huesped h = HuespedesGestion.buscarHuesped(idHuesped);
        if (h != null) {
            this.setId(h.getId());
            this.setIdHuesped(h.getIdHuesped());
            this.setNumHabit(h.getNumHabit());
            this.setNombre(h.getNombre());
            this.setApellido1(h.getApellido1());
            this.setApellido2(h.getApellido2());
            this.setTelefono(h.getTelefono());
            this.setCorreo(h.getCorreo());
            this.setFechaIngr(h.getFechaIngr());
            this.setFechaSalida(h.getFechaSalida());
           noImprimir=false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("certificacionHuespedForm:identificacion", msg);
           noImprimir=true;
        }
    }
    
    
    public String insertHuesped() {
        if (HuespedesGestion.insertaHuesped(this)) {
            return "huesped.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el huesped");
            FacesContext.getCurrentInstance().addMessage("editaHuespedForm:identificacion", msg);
            return "nuevoH.xhtml";
        }
    }

    public String updateHuesped() {
        if (HuespedesGestion.ModificarHuesped(this)) {
            return "huesped.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el huesped");
            FacesContext.getCurrentInstance().addMessage("editaHuespedForm:identificacion", msg);
            return "editaH.xhtml";
        }
    }

    public String deleteHuesped() {
        if (HuespedesGestion.eliminarHuesped(this)) {
            return "huesped.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el huesped");
            FacesContext.getCurrentInstance().addMessage("editaHuespedForm:identificacion", msg);
            return "editaH.xhtml";
        }
    }
}
