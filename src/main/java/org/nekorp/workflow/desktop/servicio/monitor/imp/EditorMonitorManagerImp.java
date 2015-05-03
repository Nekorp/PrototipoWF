/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.servicio.monitor.imp;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasEdicionActual;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitor;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.servicio.monitor.MonitorCatalog;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nekorp
 */
@Service
@Aspect
public class EditorMonitorManagerImp implements EditorMonitorManager {

    private static final Logger LOGGER = Logger.getLogger(EditorMonitorManagerImp.class);
    @Autowired
    private CustomerEditorMonitor customerMonitor;
    @Autowired
    private AutoEditorMonitor autoMonitor;
    @Autowired
    private BitacoraEditorMonitor bitacoraMonitor;
    @Autowired
    private EvidenciaEditorMonitor evidenciaMonitor;
    @Autowired
    private PresupuestoEditorMonitor presupuestoMonitor;
    @Autowired
    private InventarioDerechaEditorMonitor inventarioDerechaMonitor;
    @Autowired
    private InventarioIzquierdaEditorMonitor inventarioIzquierdaMonitor;
    @Autowired
    private InventarioFrontalEditorMonitor inventarioFrontalMonitor;
    @Autowired
    private InventarioTraseraEditorMonitor inventarioTraseraMonitor;
    @Autowired
    private CobranzaEditorMonitor cobranzaMonitor;
    @Autowired
    private EdicionServicioMetadata metadataServicio;
    
    private MonitorCatalog currentSelected;
    
    private Map<MonitorCatalog, EditorMonitor> monitores;
    
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.ControlServicio.crearServicio(..)) || "
            + "execution(* org.nekorp.workflow.desktop.control.ControlServicio.cargaServicio(..)) || "
            + "execution(* org.nekorp.workflow.desktop.control.ControlServicio.cambiarServicio(..)) ||"
            + "execution(* org.nekorp.workflow.desktop.control.ControlServicio.guardaServicio(..))")
    public void cargarServicioPointCut(){
    }
    
    @Before("cargarServicioPointCut()")
    public void saveMonitorData() {
        if (metadataServicio.getServicioActual() != null) {
            LOGGER.debug("guardando info del monitor");
            PreferenciasEdicionActual preferencias = metadataServicio.getServicioActual().getPreferenciasEdicion();
            preferencias.getMonitorData().put(MonitorCatalog.CUSTOMER, customerMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.AUTO, autoMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.BITACORA, bitacoraMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.EVIDENCIA, evidenciaMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.PRESUPUESTO, presupuestoMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.INVENTARIO_DERECHA, inventarioDerechaMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.INVENTARIO_IZQUIERDA, inventarioIzquierdaMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.INVENTARIO_FRONTAL, inventarioFrontalMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.INVENTARIO_TRASERA, inventarioTraseraMonitor.getData());
            preferencias.getMonitorData().put(MonitorCatalog.COBRANZA, cobranzaMonitor.getData());
        }
    }
    
    @AfterReturning("cargarServicioPointCut()")
    public void changeMonitorData() {
        if (metadataServicio.getServicioActual() != null) {
            LOGGER.debug("cargando info del monitor");
            PreferenciasEdicionActual preferencias = metadataServicio.getServicioActual().getPreferenciasEdicion();
            customerMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.CUSTOMER));
            autoMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.AUTO));
            bitacoraMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.BITACORA));
            evidenciaMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.EVIDENCIA));
            presupuestoMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.PRESUPUESTO));
            inventarioDerechaMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.INVENTARIO_DERECHA));
            inventarioIzquierdaMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.INVENTARIO_IZQUIERDA));
            inventarioFrontalMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.INVENTARIO_FRONTAL));
            inventarioTraseraMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.INVENTARIO_TRASERA));
            cobranzaMonitor.setData(preferencias.getMonitorData().get(MonitorCatalog.COBRANZA));
        }
    }
    
    @PostConstruct
    public void init() {
        monitores = new HashMap<>();
        monitores.put(MonitorCatalog.CUSTOMER, customerMonitor);
        monitores.put(MonitorCatalog.AUTO, autoMonitor);
        monitores.put(MonitorCatalog.BITACORA, bitacoraMonitor);
        monitores.put(MonitorCatalog.EVIDENCIA, evidenciaMonitor);
        monitores.put(MonitorCatalog.PRESUPUESTO, presupuestoMonitor);
        monitores.put(MonitorCatalog.INVENTARIO_DERECHA, inventarioDerechaMonitor);
        monitores.put(MonitorCatalog.INVENTARIO_IZQUIERDA, inventarioIzquierdaMonitor);
        monitores.put(MonitorCatalog.INVENTARIO_FRONTAL, inventarioFrontalMonitor);
        monitores.put(MonitorCatalog.INVENTARIO_TRASERA, inventarioTraseraMonitor);
        monitores.put(MonitorCatalog.COBRANZA, cobranzaMonitor);
    }
    
    @Override
    public void selectMonitor(MonitorCatalog monitor) {
       LOGGER.debug("Seleccionando monitor:" + monitor.name());
        if (this.currentSelected != null) {
            this.monitores.get(this.currentSelected).setEncendido(false);
        }
        this.currentSelected = monitor;
        this.monitores.get(this.currentSelected).setEncendido(true);
    }

    @Override
    public EditorMonitor getCurrentMonitor() {
        return this.monitores.get(this.currentSelected);
    }
    
    @Override
    public MonitorCatalog getCurrentMonitorCatalog() {
        return this.currentSelected;
    }

    @Override
    public EditorMonitor getMonitor(MonitorCatalog monitor) {
        return this.monitores.get(monitor);
    }
}
