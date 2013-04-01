/**
 *   Copyright 2013 Nekorp
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

package org.nekorp.workflow.desktop.view.model.validacion;

import org.nekorp.workflow.desktop.servicio.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component
public class ValidacionRegistroCosto extends ValidacionParticular implements Metadata {

    @Autowired
    private ValidacionGeneralRegistroCosto validacionGeneral;
    private EstatusValidacion conceptoOk;
    private EstatusValidacion cantidadOk;
    private EstatusValidacion precioUnitarioOk;
    
    public ValidacionRegistroCosto() {
        this.conceptoOk = new EstatusValidacion();
        this.cantidadOk = new EstatusValidacion();
        this.precioUnitarioOk = new EstatusValidacion();
    }

    public EstatusValidacion getConceptoOk() {
        return conceptoOk;
    }

    public void setConceptoOk(EstatusValidacion conceptoOk) {
        this.conceptoOk = conceptoOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getCantidadOk() {
        return cantidadOk;
    }

    public void setCantidadOk(EstatusValidacion cantidadOk) {
        this.cantidadOk = cantidadOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getPrecioUnitarioOk() {
        return precioUnitarioOk;
    }

    public void setPrecioUnitarioOk(EstatusValidacion precioUnitarioOk) {
        this.precioUnitarioOk = precioUnitarioOk;
        evaluaTodo(validacionGeneral);
    }
    
    public ValidacionGeneralRegistroCosto getValidacionGeneral() {
        return validacionGeneral;
    }

    public void setValidacionGeneral(ValidacionGeneralRegistroCosto validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
    }
}
