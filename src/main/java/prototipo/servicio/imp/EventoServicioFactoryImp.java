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

import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.servicio.EventoServicioFactory;

/**
 *
 * 
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