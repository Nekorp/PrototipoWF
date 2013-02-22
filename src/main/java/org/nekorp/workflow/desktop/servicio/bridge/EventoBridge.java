/**
 *   Copyright 2013 Nekorp
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
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.modelo.bitacora.Evento;
import org.nekorp.workflow.desktop.modelo.bitacora.Evidencia;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EvidenciaVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EventoBridge implements ModelBridge<Evento, EventoVB> {

    @Autowired
    private EvidenciaBridge evidenciaBridge;
    
    @Override
    public void load(Evento origen, EventoVB destino) {
        List<EvidenciaVB> evDestino = new LinkedList<>();
        EvidenciaVB nueva;
        for (Evidencia x: origen.getEvidencia()) {
            nueva = new EvidenciaVB();
            evidenciaBridge.load(x, nueva);
            evDestino.add(nueva);
        }
        destino.setEvidencia(evDestino);
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
        destino.setFechaCreacion(origen.getFechaCreacion());
        destino.setResponsable(origen.getResponsable());
        if (destino instanceof EventoEntregaVB) {
            EventoEntregaVB real = (EventoEntregaVB) destino;
            real.setNombreEvento(origen.getEtiqueta());
            real.setFecha(origen.getFecha());
            real.setDetalle(origen.getDescripcion());
        }
        if (destino instanceof EventoGeneralVB) {
            EventoGeneralVB real = (EventoGeneralVB) destino;
            real.setDetalle(origen.getDescripcion());
            real.setEtiquetas(origen.getEtiqueta());
            real.setFechaEvento(origen.getFecha());
        }
        if (destino instanceof EventoSistemaVB) {
            EventoSistemaVB real = (EventoSistemaVB) destino;
            real.setNombre(origen.getEtiqueta());
        }
        if (destino instanceof EventoReclamacionVB) {
            EventoReclamacionVB real = (EventoReclamacionVB) destino;
            real.setDetalle(origen.getDescripcion());
            real.setFundada(StringUtils.equalsIgnoreCase(
                "fundada", origen.getEtiqueta()));
        }
    }

    @Override
    public void unload(EventoVB origen, Evento destino) {
        List<Evidencia> evDestino = new LinkedList<>();
        Evidencia nueva;
        for (EvidenciaVB x: origen.getEvidencia()) {
            nueva = new Evidencia();
            evidenciaBridge.unload(x, nueva);
            evDestino.add(nueva);
        }
        destino.setEvidencia(evDestino);
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
        destino.setFechaCreacion(origen.getFechaCreacion());
        destino.setResponsable(origen.getResponsable());
        if (origen instanceof EventoEntregaVB) {
            EventoEntregaVB real = (EventoEntregaVB) origen;
            destino.setTipo("EventoEntrega");
            destino.setFecha(real.getFecha());
            destino.setEtiqueta(real.getNombreEvento());
            destino.setDescripcion(real.getDetalle());
        }
        if (origen instanceof EventoGeneralVB) {
            EventoGeneralVB real = (EventoGeneralVB) origen;
            destino.setTipo("EventoGeneral");
            destino.setDescripcion(real.getDetalle());
            destino.setEtiqueta(real.getEtiquetas());
            destino.setFecha(real.getFechaEvento());
        }
        if (origen instanceof EventoSistemaVB) {
            EventoSistemaVB real = (EventoSistemaVB) origen;
            destino.setTipo("EventoSistema");
            destino.setEtiqueta(real.getNombre());
        }
        if (origen instanceof EventoReclamacionVB) {
            EventoReclamacionVB real = (EventoReclamacionVB) origen;
            destino.setTipo("EventoReclamacion");
            if (real.isFundada()) {
                destino.setEtiqueta("fundada");
            } else {
                destino.setEtiqueta("");
            }
            destino.setDescripcion(real.getDetalle());
        }
    }
}
