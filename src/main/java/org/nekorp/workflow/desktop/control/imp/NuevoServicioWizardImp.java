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

package org.nekorp.workflow.desktop.control.imp;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.control.NuevoServicioWizard;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.servicio.bridge.customers.CustomerBridge;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import technology.tikal.customers.model.ClienteMxPojo;
import technology.tikal.customers.model.Customer;
import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;

/**
 * @author Nekorp
 */
@Service("nuevoServicioWizard")
public class NuevoServicioWizardImp implements NuevoServicioWizard {
    private static final Logger LOGGER = Logger.getLogger(NuevoServicioWizardImp.class);
    
    @Autowired
    private WorkflowApp worflowApp;
    @Autowired
    @Qualifier(value="w-servicio")
    private ServicioVB servicio;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
    @Autowired
    private CustomerDAO clienteDAO;
    @Autowired
    private CustomerBridge clienteBridge;
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private AutoBridge autoBridge;
    @Autowired
    private BitacoraDAO bitacoraDAO;
    @Autowired
    private BitacoraBridge bitacoraBridge;
    @Autowired
    private EventoServicioFactory eventoFactory;
    @Autowired
    private MensajesControl mensajesControl;
    
    @Override
    public void loadCliente(Customer origen) {
        try {
            if (origen.getId() != null) {
                Customer customer = clienteDAO.cargar(origen.getId());
                clienteBridge.load(customer, servicio.getCliente());
            } else {
                clienteBridge.load(origen, servicio.getCliente());
            }
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al cargar clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public Customer[] getClientes() {
        try {
            return clienteDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al cargar todos los clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new Customer[0];
        }
    }

    @Override
    public void buscarCliente(final String name, final Callback<Customer[]> cmd) {
        try {
            clienteDAO.buscar(name, cmd);
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al buscar un cliente por nombre" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public void inicia() {
        //Se inician todos los datos en blanco
        //datos del servicio
        Servicio nuevo = new Servicio();
        servicioBridge.load(nuevo, servicio);
        List<Evento> bitacora = new LinkedList<>();
        bitacoraBridge.load(bitacora, servicio.getBitacora());
        //los datos de costo no se editan en el wizard
        //cliente
        Customer cliente = new ClienteMxPojo();
        clienteBridge.load(cliente, servicio.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicio.getAuto());
    }
    
    @Override
    public void nuevoServicio() {
        try {
            //como es parte del wizard se asume que el servicio es nuevo.
            Servicio nuevoServicio = new Servicio();
            servicioBridge.unload(servicio, nuevoServicio);
            //primero se tratan de guardar los datos del cliente y auto.
            ClienteMxPojo nuevoCliente = new ClienteMxPojo();
            clienteBridge.unload(servicio.getCliente(), nuevoCliente);
            clienteDAO.guardar(nuevoCliente);
            //el cliente nuevo o no, al terminar ya debe tener id
            nuevoServicio.setIdCliente(nuevoCliente.getId());
            //datos del auto
            Auto nuevoAuto = new Auto();
            autoBridge.unload(servicio.getAuto(), nuevoAuto);
            autoDAO.guardar(nuevoAuto);
            nuevoServicio.setIdAuto(nuevoAuto.getNumeroSerie());
            //se guarda el nuevo servicio
            servicioDAO.guardar(nuevoServicio);
            registrarInicioServicio();
            //la bitacora del servicio esta vacia por que es nuevo.
            List<Evento> bitacora = new LinkedList<>();
            //se copian los datos de la bitacora
            bitacoraBridge.unload(servicio.getBitacora(), bitacora);
            bitacoraDAO.guardar(nuevoServicio.getId(), bitacora);
            worflowApp.cargaServicio(nuevoServicio.getId());
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al guardar el nuevo servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    /**
     * genera una nueva entrada en la bitacora indicando el inicio del servicio.
     */
    private void registrarInicioServicio() {
        EventoSistemaVB eventoDeCreacion = eventoFactory.creaEvento(EventoSistemaVB.class);
        eventoDeCreacion.setNombre("Inicio del Servicio");
        List<EventoVB> eventosVB = servicio.getBitacora().getEventos();
        eventosVB.add(eventoDeCreacion);
        servicio.getBitacora().setEventos(eventosVB);
    }

    @Override
    public void loadAuto(Auto origen) {
        try {
            autoBridge.load(origen, servicio.getAuto());
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al cargar autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    
    @Override
    public List<Auto> getAutos() {
        try {
            return autoDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al cargar todos los autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    @Override
    public void buscarAuto(String numeroSerie, Callback<List<Auto>> cmd) {
        try {
            autoDAO.buscar(numeroSerie, cmd);
        } catch(ResourceAccessException e) {
            NuevoServicioWizardImp.LOGGER.error("error al buscar un auto por numero de serie" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
}
