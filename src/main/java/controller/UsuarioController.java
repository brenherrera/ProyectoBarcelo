package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    public UsuarioController() {
    }

    public String getUsuario() {
        Usuario usuario = UsuarioGestion.getUsuario(this.getIdUsuario(), this.getPwUsuario());
        if (usuario != null) {
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            return "inicio.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }
    
}
