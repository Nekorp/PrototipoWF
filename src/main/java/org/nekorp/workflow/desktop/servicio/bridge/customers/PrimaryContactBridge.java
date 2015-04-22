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

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.servicio.bridge.ModelBridge;
import org.nekorp.workflow.desktop.view.model.cliente.ClienteVB;
import org.nekorp.workflow.desktop.view.model.cliente.TelefonoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.customers.model.contact.PrimaryContact;
import technology.tikal.customers.model.contact.PrimaryContactPojo;
import technology.tikal.customers.model.media.Email;
import technology.tikal.customers.model.media.MediaContact;
import technology.tikal.customers.model.name.OrganizationName;
import technology.tikal.customers.model.phone.MexicoPhoneNumber;
import technology.tikal.customers.model.phone.PhoneNumber;

/**
 *
 * @author Nekorp
 */
@Service
public class PrimaryContactBridge implements ModelBridge<PrimaryContact, ClienteVB> {

    @Autowired
    private NameBridge nameBridge;
    @Autowired
    private PhoneNumberBridge phoneNumberBridge;
    @Autowired
    private EmailContactBridge mediaContactBridge;
    
    @Override
    public void load(PrimaryContact origen, ClienteVB destino) {
        loadName(origen, destino);
        loadEmail(origen, destino);
        loadPhoneNumbers(origen, destino);
    }
    
    private void loadName(PrimaryContact origen, ClienteVB destino) {
        if (origen == null) {
            destino.setContacto("");
        } else {
            StringWrapper name = new StringWrapper();
            nameBridge.load(origen.getName(), name);
            destino.setContacto(name.getValue());
        }
    }
    
    private void loadEmail(PrimaryContact origen, ClienteVB destino) {
        if (origen == null || origen.getMediaContact() == null) {
            destino.setEmail("");
        } else {
            StringWrapper email = new StringWrapper();
            MediaContact[] mediaContact = origen.getMediaContact();
            boolean mailLoaded = false;
            for (MediaContact x: mediaContact) {
                if (x instanceof Email) {
                    mediaContactBridge.load(x, email);
                    mailLoaded = true;
                    break;
                }
            }
            if (!mailLoaded) {
                mediaContactBridge.load(null, email);
            }
            destino.setEmail(email.getValue());
        }
    }
    
    private void loadPhoneNumbers(PrimaryContact origen, ClienteVB destino) {
        TelefonoVB[] telefonos = new TelefonoVB[3];
        telefonos[0] = destino.getTelefonoUno();
        telefonos[1] = destino.getTelefonoDos();
        telefonos[2] = destino.getTelefonoTres();
        if (origen == null || origen.getPhoneNumber() == null) {
            for (TelefonoVB x: telefonos) {
                phoneNumberBridge.load(null, x);
            }
        } else {
            PhoneNumber[] phoneNumber = origen.getPhoneNumber();
            for (int i = 0; i < telefonos.length; i++) {
                if (i < phoneNumber.length) {
                    phoneNumberBridge.load(phoneNumber[i], telefonos[i]);
                } else {
                    phoneNumberBridge.load(null, telefonos[i]);
                }
            }
        }
    }

    @Override
    public void unload(ClienteVB origen, PrimaryContact destino) {
        unloadName(origen, destino);
        unloadEmail(origen, destino);
        unloadPhoneNumbers(origen, destino);
    }
    
    private void unloadName(ClienteVB origen, PrimaryContact destino) {
        if (StringUtils.isEmpty(origen.getContacto())) {
            destino.setName(null);
        } else {
            StringWrapper contacto = new StringWrapper();
            contacto.setValue(origen.getContacto());
            OrganizationName name = new OrganizationName();
            nameBridge.unload(contacto, name);
            destino.setName(name);
        }
    }
    
    private void unloadEmail(ClienteVB origen, PrimaryContact destino) {
        if (destino instanceof PrimaryContactPojo) {
            PrimaryContactPojo target = (PrimaryContactPojo) destino;
            if (StringUtils.isEmpty(origen.getEmail())) {
                target.setMediaContact(new MediaContact[]{});
            } else {
                StringWrapper email = new StringWrapper();
                email.setValue(origen.getEmail());
                Email emailTarget = new Email();
                mediaContactBridge.unload(email, emailTarget);
                MediaContact[] mediaContact = new MediaContact[]{
                    emailTarget
                };
                target.setMediaContact(mediaContact);
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private void unloadPhoneNumbers(ClienteVB origen, PrimaryContact destino) {
        if (destino instanceof PrimaryContactPojo) {
            PrimaryContactPojo target = (PrimaryContactPojo) destino;
            PhoneNumber[] phoneNumber = new PhoneNumber[3];
            TelefonoVB[] telefonos = new TelefonoVB[3];
            telefonos[0] = origen.getTelefonoUno();
            telefonos[1] = origen.getTelefonoDos();
            telefonos[2] = origen.getTelefonoTres();
            for (int i = 0; i < telefonos.length; i++) {
                if (!StringUtils.isEmpty(telefonos[i].getValor())) {
                    phoneNumber[i] = new MexicoPhoneNumber();
                    phoneNumberBridge.unload(telefonos[i], phoneNumber[i]);
                }
            }
            target.setPhoneNumber(phoneNumber);
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
