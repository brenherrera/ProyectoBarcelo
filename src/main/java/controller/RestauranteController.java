package controller;

import gestion.RestauranteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Restaurante;

@Named(value = "restauranteController")
@SessionScoped
public class RestauranteController extends Restaurante implements Serializable {

    public RestauranteController() {
    }
    
    public List<Restaurante> getRestaurantes() {
        return RestauranteGestion.getRestaurantes();
    }

        public String editaRestaurante(int id, int numHabit) {
        Restaurante r = RestauranteGestion.getRestaurante(id, numHabit);
        if (r != null) {
            this.setId(r.getId());
            this.setNumHabit(r.getNumHabit());
            this.setNombre(r.getNombre());
            this.setApellido1(r.getApellido1());
            this.setApellido2(r.getApellido2());
            this.setNumMesa(r.getNumMesa());
            this.setCantPersonas(r.getCantPersonas());
            return "editaR.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaRestauranteForm:identificacion", msg);
            return "restaurante.xhtml";
        }
    }

    public String insertRestaurante() {
        if (RestauranteGestion.insertaRestaurante(this)) {
            return "restaurante.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaRestauranteForm:identificacion", msg);
            return "nuevoR.xhtml";
        }
    }

    public String updateRestaurante() {
        if (RestauranteGestion.ModificarRestaurante(this)) {
            return "restaurante.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaRestauranteForm:identificacion", msg);
            return "editaR.xhtml";
        }
    }

    public String deleteRestaurante() {
        if (RestauranteGestion.eliminarRestaurante(this)) {
            return "restaurante.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaRestauranteForm:identificacion", msg);
            return "editaR.xhtml";
        }
    }
}
