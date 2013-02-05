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

package prototipo.view.model.cliente;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

/**
 *
 */
@Component
public class ValidacionCliente implements Metadata {

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
        evaluaTodo();
    }

    public ValidacionGeneralCliente getValidacionGeneral() {
        return validacionGeneral;
    }

    public void setValidacionGeneral(ValidacionGeneralCliente validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
        evaluaTodo();
    }

    public EstatusValidacion getCalleOk() {
        return calleOk;
    }

    public void setCalleOk(EstatusValidacion calleOk) {
        this.calleOk = calleOk;
        evaluaTodo();
    }

    public EstatusValidacion getNumInteriorOk() {
        return numInteriorOk;
    }

    public void setNumInteriorOk(EstatusValidacion numInteriorOk) {
        this.numInteriorOk = numInteriorOk;
        evaluaTodo();
    }

    public EstatusValidacion getColoniaOk() {
        return coloniaOk;
    }

    public void setColoniaOk(EstatusValidacion coloniaOk) {
        this.coloniaOk = coloniaOk;
        evaluaTodo();
    }

    public EstatusValidacion getCiudadOk() {
        return ciudadOk;
    }

    public void setCiudadOk(EstatusValidacion ciudadOk) {
        this.ciudadOk = ciudadOk;
        evaluaTodo();
    }
    
    public void evaluaTodo() {
        try {
            for (PropertyDescriptor x: PropertyUtils.getPropertyDescriptors(this)) {
                if (EstatusValidacion.class.isAssignableFrom(x.getPropertyType())) {
                    EstatusValidacion arg = (EstatusValidacion) PropertyUtils.getProperty(this, x.getName());
                    if (!arg.isValido()) {
                        validacionGeneral.setValido(false);
                        return;
                    }
                }
            }
            validacionGeneral.setValido(true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("No se logro evaluar", ex);
        }
    }
}
