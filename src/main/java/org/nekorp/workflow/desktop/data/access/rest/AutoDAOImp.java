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

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.modelo.pagination.PaginaAuto;
import org.nekorp.workflow.desktop.rest.util.AsyncRestCall;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AutoDAOImp extends RestDAOTemplate implements AutoDAO {

    @Override
    public void guardar(Auto dato) {
        URI resource = getTemplate().postForLocation(getRootUlr() + "/autos", dato);
        String[] uri = StringUtils.split(resource.toString(), '/');
        String id = uri[uri.length - 1];
        dato.setNumeroSerie(id);
    }

    @Override
    public void buscar(final String numeroSerie, final Callback<List<Auto>> cmd) {
        Thread task = new AsyncRestCall<List<Auto>>() {
            @Override
            public List<Auto> executeCall() {
                String url;
                if (StringUtils.isEmpty(numeroSerie)) {
                    return new LinkedList<>();
                } else {
                    url = getRootUlr() + "/autos?filtroNumeroSerie={numeroSerie}";
                }
                Map<String, Object> map = new HashMap<>();
                map.put("numeroSerie", numeroSerie);
                PaginaAuto r = getTemplate().getForObject(url, PaginaAuto.class, map);
                return r.getItems();
            }

            @Override
            public Callback getCallBack() {
                return cmd;
            }
        };
        task.start();
    }
    
    @Override
    public Auto cargar(String numeroSerie) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", numeroSerie);
        Auto r = getTemplate().getForObject(getRootUlr() + "/autos/{id}", Auto.class, map);
        return r;
    }

    @Override
    public List<Auto> consultaTodos() {
        PaginaAuto r = getTemplate().getForObject(getRootUlr() + "/autos", PaginaAuto.class);
        return r.getItems();
    }
}
