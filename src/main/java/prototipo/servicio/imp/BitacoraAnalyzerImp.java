/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import java.util.Date;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.servicio.BitacoraAnalyzer;

/**
 *
 * @author Marisa
 */
@Component
public class BitacoraAnalyzerImp implements BitacoraAnalyzer {

    @Override
    public Date getFechaEnradaAuto(Servicio datos) {
        Date fechaEntrada = null;
        for (Evento obj: datos.getBitacora().getEventos()){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
                if (ev.getNombreEvento().compareTo("Entrada de Auto") == 0) {
                    fechaEntrada = ev.getFecha();
                }
            }
        }
        return fechaEntrada;
    }

    @Override
    public Date getFechaSalidaAuto(Servicio datos) {
        Date fechaSalida = null;
        for (Evento obj: datos.getBitacora().getEventos()){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
                if (ev.getNombreEvento().compareTo("Salida de Auto") == 0) {
                    fechaSalida = ev.getFecha();
                }
            }
        }
        if (fechaSalida != null) {
            return fechaSalida;
        } else {
            if (this.getFechaEnradaAuto(datos) != null) {
                return new Date();
            } else {
                return null;
            }
        }
    }

    @Override
    public String getTiempoEstadia(Servicio datos) {
        Date fechaEntrada = null;
        Date fechaSalida = null;
        for (Evento obj: datos.getBitacora().getEventos()){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
                if (ev.getNombreEvento().compareTo("Entrada de Auto") == 0) {
                    fechaEntrada = ev.getFecha();
                }
                if (ev.getNombreEvento().compareTo("Salida de Auto") == 0) {
                    fechaSalida = ev.getFecha();
                }
            }
        }        
        if (fechaSalida == null) {
            fechaSalida = new Date();
        }
        if (fechaEntrada != null) {
            long ms = fechaSalida.getTime() - fechaEntrada.getTime();
            long x;
            x = ms / 1000;
            x /= 60;
            long minutes = x % 60;
            x /= 60;
            long hours = x % 24;
            x /= 24;
            long days = x;
            return(days+"D "+hours +"H "+minutes+ "m");
        } else {
            return("");
        }
    }
    
}
