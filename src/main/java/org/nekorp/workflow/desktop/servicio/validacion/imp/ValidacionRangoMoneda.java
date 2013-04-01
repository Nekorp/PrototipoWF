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
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;

/**
 *
 */
public class ValidacionRangoMoneda extends PropertyValidator {

    private Double min;
    private Double max;
    private boolean incMin;
    private boolean incMax;
    private String outOfRangeMessage;
    @Override
    public void doFinalValidation(Object o, DetalleValidacion detalle, ValidationContext ctx) {
        try {
            MonedaVB value = (MonedaVB) o;
            boolean minOk;
            boolean maxOk;
            if (min != null) {
                if (incMin) {
                    minOk = value.doubleValue() >= min;
                } else {
                    minOk = value.doubleValue() > min;
                }
            } else {
                minOk = true;
            }
            if (max != null) {
                if (incMax) {
                    maxOk = value.doubleValue() >= max;
                } else {
                    maxOk = value.doubleValue() > max;
                }
            } else {
                maxOk = true;
            }
            if (minOk && maxOk) {
                detalle.setCalificacion(CalifacacionValidacion.VALIDO);
            } else {
                detalle.setCalificacion(CalifacacionValidacion.INVALIDO);
                detalle.setMensaje(outOfRangeMessage);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Esto no es un Integer" , e);
        } catch (NullPointerException e) {
            detalle.setCalificacion(CalifacacionValidacion.INDETERMINADO);
        }
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public void setIncMin(boolean incMin) {
        this.incMin = incMin;
    }

    public void setIncMax(boolean incMax) {
        this.incMax = incMax;
    }

    public void setOutOfRangeMessage(String outOfRangeMessage) {
        this.outOfRangeMessage = outOfRangeMessage;
    }
}
