package controller;

import gestion.HabitacionesGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Habitaciones;

@Named(value = "habitacionesController")
@SessionScoped
public class HabitacionesController extends Habitaciones implements Serializable {

    public HabitacionesController() {
    }
    
    public List<Habitaciones> getHabitaciones() {
        return HabitacionesGestion.getHabitaciones();
    }

    public String editaHabitaciones(int numHabit, String tamaño) {
            
        Habitaciones h = HabitacionesGestion.getHabitacion(numHabit, tamaño);
//        this.setNumHabit(h.getNumHabit());
//        return "editaHab.xhtml";
        if (h != null) {
            this.setNumHabit(h.getNumHabit());
            this.setTamaño(h.getTamaño());
            this.setPrecio(h.getPrecio());
            return "editaHab.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaHabitacionForm:identificacion", msg);
            return "habitacion.xhtml";
        }
    }

    public String insertHabitacion() {
        if (HabitacionesGestion.insertaHabitacion(this)) {
            return "habitacion.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar la habitacion");
            FacesContext.getCurrentInstance().addMessage("editaHabitacionForm:identificacion", msg);
            return "nuevoH.xhtml";
        }
    }

    public String updateHabitacion() {
        if (HabitacionesGestion.ModificarHabitacion(this)) {
            return "habitacion.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar la habitacion");
            FacesContext.getCurrentInstance().addMessage("editaHabitacionForm:identificacion", msg);
            return "editaHab.xhtml";
        }
    }

    public String deleteHabitacion() {
        if (HabitacionesGestion.eliminarHabitacion(this)) {
            return "habitacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar la habitacion");
            FacesContext.getCurrentInstance().addMessage("editaHabitacionForm:identificacion", msg);
            return "editaHab.xhtml";
        }
    }
}
