/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import prototipo.modelo.bitacora.Evento;
import java.util.Date;

/**
 *
 * @author Marisa
 */
public class EventoEntrega extends Evento {
    private String entrego;
    private String recibio;
    private Date fecha;
    private String nombreEvento;

    public String getEntrego() {
        return entrego;
    }

    public void setEntrego(String entrego) {
        this.entrego = entrego;
    }

    public String getRecibio() {
        return recibio;
    }

    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
}
