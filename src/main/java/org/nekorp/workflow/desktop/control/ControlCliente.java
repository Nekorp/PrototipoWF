/**
 *   Copyright 2013 Nekorp
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

import java.util.List;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.rest.util.Callback;

/**
 *
 */
public interface ControlCliente {

    /**
     * carga un cliente a la aplicacion para trabajar con el.
     * @param origen el cliente a cargar.
     */
    void loadCliente(Cliente origen);
    
    /**
     * consulta todos los clientes que se tienen registrados.
     * @return el cliente.
     */
    List<Cliente> getClientes();
    
    /**
     * busca clientes por el nombre.
     * @param name los clientes que coinciden con el criterio de busqueda.
     * @return 
     */
    void buscarCliente(String name, Callback<List<Cliente>> cmd);
}
