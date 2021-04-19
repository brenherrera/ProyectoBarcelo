package controller;

import gestion.EmpleadosGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Empleados;

@Named(value = "empleadosController")
@SessionScoped
public class EmpleadosController extends Empleados implements Serializable {

    public EmpleadosController() {
    }
    
    public List<Empleados> getEmpleados() {
        return EmpleadosGestion.getEmpleados();
    }

        public String editaEmpleado(int id, String idEmpleado) {
        Empleados e = EmpleadosGestion.getEmpleado(id, idEmpleado);
        if (e != null) {
            this.setId(e.getId());
            this.setIdEmpleado(e.getIdEmpleado());
            this.setNombre(e.getNombre());
            this.setApellido1(e.getApellido1());
            this.setApellido2(e.getApellido2());
            this.setIdRol(e.getIdRol());
            this.setTelefono(e.getTelefono());
            this.setCorreo(e.getCorreo());
            this.setDireccion(e.getDireccion());
            this.setFechaNaci(e.getFechaNaci());
            this.setEdad(e.getEdad());
            this.setEstadoCivil(e.getEstadoCivil());
            return "editaE.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "empleado.xhtml";
        }
    }

    public String insertEmpleado() {
        if (EmpleadosGestion.insertaEmpleados(this)) {
            return "confirmacion.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "nuevoE.xhtml";
        }
    }

    public String updateEmpleado() {
        if (EmpleadosGestion.ModificarEmpleados(this)) {
            return "empleado.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "editaE.xhtml";
        }
    }

    public String deleteEmpleado() {
        if (EmpleadosGestion.eliminarEmpleados(this)) {
            return "empleado.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el empleado");
            FacesContext.getCurrentInstance().addMessage("editaEmpleadoForm:identificacion", msg);
            return "editaE.xhtml";
        }
    }
}
