/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio;

import prototipo.modelo.bitacora.Evento;

/**
 *
 * @author Marisa
 */
public interface EventoServicioFactory {
    public <T extends Evento> T creaEvento(Class<T> type);
}
