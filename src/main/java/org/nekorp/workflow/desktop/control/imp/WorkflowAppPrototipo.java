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
package org.nekorp.workflow.desktop.control.imp;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.data.access.InventarioDamageDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.modelo.bitacora.Evento;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.costo.RegistroCosto;
import org.nekorp.workflow.desktop.modelo.index.ServicioIndex;
import org.nekorp.workflow.desktop.modelo.inventario.damage.DamageDetail;
import org.nekorp.workflow.desktop.modelo.reporte.ParametrosReporte;
import org.nekorp.workflow.desktop.modelo.reporte.global.ParametrosReporteGlobal;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EditorMonitor;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ClienteBridge;
import org.nekorp.workflow.desktop.servicio.bridge.CostoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.InventarioDamageBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioIndexBridge;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralCliente;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralDatosAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

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
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
    @Autowired
    private ServicioIndexBridge servicioIndexBridge;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ClienteBridge clienteBridge;
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private AutoBridge autoBridge;
    @Autowired
    private BitacoraDAO bitacoraDAO;
    @Autowired
    private BitacoraBridge bitacoraBridge;
    @Autowired
    private CostoDAO costoDAO;
    @Autowired
    private CostoBridge costoBridge;
    @Autowired
    private InventarioDamageDAO inventarioDamageDAO;
    @Autowired 
    private InventarioDamageBridge inventarioDamageBridge;
    @Autowired
    private EditorMonitor editorMonitor;
    @Autowired
    private MensajesControl mensajesControl;
    @Autowired
    @Qualifier("validacionGeneralCliente")
    private ValidacionGeneralCliente validacionGeneralCliente;
    @Autowired
    @Qualifier("validacionGeneralDatosAuto")
    private ValidacionGeneralDatosAuto validacionGeneralDatosAuto;
    
    @Override
    public void startApliacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
         List<ServicioIndex> vencidos = servicioDAO.getIndiceServiciosPorStatus("Vencido");
         for (ServicioIndex x: vencidos) {
             String msg = "El folio " + x.getId() + " está vencido.";
             mensajesControl.reportarAlerta(x.getId(), msg);
         }
         List<ServicioIndex> sinCerrar = servicioDAO.getIndiceServiciosPorStatus("SinCerrar");
         for (ServicioIndex x: sinCerrar) {
             String msg = "Servicio " + x.getId() + " sin cerrar.";
             mensajesControl.reportarAlerta(x.getId(), msg);
         }
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    @Override
    public List<ServicioIndexVB> getIndexServicios() {
        try {
            List<ServicioIndexVB> respuesta = new LinkedList<>();
            servicioIndexBridge.load(this.servicioDAO.getIndiceServicios(), respuesta);
            return respuesta;
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar el indice de los servicios" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    @Override
    public void cargaServicio(Long idServicio) {
        try {
            Servicio servicio = servicioDAO.cargar(idServicio);
            servicioBridge.load(servicio, servicioVB);
            //se consultan los costos
            List<RegistroCostoVB> costosVB = new LinkedList<>();
            List<RegistroCosto> costo = costoDAO.cargar(servicio.getId());
            costoBridge.load(costo, costosVB);
            Collections.sort(costosVB);
            servicioVB.setCostos(costosVB);
            //se consulta la bitacora
            List<Evento> bitacora = bitacoraDAO.cargar(servicio.getId());
            bitacoraBridge.load(bitacora, servicioVB.getBitacora());
            //inventario de daños
            List<DamageDetail> damage = inventarioDamageDAO.cargar(idServicio);
            inventarioDamageBridge.load(damage, servicioVB.getDatosAuto().getDamage());
            //datos del auto
            Auto auto = autoDAO.cargar(servicio.getIdAuto());
            autoBridge.load(auto, servicioVB.getAuto());
            //datos del cliente
            Cliente cliente = clienteDAO.cargar(servicio.getIdCliente());
            clienteBridge.load(cliente, servicioVB.getCliente());
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public void guardaServicio() {
        try {
            if (StringUtils.isEmpty(servicioVB.getId())) {
                throw new IllegalArgumentException();
            }
            if (!validacionGeneralCliente.isValido()) {
                mensajesControl.reportaError("Los datos del cliente contienen errores.");
                throw new IllegalArgumentException();
            }
            if (!validacionGeneralDatosAuto.isValido()) {
                mensajesControl.reportaError("Los datos del auto contienen errores.");
                throw new IllegalArgumentException();
            }
            Servicio servicio = new Servicio();
            servicioBridge.unload(servicioVB, servicio);
            
            //TODO implementar una version que si sirva del editor monitor para ver que esta editado
            //y guardar unicamente lo que se haya editado
            //se guardan los datos del cliente.
            Cliente cliente = new Cliente();
            clienteBridge.unload(servicioVB.getCliente(), cliente);
            clienteDAO.guardar(cliente);
            //se vuelve a cargar el cliente
            clienteBridge.load(cliente, servicioVB.getCliente());
            //se actualiza el id del cliente en el servicio
            servicio.setIdCliente(cliente.getId());
            //se guardan los datos del auto,
            Auto auto = new Auto();
            autoBridge.unload(servicioVB.getAuto(), auto);
            autoDAO.guardar(auto);
            //se vuelve a cargar el auto
            autoBridge.load(auto, servicioVB.getAuto());
            //se actualiza el id del auto en el servicio
            servicio.setIdAuto(auto.getNumeroSerie());
            //se guarda el servicio
            servicioDAO.guardar(servicio);
            
            //en teoria solo habria que guardar los que se editaron
            //pero en la opcion de guardar individual
            List<Evento> bitacora = new LinkedList<>();
            bitacoraBridge.unload(servicioVB.getBitacora(), bitacora);
            //el servicio regresa los registros de la bitacora con id
            bitacora = bitacoraDAO.guardar(servicio.getId(), bitacora);
            //se vuelven a cargar los eventos pero ahora con id.
            bitacoraBridge.load(bitacora, servicioVB.getBitacora());
            //los costos
            List<RegistroCosto> costo = new LinkedList<>();
            costoBridge.unload(servicioVB.getCostos(), costo);
            //el servicio regresa los registros de costo con id
            costo = costoDAO.guardar(servicio.getId(), costo);
            //se cargan los costos de nuevo
            List<RegistroCostoVB> costoVB = new LinkedList<>();
            costoBridge.load(costo, costoVB);
            Collections.sort(costoVB);
            servicioVB.setCostos(costoVB);
            // inventario de daños
            List<DamageDetail> damage = new LinkedList<>();
            inventarioDamageBridge.unload(servicioVB.getDatosAuto().getDamage(), damage);
            damage = inventarioDamageDAO.guardar(servicio.getId(), damage);
            //se vuelven a cargar pero ahora con id.
            inventarioDamageBridge.load(damage, servicioVB.getDatosAuto().getDamage());
            //se carga de nuevo el servicio para tener el metadata
            servicio = servicioDAO.cargar(servicio.getId());
            servicioBridge.load(servicio, servicioVB);
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al guardar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public void loadCliente(Cliente origen) {
        try {
            clienteBridge.load(origen, servicioVB.getCliente());
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar un cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public List<Cliente> getClientes() {
        try {
            return clienteDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar todos los cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    @Override
    public void buscarCliente(String name, final Callback cmd) {
        try {
            clienteDAO.buscar(name, cmd);
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al buscar uncliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
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
    public void loadAuto(Auto origen) {
        try {
            autoBridge.load(origen, servicioVB.getAuto());
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    
    @Override
    public List<Auto> getAutos() {
        try {
            return autoDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar todos los autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    @Override
    public void buscarAuto(String numeroSerie, Callback<List<Auto>> cmd) {
        try {
            autoDAO.buscar(numeroSerie, cmd);
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al buscar un auto por numero de serie" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
}
