package prototipo.control.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.aop.ServicioAop;
import prototipo.modelo.control.ModelControl;

@Component
public class WorkflowAppPrototipo implements WorkflowApp {
    private static final Logger LOGGER = Logger
            .getLogger(WorkflowAppPrototipo.class);
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private ServicioAop viewModel;
    
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
        BeanUtils.copyProperties(nuevo, this.viewModel);
        dataLoaded = true;
    }

    @Override
    public void guardaServicio() {
        //        if (dataLoaded) {
//            modelControl.guardaServicio(viewModel.getData());
//        }
    }
}
