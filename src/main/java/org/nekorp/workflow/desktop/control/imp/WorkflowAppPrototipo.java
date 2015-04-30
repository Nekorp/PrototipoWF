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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.data.access.InventarioDamageDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasUsuario;
import org.nekorp.workflow.desktop.modelo.reporte.ParametrosReporte;
import org.nekorp.workflow.desktop.modelo.reporte.global.ParametrosReporteGlobal;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import technology.tikal.taller.automotriz.model.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.rest.util.RestTemplateFactory;
import org.nekorp.workflow.desktop.servicio.EditorMonitor;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.CostoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.InventarioDamageBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioIndexBridge;
import org.nekorp.workflow.desktop.servicio.bridge.customers.CustomerBridge;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioLoadedListMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralCliente;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralDatosAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;
import technology.tikal.customers.model.ClienteMxPojo;
import technology.tikal.customers.model.Customer;
import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndex;
import technology.tikal.taller.automotriz.model.servicio.auto.damage.DamageDetail;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;
import technology.tikal.taller.automotriz.model.servicio.costo.RegistroCosto;

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
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
    @Autowired
    private ServicioIndexBridge servicioIndexBridge;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CustomerBridge customerBridge;
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
    private EventoServicioFactory eventoFactory;
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
    @Autowired
    private PreferenciasUsuario preferenciasUsuario;
    private boolean cancelarAlertas;
    @Autowired
    private ServicioLoadedListMetadata servicioLoadedListMetadata;
    @Autowired
    private EdicionServicioMetadata edicionMetadata;
    
    @Autowired
    @Qualifier("taller-RestTemplateFactory")
    private RestTemplateFactory factoryAuto;
    @Autowired
    @Qualifier("customer-RestTemplateFactory")
    private RestTemplateFactory factoryCustomer;
    
    @Override
    public void warmupApp() {
        try {
            WorkflowAppPrototipo.LOGGER.debug("Inicializando el cache");
            customerDAO.consultaTodos();
            WorkflowAppPrototipo.LOGGER.debug("Cache inicializado");
        } catch(Exception e) {
            
        }
    }
    
    @Override
    public void startAplicacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
        this.cancelarAlertas = false;
        try {
            if(!cancelarAlertas) {
                List<ServicioIndex> vencidos = servicioDAO.getIndiceServiciosPorStatus("Vencido");
                for (ServicioIndex x: vencidos) {
                    if (cancelarAlertas) break;
                    String msg = "El folio " + x.getId() + " está vencido.";
                    mensajesControl.reportarAlerta(x.getId(), msg);
                }
            }
            if(!cancelarAlertas) {
                List<ServicioIndex> sinCerrar = servicioDAO.getIndiceServiciosPorStatus("SinCerrar");
                for (ServicioIndex x: sinCerrar) {
                    if (cancelarAlertas) break;
                    String msg = "Servicio " + x.getId() + " sin cerrar.";
                    mensajesControl.reportarAlerta(x.getId(), msg);
                }
            }
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al consultar alertas" + e.getMessage());
            this.mensajesControl.reportaError("Error al consultar alertas");
        }
    }

    @Override
    public void closeAplicacion() {
        factoryAuto.shutdown();
        factoryCustomer.shutdown();
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
    public List<ServicioIndexVB> getIndexServicios(Long sinceId) {
        try {
            List<ServicioIndexVB> respuesta = new LinkedList<>();
            servicioIndexBridge.load(this.servicioDAO.getIndiceServicios(sinceId), respuesta);
            return respuesta;
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar el indice de los servicios" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }
    
    @Override
    public boolean crearServicio() {
        updateUltimoCargado();
        Servicio nuevo = new Servicio();
        servicioBridge.load(nuevo, servicioVB);
        // costos
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        List<RegistroCosto> costo = new LinkedList<>();
        costoBridge.load(costo, costosVB);
        servicioVB.setCostos(costosVB);
        //bitacora
        List<Evento> bitacora = new LinkedList<>();
        bitacoraBridge.load(bitacora, servicioVB.getBitacora());
        registrarInicioServicio();
        //inventario de daños
        List<DamageDetail> damage = new LinkedList<>();
        inventarioDamageBridge.load(damage, servicioVB.getDatosAuto().getDamage());
        //cliente
        Customer cliente = new ClienteMxPojo();
        customerBridge.load(cliente, servicioVB.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicioVB.getAuto());
        //se agrega a la lista de cargados
        ServicioLoaded servicioLoaded = new ServicioLoaded();
        servicioLoaded.setAuto(auto);
        servicioLoaded.setBitacora(bitacora);
        servicioLoaded.setCliente(cliente);
        servicioLoaded.setCosto(costo);
        servicioLoaded.setDamage(damage);
        servicioLoaded.setServicio(nuevo);
        servicioLoaded.getPreferenciasEdicion().setCurrentDamageTab("izquierda");
        servicioLoaded.getPreferenciasEdicion().setCurrentTab("cliente");
        List<ServicioLoaded> nuevos = servicioLoadedListMetadata.getServiciosNuevos();
        nuevos.add(servicioLoaded);
        servicioLoadedListMetadata.setServiciosNuevos(nuevos);
        edicionMetadata.setServicioActual(servicioLoaded);
        return true;
    }
    
    private void registrarInicioServicio() {
        EventoSistemaVB eventoDeCreacion = eventoFactory.creaEvento(EventoSistemaVB.class);
        eventoDeCreacion.setNombre("Inicio del Servicio");
        List<EventoVB> eventosVB = servicioVB.getBitacora().getEventos();
        eventosVB.add(eventoDeCreacion);
        servicioVB.getBitacora().setEventos(eventosVB);
    }

    @Override
    public boolean cambiarServicio(ServicioLoaded servicio) {
        if (servicio != edicionMetadata.getServicioActual()) {
            updateUltimoCargado();
            edicionMetadata.setServicioActual(servicio);
            loadAll(servicio);
            return true;
        }
        return false;
    }
    
    private void updateUltimoCargado() {
        if (edicionMetadata.getServicioActual() != null) {
            ServicioLoaded actual = unloadAll();
            List<ServicioLoaded> servicios;
            if(edicionMetadata.getServicioActual().isNuevo()) {
                servicios = servicioLoadedListMetadata.getServiciosNuevos();
            } else {
                servicios = servicioLoadedListMetadata.getServicios();
            }
            int index = servicios.indexOf(edicionMetadata.getServicioActual());
            servicios.add(index, actual);
            servicios.remove(edicionMetadata.getServicioActual());
            if(edicionMetadata.getServicioActual().isNuevo()) {
                servicioLoadedListMetadata.setServiciosNuevos(servicios);
            } else {
                servicioLoadedListMetadata.setServicios(servicios);
            }
        }
    }
    
    private ServicioLoaded unloadAll() {
        Servicio servicio = new Servicio();
        servicioBridge.unload(servicioVB, servicio);
        ClienteMxPojo cliente = new ClienteMxPojo();
        customerBridge.unload(servicioVB.getCliente(), cliente);
        servicio.setIdCliente(cliente.getId());
        Auto auto = new Auto();
        autoBridge.unload(servicioVB.getAuto(), auto);
        servicio.setIdAuto(auto.getNumeroSerie());
        List<Evento> bitacora = new LinkedList<>();
        bitacoraBridge.unload(servicioVB.getBitacora(), bitacora);
        List<RegistroCosto> costo = new LinkedList<>();
        costoBridge.unload(servicioVB.getCostos(), costo);
        // inventario de daños
        List<DamageDetail> damage = new LinkedList<>();
        inventarioDamageBridge.unload(servicioVB.getDatosAuto().getDamage(), damage);
        ServicioLoaded servicioLoaded = edicionMetadata.getServicioActual();
        servicioLoaded.setAuto(auto);
        servicioLoaded.setBitacora(bitacora);
        servicioLoaded.setCliente(cliente);
        servicioLoaded.setCosto(costo);
        servicioLoaded.setDamage(damage);
        servicioLoaded.setServicio(servicio);
        return servicioLoaded;
    }
    
    private void loadAll(ServicioLoaded servicio) {
        servicioBridge.load(servicio.getServicio(), servicioVB);
        // costos
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        costoBridge.load(servicio.getCosto(), costosVB);
        servicioVB.setCostos(costosVB);
        //bitacora
        bitacoraBridge.load(servicio.getBitacora(), servicioVB.getBitacora());
        //inventario de daños
        inventarioDamageBridge.load(servicio.getDamage(), servicioVB.getDatosAuto().getDamage());
        //cliente
        customerBridge.load(servicio.getCliente(), servicioVB.getCliente());
        //auto
        autoBridge.load(servicio.getAuto(), servicioVB.getAuto());
    }
    
    
    @Override
    public void cerrarServicio() {
        if(edicionMetadata.getServicioActual() == null) {
            return;
        }
        if (edicionMetadata.getServicioActual().isNuevo()) {
            List<ServicioLoaded> nuevos = servicioLoadedListMetadata.getServiciosNuevos();
            nuevos.remove(edicionMetadata.getServicioActual());
            servicioLoadedListMetadata.setServiciosNuevos(nuevos);
        } else {
            List<ServicioLoaded> servicios = servicioLoadedListMetadata.getServicios();
            servicios.remove(edicionMetadata.getServicioActual());
            servicioLoadedListMetadata.setServicios(servicios);
        }
        Servicio nuevo = new Servicio();
        servicioBridge.load(nuevo, servicioVB);
        // costos
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        List<RegistroCosto> costo = new LinkedList<>();
        costoBridge.load(costo, costosVB);
        servicioVB.setCostos(costosVB);
        //bitacora
        List<Evento> bitacora = new LinkedList<>();
        bitacoraBridge.load(bitacora, servicioVB.getBitacora());
        //inventario de daños
        List<DamageDetail> damage = new LinkedList<>();
        inventarioDamageBridge.load(damage, servicioVB.getDatosAuto().getDamage());
        //cliente
        Customer cliente = new ClienteMxPojo();
        customerBridge.load(cliente, servicioVB.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicioVB.getAuto());
        edicionMetadata.setServicioActual(null);
    }

    @Override
    public boolean cargaServicio(Long idServicio) {
        List<ServicioLoaded> serviciosActuales = servicioLoadedListMetadata.getServicios();
        for (ServicioLoaded x: serviciosActuales) {
            if (Objects.equals(x.getServicio().getId(), idServicio))  {
                this.mensajesControl.reportaError("El servicio["+idServicio+"] ya esta cargado" );
                return false;
            }
        }
        try {
            updateUltimoCargado();
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
            Customer cliente = customerDAO.cargar(servicio.getIdCliente());
            customerBridge.load(cliente, servicioVB.getCliente());
            // se agrega a la lista de cargados
            ServicioLoaded servicioLoaded = new ServicioLoaded();
            servicioLoaded.setAuto(auto);
            servicioLoaded.setBitacora(bitacora);
            servicioLoaded.setCliente(cliente);
            servicioLoaded.setCosto(costo);
            servicioLoaded.setDamage(damage);
            servicioLoaded.setServicio(servicio);
            servicioLoaded.getPreferenciasEdicion().setCurrentDamageTab("izquierda");
            servicioLoaded.getPreferenciasEdicion().setCurrentTab("presupuesto");
            List<ServicioLoaded> servicios = servicioLoadedListMetadata.getServicios();
            servicios.add(servicioLoaded);
            servicioLoadedListMetadata.setServicios(servicios);
            edicionMetadata.setServicioActual(servicioLoaded);
            return true;
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return false;
        }
    }

    @Override
    public void guardaServicio() {
        try {
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
            ClienteMxPojo cliente = new ClienteMxPojo();
            customerBridge.unload(servicioVB.getCliente(), cliente);
            customerDAO.guardar(cliente);
            //se vuelve a cargar el cliente
            customerBridge.load(cliente, servicioVB.getCliente());
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
            boolean eraNuevo = edicionMetadata.getServicioActual().isNuevo();
            ServicioLoaded servicioLoaded = edicionMetadata.getServicioActual();
            servicioLoaded.setAuto(auto);
            servicioLoaded.setBitacora(bitacora);
            servicioLoaded.setCliente(cliente);
            servicioLoaded.setCosto(costo);
            servicioLoaded.setDamage(damage);
            servicioLoaded.setServicio(servicio);
            if(eraNuevo) {
                List<ServicioLoaded> nuevos = servicioLoadedListMetadata.getServiciosNuevos();
                nuevos.remove(edicionMetadata.getServicioActual());
                servicioLoadedListMetadata.setServiciosNuevos(nuevos);
                List<ServicioLoaded> servicios = servicioLoadedListMetadata.getServicios();
                servicios.add(servicioLoaded);
                servicioLoadedListMetadata.setServicios(servicios);
            } else {
                List<ServicioLoaded> servicios = servicioLoadedListMetadata.getServicios();
                int index = servicios.indexOf(edicionMetadata.getServicioActual());
                servicios.add(index, servicioLoaded);
                servicios.remove(edicionMetadata.getServicioActual());
                servicioLoadedListMetadata.setServicios(servicios);
            }
            edicionMetadata.setServicioActual(servicioLoaded);
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al guardar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    
    @Override
    public void loadCliente(Customer origen) {
        try {
            if (origen.getId() != null) {
                Customer customer = customerDAO.cargar(origen.getId());
                customerBridge.load(customer, servicioVB.getCliente());
            } else {
                customerBridge.load(origen, servicioVB.getCliente());
            }
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar un cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public Customer[] getClientes() {
        try {
            return customerDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            WorkflowAppPrototipo.LOGGER.error("error al cargar todos los cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new Customer[0];
        }
    }

    @Override
    public void buscarCliente(String name, final Callback cmd) {
        try {
            customerDAO.buscar(name, cmd);
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

    @Override
    public void cancelarAlertas() {
        this.cancelarAlertas = true;
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
