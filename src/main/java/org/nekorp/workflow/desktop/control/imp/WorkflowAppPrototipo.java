/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.control.imp;

import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.control.delegate.ControlAutoDelegate;
import org.nekorp.workflow.desktop.control.delegate.ControlClienteDelegate;
import org.nekorp.workflow.desktop.control.delegate.ServicioController;
import org.nekorp.workflow.desktop.control.delegate.ServicioIndexController;
import org.nekorp.workflow.desktop.control.delegate.StartStopController;
import org.nekorp.workflow.desktop.control.delegate.WarmupAppController;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasUsuario;
import org.nekorp.workflow.desktop.modelo.reporte.ParametrosReporte;
import org.nekorp.workflow.desktop.modelo.reporte.global.ParametrosReporteGlobal;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import technology.tikal.customers.model.Customer;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

/**
 * 
 * @author Nekorp
 */
@Controller("application")
public class WorkflowAppPrototipo implements WorkflowApp {

    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Autowired
    @Qualifier(value = "GeneradorReporteCliente")
    private GeneradorReporte<ParametrosReporte> generadorReporte;
    @Autowired
    @Qualifier(value = "GeneradorOrdenServicio")
    private GeneradorReporte<ParametrosReporteOS> generadorOrdenServicio;
    @Autowired
    @Qualifier(value = "GeneradorReporteGlobal")
    private GeneradorReporte<ParametrosReporteGlobal> generadorReporteGlobal;
    @Autowired
    private PreferenciasUsuario preferenciasUsuario;
    @Autowired
    private WarmupAppController warmupController;
    @Autowired
    private ServicioIndexController servicioIndexController;
    @Autowired
    private StartStopController startStopController;
    @Autowired
    private ServicioController servicioController;
    @Autowired
    private ControlClienteDelegate controlClienteDelegate;
    @Autowired
    private ControlAutoDelegate controlAutoDelegate;
    
    @Override
    public void warmupCustomer() {
        warmupController.warmupCustomer();
    }
    
    @Override
    public void warmupAutoIndex() {
        warmupController.warmupAutoIndex();
    }
    
    @Override
    public void warmupServicioIndex() {
        warmupController.warmupServicioIndex();
    }
    
    @Override
    public void startAplicacion() {
        startStopController.startAplicacion();
    }

    @Override
    public void closeAplicacion() {
        startStopController.closeAplicacion();
    }

    @Override
    public List<ServicioIndexVB> getIndexServicios() {
        return servicioIndexController.getIndexServicios();
    }
    
    @Override
    public List<ServicioIndexVB> getIndexServicios(Long sinceId) {
        return servicioIndexController.getIndexServicios(sinceId);
    }
    
    @Override
    public boolean crearServicio() {
        return this.servicioController.crearServicio();
    }

    @Override
    public boolean cambiarServicio(ServicioLoaded servicio) {
        return this.servicioController.cambiarServicio(servicio);
    }
    
    @Override
    public void cerrarServicio() {
        this.servicioController.cerrarServicio();
    }

    @Override
    public boolean cargaServicio(Long idServicio) {
        return this.servicioController.cargaServicio(idServicio);
    }

    @Override
    public boolean guardaServicio() {
        return this.servicioController.guardaServicio();
    }
    
    @Override
    public void loadCliente(Customer origen) {
        controlClienteDelegate.loadCliente(origen);
    }

    @Override
    public Customer[] getClientes() {
        return controlClienteDelegate.getClientes();
    }

    @Override
    public void buscarCliente(String name, final Callback cmd) {
        controlClienteDelegate.buscarCliente(name, cmd);
    }

    @Override
    public void generaReporte(ParametrosReporte param) {
        this.generadorReporte.generaReporte(param);
    }

    @Override
    public void generaOrdenServicio(ParametrosReporteOS param) {
        this.generadorOrdenServicio.generaReporte(param);
    }

    @Override
    public void generaReporteGlobal(ParametrosReporteGlobal param) {
        generadorReporteGlobal.generaReporte(param);
    }

    @Override
    public void loadAuto(ServicioIndexAutoData origen) {
        controlAutoDelegate.loadAuto(origen);
    }
    
    @Override
    public List<ServicioIndexAutoData> getAutos() {
        return controlAutoDelegate.getAutos();
    }

    @Override
    public void buscarAuto(String numeroSerie, Callback<List<ServicioIndexAutoData>> cmd) {
        controlAutoDelegate.buscarAuto(numeroSerie, cmd);
    }

    @Override
    public void cancelarAlertas() {
        this.startStopController.cancelarAlertas();
    }
    
    @Override
    public PreferenciasUsuario getPreferenciasUsuario() {
        return this.preferenciasUsuario;
    }

    @Override
    public void setPreferenciasUsuario(PreferenciasUsuario param) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("app.properties");
            config.setProperty("app.service.busqueda.firstId", param.getFirstId());
            config.setProperty("app.service.busqueda.filtro.ultimo", param.getUltimoFiltro());
            config.save();
        } catch (ConfigurationException ex) {
            WorkflowAppPrototipo.LOGGER.error("error al guardar las preferencias" + ex.getMessage());
        }
    }
}
