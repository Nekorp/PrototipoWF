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

package org.nekorp.workflow.desktop.servicio.imp;

/**
 *
 */
public class RangoNumero {

    private Long limiteInferior;
    private Long limiteSuperior;
    private boolean incluyente = true;
    
    public boolean dentroDelRango(Long value) {
        return mayorQue(value, limiteInferior) && menorQue(value, limiteSuperior);
    }
    
    private boolean menorQue(Long valueA, Long valueB) {
        if (valueB == null) {
            return true;
        } else {
            if (incluyente) {
                return valueA <= valueB;
            } else {
                return valueA < valueB;
            }
        }
    }
    
    private boolean mayorQue(Long valueA, Long valueB) {
        if (valueB == null) {
            return false;
        } else {
            if (incluyente) {
                return valueA >= valueB;
            } else {
                return valueA > valueB;
            }
        }
    }

    public Long getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Long limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Long getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Long limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public boolean isIncluyente() {
        return incluyente;
    }

    public void setIncluyente(boolean incluyente) {
        this.incluyente = incluyente;
    }
}
