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
import org.nekorp.workflow.desktop.modelo.inventario.damage.DamageDetail;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DamageDetailBridge implements ModelBridge<DamageDetail, DamageDetailsVB> {

    @Override
    public void load(DamageDetail origen, DamageDetailsVB destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "id",
            "esquema"
        });
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
    }

    @Override
    public void unload(DamageDetailsVB origen, DamageDetail destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "id"
        });
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
    }

}
