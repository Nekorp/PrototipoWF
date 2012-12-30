package prototipo.control.imp;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.servicio.EventoServicioFactory;
import prototipo.servicio.imp.ModelControl;

@Controller
public class WorkflowAppPrototipo implements WorkflowApp{
    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private Servicio viewServicioModel;
    @Autowired
    private EventoServicioFactory eventoFactory;
    
    private boolean dataLoaded = false;
    
    //private ApplicationContext ctx;

    @Override
    public void startApliacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    @Override
    public void nuevoServicio() {
        WorkflowAppPrototipo.LOGGER.debug("Hacer un nuevo servicio");
        String folio = modelControl.getFolioServicio();
        Servicio nuevo = new Servicio();
        nuevo.setId(folio);
        copiarPropiedades(nuevo, viewServicioModel, true);
        dataLoaded = true;
    }
    
    @Override
    public void guardaServicio() {
        if (dataLoaded) {
            Servicio datos = new Servicio();
            copiarPropiedades(viewServicioModel, datos, false);
            modelControl.guardaServicio(datos);
        }
    }

    @Override
    public List<ServicioIndex> getIndexServicios() {
        return this.modelControl.getIndiceServicios();
    }

    @Override
    public void cargaServicio(ServicioIndex index) {
        Servicio cargado = this.modelControl.cargaServicio(index);
        this.copiarPropiedades(cargado, this.viewServicioModel, true);
        dataLoaded = true;
    }
    
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    private void copiarPropiedades(Servicio origen, Servicio destino, boolean proxy) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "bitacora",
                "datosAuto",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres"
        });
        List<Evento> eventosOrigen = origen.getBitacora().getEventos();
        LinkedList<Evento> eventosDestino = new LinkedList<>();
        for (Evento x: eventosOrigen) {
            if (x instanceof EventoGeneral) {
                EventoGeneral nuevo;
                if (proxy) {
                    nuevo = eventoFactory.creaEvento(EventoGeneral.class);
                } else {
                    nuevo = new EventoGeneral();
                }
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
            if (x instanceof EventoEntrega) {
                EventoEntrega nuevo;
                if (proxy) {
                    nuevo = eventoFactory.creaEvento(EventoEntrega.class);
                } else {
                    nuevo = new EventoEntrega();
                }
                BeanUtils.copyProperties(x, nuevo);
                eventosDestino.add(nuevo);
            }
        }
        destino.getBitacora().setEventos(eventosDestino);
        BeanUtils.copyProperties(origen.getDatosAuto(), destino.getDatosAuto(), 
            new String[]{
                "equipamiento"
        });
        BeanUtils.copyProperties(origen.getDatosAuto().getEquipamiento(), destino.getDatosAuto().getEquipamiento(), 
            new String[]{
                "transmision",
                "elevadores"
        });
        destino.getDatosAuto().getEquipamiento().setTransmision(origen.getDatosAuto().getEquipamiento().getTransmision());
        destino.getDatosAuto().getEquipamiento().setElevadores(origen.getDatosAuto().getEquipamiento().getElevadores());
        
        BeanUtils.copyProperties(origen.getTelefonoUno(), destino.getTelefonoUno());
        BeanUtils.copyProperties(origen.getTelefonoDos(), destino.getTelefonoDos());
        BeanUtils.copyProperties(origen.getTelefonoTres(), destino.getTelefonoTres());
    }

//    @Override
//    public void addEventoEntrega(String nombre) {
//        List<Evento> eventos = this.viewServicioModel.getBitacora().getEventos();
//        EventoEntrega nuevo = this.creaEventoEntrega();
//        nuevo.setNombreEvento(nombre);
//        eventos.add(nuevo);
//        this.viewServicioModel.getBitacora().setEventos(eventos);
//    }
//
//    @Override
//    public void addEventoGeneral() {
//        List<Evento> eventos = this.viewServicioModel.getBitacora().getEventos();
//        EventoGeneral nuevo = this.creaEventoGeneral();
//        eventos.add(nuevo);
//        this.viewServicioModel.getBitacora().setEventos(eventos);
//    }
}
