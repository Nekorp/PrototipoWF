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
package org.nekorp.workflow.desktop.servicio.imp;

import org.nekorp.workflow.desktop.servicio.CobranzaPagoServicioFactory;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;


/**
 * @author Nekorp
 * 
 */
public abstract class CobranzaPagoServicioFactoryImp implements CobranzaPagoServicioFactory {

    @Override
    public PagoCobranzaVB creaPago() {
        return creaPagoVB();
    }
    
    /**
     * se utilzan para method injection de spring
     * para poder tener objetos con scope prototype
     * para poder tener AOP
     * @return objeto proxeado
     */
    public abstract PagoCobranzaVB creaPagoVB();
}
