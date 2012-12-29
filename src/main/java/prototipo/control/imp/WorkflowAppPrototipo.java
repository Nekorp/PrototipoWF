package prototipo.control.imp;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.control.ModelControl;

@Component
public class WorkflowAppPrototipo implements WorkflowApp {
    private static final Logger LOGGER = Logger
            .getLogger(WorkflowAppPrototipo.class);
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private Servicio viewServicioModel;
    
    private boolean dataLoaded = false;

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
        copiarPropiedades(nuevo, viewServicioModel);
        dataLoaded = true;
    }
    
    @Override
    public void guardaServicio() {
        if (dataLoaded) {
            Servicio datos = new Servicio();
            copiarPropiedades(viewServicioModel, datos);
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
        this.copiarPropiedades(cargado, this.viewServicioModel);
    }
    
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    private void copiarPropiedades(Servicio origen, Servicio destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "datosAuto",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres"
        });
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
    
}
