/**
 *   Copyright 2012-2013 Nekorp
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
