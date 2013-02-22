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
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.view.model.auto.DatosAutoVB;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AutoBridge implements ModelBridge<Auto, DatosAutoVB> {

    @Autowired
    private EquipamientoBridge equipamientoBridge;
    
    @Override
    public void load(Auto origen, DatosAutoVB destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "equipamiento",
            "combustible"
        });
        equipamientoBridge.load(origen.getEquipamiento(), destino.getEquipamiento());
        destino.setCombustible(StringUtils.defaultIfEmpty(origen.getCombustible(), "0"));
    }

    @Override
    public void unload(DatosAutoVB origen, Auto destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "equipamiento",
            "combustible"
        });
        equipamientoBridge.unload(origen.getEquipamiento(), destino.getEquipamiento());
        destino.setCombustible(StringUtils.defaultIfEmpty(origen.getCombustible(), "0"));
    }

}