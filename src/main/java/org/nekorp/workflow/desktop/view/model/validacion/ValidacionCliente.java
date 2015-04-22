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

package org.nekorp.workflow.desktop.view.model.validacion;

import org.nekorp.workflow.desktop.servicio.Metadata;
/**
 * @author Nekorp
 */
public class ValidacionCliente extends ValidacionParticular implements Metadata {

    private ValidacionGeneralCliente validacionGeneral;
    private EstatusValidacion nombreOk;
    private EstatusValidacion rfcOk;
    private EstatusValidacion calleOk;
    private EstatusValidacion numInteriorOk;
    private EstatusValidacion codigoPostalOk;
    private EstatusValidacion coloniaOk;
    private EstatusValidacion ciudadOk;
    private EstatusValidacion contactoOk;
    private EstatusValidacion emailOk;
    private EstatusValidacion telefonoUnoOk;
    private EstatusValidacion telefonoDosOk;
    private EstatusValidacion telefonoTresOk;
    
    public ValidacionCliente() {
        this.nombreOk = new EstatusValidacion();
        this.rfcOk = new EstatusValidacion();
        this.calleOk = new EstatusValidacion();
        this.numInteriorOk = new EstatusValidacion();
        this.codigoPostalOk = new EstatusValidacion();
        this.coloniaOk = new EstatusValidacion();
        this.ciudadOk = new EstatusValidacion();
        this.contactoOk = new EstatusValidacion();
        this.emailOk = new EstatusValidacion();
        this.telefonoUnoOk = new EstatusValidacion();
        this.telefonoDosOk = new EstatusValidacion();
        this.telefonoTresOk = new EstatusValidacion();
    }

    public EstatusValidacion getNombreOk() {
        return nombreOk;
    }

    public void setNombreOk(EstatusValidacion nombreOk) {
        this.nombreOk = nombreOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getRfcOk() {
        return rfcOk;
    }

    public void setRfcOk(EstatusValidacion rfcOk) {
        this.rfcOk = rfcOk;
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

    public EstatusValidacion getCodigoPostalOk() {
        return codigoPostalOk;
    }

    public void setCodigoPostalOk(EstatusValidacion codigoPostalOk) {
        this.codigoPostalOk = codigoPostalOk;
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

    public EstatusValidacion getContactoOk() {
        return contactoOk;
    }

    public void setContactoOk(EstatusValidacion contactoOk) {
        this.contactoOk = contactoOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getEmailOk() {
        return emailOk;
    }

    public void setEmailOk(EstatusValidacion emailOk) {
        this.emailOk = emailOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getTelefonoUnoOk() {
        return telefonoUnoOk;
    }

    public void setTelefonoUnoOk(EstatusValidacion telefonoUnoOk) {
        this.telefonoUnoOk = telefonoUnoOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getTelefonoDosOk() {
        return telefonoDosOk;
    }

    public void setTelefonoDosOk(EstatusValidacion telefonoDosOk) {
        this.telefonoDosOk = telefonoDosOk;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getTelefonoTresOk() {
        return telefonoTresOk;
    }

    public void setTelefonoTresOk(EstatusValidacion telefonoTresOk) {
        this.telefonoTresOk = telefonoTresOk;
        evaluaTodo(validacionGeneral);
    }
    
    public ValidacionGeneralCliente getValidacionGeneral() {
        return validacionGeneral;
    }

    public void setValidacionGeneral(ValidacionGeneralCliente validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
    }
}
