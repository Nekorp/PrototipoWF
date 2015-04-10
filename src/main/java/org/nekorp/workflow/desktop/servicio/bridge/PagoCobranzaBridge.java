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

import org.nekorp.workflow.desktop.modelo.cobranza.PagoCobranza;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nekorp
 */
@Service
public class PagoCobranzaBridge implements ModelBridge<PagoCobranza, PagoCobranzaVB>{

    @Override
    public void load(PagoCobranza origen, PagoCobranzaVB destino) {
        BeanUtils.copyProperties(origen, destino, "monto");
        destino.setMonto(MonedaVB.valueOf(origen.getMonto().getValue()));
    }

    @Override
    public void unload(PagoCobranzaVB origen, PagoCobranza destino) {
        BeanUtils.copyProperties(origen, destino, "monto");
        destino.getMonto().setValue(origen.getMonto().toString());
    }
    
}
