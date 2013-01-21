/**
 *   Copyright 2012-2013 Nekorp
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */
package prototipo.servicio.imp;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototipo.modelo.Servicio;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.modelo.cliente.Cliente;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.modelo.currency.Moneda;
import prototipo.servicio.EventoServicioFactory;
import prototipo.servicio.RegistroCostoFactory;
import prototipo.view.model.ServicioVB;
import prototipo.view.model.auto.TipoElevadorVB;
import prototipo.view.model.auto.TipoTransmisionVB;
import prototipo.view.model.bitacora.EventoEntregaVB;
import prototipo.view.model.bitacora.EventoGeneralVB;
import prototipo.view.model.bitacora.EventoVB;
import prototipo.view.model.cliente.ClienteVB;
import prototipo.view.model.costo.RegistroCostoVB;
import prototipo.view.model.currency.MonedaVB;

/**
 *
 * 
 */
@Service
public class ProxyUtil {
    private static final Logger LOGGER = Logger.getLogger(ProxyUtil.class);
    @Autowired
    private EventoServicioFactory eventoFactory;
    @Autowired
    private RegistroCostoFactory registroCostofactory;
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    public void copiarPropiedades(Servicio origen, ServicioVB destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "bitacora",
                "datosAuto",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres",
                "costos"
        });
        List<Evento> eventosOrigen = origen.getBitacora().getEventos();
        LinkedList<EventoVB> eventosDestino = new LinkedList<>();
        for (Evento x: eventosOrigen) {
            if (x instanceof EventoGeneral) {
                EventoGeneralVB nuevo;
                nuevo = eventoFactory.creaEvento(EventoGeneralVB.class);
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
            if (x instanceof EventoEntrega) {
                EventoEntregaVB nuevo;
                nuevo = eventoFactory.creaEvento(EventoEntregaVB.class);
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
        destino.getDatosAuto().getEquipamiento().setTransmision(
            TipoTransmisionVB.valueOf(origen.getDatosAuto().getEquipamiento().getTransmision()));
        destino.getDatosAuto().getEquipamiento().setElevadores(
            TipoElevadorVB.valueOf(origen.getDatosAuto().getEquipamiento().getElevadores()));
        
        BeanUtils.copyProperties(origen.getTelefonoUno(), destino.getTelefonoUno());
        BeanUtils.copyProperties(origen.getTelefonoDos(), destino.getTelefonoDos());
        BeanUtils.copyProperties(origen.getTelefonoTres(), destino.getTelefonoTres());
        
        List<RegistroCosto> costosOrigen = origen.getCostos();
        LinkedList<RegistroCostoVB> costosDestino = new LinkedList<>();
        RegistroCostoVB costo;
        for (RegistroCosto x: costosOrigen) {
            costo = registroCostofactory.getRegistroCosto(x.getTipo());
            BeanUtils.copyProperties(x, costo, new String[]{
                "tipo",
                "precioUnitario",
                "precioCliente"
            });
            MonedaVB precioUnitario = MonedaVB.valueOf(x.getPrecioUnitario().getValue());
            costo.setPrecioUnitario(precioUnitario);
            MonedaVB precioCliente = MonedaVB.valueOf(x.getPrecioCliente().getValue());
            costo.setPrecioCliente(precioCliente);
            costosDestino.add(costo);
        }
        destino.setCostos(costosDestino);
    }
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    public void copiarPropiedades(ServicioVB origen, Servicio destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "bitacora",
                "datosAuto",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres",
                "costos"
        });
        List<EventoVB> eventosOrigen = origen.getBitacora().getEventos();
        LinkedList<Evento> eventosDestino = new LinkedList<>();
        for (EventoVB x: eventosOrigen) {
            if (x instanceof EventoGeneralVB) {
                EventoGeneral nuevo;
                nuevo = new EventoGeneral();
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
            if (x instanceof EventoEntregaVB) {
                EventoEntrega nuevo;
                nuevo = new EventoEntrega();
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
        destino.getDatosAuto().getEquipamiento().setTransmision(
            origen.getDatosAuto().getEquipamiento().getTransmision().toString());
        destino.getDatosAuto().getEquipamiento().setElevadores(
            origen.getDatosAuto().getEquipamiento().getElevadores().toString());
        
        BeanUtils.copyProperties(origen.getTelefonoUno(), destino.getTelefonoUno());
        BeanUtils.copyProperties(origen.getTelefonoDos(), destino.getTelefonoDos());
        BeanUtils.copyProperties(origen.getTelefonoTres(), destino.getTelefonoTres());
        
        List<RegistroCostoVB> costosOrigen = origen.getCostos();
        LinkedList<RegistroCosto> costosDestino = new LinkedList<>();
        RegistroCosto costo;
        for (RegistroCostoVB x: costosOrigen) {
            costo = new RegistroCosto();
            BeanUtils.copyProperties(x, costo, new String[]{
                "tipo",
                "precioUnitario",
                "precioCliente"
            });
            costo.setTipo(x.getTipo());
            Moneda precioUnitario = new Moneda(x.getPrecioUnitario().toString());
            costo.setPrecioUnitario(precioUnitario);
            Moneda precioCliente = new Moneda(x.getPrecioCliente().toString());
            costo.setPrecioCliente(precioCliente);
            costosDestino.add(costo);
        }
        destino.setCostos(costosDestino);
    }
    /**
     * copia las propiedades de un cliente a otro.
     * @param origen cliente origen
     * @param destino cliente destino
     */
    public void copiarPropiedades(Cliente origen, ClienteVB destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "domicilio"
        });
        BeanUtils.copyProperties(origen.getDomicilio(), destino.getDomicilio());
    }
    
    /**
     * copia las propiedades de un cliente a otro.
     * @param origen cliente origen
     * @param destino cliente destino
     */
    public void copiarPropiedades(ClienteVB origen, Cliente destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "domicilio"
        });
        BeanUtils.copyProperties(origen.getDomicilio(), destino.getDomicilio());
    }
    
    /**
     * recupera el objeto proxeado.
     * @param proxy el supuesto proxy
     * @return el objeto proxeado
     */
    public Object getTarget(Object proxy) {
        Object obj = proxy;
        if(AopUtils.isAopProxy(proxy)){
            try {
                Advised advised = (Advised) proxy;
                obj = advised.getTargetSource().getTarget();
            } catch (Exception ex) {
                ProxyUtil.LOGGER.error("No se logro recuperar el proxy", ex);
            }
        }
        return obj;
    }
}
