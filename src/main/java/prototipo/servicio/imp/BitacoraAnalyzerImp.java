/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import java.util.Date;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototipo.modelo.bitacora.Bitacora;
import prototipo.modelo.bitacora.BitacoraMetaData;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.servicio.BitacoraAnalyzer;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 * @author Marisa
 */
@Service
public class BitacoraAnalyzerImp implements BitacoraAnalyzer, Bindable {
    @Autowired
    private Converter dateConverter;
    @Autowired
    private BitacoraMetaData metaData;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private Bitacora bitacora;
    
    private EventoEntrega eventoEntrada;
    private EventoEntrega eventoSalida;
    
    private Thread hilo;
    
    @PostConstruct
    public void init(){
        this.bindingManager.registerBind(this.bitacora, "eventos", this);
        BitacoraRefreshTask task = new BitacoraRefreshTask(this);
        hilo = new Thread(task);
        hilo.start();
    }
    
    public void buscaEventoEntrada(LinkedList<Evento> datos) {
        if (this.eventoEntrada != null) {
            this.bindingManager.removeBind(eventoEntrada, "fecha", this);
            eventoEntrada = null;
        }
        for (Evento obj: datos){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
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
    

    public void buscaEventoSalida(LinkedList<Evento> datos) {
        if (this.eventoSalida != null) {
            this.bindingManager.removeBind(eventoSalida, "fecha", this);
            this.eventoSalida = null;
        }
        for (Evento obj: datos){
            if (obj instanceof EventoEntrega) {
                EventoEntrega ev = (EventoEntrega)obj;
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
    public synchronized void updateModel(Object origen, Object value) {
        if (origen instanceof Bitacora ) {
            LinkedList<Evento> eventos = (LinkedList<Evento>) value;
            this.buscaEventoEntrada(eventos);
            this.buscaEventoSalida(eventos);
        }
        String fecha = (String) dateConverter.convert(String.class, this.getFechaEnradaAuto());
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
    public Object getValue() {
        throw new UnsupportedOperationException("Objeto que no modifica");
    }

    @Override
    public void bindListener(Object target, String property) {
        //No hacer nada por que esta cosa no actualiza los eventos solo
        //los observa
    }
    
}
