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
import org.nekorp.workflow.desktop.view.model.cliente.DomicilioFiscalVB;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import technology.tikal.customers.model.address.Address;
import technology.tikal.customers.model.address.MexicoAddress;

/**
 *
 * @author Nekorp
 */
@Service
public class DomicilioFiscalBridge implements ModelBridge<Address, DomicilioFiscalVB>{

    @Override
    public void load(Address origen, DomicilioFiscalVB destino) {
        if (origen == null) {
            destino.setCalle("");
            destino.setCiudad("");
            destino.setCodigoPostal("");
            destino.setColonia("");
            destino.setNumInterior("");
        } else {
            if (origen instanceof MexicoAddress) {
                MexicoAddress source = (MexicoAddress) origen;
                destino.setCalle(getSafeValue(source.getCalle()));
                destino.setCiudad(getSafeValue(source.getCiudad()));
                destino.setCodigoPostal(getSafeValue(source.getCodigoPostal()));
                destino.setColonia(getSafeValue(source.getColonia()));
                destino.setNumInterior(getSafeValue(source.getNumeroInterior()));
            } else {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }
    
    private String getSafeValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        } else {
            return value;
        }
    }

    @Override
    public void unload(DomicilioFiscalVB origen, Address destino) {
        if (destino instanceof MexicoAddress) {
            MexicoAddress target = (MexicoAddress) destino;
            target.setCalle(getEmptyAsNull(origen.getCalle()));
            target.setCiudad(getEmptyAsNull(origen.getCiudad()));
            target.setCodigoPostal(getEmptyAsNull(origen.getCodigoPostal()));
            target.setColonia(getEmptyAsNull(origen.getColonia()));
            target.setNumeroInterior(getEmptyAsNull(origen.getNumInterior()));
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    private String getEmptyAsNull(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            return value;
        }
    }
}
