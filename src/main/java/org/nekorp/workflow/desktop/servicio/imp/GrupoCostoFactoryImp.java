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
package org.nekorp.workflow.desktop.servicio.imp;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.servicio.GrupoCostoFactory;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;

/**
 *
 * @author Nekorp
 */
public abstract class GrupoCostoFactoryImp implements GrupoCostoFactory {

    private List<GrupoCostoVB> cache;
    public GrupoCostoFactoryImp() {
        this.cache = new LinkedList<>();
    }
    
    @Override
    public GrupoCostoVB get(String grupo) {
        GrupoCostoVB existente = null;
        for (GrupoCostoVB x: this.cache) {
            if (StringUtils.equals(x.getGrupo(), grupo)) {
                existente = x;
                break;
            }
        }
        if (existente == null) {
            GrupoCostoVB nuevo = this.creaGrupoCostoVB();
            nuevo.setGrupo(grupo);
            this.cache.add(nuevo);
            return nuevo;
        } else {
            return existente;
        }
    }

    @Override
    public void resetCache() {
        this.cache = new LinkedList<>();
    }
    
    public abstract GrupoCostoVB creaGrupoCostoVB();
}
