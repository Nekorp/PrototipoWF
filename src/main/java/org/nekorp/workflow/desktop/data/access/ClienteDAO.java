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

package org.nekorp.workflow.desktop.data.access;

import java.util.List;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;

/**
 *
 */
public interface ClienteDAO {

    /**
     * Guarda o actualiza un cliente.
     * Si el cliente no tiene id al terminar de guardar se le asignara un id.
     * @param dato el cliente a guarda.
     */
    void save(Cliente dato);
    
    /**
     * consulta todos los clientes, ignora por el momento temas de paginacion.
     * @return la lista de todos los clientes.
     */
    List<Cliente> consultaClientes();
}