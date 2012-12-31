package prototipo.control;

import java.util.List;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.cliente.Cliente;

public interface WorkflowApp {
    /**
     * solicita el inicaio de la aplicacion.
     */
    void startApliacion();
    /**
     * inicia el cierre de la aplicacion
     */
    void closeAplicacion();
    
    /**
     * @return TODOS los servicios disponibles.
     */
    //TODO paginacion.
    List<ServicioIndex> getIndexServicios();
    
    /**
     * solicita que se cree un nuevo servicio
     */
    void nuevoServicio();
    
    /**
     * solicita que se cargue un nuevo servicio.
     * @param index el id del servicio.
     */
    void cargaServicio(ServicioIndex index);
    
    /**
     * guarda el servicio actualmente cargado.
     */
    void guardaServicio();

    void loadCliente(Cliente origen);
    
    void unloadCliente();
    
    void nuevoCliente();
    
    void guardarCliente();
    
    List<Cliente> getClientes();
    
}
