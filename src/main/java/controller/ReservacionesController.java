/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.GymGestion;
import gestion.RestauranteGestion;
import gestion.SpaGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@Named(value = "reservacionesController")
@Dependent
public class ReservacionesController {

    public ReservacionesController() {
    }
    
        public void respaldos() {
        ZipOutputStream out = null;
        try {

            String json = SpaGestion.generarJson();
            String json1 = GymGestion.generarJson();
            String json2 = RestauranteGestion.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldos.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("respaldos.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            byte[] data1 = json1.getBytes();
            byte[] data2 = json2.getBytes();
            out.write(data, 0, data.length);
            out.write(data1, 0, data1.length);
            out.write(data2, 0, data2.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldos.zip");

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
