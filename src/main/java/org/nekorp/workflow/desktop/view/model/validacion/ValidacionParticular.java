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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.ViewValidIndicator;

/**
 *
 */
public abstract class ValidacionParticular {

    public void evaluaTodo(ViewValidIndicator evaluacionGeneral) {
        try {
            for (PropertyDescriptor x: PropertyUtils.getPropertyDescriptors(this)) {
                if (EstatusValidacion.class.isAssignableFrom(x.getPropertyType())) {
                    EstatusValidacion arg = (EstatusValidacion) PropertyUtils.getProperty(this, x.getName());
                    if (!arg.isValido()) {
                        evaluacionGeneral.setValido(false);
                        return;
                    }
                }
            }
            evaluacionGeneral.setValido(true);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("No se logro evaluar", ex);
        }
    }
    
    public String concatenaErrores() {
        try {
            String result = "";
            for (PropertyDescriptor x: PropertyUtils.getPropertyDescriptors(this)) {
                if (EstatusValidacion.class.isAssignableFrom(x.getPropertyType())) {
                    EstatusValidacion arg = (EstatusValidacion) PropertyUtils.getProperty(this, x.getName());
                    if (!arg.isValido()) {
                        if (!StringUtils.isEmpty(result)) {
                            result = result + "\n";
                        }
                        result = result + arg.getDetalle();
                    }
                }
            }
            return result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("No se logro evaluar", ex);
        }
    }
}
