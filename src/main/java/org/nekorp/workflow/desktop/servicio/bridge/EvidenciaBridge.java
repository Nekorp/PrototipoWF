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

import org.nekorp.workflow.desktop.view.model.bitacora.EvidenciaVB;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.servicio.bitacora.Evidencia;

/**
 * @author Nekorp
 */
@Service
public class EvidenciaBridge implements ModelBridge<Evidencia, EvidenciaVB> {

    @Override
    public void load(Evidencia origen, EvidenciaVB destino) {
        BeanUtils.copyProperties(origen, destino);
    }

    @Override
    public void unload(EvidenciaVB origen, Evidencia destino) {
        BeanUtils.copyProperties(origen, destino);
    }

}
