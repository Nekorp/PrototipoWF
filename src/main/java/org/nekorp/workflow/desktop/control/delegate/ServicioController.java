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
package org.nekorp.workflow.desktop.control.delegate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.data.access.InventarioDamageDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.CostoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.InventarioDamageBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.servicio.bridge.customers.CustomerBridge;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexMetadata;
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
import technology.tikal.taller.automotriz.model.servicio.Servicio;
import technology.tikal.taller.automotriz.model.servicio.auto.damage.DamageDetail;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;
import technology.tikal.taller.automotriz.model.servicio.costo.RegistroCosto;

/**
 *
 * @author Nekorp
 */
@Controller
public class ServicioController {
    private static final Logger LOGGER = Logger.getLogger(ServicioController.class);
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
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
    private MensajesControl mensajesControl;
    @Autowired
    @Qualifier("validacionGeneralCliente")
    private ValidacionGeneralCliente validacionGeneralCliente;
    @Autowired
    @Qualifier("validacionGeneralDatosAuto")
    private ValidacionGeneralDatosAuto validacionGeneralDatosAuto;
    @Autowired
    private ServicioLoadedListMetadata servicioLoadedListMetadata;
    @Autowired
    private EdicionServicioMetadata edicionMetadata;
    @Autowired
    private ServicioIndexMetadata servicioIndexMetadata;
    @Autowired
    private ServicioIndexController controlServicioIndex;
    
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

    
    public boolean cargaServicio(Long idServicio) {
        List<ServicioLoaded> serviciosActuales = servicioLoadedListMetadata.getServicios();
        for (ServicioLoaded x: serviciosActuales) {
            if (Objects.equals(x.getServicio().getId(), idServicio))  {
                return false;
            }
        }
        ServicioLoaded currentLoad = parallelLoad(idServicio);
        if (currentLoad == null) {
            return false;
        }
        //si todo cargo bien
        updateUltimoCargado();
        servicioBridge.load(currentLoad.getServicio(), servicioVB);
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        costoBridge.load(currentLoad.getCosto(), costosVB);
        servicioVB.setCostos(costosVB);
        bitacoraBridge.load(currentLoad.getBitacora(), servicioVB.getBitacora());
        inventarioDamageBridge.load(currentLoad.getDamage(), servicioVB.getDatosAuto().getDamage());
        autoBridge.load(currentLoad.getAuto(), servicioVB.getAuto());
        customerBridge.load(currentLoad.getCliente(), servicioVB.getCliente());
        // se agrega a la lista de cargados
        ServicioLoaded servicioLoaded = new ServicioLoaded();
        servicioLoaded.setAuto(currentLoad.getAuto());
        servicioLoaded.setBitacora(currentLoad.getBitacora());
        servicioLoaded.setCliente(currentLoad.getCliente());
        servicioLoaded.setCosto(currentLoad.getCosto());
        servicioLoaded.setDamage(currentLoad.getDamage());
        servicioLoaded.setServicio(currentLoad.getServicio());
        servicioLoaded.getPreferenciasEdicion().setCurrentDamageTab("izquierda");
        servicioLoaded.getPreferenciasEdicion().setCurrentTab("presupuesto");
        List<ServicioLoaded> servicios = servicioLoadedListMetadata.getServicios();
        servicios.add(servicioLoaded);
        servicioLoadedListMetadata.setServicios(servicios);
        edicionMetadata.setServicioActual(servicioLoaded);
        return true;
    }
    
