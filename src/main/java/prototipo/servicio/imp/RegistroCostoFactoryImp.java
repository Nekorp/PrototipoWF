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
package prototipo.servicio.imp;

import org.apache.commons.lang.StringUtils;
import prototipo.servicio.RegistroCostoFactory;
import prototipo.view.model.costo.RegistroCostoVB;
import prototipo.view.model.costo.RegistroHojalateriaPinturaVB;
import prototipo.view.model.costo.RegistroMecanicaVB;
import prototipo.view.model.costo.RegistroOtrosGastosVB;
/**
 *
 *
 */
public abstract class RegistroCostoFactoryImp implements RegistroCostoFactory {

    @Override
    public RegistroCostoVB getRegistroCosto(String tipo) {
        if (StringUtils.equals(tipo, RegistroHojalateriaPinturaVB.TIPO)) {
            return this.creaRegistroHojalateriaPintura();
        }
        if (StringUtils.equals(tipo, RegistroMecanicaVB.TIPO)) {
            return this.creaRegistroMecanica();
        }
        if (StringUtils.equals(tipo, RegistroOtrosGastosVB.TIPO) ) {
            return this.creaRegistroOtrosGastos();
        }
        throw new IllegalArgumentException("el tipo:" + tipo + " no corresponde a ningun tipo");
    }
    
    public abstract RegistroCostoVB creaRegistroHojalateriaPintura();
    public abstract RegistroCostoVB creaRegistroMecanica();
    public abstract RegistroCostoVB creaRegistroOtrosGastos();
}
