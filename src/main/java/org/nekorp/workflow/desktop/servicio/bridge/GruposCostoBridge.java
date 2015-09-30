/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
import org.nekorp.workflow.desktop.servicio.GrupoCostoFactory;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nekorp
 */
@Service
public class GruposCostoBridge implements ModelBridge<List<String>, List<GrupoCostoVB>> {

    @Autowired
    private GrupoCostoFactory grupoCostoFactory;
    
    @Override
    public void load(List<String> origen, List<GrupoCostoVB> destino) {
        //ver si tiene el grupo default
        grupoCostoFactory.resetCache();
        if (origen.isEmpty()) {
            origen.add("");
        }
        for (String x: origen) {
            destino.add(grupoCostoFactory.get(x));
        }
    }

    @Override
    public void unload(List<GrupoCostoVB> origen, List<String> destino) {
        //eliminar repetidos
        List<GrupoCostoVB> tmp = new ArrayList<>();
        for (GrupoCostoVB x: origen) {
            boolean repetido = false;
            for (GrupoCostoVB y: tmp) {
                if (StringUtils.equals(x.getGrupo(), y.getGrupo())) {
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                tmp.add(x);
            }
        }
        for (GrupoCostoVB x: tmp) {
            destino.add(x.getGrupo());
        }
    }
}
