package controller;

import gestion.GymGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Gym;

@Named(value = "gymController")
@SessionScoped
public class GymController extends Gym implements Serializable {

    public GymController() {
    }
    
    public List<Gym> getGyms() {
        return GymGestion.getReservacionesG();
    }

        public String editaGym(int id, int numHabit) {
        Gym g = GymGestion.getReservacionG(id, numHabit);
        if (g != null) {
            this.setId(g.getId());
            this.setNumHabit(g.getNumHabit());
            this.setNombre(g.getNombre());
            this.setApellido1(g.getApellido1());
            this.setApellido2(g.getApellido2());
            this.setHoraIngr(g.getHoraIngr());
            return "editaG.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaGymForm:identificacion", msg);
            return "gym.xhtml";
        }
    }

    public String insertGym() {
        if (GymGestion.insertaReservacionG(this)) {
            return "gym.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaGymForm:identificacion", msg);
            return "nuevoG.xhtml";
        }
    }

    public String updateGym() {
        if (GymGestion.ModificarReservacionG(this)) {
            return "gym.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaGymForm:identificacion", msg);
            return "editaG.xhtml";
        }
    }

    public String deleteGym() {
        if (GymGestion.eliminarReservacionG(this)) {
            return "gym.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaGymForm:identificacion", msg);
            return "editaG.xhtml";
        }
    }
}
