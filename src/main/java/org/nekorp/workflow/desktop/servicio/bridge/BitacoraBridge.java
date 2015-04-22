/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

package org.nekorp.workflow.desktop.servicio.bridge;

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoDiagnosticoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoObservacionesVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;

/**
 * @author Nekorp
 */
@Service
public class BitacoraBridge implements ModelBridge<List<Evento>, BitacoraVB> {

    @Autowired
    private EventoBridge eventoBridge;
    @Autowired
    private EventoServicioFactory eventoFactory;
    @Override
    public void load(List<Evento> origen, BitacoraVB destino) {
        List<EventoVB> eventos = new LinkedList<>();
        EventoVB nuevo;
        for (Evento x: origen) {
            if (x.getTipo().equals("EventoEntrega")) {
                nuevo = eventoFactory.creaEvento(EventoEntregaVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoGeneral")) {
                nuevo = eventoFactory.creaEvento(EventoGeneralVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoSistema")) {
                nuevo = eventoFactory.creaEvento(EventoSistemaVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoReclamacion")) {
                nuevo = eventoFactory.creaEvento(EventoReclamacionVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoFinServicio")) {
                nuevo = eventoFactory.creaEvento(EventoFinServicioVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoDiagnostico")) {
                nuevo = eventoFactory.creaEvento(EventoDiagnosticoVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
            if (x.getTipo().equals("EventoObservaciones")) {
                nuevo = eventoFactory.creaEvento(EventoObservacionesVB.class);
                eventoBridge.load(x, nuevo);
                eventos.add(nuevo);
            }
        }
        destino.setEventos(eventos);
    }

    @Override
    public void unload(final BitacoraVB origen, final List<Evento> destino) {
        Evento nuevo;
        for (EventoVB x: origen.getEventos()) {
            nuevo = new Evento();
            eventoBridge.unload(x, nuevo);
            destino.add(nuevo);
        }
    }
}
