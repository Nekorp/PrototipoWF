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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.control.ProgramacionServicioWizard;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.BitacoraDAO;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.alerta.AlertaServicio;
import org.nekorp.workflow.desktop.modelo.alerta.AlertaVerificacion;
import org.nekorp.workflow.desktop.modelo.alerta.RangoAlertaVerificacion;
import technology.tikal.taller.automotriz.model.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.ServicioAlertaEmail;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.BitacoraBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.servicio.bridge.customers.CustomerBridge;
import org.nekorp.workflow.desktop.servicio.imp.RangoNumero;
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
import technology.tikal.customers.model.ClienteMxPojo;
import technology.tikal.customers.model.Customer;
import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evento;

/**
 * @author Nekorp
 */
@Service("programacionServicioWizard")
public class ProgramacionServicioWizardImp implements ProgramacionServicioWizard {
    private static final Logger LOGGER = Logger.getLogger(ProgramacionServicioWizardImp.class);
    
    @Autowired
    private WorkflowApp worflowApp;
    @Autowired
    @Qualifier("servicioAlertaEmail")
    private ServicioAlertaEmail<AlertaServicio> servicioAlertaEmail;
    @Autowired
    @Qualifier("servicioAlertaVerificacionEmail")
    private ServicioAlertaEmail<AlertaVerificacion> servicioAlertaVerificacionEmail;
    @Autowired
    @Qualifier(value="p-servicio")
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
    
