/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio;

import java.util.Date;
import prototipo.modelo.Servicio;

/**
 *
 * @author Marisa
 */
public interface BitacoraAnalyzer {
    Date getFechaEnradaAuto(Servicio datos);
    Date getFechaSalidaAuto(Servicio datos);
    String getTiempoEstadia(Servicio datos);
}
