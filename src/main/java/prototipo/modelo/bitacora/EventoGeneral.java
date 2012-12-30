/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Marisa
 */
@Component("eventoGeneral")
@Scope("prototype")
public class EventoGeneral extends Evento {
    private String detalle;
    private Date fechaEvento;
    private String etiquetas;

    public EventoGeneral() {
        this.detalle = "";
        this.fechaEvento = new Date();
        this.etiquetas = "";
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

}
