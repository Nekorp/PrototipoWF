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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.customers.model.ClienteMxPojo;
import technology.tikal.customers.model.Customer;
import technology.tikal.customers.model.address.MexicoAddress;
import technology.tikal.customers.model.contact.PrimaryContactPojo;
import technology.tikal.customers.model.name.Name;
import technology.tikal.customers.model.name.OrganizationName;

/**
 *
 * @author Nekorp
 */
@Service
public class CustomerBridge implements ModelBridge<Customer, ClienteVB> {

    @Autowired
    private NameBridge nameBridge;
    @Autowired
    private PrimaryContactBridge primaryContactBridge;
    @Autowired
    private DomicilioFiscalBridge domicilioFiscalBridge;
    @Override
    public void load(Customer origen, ClienteVB destino) {
        if (origen instanceof ClienteMxPojo) {
            ClienteMxPojo source = (ClienteMxPojo) origen;
            loadId(source, destino);
            loadName(source, destino);
            loadRfc(source, destino);
            primaryContactBridge.load(source.getPrimaryContact(), destino);
            domicilioFiscalBridge.load(source.getDomicilioFiscal(), destino.getDomicilio());
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private void loadId(ClienteMxPojo origen, ClienteVB destino) {
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
    }
    
    private void loadName(ClienteMxPojo origen, ClienteVB destino) {
        StringWrapper name = new StringWrapper();
        nameBridge.load(origen.getName(), name);
        destino.setNombre(name.getValue());
    }
    
    private void loadRfc(ClienteMxPojo origen, ClienteVB destino) {
        if (StringUtils.isEmpty(origen.getRfc())) {
            destino.setRfc("");
        } else {
            destino.setRfc(origen.getRfc());
        }
    }
    
    @Override
    public void unload(ClienteVB origen, Customer destino) {
        if (destino instanceof ClienteMxPojo) {
            ClienteMxPojo target = (ClienteMxPojo) destino;
            unloadId(origen, target);
            unloadName(origen, target);
            unloadRfc(origen, target);
            PrimaryContactPojo contact = new PrimaryContactPojo();
            primaryContactBridge.unload(origen, contact);
            target.setPrimaryContact(contact);
            MexicoAddress address = new MexicoAddress();
            domicilioFiscalBridge.unload(origen.getDomicilio(), address);
            target.setDomicilioFiscal(address);
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private void unloadId(ClienteVB origen, ClienteMxPojo destino) {
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
    }
    
    private void unloadName(ClienteVB origen, ClienteMxPojo destino) {
        if (StringUtils.isEmpty(origen.getNombre())) {
            destino.setName(null);
        } else {
            Name name = new OrganizationName();
            nameBridge.unload(new StringWrapper(origen.getNombre()), name); 
            destino.setName(name);
        }
    }
    
    private void unloadRfc(ClienteVB origen, ClienteMxPojo destino) {
        if (StringUtils.isEmpty(origen.getRfc())) {
            destino.setRfc(null);
        } else {
            destino.setRfc(origen.getRfc());
        }
    }
}
