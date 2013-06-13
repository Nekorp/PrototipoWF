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

package org.nekorp.workflow.desktop.servicio.reporte.cliente;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.AutoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.EventoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.RegistroCostoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.ReporteCliente;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraMetaData;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoDiagnosticoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoObservacionesVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroHojalateriaPinturaVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroMecanicaVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DatosReporteClienteFactory {
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private BitacoraMetaData bitacoraMetaData;
    
    public ReporteCliente getData() {
        ReporteCliente dato = new ReporteCliente();
        dato.setNumeroDeServicio(servicioVB.getId());
        dato.setNombreDelCliente(servicioVB.getCliente().getNombre());
        dato.setDescripcionServicio(servicioVB.getDescripcion());
        
        dato.setTiempoReparacion(bitacoraMetaData.getTiempoServicio());
        AutoRC auto = new AutoRC();
        auto.setMarca(servicioVB.getAuto().getMarca());
        auto.setTipo(servicioVB.getAuto().getTipo());
        auto.setVersion(servicioVB.getAuto().getVersion());
        auto.setSerie(servicioVB.getAuto().getNumeroSerie());
        auto.setModelo(servicioVB.getAuto().getModelo());
        auto.setColor(servicioVB.getAuto().getColor());
        auto.setPlacas(servicioVB.getAuto().getPlacas());
        auto.setKilometraje(servicioVB.getDatosAuto().getKilometraje());
        dato.setAuto(auto);
        
        List<RegistroCostoRC> mecanica = new LinkedList<>();
        List<RegistroCostoRC> hojalateria = new LinkedList<>();
        for (RegistroCostoVB x: servicioVB.getCostos()) {
            if (x.getTipo().equals(RegistroMecanicaVB.TIPO) && !StringUtils.equals(x.getSubtipo(), "Insumo")) {
                RegistroCostoRC registro = new RegistroCostoRC();
                registro.setTipo(x.getSubtipo());
                registro.setDescripcion(x.getConcepto());
                registro.setCosto(x.getSubtotal().doubleValue());
                mecanica.add(registro);
            }
            if (x.getTipo().equals(RegistroHojalateriaPinturaVB.TIPO) && !StringUtils.equals(x.getSubtipo(), "Insumo")) {
                RegistroCostoRC registro = new RegistroCostoRC();
                registro.setTipo(x.getSubtipo());
                registro.setDescripcion(x.getConcepto());
                registro.setCosto(x.getSubtotal().doubleValue());
                hojalateria.add(registro);
            }
        }
        dato.setRegistroMecanica(mecanica);
        dato.setRegistroHojalateriaPintura(hojalateria);
        
        List<EventoRC> eventos = new LinkedList<>();
        EventoRC evento;
        for (EventoVB x: servicioVB.getBitacora().getEventos()) {
            evento = new EventoRC();
            if (x instanceof EventoEntregaVB) {
                EventoEntregaVB ev = (EventoEntregaVB) x;
                evento.setNombreEvento(ev.getNombreEvento());
                evento.setDetalle(ev.getResponsable());
                evento.setFecha(ev.getFecha());
                evento.setEtiqueta("");
            }
            if (x instanceof EventoFinServicioVB) {
                EventoFinServicioVB ev = (EventoFinServicioVB) x;
                evento.setNombreEvento(ev.getNombreEvento());
                evento.setDetalle(ev.getResponsable());
                evento.setFecha(ev.getFechaCreacion());
                evento.setEtiqueta("");
            }
            if (x instanceof EventoGeneralVB) {
                EventoGeneralVB ev = (EventoGeneralVB) x;
                evento.setNombreEvento(ev.getEtiquetas());
                evento.setDetalle(ev.getDetalle());
                evento.setFecha(ev.getFechaEvento());
                evento.setEtiqueta("");
            }
            if (x instanceof EventoReclamacionVB) {
                EventoReclamacionVB ev = (EventoReclamacionVB) x;
                evento.setNombreEvento("Reclamaciones");
                evento.setDetalle(ev.getDetalle());
                if (ev.isFundada()) {
                    evento.setEtiqueta("Fundada");
                } else {
                    evento.setEtiqueta("Infundada");
                }
                evento.setFecha(ev.getFechaCreacion());
            }
            if (x instanceof EventoSistemaVB) {
                EventoSistemaVB ev = (EventoSistemaVB) x;
                evento.setNombreEvento(ev.getNombre());
                evento.setDetalle("");
                evento.setFecha(ev.getFechaCreacion());
                evento.setEtiqueta("");
            }
            if (x instanceof EventoDiagnosticoVB) {
                EventoDiagnosticoVB ev = (EventoDiagnosticoVB) x;
                evento.setNombreEvento("Diagnostico");
                evento.setDetalle(ev.getDetalle());
                evento.setEtiqueta("");
                evento.setFecha(ev.getFechaCreacion());
            }
            if (x instanceof EventoObservacionesVB) {
                EventoObservacionesVB ev = (EventoObservacionesVB) x;
                evento.setNombreEvento("Observaciones");
                evento.setDetalle(ev.getObservaciones());
                evento.setEtiqueta("");
                evento.setFecha(ev.getFechaCreacion());
            }
            eventos.add(evento);
        }
        dato.setBitacora(eventos);
        return dato;
    }
}
