/**
 *   Copyright 2012-2013 Nekorp
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

import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("registroOtrosGastos")
@Scope("prototype")
public class RegistroOtrosGastosVB extends RegistroCostoVB {
    public static final String TIPO = "Otros Gastos";
    public RegistroOtrosGastosVB() {
        super();
        setSubtipo("Otros Gastos");
    }
    
    @Override
    public MonedaVB getIvaPrecioUnitario() {
        if (this.isPrecioUnitarioConIVA()) {
            return getPrecioUnitario().multiplica(MonedaVB.valueOf("0.16"));
        } else {
            return new MonedaVB();
        }
    }
    
    @Override
    public MonedaVB getIvaSubtotal() {
        if (this.isSubtotalConIVA()) {
            return getSubtotal().multiplica(MonedaVB.valueOf("0.16"));
        } else {
            return new MonedaVB();
        }
    }

    @Override
    public String getTipo() {
        return RegistroOtrosGastosVB.TIPO;
    }

    @Override
    public int compareTo(RegistroCostoVB o) {
        if (o instanceof RegistroHojalateriaPinturaVB) {
            return 1;
        }
        if (o instanceof RegistroMecanicaVB) {
            return 1;
        }
        if (o instanceof RegistroOtrosGastosVB) {
            return super.compareTo(o);
        }
        return 0;
    }
}
