/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.model.costo;

import org.nekorp.workflow.desktop.servicio.Metadata;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Nekorp
 */
@Component("grupoCostoMetadata")
@Scope("prototype")
public class GrupoCostoMetadata implements Metadata {
    private MonedaVB subtotal;
    private MonedaVB iva;
    private MonedaVB total;
    
    public GrupoCostoMetadata() {
        this.subtotal = new MonedaVB();
        this.iva = new MonedaVB();
        this.total = new MonedaVB();
    }
    
    public MonedaVB getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(MonedaVB subtotal) {
        this.subtotal = subtotal;
    }

    public MonedaVB getIva() {
        return iva;
    }

    public void setIva(MonedaVB iva) {
        this.iva = iva;
    }

    public MonedaVB getTotal() {
        return total;
    }

    public void setTotal(MonedaVB total) {
        this.total = total;
    }
    
}
