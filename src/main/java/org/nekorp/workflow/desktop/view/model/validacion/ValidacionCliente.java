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
import org.nekorp.workflow.desktop.view.model.cliente.EstatusValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component
public class ValidacionCliente extends ValidacionParticular implements Metadata {

    @Autowired
    private ValidacionGeneralCliente validacionGeneral;
    private EstatusValidacion nombreOk;
    private EstatusValidacion calleOk;
    private EstatusValidacion numInteriorOk;
    private EstatusValidacion coloniaOk;
    private EstatusValidacion ciudadOk;
    
    public ValidacionCliente() {
        this.nombreOk = new EstatusValidacion();
        this.calleOk = new EstatusValidacion();
        this.numInteriorOk = new EstatusValidacion();
        this.coloniaOk = new EstatusValidacion();
        this.ciudadOk = new EstatusValidacion();
    }

    public EstatusValidacion getNombreOk() {
        return nombreOk;
    }

    public void setNombreOk(EstatusValidacion nombreOk) {
        this.nombreOk = nombreOk;
        evaluaTodo(validacionGeneral);
    }

    public ValidacionGeneralCliente getValidacionGeneral() {
        return validacionGeneral;
    }

    public void setValidacionGeneral(ValidacionGeneralCliente validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getCalleOk() {
        return calleOk;
    }

    public void setCalleOk(EstatusValidacion calleOk) {
        this.calleOk = calleOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getNumInteriorOk() {
        return numInteriorOk;
    }

    public void setNumInteriorOk(EstatusValidacion numInteriorOk) {
        this.numInteriorOk = numInteriorOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getColoniaOk() {
        return coloniaOk;
    }

    public void setColoniaOk(EstatusValidacion coloniaOk) {
        this.coloniaOk = coloniaOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getCiudadOk() {
        return ciudadOk;
    }

    public void setCiudadOk(EstatusValidacion ciudadOk) {
        this.ciudadOk = ciudadOk;
        evaluaTodo(validacionGeneral);
    }
}
