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
import org.nekorp.workflow.desktop.modelo.auto.Equipamiento;
import org.nekorp.workflow.desktop.view.model.auto.EquipamientoVB;
import org.nekorp.workflow.desktop.view.model.auto.TipoElevadorVB;
import org.nekorp.workflow.desktop.view.model.auto.TipoTransmisionVB;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EquipamientoBridge implements ModelBridge<Equipamiento, EquipamientoVB> {

    @Override
    public void load(Equipamiento origen, EquipamientoVB destino) {
        BeanUtils.copyProperties(origen, destino, new String[]{
            "transmision",
            "elevadores"
        });
        destino.setTransmision(TipoTransmisionVB.valueOf(
            StringUtils.defaultIfEmpty(origen.getTransmision(),"estandar")));
        destino.setElevadores(TipoElevadorVB.valueOf(
            StringUtils.defaultIfEmpty(origen.getElevadores(), "manuales")));
    }

    @Override
    public void unload(EquipamientoVB origen, Equipamiento destino) {
        BeanUtils.copyProperties(origen, destino, new String[]{
            "transmision",
            "elevadores"
        });
        destino.setTransmision(origen.getTransmision().toString());
        destino.setElevadores(origen.getElevadores().toString());
    }
}
