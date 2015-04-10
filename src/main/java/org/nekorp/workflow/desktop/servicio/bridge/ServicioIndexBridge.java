/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

import java.util.List;
import org.nekorp.workflow.desktop.modelo.index.ServicioIndex;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.springframework.stereotype.Service;

/**
 * @author Nekorp
 */
@Service
public class ServicioIndexBridge implements ModelBridge<List<ServicioIndex>, List<ServicioIndexVB>> {

    @Override
    public void load(List<ServicioIndex> origen, List<ServicioIndexVB> destino) {
        ServicioIndexVB nuevo;
        for (ServicioIndex x: origen) {
            //TODO quitar este new cuando se requieran pojos proxeados.
            nuevo = new ServicioIndexVB();
            nuevo.setDescripcion(x.getDescripcion());
            nuevo.setFechaRecepcion(x.getFechaInicio());
            nuevo.setIdCliente(x.getClienteData().getId().toString());
            nuevo.setIdServicio(x.getId());
            nuevo.setNombreCliente(x.getClienteData().getNombre());
            nuevo.setNumeroSerieAuto(x.getAutoData().getNumeroSerie());
            nuevo.setPlacasAuto(x.getAutoData().getPlacas());
            nuevo.setTipo(x.getAutoData().getTipo());
            destino.add(nuevo);
        }
    }

    @Override
    public void unload(List<ServicioIndexVB> origen, List<ServicioIndex> destino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
