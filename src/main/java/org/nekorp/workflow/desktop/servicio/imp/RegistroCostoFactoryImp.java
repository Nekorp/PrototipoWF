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
package org.nekorp.workflow.desktop.servicio.imp;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.servicio.RegistroCostoFactory;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroHojalateriaPinturaVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroMecanicaVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroOtrosGastosVB;

/**
 * @author Nekorp
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

    @Override
    public RegistroCostoVB copyRegistroCosto(RegistroCostoVB origen) {
        RegistroCostoVB response = null;
        if (StringUtils.equals(origen.getTipo(), RegistroHojalateriaPinturaVB.TIPO)) {
            response = this.creaRegistroHojalateriaPintura();
        }
        if (StringUtils.equals(origen.getTipo(), RegistroMecanicaVB.TIPO)) {
            response = this.creaRegistroMecanica();
        }
        if (StringUtils.equals(origen.getTipo(), RegistroOtrosGastosVB.TIPO) ) {
            response = this.creaRegistroOtrosGastos();
        }
        if (response == null) {
            throw new IllegalArgumentException("el tipo:" + origen.getTipo() + " no corresponde a ningun tipo");
        }
        response.setCantidad(origen.getCantidad());
        response.setConcepto(origen.getConcepto());
        response.setFechaCreacion(new Date());
        response.setPrecioCliente(origen.getPrecioCliente());
        response.setPrecioUnitario(origen.getPrecioUnitario());
        response.setPrecioUnitarioConIVA(origen.isPrecioUnitarioConIVA());
        response.setSubtipo(origen.getSubtipo());
        response.setSubtotalConIVA(origen.isSubtotalConIVA());
        return response;
    }
    
    
    public abstract RegistroCostoVB creaRegistroHojalateriaPintura();
    public abstract RegistroCostoVB creaRegistroMecanica();
    public abstract RegistroCostoVB creaRegistroOtrosGastos();
}