    private Long toleranciaAlerta = 1000l;
    
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
        "placas"
    };
    
    private List<Auto> nuevosAutos;
    private List<Servicio> nuevosServicio;
    
    private List<AlertaServicio> alertas;
    private List<AlertaVerificacion> alertasVerificacion;
    
    private RangoNumero rangoNuevo;
    private RangoNumero rangoAlerta;
    
    private Long ultimoCreado;
    
    public ProgramacionServicioWizardImp() {
        rangoNuevo = new RangoNumero();
        rangoNuevo.setIncluyente(true);
        rangoNuevo.setLimiteInferior(0l);
        rangoAlerta = new RangoNumero();
        rangoAlerta.setIncluyente(true);
        rangoAlerta.setLimiteSuperior(-1l);
        rangoAlerta.setLimiteInferior(toleranciaAlerta * -1);
    }
    
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
            ProgramacionServicioWizardImp.LOGGER.error("error al cargar clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }

    @Override
    public Customer[] getClientes() {
        try {
            return clienteDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            ProgramacionServicioWizardImp.LOGGER.error("error al cargar todos los clientes" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new Customer[0];
        }
    }

    @Override
    public void buscarCliente(final String name, final Callback<Customer[]> cmd) {
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
        nuevosAutos = new LinkedList<>();
        nuevosServicio = new LinkedList<>();
        alertas = new LinkedList<>();
        alertasVerificacion = new LinkedList<>();
        ultimoCreado = null;
        //datos del servicio
        Servicio nuevo = new Servicio();
        servicioBridge.load(nuevo, servicio);
        List<Evento> bitacora = new LinkedList<>();
        bitacoraBridge.load(bitacora, servicio.getBitacora());
        //los datos de costo no se editan en el wizard
        //cliente
        ClienteMxPojo cliente = new ClienteMxPojo();
        clienteBridge.load(cliente, servicio.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicio.getAuto());
        programacionMetadata.setDetalles("");
    }
    
    @Override
    public void crearServicios() {
        for (int i = 0; i < nuevosServicio.size(); i++) {
            //se carga el servicio previamente creado
            Servicio x = nuevosServicio.get(i);
            servicioBridge.load(x, servicio);
            //se carga el auto previamente creado
            Auto y = nuevosAutos.get(i);
            autoBridge.load(y, servicio.getAuto());
            //se borra la bitacora
            List<Evento> bitacora = new LinkedList<>();
            bitacoraBridge.load(bitacora, servicio.getBitacora());
            //se crea el nuevo servicio
            ultimoCreado = nuevoServicio();
        }
    }
    
    @Override
    public void enviarAlertas() {
        servicioAlertaEmail.enviarAlerta(alertas);
        servicioAlertaVerificacionEmail.enviarAlerta(alertasVerificacion);
    }
    
    @Override
    public void finalizar() {
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
        programacionMetadata.setDetalles("");
        OPCPackage pkg = null;
        try {
            addDetail("Leyendo archivo: " + archivo.getCanonicalPath());
            pkg = OPCPackage.open(archivo);
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            //Workbook wb = WorkbookFactory.create(archivo);
            Sheet sheet = wb.getSheetAt(0);
            //Row encabezado = sheet.getRow(0);
            List<String> nombresServicios = null;
            boolean inicio = true;
            nuevosAutos = new LinkedList<>();
            nuevosServicio = new LinkedList<>();
            for (Row row : sheet) {
                if (inicio) {
                    nombresServicios = procesarEncabezado(row);
                    inicio = false;
                } else {
                    if (row.getPhysicalNumberOfCells() == nombresServicios.size()) {
                        Auto autoCargado = new Auto();
                        cargarDatosAuto(row, autoCargado);
                        autoBridge.load(autoCargado, servicio.getAuto());
                        Servicio nuevoServicio = new Servicio();
                        if (buscarNuevoServicio(row, nuevoServicio, nombresServicios, rangoNuevo)) {
                            //se intentan cargar
                            servicioBridge.load(nuevoServicio, servicio);
                            addDetail("Se encontro un nuevo servicio para el auto con numero de serie: " + autoCargado.getNumeroSerie());
                            addDetail("Descripción del servicio encontrado:\n" + nuevoServicio.getDescripcion());
                            if (validacionGeneralDatosAuto.isValido()) {
                                nuevosAutos.add(autoCargado);
                                nuevosServicio.add(nuevoServicio);
                            } else {
                                addDetail("los datos del nuevo servicio tienen los siguientes errores y no se dara de alta:");
                                addDetail(validacionDatosAuto.concatenaErrores());
                            }
                        }
                        List<AlertaServicio> nuevasAlertas = buscarAlertas(row, nombresServicios, rangoAlerta);
                        for (AlertaServicio x: nuevasAlertas) {
                            x.setMarcaAuto(autoCargado.getMarca());
                            x.setPlacasAuto(autoCargado.getPlacas());
                            x.setTipoAuto(autoCargado.getTipo());
                            x.setNombreCliente(servicio.getCliente().getNombre());
                            addDetail("Se encontro un servicio proximo para el auto con numero de serie: " + autoCargado.getNumeroSerie());
                            addDetail("Descripción de la nueva alerta:\n" + x.getDescripcionServicio());
                            alertas.add(x);
                        }
                        AlertaVerificacion nuevaAlerta = buscarAlertasVerificacion(autoCargado.getPlacas());
                        if (nuevaAlerta != null) {
                            addDetail("Se encontro un auto que pudiera necesitar verificación: " + autoCargado.getNumeroSerie());
                            addDetail("placas: " + nuevaAlerta.getPlacas() + " periodo: " + nuevaAlerta.getPeriodo());
                            alertasVerificacion.add(nuevaAlerta);
                        }
                    }
                }
            }
            if (nuevosServicio.size() > 0) {
                if (nuevosServicio.size() == 1) {
                    addDetail("Se tiene listo para crear un nuevo servicio");
                } else {
                    addDetail("Se tienen listos para crear " + nuevosServicio.size() + " servicios nuevos");
                }
            } else {
                addDetail("No se encontro ningun nuevo servicio");
            }
            int cantidadAlertas = alertas.size() + alertasVerificacion.size();
            if (cantidadAlertas > 0) {
                if (cantidadAlertas == 1) {
                    addDetail("Se tiene lista para enviar una nueva alerta");
                } else {
                    addDetail("Se tienen listas para enviar " + cantidadAlertas + " alertas");
                }
            } else {
                addDetail("No se encontro ninguna alerta");
            }
            if (nuevosServicio.size() > 0 || cantidadAlertas > 0) {
                validacionGeneralProgramacion.setValido(true);
            } else {
                validacionGeneralProgramacion.setValido(false);
            }
        } catch (IOException | InvalidFormatException | IllegalArgumentException ex) {
            ProgramacionServicioWizardImp.LOGGER.error("exploto!!!", ex);
            addDetail("ocurrio un error inesperado al leer el archivo." + ex.getMessage());
        } finally {
            if (pkg != null) {
                try {
                    pkg.close();
                } catch (IOException ex) {
                    //ProgramacionServicioWizardImp.LOGGER.error("exploto!!!", ex);
                }
            }
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
    
    private List<String> procesarEncabezado(Row encabezado) {
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
    
    private void cargarDatosAuto(Row row, Auto nuevoAuto) {
        try {
            int index = 0;
            for (Cell x: row) {
                if(index < atributosAuto.length) {
                    BeanUtils.setProperty(nuevoAuto, atributosAuto[index], getStringValue(x));
                }
                index = index + 1;
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ProgramacionServicioWizardImp.LOGGER.error("exploto!!!", ex);
            addDetail("ocurrio un error inesperado al leer el archivo." + ex.getMessage());
        }
    }
    
    private boolean buscarNuevoServicio(Row row, Servicio nuevoServicio, List<String> nombresServicios, RangoNumero rango) {
        int index = 0;
        String descripcion = "";
        Long kilometraje = getLongValue(row.getCell(atributosAuto.length));
        for (Cell x : row) {
            if (index > atributosAuto.length) {
                if (kilometraje != null && index < nombresServicios.size()) {
                    Long value = getLongValue(x);
                    if (rango.dentroDelRango(kilometraje - value)) {
                        if (!StringUtils.isEmpty(descripcion)) {
                            descripcion = descripcion + "\n";
                        }
                        descripcion = descripcion + nombresServicios.get(index);
                    }
                }
            }
            index = index + 1;
        }
        if (!StringUtils.isEmpty(descripcion) && kilometraje != null) {
            nuevoServicio.setDescripcion(descripcion);
            nuevoServicio.getDatosAuto().setKilometraje(kilometraje.toString());
            return true;
        } else {
            return false;
        }
    }
    
    private List<AlertaServicio> buscarAlertas(Row row, List<String> nombresServicios, RangoNumero rango) {
        int index = 0;
        List<AlertaServicio> respuesta = new LinkedList<>();
        AlertaServicio nuevaAlerta;
        Long kilometraje = getLongValue(row.getCell(atributosAuto.length));
        for (Cell x : row) {
            if (index > atributosAuto.length) {
                if (kilometraje != null && index < nombresServicios.size()) {
                    Long value = getLongValue(x);
                    if (rango.dentroDelRango(kilometraje - value)) {
                        nuevaAlerta = new AlertaServicio();
                        nuevaAlerta.setDescripcionServicio(nombresServicios.get(index));
                        nuevaAlerta.setKilometrajeServicio(value.toString());
                        nuevaAlerta.setKilometrajeAuto(kilometraje.toString());
                        respuesta.add(nuevaAlerta);
                    }
                }
            }
            index = index + 1;
        }
        return respuesta;
    }
    
    private AlertaVerificacion buscarAlertasVerificacion(final String placas) {
        String rawValue = StringUtils.trim(placas);
        if (StringUtils.isEmpty(rawValue)) {
            return null;
        }
        String ultimoCaracter = rawValue.charAt(rawValue.length() - 1) + "";
        int numeroFinal;
        try {
            numeroFinal = Integer.parseInt(ultimoCaracter);
        } catch (NumberFormatException e) {
            return null;
        }
        RangoAlertaVerificacion rango = RangoAlertaVerificacion.getRango(numeroFinal);
        if (rango == null) {
            return null;
        }
        if (rango.dentroRango(new DateTime())) {
            AlertaVerificacion nueva = new AlertaVerificacion();
            nueva.setPlacas(placas);
            nueva.setPeriodo(rango.toString());
            return nueva;
        }
        return null;
    }
    
    private String getStringValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
           return cell.getRichStringCellValue().getString();
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Double raw = cell.getNumericCellValue();
            DecimalFormat fm = new DecimalFormat("#.##");
            String r = fm.format(raw);
            return r;
        }
        return null;
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
    
}
