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

package prototipo.view.model.costo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import prototipo.view.model.currency.MonedaVB;

/**
 *
 */
@Component("registroMecanica")
@Scope("prototype")
public class RegistroMecanicaVB extends RegistroCostoVB {
    public static final String TIPO = "Mecanica";
    public RegistroMecanicaVB() {
        super();
        setSubtipo("Mano de Obra");
    }
    
    @Override
    public MonedaVB getIvaPrecioUnitario() {
        return getPrecioUnitario().multiplica(MonedaVB.valueOf("0.16"));
    }
    
    @Override
    public MonedaVB getIvaSubtotal() {
        return getPrecioCliente().multiplica(getCantidad()).multiplica(MonedaVB.valueOf("0.16"));
    }

    @Override
    public String getTipo() {
        return RegistroMecanicaVB.TIPO;
    }
}
