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

import java.util.List;
import org.nekorp.workflow.desktop.servicio.RegistroCostoFactory;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.servicio.costo.RegistroCosto;

/**
 * @author Nekorp
 */
@Service
public class CostoBridge implements ModelBridge<List<RegistroCosto>, List<RegistroCostoVB>>{

    @Autowired
    private RegistroCostoBridge registroCostoBridge;
    @Autowired
    private RegistroCostoFactory registroCostofactory;
    @Override
    public void load(List<RegistroCosto> origen, List<RegistroCostoVB> destino) {
        RegistroCostoVB nuevo;
        for (RegistroCosto x: origen) {
            nuevo = registroCostofactory.getRegistroCosto(x.getTipo());
            registroCostoBridge.load(x, nuevo);
            destino.add(nuevo);
        }
    }

    @Override
    public void unload(List<RegistroCostoVB> origen, List<RegistroCosto> destino) {
        RegistroCosto nuevo;
        for(RegistroCostoVB x: origen) {
            nuevo = new RegistroCosto();
            registroCostoBridge.unload(x, nuevo);
            destino.add(nuevo);
        }
    }
}
