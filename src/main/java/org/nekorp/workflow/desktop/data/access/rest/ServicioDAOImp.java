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
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.index.ServicioIndex;
import org.nekorp.workflow.desktop.modelo.pagination.PaginaServicio;
import org.nekorp.workflow.desktop.modelo.pagination.PaginaServicioIndex;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.view.model.ServicioIndexVB;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ServicioDAOImp extends RestDAOTemplate implements ServicioDAO {

    @Override
    public void guardar(Servicio dato) {
        if (dato.getId() == null) {
            URI resource = getTemplate().postForLocation(getRootUlr() + "/servicios", dato);
            String[] uri = StringUtils.split(resource.toString(), '/');
            String id = uri[uri.length - 1];
            dato.setId(Long.valueOf(id));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dato.getId());
            getTemplate().postForLocation(getRootUlr() + "/servicios/{id}", dato, map);
        }
    }

    @Override
    public Servicio cargar(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Servicio r = getTemplate().getForObject(getRootUlr() + "/servicios/{id}", Servicio.class, map);
        return r;
    }

    @Override
    public List<ServicioIndex> getIndiceServicios() {
        PaginaServicioIndex r = getTemplate().getForObject(getRootUlr() + "/index/servicio", PaginaServicioIndex.class);
        return r.getItems();
    }

}
