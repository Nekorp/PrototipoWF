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
package org.nekorp.workflow.desktop.servicio.imp;

import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoDiagnosticoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoObservacionesVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;


/**
 *
 * 
 */
public abstract class EventoServicioFactoryImp implements EventoServicioFactory {

    @Override
    public <T extends EventoVB> T creaEvento(Class<T> type) {
        if (type == EventoGeneralVB.class) {
            return (T) creaEventoGeneral();
        }
        if (type == EventoEntregaVB.class) {
            return (T) creaEventoEntrega();
        }
        if (type == EventoSistemaVB.class) {
            return (T) creaEventoSistema();
        }
        if (type == EventoReclamacionVB.class) {
            return (T) creaEventoReclamacion();
        }
        if (type == EventoFinServicioVB.class) {
            return (T) creaEventoFinServicio();
        }
        if (type == EventoDiagnosticoVB.class) {
            return (T) creaEventoDiagnostico();
        }
        if (type == EventoObservacionesVB.class) {
            return (T) creaEventoObservaciones();
        }
        throw new IllegalArgumentException("Tipo no reconocido por la fabrica");
    }
    
    /**
     * se utilzan para method injection de spring
     * para poder tener objetos con scope prototype
     * para poder tener AOP
     * @return objeto proxeado
     */
    public abstract EventoGeneralVB creaEventoGeneral();
    
    public abstract EventoEntregaVB creaEventoEntrega();
    
    public abstract EventoSistemaVB creaEventoSistema();
    
    public abstract EventoReclamacionVB creaEventoReclamacion();
    
    public abstract EventoFinServicioVB creaEventoFinServicio();
    
    public abstract EventoDiagnosticoVB creaEventoDiagnostico();
    
    public abstract EventoObservacionesVB creaEventoObservaciones();
}
