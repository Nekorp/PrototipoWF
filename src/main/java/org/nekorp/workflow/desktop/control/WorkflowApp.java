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
package org.nekorp.workflow.desktop.control;

import java.io.File;
import java.util.List;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import prototipo.view.model.ServicioIndex;

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
     * deja vacio los datos del servicio.
     */
    void unloadServicio();
    /**
     * carga el servicio anterior, si es que hay alguno.
     */
    void reloadServicio();
    /**
     * solicita que se cargue un nuevo servicio.
     * @param idServicio el id del servicio.
     */
    void cargaServicio(String idServicio);
    
    /**
     * guarda el servicio actualmente cargado.
     */
    void guardaServicio();
    /**
     * carga un cliente a la aplicacion para trabajar con el.
     * @param origen el cliente a cargar.
     */
    void loadCliente(Cliente origen);
    /**
     * busca y carga un cliente, en caso de que no exista el cliente
     * se carga un cliente vacio con el nombre que se indico.
     * @param name el nombre del cliente a cargar
     */
    void loadByName(String name);
    /**
     * carga un cliente vacio a la aplicacion.
     */
    void unloadCliente();
    /**
     * crea un cliente nuevo vacio y lo carga a la aplicacion.
     */
    void nuevoCliente();
    /**
     * guarda el cliente que se encuentra cargado actualmente
     * en la aplicacion.
     */
    void guardarCliente();
    /**
     * consulta todos los clientes que se tienen registrados.
     * @return el cliente.
     */
    //TODO cambiar el cliente por otra clase que sea un indice a los clientes.
    List<Cliente> getClientes();
    
    //TODO cambiar el cliente por otra clase que sea un indice a los clientes.
    /**
     * busca clientes por el nombre.
     * @param name los clientes que coinciden con el criterio de busqueda.
     * @return 
     */
    List<Cliente> buscarCliente(String name);
    
    /**
     * genera un reporte con los datos que se tienen cargados en la aplicacion.
     * @param destination la ruta donde se dejara el reporte.
     */
    void generaReporte(File destination);
}
