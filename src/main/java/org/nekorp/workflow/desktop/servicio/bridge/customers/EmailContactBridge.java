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
import org.springframework.stereotype.Service;
import technology.tikal.customers.model.media.Email;
import technology.tikal.customers.model.media.MediaContact;

/**
 *
 * @author Nekorp
 */
@Service
public class EmailContactBridge implements ModelBridge<MediaContact, StringWrapper> {

    @Override
    public void load(MediaContact origen, StringWrapper destino) {
        if (origen == null) {
            destino.setValue("");
        } else {
            if (origen instanceof Email) {
                Email source = (Email) origen;
                destino.setValue(source.getRawEmail());
            } else {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }

    @Override
    public void unload(StringWrapper origen, MediaContact destino) {
        if (destino instanceof Email) {
            Email target = (Email) destino;
            target.setEtiqueta("email");
            target.setRawEmail(origen.getValue());
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
}
