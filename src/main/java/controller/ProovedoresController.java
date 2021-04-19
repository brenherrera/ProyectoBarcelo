package controller;

import gestion.ProovedoresGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Proovedores;

@Named(value = "proovedoresController")
@SessionScoped
public class ProovedoresController extends Proovedores implements Serializable {

    public ProovedoresController() {
    }
    
    public List<Proovedores> getProovedores() {
        return ProovedoresGestion.getProovedores();
    }

        public String editaProovedores(int id, int codProv) {
        Proovedores p = ProovedoresGestion.getProovedor(id, codProv);
        if (p != null) {
            this.setId(p.getId());
            this.setCodProv(p.getCodProv());
            this.setNombre(p.getNombre());
            this.setApellido1(p.getApellido1());
            this.setApellido2(p.getApellido2());
            this.setTelefono(p.getTelefono());
            this.setCorreo(p.getCorreo());
            this.setTipoProd(p.getTipoProd());
            return "editaP.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaProovedorForm:identificacion", msg);
            return "proovedor.xhtml";
        }
    }

    public String insertProovedor() {
        if (ProovedoresGestion.insertaProovedores(this)) {
            return "proovedor.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el proovedor");
            FacesContext.getCurrentInstance().addMessage("editaProovedorForm:identificacion", msg);
            return "nuevoP.xhtml";
        }
    }

    public String updateProovedor() {
        if (ProovedoresGestion.ModificarProovedores(this)) {
            return "proovedor.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el proovedor");
            FacesContext.getCurrentInstance().addMessage("editaProovedorForm:identificacion", msg);
            return "editaP.xhtml";
        }
    }

    public String deleteProovedor() {
        if (ProovedoresGestion.eliminarProovedores(this)) {
            return "proovedor.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el proovedor");
            FacesContext.getCurrentInstance().addMessage("editaProovedorForm:identificacion", msg);
            return "editaP.xhtml";
        }
    }
}
