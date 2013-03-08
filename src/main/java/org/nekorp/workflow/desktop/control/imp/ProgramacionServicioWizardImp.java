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

package org.nekorp.workflow.desktop.control.imp;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.control.ProgramacionServicioWizard;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.modelo.bitacora.Evento;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ClienteBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.importar.ProgramacionMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionDatosAuto;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralDatosAuto;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralProgramacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 */
@Service("programacionServicioWizard")
public class ProgramacionServicioWizardImp implements ProgramacionServicioWizard {
    private static final Logger LOGGER = Logger.getLogger(ProgramacionServicioWizardImp.class);
    
    @Autowired
    private WorkflowApp worflowApp;
    @Autowired
    @Qualifier(value="p-servicio")
    private ServicioVB servicio;
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
    private EventoServicioFactory eventoFactory;
    @Autowired
    private MensajesControl mensajesControl;
    @Autowired
    private ValidacionGeneralProgramacion validacionGeneralProgramacion;
    @Autowired
    @Qualifier(value="p-validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAuto;
    @Autowired
    @Qualifier(value="p-validacionGeneralDatosAuto")
    private ValidacionGeneralDatosAuto validacionGeneralDatosAuto;
    @Autowired
    private ProgramacionMetadata programacionMetadata;
    
    private String[] encabezadoEsperado = new String[] {
        "marca",
        "tipo",
        "version",
        "serie",
        "modelo",
        "color",
        "placas",
        "kilometraje"
    };
    private String[] atributosAuto = new String[] {
        "marca",
        "tipo",
        "version",
        "numeroSerie",
        "modelo",
        "color",
        "placas",
        "kilometraje"
    };
    
    private List<Auto> nuevosAutos;
    private List<Servicio> nuevosServicio;
    
    public ProgramacionServicioWizardImp() {
        nuevosAutos = new LinkedList<>();
        nuevosServicio = new LinkedList<>();
    }
    
    @Override
    public void loadCliente(Cliente origen) {
        try {
            clienteBridge.load(origen, servicio.getCliente());
        } catch(ResourceAccessException e) {
            ProgramacionServicioWizardImp.LOGGER.error("error al cargar clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public List<Cliente> getClientes() {
        try {
            return clienteDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            ProgramacionServicioWizardImp.LOGGER.error("error al cargar todos los clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    @Override
    public void buscarCliente(final String name, final Callback<List<Cliente>> cmd) {
        try {
            clienteDAO.buscar(name, cmd);
        } catch(ResourceAccessException e) {
            ProgramacionServicioWizardImp.LOGGER.error("error al buscar un cliente por nombre" + e.getMessage());
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
        Cliente cliente = new Cliente();
        clienteBridge.load(cliente, servicio.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicio.getDatosAuto());
    }
    
    @Override
    public void crearServicios() {
        Long ultimoCreado = null;
        for (int i = 0; i < nuevosServicio.size(); i++) {
            //se carga el servicio previamente creado
            Servicio x = nuevosServicio.get(i);
            servicioBridge.load(x, servicio);
            //se carga el auto previamente creado
            Auto y = nuevosAutos.get(i);
            autoBridge.load(y, servicio.getDatosAuto());
            //se borra la bitacora
            List<Evento> bitacora = new LinkedList<>();
            bitacoraBridge.load(bitacora, servicio.getBitacora());
            //se crea el nuevo servicio
            ultimoCreado = nuevoServicio();
        }
        //carga el ultimo que se creo
        if (ultimoCreado != null) {
            worflowApp.cargaServicio(ultimoCreado);
        }
    }
    
    
    private Long nuevoServicio() {
        try {
            //como es parte del wizard se asume que el servicio es nuevo.
            Servicio nuevoServicio = new Servicio();
            servicioBridge.unload(servicio, nuevoServicio);
            //primero se tratan de guardar los datos del cliente y auto.
            Cliente nuevoCliente = new Cliente();
            clienteBridge.unload(servicio.getCliente(), nuevoCliente);
            clienteDAO.guardar(nuevoCliente);
            //el cliente nuevo o no, al terminar ya debe tener id
            nuevoServicio.setIdCliente(nuevoCliente.getId());
            //datos del auto
            Auto nuevoAuto = new Auto();
            autoBridge.unload(servicio.getDatosAuto(), nuevoAuto);
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
            return nuevoServicio.getId();
        } catch(ResourceAccessException e) {
            ProgramacionServicioWizardImp.LOGGER.error("error al guardar el nuevo servicio" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return null;
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
    public void importarArchivo(File archivo) {
        try {
            addDetail("Leyendo archivo: " + archivo.getCanonicalPath());
            Workbook wb = WorkbookFactory.create(archivo);
            Sheet sheet = wb.getSheetAt(0);
            //Row encabezado = sheet.getRow(0);
            List<String> nombresServicios = null;
            boolean inicio = true;
            nuevosAutos = new LinkedList<>();
            nuevosServicio = new LinkedList<>();
            for (Row row : sheet) {
                if (inicio) {
                    nombresServicios = procesaEncabezado(row);
                    inicio = false;
                } else {
                    Auto nuevoAuto = new Auto();
                    Servicio nuevoServicio = new Servicio();
                    if (procesaRegistro(row, nuevoAuto, nuevoServicio, nombresServicios)) {
                        //se intentan cargar
                        servicioBridge.load(nuevoServicio, servicio);
                        autoBridge.load(nuevoAuto, servicio.getDatosAuto());
                        if (validacionGeneralDatosAuto.isValido()) {
                            nuevosAutos.add(nuevoAuto);
                            nuevosServicio.add(nuevoServicio);
                            addDetail("Se encontro un nuevo servicio para el auto con numero de serie: " + nuevoAuto.getNumeroSerie());
                            addDetail("Detalle del servicio encontrado: " + nuevoServicio.getDescripcion());
                        } else {
                            addDetail("Se encontro un nuevo servicio para el auto con numero de serie: " + nuevoAuto.getNumeroSerie());
                            addDetail("los datos del nuevo servicio tienen los siguientes errores:");
                            addDetail(validacionDatosAuto.concatenaErrores());
                        }
                    }
                }
            }
            if (nuevosServicio.size() > 0) {
                validacionGeneralProgramacion.setValido(true);
                if (nuevosServicio.size() == 1) {
                    addDetail("Se tien listo para crear un nuevo servicio");
                } else {
                    addDetail("Se tienen listos para crear " + nuevosServicio.size() + " servicios nuevos");
                }
            } else {
                validacionGeneralProgramacion.setValido(false);
                addDetail("No se encontro ningun nuevo servicio");
            }
        } catch (IOException | InvalidFormatException | IllegalArgumentException ex) {
            ProgramacionServicioWizardImp.LOGGER.error("exploto!!!", ex);
            addDetail("ocurrio un error inesperado al leer el archivo." + ex.getMessage());
        }
    }
    
    private void addDetail(String arg) {
        String value = programacionMetadata.getDetalles();
        if (!StringUtils.isEmpty(value)) {
            value = value + "\n";
        }
        value = value + arg;
        programacionMetadata.setDetalles(value);
    }
    
    private boolean procesaRegistro(Row row, Auto nuevoAuto, Servicio nuevoServicio, List<String> nombresServicios) {
        int index = 0;
        String descripcion = "";
        Long kilometraje = null;
        for (Cell x : row) {
            if (index == 7) {
                kilometraje = getLongValue(x);
            }
            if (index < atributosAuto.length) {
                actualizaAuto(nuevoAuto, x, index);
            } else {
                if (kilometraje != null && index < nombresServicios.size()) {
                    Long value = getLongValue(x);
                    if (kilometraje >= value) {
                        if (!StringUtils.isEmpty(descripcion)) {
                            descripcion = descripcion + "/n";
                        }
                        descripcion = descripcion + nombresServicios.get(index);
                    }
                }
            }
            index = index + 1;
        }
        if (!StringUtils.isEmpty(descripcion)) {
            nuevoServicio.setDescripcion(descripcion);
            return true;
        } else {
            return false;
        }
    }
    
    private void actualizaAuto(Auto auto, Cell cell, int index) {
        try {
            BeanUtils.setProperty(auto, atributosAuto[index], cell.getRichStringCellValue().getString());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ProgramacionServicioWizardImp.LOGGER.error("exploto!!!", ex);
            addDetail("ocurrio un error inesperado al leer el archivo." + ex.getMessage());
        }
    }
    
    private Long getLongValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
           String raw =  cell.getRichStringCellValue().getString();
           try {
               return Long.parseLong(raw);
           } catch (NumberFormatException e) {
               return null;
           }
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Double raw = cell.getNumericCellValue();
            return raw.longValue();
        }
        return null;
    }
    
    private List<String> procesaEncabezado(Row encabezado) {
        int index = 0;
        List<String> respuesta = new LinkedList<>();
        for (Cell cell: encabezado) {
            if (index < encabezadoEsperado.length) {
                String value = cell.getRichStringCellValue().getString();
                if (!StringUtils.equalsIgnoreCase(value, encabezadoEsperado[index])) {
                    throw new IllegalArgumentException("el encabezado no es valido");
                }
                index = index + 1;
            }
            String content = cell.getRichStringCellValue().getString();
            if (!StringUtils.isEmpty(content)) {
                respuesta.add(content);
            } else {
                respuesta.add("sin valor");
            }
        }
        return respuesta;
    }
}
