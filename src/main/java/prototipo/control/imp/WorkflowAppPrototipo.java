package prototipo.control.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.control.ModelControl;

@Component
public class WorkflowAppPrototipo implements WorkflowApp {
    private static final Logger LOGGER = Logger
            .getLogger(WorkflowAppPrototipo.class);
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private Servicio viewModel;
    
    private boolean dataLoaded = false;

    @Override
    public void startApliacion() {
        //WorkflowAppPrototipo.LOGGER.info("iniciando aplicacion");
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    @Override
    public void nuevoServicio() {
        //WorkflowAppPrototipo.LOGGER.info("Hacer un nuevo servicio");
        String folio = modelControl.getFolioServicio();
        Servicio nuevo = new Servicio();
        nuevo.setId(folio);
        nuevo.setContacto("test Contacto");
        nuevo.getTelefonoUno().setValor("01800123456");
        nuevo.getTelefonoUno().setLabel("Movil");
        nuevo.setDescripcion("Arreglar coso");
        copiarPropiedades(nuevo, this.viewModel);
        dataLoaded = true;
    }
    /**
     * copia los valores de la propiedades sin remplazar objetos no inmutables.
     * @param origen el origen.
     * @param destino el destino.
     */
    private void copiarPropiedades(Servicio origen, Servicio destino) {
        BeanUtils.copyProperties(origen, destino, 
            new String[]{
                "key",
                "telefonoUno",
                "telefonoDos",
                "telefonoTres"
        });
        BeanUtils.copyProperties(origen.getTelefonoUno(), destino.getTelefonoUno(),
            new String[]{"key"
        });
        BeanUtils.copyProperties(origen.getTelefonoDos(), destino.getTelefonoDos(),
            new String[]{"key"
        });
        BeanUtils.copyProperties(origen.getTelefonoTres(), destino.getTelefonoTres(),
            new String[]{"key"
        });
    }

    @Override
    public void guardaServicio() {
        //        if (dataLoaded) {
//            modelControl.guardaServicio(viewModel.getData());
//        }
    }
}
