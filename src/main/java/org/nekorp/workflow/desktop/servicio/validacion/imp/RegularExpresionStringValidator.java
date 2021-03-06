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

package org.nekorp.workflow.desktop.servicio.validacion.imp;

import org.nekorp.workflow.desktop.servicio.validacion.CalifacacionValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.DetalleValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ValidationContext;

/**
 *
 */
public class RegularExpresionStringValidator extends PropertyValidator {

    private String noMatchMessage;
    private String regExp;
    @Override
    public void doFinalValidation(Object o, DetalleValidacion detalle, ValidationContext ctx) {
        try {
            String dato = (String) o;
            if (dato.matches(regExp)) {
                detalle.setCalificacion(CalifacacionValidacion.VALIDO);
            } else {
                detalle.setCalificacion(CalifacacionValidacion.INVALIDO);
                detalle.setMensaje(noMatchMessage);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Esto no es un string" , e);
        } catch (NullPointerException e) {
            detalle.setCalificacion(CalifacacionValidacion.INDETERMINADO);
        }
    }

    public void setNoMatchMessage(String noMatchMessage) {
        this.noMatchMessage = noMatchMessage;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }
    
}
