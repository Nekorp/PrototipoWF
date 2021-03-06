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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.servicio.Servicio;

/**
 * @author Nekorp
 */
@Service
public class ServicioBridge implements ModelBridge<Servicio, ServicioVB> {

    @Autowired
    private DatosAutoBridge datosAutoBridge;
    @Autowired
    private DatosCobranzaBridge datosCobranzaBridge;
    @Autowired
    private GruposCostoBridge gruposCostoBridge;
    @Override
    public void load(Servicio origen, ServicioVB destino) {
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
        destino.setDescripcion(origen.getDescripcion());
        destino.setFechaInicio(origen.getMetadata().getFechaInicio());
        destino.setStatus(origen.getMetadata().getStatus());
        datosAutoBridge.load(origen.getDatosAuto(), destino.getDatosAuto());
        datosCobranzaBridge.load(origen.getCobranza(), destino.getCobranza());
        //grupos
        List<GrupoCostoVB> grupos = new ArrayList<>();
        gruposCostoBridge.load(origen.getGruposCosto(), grupos);
        destino.setGruposCosto(grupos);
    }

    @Override
    public void unload(ServicioVB origen, Servicio destino) {
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
        if (StringUtils.isEmpty(origen.getCliente().getId())) {
            destino.setIdCliente(null);
        } else {
            destino.setIdCliente(Long.valueOf(origen.getCliente().getId()));
        }
        if (StringUtils.isEmpty(origen.getAuto().getNumeroSerie())) {
            destino.setIdAuto(null);
        } else {
            destino.setIdAuto(origen.getAuto().getNumeroSerie());
        }
        destino.setDescripcion(origen.getDescripcion());
        destino.getMetadata().setStatus(origen.getStatus());
        datosAutoBridge.unload(origen.getDatosAuto(), destino.getDatosAuto());
        datosCobranzaBridge.unload(origen.getCobranza(), destino.getCobranza());
        //grupos
        List<String> grupos = new ArrayList<>();
        gruposCostoBridge.unload(origen.getGruposCosto(), grupos);
        destino.setGruposCosto(grupos);
    }
}
