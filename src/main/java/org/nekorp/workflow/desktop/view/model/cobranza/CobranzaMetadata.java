/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.model.cobranza;

import org.nekorp.workflow.desktop.servicio.Metadata;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Nekorp
 */
@Component
public class CobranzaMetadata implements Metadata {
    private MonedaVB totalServicio;
    private MonedaVB acuenta;
    private MonedaVB saldo;
    private CobranzaWarningLevel warningLevel;
    public CobranzaMetadata() {
        totalServicio = new MonedaVB();
        acuenta = new MonedaVB();
        saldo = new MonedaVB();
        warningLevel = CobranzaWarningLevel.info;
    }
    public MonedaVB getTotalServicio() {
        return totalServicio;
    }

    public void setTotalServicio(MonedaVB totalServicio) {
        this.totalServicio = totalServicio;
    }

    public MonedaVB getAcuenta() {
        return acuenta;
    }

    public void setAcuenta(MonedaVB acuenta) {
        this.acuenta = acuenta;
    }

    public MonedaVB getSaldo() {
        return saldo;
    }

    public void setSaldo(MonedaVB saldo) {
        this.saldo = saldo;
    }

    public CobranzaWarningLevel getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(CobranzaWarningLevel warningLevel) {
        this.warningLevel = warningLevel;
    }
}
