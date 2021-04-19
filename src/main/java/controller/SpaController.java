package controller;

import gestion.SpaGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Spa;

@Named(value = "spaController")
@SessionScoped
public class SpaController extends Spa implements Serializable {

    public SpaController() {
    }

    public List<Spa> getSpas() {
        return SpaGestion.getSpas();
    }

    public String editaSpa(int id, int numHabit) {
        Spa s = SpaGestion.getSpa(id, numHabit);
        if (s != null) {
            this.setId(s.getId());
            this.setNumHabit(s.getNumHabit());
            this.setNombre(s.getNombre());
            this.setApellido1(s.getApellido1());
            this.setApellido2(s.getApellido2());
            this.setHoraIngr(s.getHoraIngr());
            return "editaS.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("editaSpaForm:identificacion", msg);
            return "spa.xhtml";
        }
    }

    public String insertSpa() {
        if (SpaGestion.insertaSpa(this)) {
            return "spa.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaSpaForm:identificacion", msg);
            return "nuevoS.xhtml";
        }
    }

    public String updateSpa() {
        if (SpaGestion.ModificarSpa(this)) {
            return "spa.xhtml";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al actualizar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaSpaForm:identificacion", msg);
            return "editaS.xhtml";
        }
    }

    public String deleteSpa() {
        if (SpaGestion.eliminarSpa(this)) {
            return "spa.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar la reservacion");
            FacesContext.getCurrentInstance().addMessage("editaSpaForm:identificacion", msg);
            return "editaS.xhtml";
        }
    }

    public void respaldo() {
        ZipOutputStream out = null;
        try {

            String json = SpaGestion.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldoS.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("respaldoS.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldoS.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=respaldos.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SpaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
