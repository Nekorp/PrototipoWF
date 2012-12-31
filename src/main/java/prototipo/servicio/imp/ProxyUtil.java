/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototipo.modelo.Servicio;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.EventoServicioFactory;

/**
 *
 * @author Marisa
 */
@Service
public class ProxyUtil {
    @Autowired
    private EventoServicioFactory eventoFactory;
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    public void copiarPropiedades(Servicio origen, Servicio destino, boolean proxy) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "bitacora",
                "datosAuto",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres"
        });
        List<Evento> eventosOrigen = origen.getBitacora().getEventos();
        LinkedList<Evento> eventosDestino = new LinkedList<>();
        for (Evento x: eventosOrigen) {
            if (x instanceof EventoGeneral) {
                EventoGeneral nuevo;
                if (proxy) {
                    nuevo = eventoFactory.creaEvento(EventoGeneral.class);
                } else {
                    nuevo = new EventoGeneral();
                }
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
            if (x instanceof EventoEntrega) {
                EventoEntrega nuevo;
                if (proxy) {
                    nuevo = eventoFactory.creaEvento(EventoEntrega.class);
                } else {
                    nuevo = new EventoEntrega();
                }
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
        }
        destino.getBitacora().setEventos(eventosDestino);
        BeanUtils.copyProperties(origen.getDatosAuto(), destino.getDatosAuto(), 
            new String[]{
                "equipamiento"
        });
        BeanUtils.copyProperties(origen.getDatosAuto().getEquipamiento(), destino.getDatosAuto().getEquipamiento(), 
            new String[]{
                "transmision",
                "elevadores"
        });
        destino.getDatosAuto().getEquipamiento().setTransmision(origen.getDatosAuto().getEquipamiento().getTransmision());
        destino.getDatosAuto().getEquipamiento().setElevadores(origen.getDatosAuto().getEquipamiento().getElevadores());
        
        BeanUtils.copyProperties(origen.getTelefonoUno(), destino.getTelefonoUno());
        BeanUtils.copyProperties(origen.getTelefonoDos(), destino.getTelefonoDos());
        BeanUtils.copyProperties(origen.getTelefonoTres(), destino.getTelefonoTres());
    }
    /**
     * copia las propiedades de un cliente a otro.
     * @param origen cliente origen
     * @param destino cliente destino
     */
    public void copiarPropiedades(Cliente origen, Cliente destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "domicilio"
        });
        BeanUtils.copyProperties(origen.getDomicilio(), destino.getDomicilio());
    }
}
