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

package org.nekorp.workflow.desktop.data.access.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.pagination.PaginaCliente;
import org.nekorp.workflow.desktop.rest.util.AsyncRestCall;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ClienteDAOImp extends RestDAOTemplate implements ClienteDAO {
    
    @Override
    public void guardar(Cliente dato) {
    }

    @Override
    public List<Cliente> consultaTodos() {
        PaginaCliente r = getTemplate().getForObject(getRootUlr() + "/clientes", PaginaCliente.class);
        return r.getItems();
    }

    @Override
    public void buscar(final String name, final Callback<List<Cliente>> cmd) {
        Thread task = new AsyncRestCall<List<Cliente>>() {
            @Override
            public List<Cliente> executeCall() {
                String url;
                if (StringUtils.isEmpty(name)) {
                    return new LinkedList<>();
                } else {
                    url = getRootUlr() + "/clientes?filtroNombre={nombre}";
                }
                Map<String, Object> map = new HashMap<>();
                map.put("nombre", name);
                PaginaCliente r = getTemplate().getForObject(url, PaginaCliente.class, map);
                return r.getItems();
            }

            @Override
            public Callback getCallBack() {
                return cmd;
            }
        };
        task.start();
    }

//    @Override
//    public Cliente buscarUnico(String name) {
//        String url;
//        if (StringUtils.isEmpty(name)) {
//            return null;
//        } else {
//            url = getRootUlr() + "/clientes?filtroNombre={nombre}";
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("nombre", name);
//        PaginaCliente r = getTemplate().getForObject(url, PaginaCliente.class, map);
//        if (r.getItems().size() != 1) { //no se encontro un unico resultado
//            return null;
//        }
//        return r.getItems().get(0);
//    }

}
