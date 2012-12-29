package prototipo.control;

import java.util.List;
import prototipo.modelo.ServicioIndex;

public interface WorkflowApp {

    void startApliacion();
    
    void closeAplicacion();
    
    List<ServicioIndex> getIndexServicios();
    
    void nuevoServicio();
    
    void cargaServicio(ServicioIndex index);
    
    void guardaServicio();
}
