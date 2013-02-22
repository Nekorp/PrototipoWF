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

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.modelo.bitacora.Evento;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.costo.RegistroCosto;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EditorMonitor;
import org.nekorp.workflow.desktop.servicio.GeneradorReporte;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ClienteBridge;
import org.nekorp.workflow.desktop.servicio.bridge.CostoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.view.model.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.ServicioIndex;
import org.nekorp.workflow.desktop.view.model.ServicioVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("application")
public class WorkflowAppPrototipo implements WorkflowApp {

    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Autowired
    private GeneradorReporte generadorReporte;
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
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
    private EdicionServicioMetadata metadataServicio;
    @Autowired
    private EditorMonitor editorMonitor;

    @Override
    public void startApliacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    @Override
    public List<ServicioIndex> getIndexServicios() {
        return this.servicioDAO.getIndiceServicios();
    }

    @Override
    public void cargaServicio(Long idServicio) {
        Servicio servicio = servicioDAO.cargar(idServicio);
        servicioBridge.load(servicio, servicioVB);
        //se consultan los costos
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        List<RegistroCosto> costo = costoDAO.cargar(servicio.getId());
        costoBridge.load(costo, costosVB);
        servicioVB.setCostos(costosVB);
        //se consulta la bitacora
        List<Evento> bitacora = bitacoraDAO.cargar(servicio.getId());
        bitacoraBridge.load(bitacora, servicioVB.getBitacora());
        //datos del auto
        Auto auto = autoDAO.cargar(servicio.getIdAuto());
        autoBridge.load(auto, servicioVB.getDatosAuto());
        //datos del cliente
        Cliente cliente = clienteDAO.cargar(servicio.getIdCliente());
        clienteBridge.load(cliente, servicioVB.getCliente());
        metadataServicio.setServicioCargado(true);
    }

    @Override
    public void guardaServicio() {
        
    }

    @Override
    public void loadCliente(Cliente origen) {
        clienteBridge.load(origen, servicioVB.getCliente());
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteDAO.consultaTodos();
    }

    @Override
    public void buscarCliente(String name, final Callback cmd) {
        clienteDAO.buscar(name, cmd);
    }

    @Override
    public void generaReporte(File destination) {
        this.generadorReporte.generaReporte(destination);
    }
}