    private ServicioLoaded parallelLoad(final Long idServicio) {
        try {
            final ServicioLoaded currentLoad = new ServicioLoaded();
            Thread cargarServicio = new Thread(new Runnable() {
                @Override
                public void run() {
                    //se consulta el servicio
                    Servicio servicio = servicioDAO.cargar(idServicio);
                    currentLoad.setServicio(servicio);
                }
            });
            Thread cargarCosto = new Thread(new Runnable() {
                @Override
                public void run() {
                    //se consultan los costos
                    List<RegistroCosto> costo = costoDAO.cargar(idServicio);
                    currentLoad.setCosto(costo);
                }
            });
            Thread cargarBitacora = new Thread(new Runnable() {
                @Override
                public void run() {
                    //se consulta la bitacora
                    List<Evento> bitacora = bitacoraDAO.cargar(idServicio);
                    currentLoad.setBitacora(bitacora);
                }
            });
            Thread cargarDamage = new Thread(new Runnable() {
                @Override
                public void run() {
                    //inventario de daños
                    List<DamageDetail> damage = inventarioDamageDAO.cargar(idServicio);
                    currentLoad.setDamage(damage);
                }
            });
            cargarServicio.start();
            cargarCosto.start();
            cargarBitacora.start();
            cargarDamage.start();
            //espera que termine de cargar el servicio
            cargarServicio.join();
            Thread cargarAuto = new Thread(new Runnable() {
                @Override
                public void run() {
                    //datos del auto
                    Auto auto = autoDAO.cargar(currentLoad.getServicio().getIdAuto());
                    currentLoad.setAuto(auto);
                }
            });
            Thread cargarCliente = new Thread(new Runnable() {
                @Override
                public void run() {
                    //datos del cliente
                    Customer cliente = customerDAO.cargar(currentLoad.getServicio().getIdCliente());
                    currentLoad.setCliente(cliente);
                }
            });
            cargarAuto.start();
            cargarCliente.start();
            //espera a que todo termine
            cargarCosto.join();
            cargarBitacora.join();
            cargarDamage.join();
            cargarAuto.join();
            cargarCliente.join();
            return currentLoad;
        } catch (InterruptedException ex) {
            ServicioController.LOGGER.error("error con el manejo de hilos" + ex.getMessage());
            return null;
        } catch(ResourceAccessException e) {
            ServicioController.LOGGER.error("error al cargar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return null;
        }
    }

    
    public boolean guardaServicio() {
        if (!validacionGeneralCliente.isValido()) {
            mensajesControl.reportaError("Los datos del cliente contienen errores.");
            return false;
        }
        if (!validacionGeneralDatosAuto.isValido()) {
            mensajesControl.reportaError("Los datos del auto contienen errores.");
            return false;
        }
        ServicioLoaded nuevoServicio = this.parallelSave();
        if (nuevoServicio == null) {
            return false;
        }
        boolean eraNuevo = edicionMetadata.getServicioActual().isNuevo();
        ServicioLoaded servicioLoaded = edicionMetadata.getServicioActual();
        servicioLoaded.setAuto(nuevoServicio.getAuto());
        servicioLoaded.setBitacora(nuevoServicio.getBitacora());
        servicioLoaded.setCliente(nuevoServicio.getCliente());
        servicioLoaded.setCosto(nuevoServicio.getCosto());
        servicioLoaded.setDamage(nuevoServicio.getDamage());
        servicioLoaded.setServicio(nuevoServicio.getServicio());
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
        return true;
    }
    
    private ServicioLoaded parallelSave() {
        try {
            final ServicioLoaded nuevoServicio = new ServicioLoaded();
            Servicio servicio = new Servicio();
            servicioBridge.unload(servicioVB, servicio);
            nuevoServicio.setServicio(servicio);
            //TODO implementar una version que si sirva del editor monitor para ver que esta editado
            //y guardar unicamente lo que se haya editado
            //se guardan los datos del cliente.
            ClienteMxPojo cliente = new ClienteMxPojo();
            customerBridge.unload(servicioVB.getCliente(), cliente);
            nuevoServicio.setCliente(cliente);
            //se guardan los datos del auto,
            Auto auto = new Auto();
            autoBridge.unload(servicioVB.getAuto(), auto);
            nuevoServicio.setAuto(auto);
            Thread guardarCliente = new Thread(new Runnable() {
                @Override
                public void run() {
                    customerDAO.guardar(nuevoServicio.getCliente());
                    //se vuelve a cargar el cliente
                    customerBridge.load(nuevoServicio.getCliente(), servicioVB.getCliente());
                }
            });
            Thread guardarAuto = new Thread(new Runnable() {
                @Override
                public void run() {
                    autoDAO.guardar(nuevoServicio.getAuto());
                    //se vuelve a cargar el auto
                    autoBridge.load(nuevoServicio.getAuto(), servicioVB.getAuto());
                }
            });
            guardarCliente.start();
            guardarAuto.start();
            
            guardarCliente.join();
            guardarAuto.join();
            //se refrescan los indices
            Thread refreshAutoIndex = new Thread(new Runnable() {
                @Override
                public void run() {
                    autoDAO.getIndiceAutos();
                }
            });
            Thread refreshClienteIndex = new Thread(new Runnable() {
                @Override
                public void run() {
                    customerDAO.consultaTodos();
                }
            });
            refreshAutoIndex.start();
            refreshClienteIndex.start();
            //se actualiza el id del cliente en el servicio
            servicio.setIdCliente(cliente.getId());
            //se actualiza el id del auto en el servicio
            servicio.setIdAuto(auto.getNumeroSerie());
            //se guarda el servicio para obtener el id
            servicioDAO.guardar(servicio);
            //en teoria solo habria que guardar los que se editaron en la opcion de guardar individual
            List<Evento> bitacora = new LinkedList<>();
            bitacoraBridge.unload(servicioVB.getBitacora(), bitacora);
            nuevoServicio.setBitacora(bitacora);
            //los costos
            List<RegistroCosto> costo = new LinkedList<>();
            costoBridge.unload(servicioVB.getCostos(), costo);
            nuevoServicio.setCosto(costo);
            // inventario de daños
            List<DamageDetail> damage = new LinkedList<>();
            inventarioDamageBridge.unload(servicioVB.getDatosAuto().getDamage(), damage);
            nuevoServicio.setDamage(damage);
            Thread guardarBitacora = new Thread(new Runnable() {
                @Override
                public void run() {
                    //el servicio regresa los registros de la bitacora con id
                    nuevoServicio.setBitacora(bitacoraDAO.guardar(nuevoServicio.getServicio().getId(), nuevoServicio.getBitacora()));
                    //se vuelven a cargar los eventos pero ahora con id.
                    bitacoraBridge.load(nuevoServicio.getBitacora(), servicioVB.getBitacora());
                }
            });
            Thread guardarCosto = new Thread(new Runnable() {
                @Override
                public void run() {
                    //el servicio regresa los registros de costo con id
                    nuevoServicio.setCosto(costoDAO.guardar(nuevoServicio.getServicio().getId(), nuevoServicio.getCosto()));
                }
            });
            Thread guardarDamage = new Thread(new Runnable() {
                @Override
                public void run() {
                    nuevoServicio.setDamage(inventarioDamageDAO.guardar(nuevoServicio.getServicio().getId(), nuevoServicio.getDamage()));
                    //se vuelven a cargar pero ahora con id.
                    inventarioDamageBridge.load(nuevoServicio.getDamage(), servicioVB.getDatosAuto().getDamage());
                }
            });
            guardarBitacora.start();
            guardarCosto.start();
            guardarDamage.start();
            
            guardarBitacora.join();
            guardarCosto.join();
            guardarDamage.join();
            Thread cargarServicio = new Thread(new Runnable() {
                @Override
                public void run() {
                    //se carga de nuevo el servicio para tener el metadata
                    nuevoServicio.setServicio(servicioDAO.cargar(nuevoServicio.getServicio().getId()));
                    servicioBridge.load(nuevoServicio.getServicio(), servicioVB);
                    //se cargan los costos de nuevo
                    //se cambio aqui por que se crean nuevas instancias de los grupos de los costos cada vez que se carga el servicio
                    List<RegistroCostoVB> costoVB = new LinkedList<>();
                    costoBridge.load(nuevoServicio.getCosto(), costoVB);
                    servicioVB.setCostos(costoVB);
                }
            });
            cargarServicio.start();
            cargarServicio.join();
            Thread refreshServicioIndex = new Thread(new Runnable() {
                @Override
                public void run() {
                    servicioIndexMetadata.setIndex(controlServicioIndex.getIndexServicios());
                }
            });
            refreshServicioIndex.start();
            //esperar el refresh de los indices o no...
            refreshAutoIndex.join();
            refreshClienteIndex.join();
            refreshServicioIndex.join();
            return nuevoServicio;
        } catch(ResourceAccessException e) {
            ServicioController.LOGGER.error("error al guardar un servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return null;
        } catch (InterruptedException ex) {
            ServicioController.LOGGER.error("error al guardar un servicio" + ex.getMessage());
            return null;
        }
    }
}
