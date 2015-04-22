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

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.cliente.ClienteVB;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import technology.tikal.taller.automotriz.model.cliente.Cliente;

/**
 * @author Nekorp
 */
@Deprecated
public class ClienteBridge implements ModelBridge<Cliente, ClienteVB> {

    @Autowired
    private DomicilioFiscalBridge domicilioBridge;
    @Autowired
    private TelefonoModelBridge telefonoBridge;
    @Override
    public void load(Cliente origen, ClienteVB destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "id",
            "domicilio",
            "telefonoContacto"
        });
        if (origen.getId() != null) {
            destino.setId(origen.getId().toString());
        } else {
            destino.setId("");
        }
        domicilioBridge.load(origen.getDomicilio(), destino.getDomicilio());
        telefonoBridge.load(origen.getTelefonoContacto().get(0), destino.getTelefonoUno());
        telefonoBridge.load(origen.getTelefonoContacto().get(1), destino.getTelefonoDos());
        telefonoBridge.load(origen.getTelefonoContacto().get(2), destino.getTelefonoTres());
    }

    @Override
    public void unload(ClienteVB origen, Cliente destino) {
        BeanUtils.copyProperties(origen, destino, new String[] {
            "id",
            "domicilio",
            "telefonoUno",
            "telefonoDos",
            "telefonoTres"
        });
        if (StringUtils.isEmpty(origen.getId())) {
            destino.setId(null);
        } else {
            destino.setId(Long.valueOf(origen.getId()));
        }
        domicilioBridge.unload(origen.getDomicilio(), destino.getDomicilio());
        telefonoBridge.unload(origen.getTelefonoUno(), destino.getTelefonoContacto().get(0));
        telefonoBridge.unload(origen.getTelefonoDos(), destino.getTelefonoContacto().get(1));
        telefonoBridge.unload(origen.getTelefonoTres(), destino.getTelefonoContacto().get(2));
    }

}
