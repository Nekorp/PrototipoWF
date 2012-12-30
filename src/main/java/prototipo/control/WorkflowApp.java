package prototipo.control;

import java.util.List;
import prototipo.modelo.ServicioIndex;

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
//    /**
//     * 
//     * @param nombre el nombre del nuevo evento.
//     */
//    void addEventoEntrega(String nombre);
//    
//    /**
//     * solicita la creacion de un nuevo evento general.
//     */
//    void addEventoGeneral();
}
