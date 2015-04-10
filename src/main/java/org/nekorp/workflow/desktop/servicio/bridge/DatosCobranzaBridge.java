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

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.modelo.cobranza.DatosCobranza;
import org.nekorp.workflow.desktop.modelo.cobranza.PagoCobranza;
import org.nekorp.workflow.desktop.servicio.CobranzaPagoServicioFactory;
import org.nekorp.workflow.desktop.view.model.cobranza.DatosCobranzaVB;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nekorp
 */
@Service
public class DatosCobranzaBridge implements ModelBridge<DatosCobranza, DatosCobranzaVB> {

    @Autowired
    private PagoCobranzaBridge pagoCobranzaBridge;
    @Autowired
    private CobranzaPagoServicioFactory pagoFactory;
    @Override
    public void load(DatosCobranza origen, DatosCobranzaVB destino) {
        BeanUtils.copyProperties(origen, destino, "pagos");
        List<PagoCobranzaVB> pagos = new LinkedList<>();
        for (PagoCobranza x :origen.getPagos()) {
            PagoCobranzaVB pagoVB = pagoFactory.creaPago();
            pagoCobranzaBridge.load(x, pagoVB);
            pagos.add(pagoVB);
        }
        destino.setPagos(pagos);
    }

    @Override
    public void unload(DatosCobranzaVB origen, DatosCobranza destino) {
        BeanUtils.copyProperties(origen, destino, "pagos");
        List<PagoCobranza> pagos = new LinkedList<>();
        for (PagoCobranzaVB x :origen.getPagos()) {
            PagoCobranza pago = new PagoCobranza();
            pagoCobranzaBridge.unload(x, pago);
            pagos.add(pago);
        }
        destino.setPagos(pagos);
    }
}
