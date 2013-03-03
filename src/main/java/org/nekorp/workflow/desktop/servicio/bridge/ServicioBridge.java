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

package org.nekorp.workflow.desktop.servicio.bridge;

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ServicioBridge implements ModelBridge<Servicio, ServicioVB> {

    @Override
    public void load(Servicio origen, ServicioVB destino) {
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
        destino.setDescripcion(origen.getDescripcion());
    }

    @Override
    public void unload(ServicioVB origen, Servicio destino) {
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
        destino.setDescripcion(origen.getDescripcion());
    }
}
