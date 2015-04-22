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
package org.nekorp.workflow.desktop.servicio.bridge.customers;

import org.nekorp.workflow.desktop.servicio.bridge.ModelBridge;
import org.nekorp.workflow.desktop.view.model.cliente.TelefonoVB;
import org.springframework.stereotype.Service;
import technology.tikal.customers.model.phone.MexicoPhoneNumber;
import technology.tikal.customers.model.phone.PhoneNumber;

/**
 *
 * @author Nekorp
 */
@Service
public class PhoneNumberBridge implements ModelBridge<PhoneNumber, TelefonoVB> {

    @Override
    public void load(PhoneNumber origen, TelefonoVB destino) {
        if (origen == null) {
            destino.setLabel("Casa");
            destino.setValor("");
        } else {
            if (origen instanceof MexicoPhoneNumber) {
                MexicoPhoneNumber source = (MexicoPhoneNumber) origen;
                destino.setLabel(source.getEtiqueta());
                destino.setValor(source.getTelefono());
            } else {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }

    @Override
    public void unload(TelefonoVB origen, PhoneNumber destino) {
        if (destino instanceof MexicoPhoneNumber) {
            MexicoPhoneNumber target = (MexicoPhoneNumber) destino;
            target.setEtiqueta(origen.getLabel());
            target.setTelefono(origen.getValor());
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
