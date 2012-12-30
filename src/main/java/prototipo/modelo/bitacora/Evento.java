/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import java.util.Date;

/**
 * 
 * @author Marisa
 */
public class Evento implements Comparable<Evento> {

    private Date fechaCreacion;
    
    public Evento() {
        this.fechaCreacion = new Date();
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int compareTo(Evento o) {
        return this.fechaCreacion.compareTo(o.getFechaCreacion());
    }

}
