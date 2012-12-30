/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.servicio.EventoServicioFactory;

/**
 *
 * @author Marisa
 */
public abstract class EventoServicioFactoryImp implements EventoServicioFactory {

    @Override
    public <T extends Evento> T creaEvento(Class<T> type) {
        if (type == EventoGeneral.class) {
            return (T) creaEventoGeneral();
        }
        if (type == EventoEntrega.class) {
            return (T) creaEventoEntrega();
        }
        throw new IllegalArgumentException("Tipo no reconocido por la fabrica");
    }
    
    /**
     * se utilzan para method injection de spring
     * para poder tener objetos con scope prototype
     * para poder tener AOP
     * @return objeto proxeado
     */
    public abstract EventoGeneral creaEventoGeneral();
    
    public abstract EventoEntrega creaEventoEntrega();
}
