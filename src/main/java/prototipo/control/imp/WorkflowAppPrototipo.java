package prototipo.control.imp;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.EditorMonitor;
import prototipo.servicio.imp.ModelControl;
import prototipo.servicio.imp.ProxyUtil;

@Controller("application")
public class WorkflowAppPrototipo implements WorkflowApp{
    
    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Resource(name="application")
    private WorkflowAppPrototipo mySelf;
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private Servicio viewServicioModel;
    @Autowired
    private Cliente viewClienteModel;
    @Autowired
    private EditorMonitor editorMonitor;
    @Autowired
    private ProxyUtil proxyUtil;
    
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
        editorMonitor.clear();
        String folio = modelControl.getFolioServicio();
        Servicio nuevo = new Servicio();
        nuevo.setId(folio);
        proxyUtil.copiarPropiedades(nuevo, viewServicioModel, true);
        mySelf.unloadCliente();
    }
    
    @Override
    public void guardaServicio() {
        Servicio datos = new Servicio();
        proxyUtil.copiarPropiedades(viewServicioModel, datos, false);
        modelControl.guardaServicio(datos);
        this.guardarCliente();
        editorMonitor.clear();
    }

    @Override
    public List<ServicioIndex> getIndexServicios() {
        return this.modelControl.getIndiceServicios();
    }

    @Override
    public void cargaServicio(ServicioIndex index) {
        Servicio cargado = this.modelControl.cargaServicio(index);
        proxyUtil.copiarPropiedades(cargado, this.viewServicioModel, true);
        Cliente cliente = this.modelControl.getCliente(cargado.getIdCliente());
        if (cliente != null) {
            mySelf.loadCliente(cliente);
        } else {
            mySelf.unloadCliente();
        }
        editorMonitor.clear();
    }
    
    @Override
    public void loadCliente(Cliente origen) {
        proxyUtil.copiarPropiedades(origen, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(origen.getId());
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
    }
    
    @Override
    public void unloadCliente() {
        Cliente vacio = new Cliente();
        proxyUtil.copiarPropiedades(vacio, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(vacio.getId());
    }

    @Override
    public void nuevoCliente() {
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
        Cliente nuevo = this.modelControl.nuevoCliente();
        proxyUtil.copiarPropiedades(nuevo, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(nuevo.getId());
    }

    @Override
    public void guardarCliente() {
        Cliente cliente = this.modelControl.getCliente(this.viewClienteModel.getId());
        if (cliente != null) {
            proxyUtil.copiarPropiedades(this.viewClienteModel, cliente);
        }
        this.modelControl.guardaClientes();
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
    }

    @Override
    public List<Cliente> getClientes() {
        return this.modelControl.getClientes();
    }
}
