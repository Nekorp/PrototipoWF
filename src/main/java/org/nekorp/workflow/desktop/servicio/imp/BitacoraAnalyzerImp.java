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

import java.util.Date;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import org.apache.commons.beanutils.Converter;
import org.nekorp.workflow.desktop.servicio.BitacoraAnalyzer;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraMetaData;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BitacoraAnalyzerImp implements BitacoraAnalyzer, Bindable {
    @Autowired
    private Converter dateConverter;
    @Autowired
    private BitacoraMetaData metaData;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private BitacoraVB bitacora;
    
    private EventoEntregaVB eventoEntrada;
    private EventoEntregaVB eventoSalida;
    
    private EventoSistemaVB eventoInicioServicio;
    
    private Thread hilo;
    
    @PostConstruct
    public void init(){
        this.bindingManager.registerBind(this.bitacora, "eventos", this);
        BitacoraRefreshTask task = new BitacoraRefreshTask(this);
        hilo = new Thread(task);
        hilo.start();
    }
    
    public void buscaEventoInicioServicio(LinkedList<EventoVB> datos) {
        if (this.eventoInicioServicio != null) {
            this.bindingManager.removeBind(eventoInicioServicio, "fechaCreacion", this);
            eventoInicioServicio = null;
        }
        for (EventoVB obj: datos){
            if (obj instanceof EventoSistemaVB) {
                EventoSistemaVB ev = (EventoSistemaVB)obj;
                if (ev.getNombre().compareTo("Inicio del Servicio") == 0) {
                    eventoInicioServicio = ev;
                    this.bindingManager.registerBind(ev, "fechaCreacion", this);
                    return;
                }
            }
        }
    }
    
    public Date getFechaInicioServicio() {
        Date fechaEntrada = null;
        if (this.eventoInicioServicio != null) {
            fechaEntrada = eventoInicioServicio.getFechaCreacion();
        }
        return fechaEntrada;
    }
    
    public Date getFechaFinServicio() {
        return null;
    }
    
    public String getTiempoServicio() {
        Date fechaEntrada = this.getFechaInicioServicio();
        Date fechaSalida = this.getFechaFinServicio();
        if (fechaSalida == null) {
            fechaSalida = new Date();
        }
        if (fechaEntrada != null) {
            long ms = fechaSalida.getTime() - fechaEntrada.getTime();
            long x;
            x = ms / 1000;
            long segundos = x % 60;
            x /= 60;
            long minutes = x % 60;
            x /= 60;
            long hours = x % 24;
            x /= 24;
            long days = x;
            return(days+"D "+hours +"H "+minutes+ "m "+segundos+"s");
        } else {
            return("");
        }
    }
    
    public void buscaEventoEntrada(LinkedList<EventoVB> datos) {
        if (this.eventoEntrada != null) {
            this.bindingManager.removeBind(eventoEntrada, "fecha", this);
            eventoEntrada = null;
        }
        for (EventoVB obj: datos){
            if (obj instanceof EventoEntregaVB) {
                EventoEntregaVB ev = (EventoEntregaVB)obj;
                if (ev.getNombreEvento().compareTo("Entrada de Auto") == 0) {
                    eventoEntrada = ev;
                    this.bindingManager.registerBind(ev, "fecha", this);
                    return;
                }
            }
        }
    }
    
    
    public Date getFechaEnradaAuto() {
        Date fechaEntrada = null;
        if (this.eventoEntrada != null) {
            fechaEntrada = eventoEntrada.getFecha();
        }
        return fechaEntrada;
    }
    

    public void buscaEventoSalida(LinkedList<EventoVB> datos) {
        if (this.eventoSalida != null) {
            this.bindingManager.removeBind(eventoSalida, "fecha", this);
            this.eventoSalida = null;
        }
        for (EventoVB obj: datos){
            if (obj instanceof EventoEntregaVB) {
                EventoEntregaVB ev = (EventoEntregaVB)obj;
                if (ev.getNombreEvento().compareTo("Salida de Auto") == 0) {
                    eventoSalida = ev;
                    this.bindingManager.registerBind(ev, "fecha", this);
                    return;
                }
            }
        }
    }
    
    public Date getFechaSalidaAuto() {
        if (eventoSalida != null) {
            return eventoSalida.getFecha();
        } 
        return null;
    }

    
    public String getTiempoEstadia() {
        Date fechaEntrada = this.getFechaEnradaAuto();
        Date fechaSalida = this.getFechaSalidaAuto();
        if (fechaSalida == null) {
            fechaSalida = new Date();
        }
        if (fechaEntrada != null) {
            long ms = fechaSalida.getTime() - fechaEntrada.getTime();
            long x;
            x = ms / 1000;
            long segundos = x % 60;
            x /= 60;
            long minutes = x % 60;
            x /= 60;
            long hours = x % 24;
            x /= 24;
            long days = x;
            return(days+"D "+hours +"H "+minutes+ "m "+segundos+"s");
        } else {
            return("");
        }
    }

    @Override
    public synchronized void updateModel(Object origen, String property, Object value) {
        if (origen instanceof BitacoraVB ) {
            LinkedList<EventoVB> eventos = (LinkedList<EventoVB>) value;
            this.buscaEventoEntrada(eventos);
            this.buscaEventoSalida(eventos);
            this.buscaEventoInicioServicio(eventos);
        }
        //duracion servicio
        String fecha = (String) dateConverter.convert(String.class, this.getFechaInicioServicio());
        this.metaData.setFechaInicioServicio(fecha);
        fecha = (String) dateConverter.convert(String.class, this.getFechaFinServicio());
        this.metaData.setFechaFinServicio(fecha);
        this.metaData.setTiempoServicio(this.getTiempoServicio());
        //estadia auto
        fecha = (String) dateConverter.convert(String.class, this.getFechaEnradaAuto());
        this.metaData.setFechaEntrada(fecha);
        fecha = (String) dateConverter.convert(String.class, this.getFechaSalidaAuto());
        this.metaData.setFechaSalidaAuto(fecha);
        this.metaData.setTiempoEstadia(this.getTiempoEstadia());
        
    }

    @Override
    public void ignoreUpdate(Object value) {
        throw new UnsupportedOperationException("Objeto que no modifica");
    }

    @Override
    public Object getModelValue() {
        throw new UnsupportedOperationException("Objeto que no modifica");
    }

    @Override
    public void bindListener(Object target, String property) {
        //No hacer nada por que esta cosa no actualiza los eventos solo
        //los observa
    }
    
}
