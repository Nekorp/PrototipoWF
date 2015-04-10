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

import java.util.Date;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component("pagoCobranza")
@Scope("prototype")
public class PagoCobranzaVB {
    private Date fecha;
    private String responsable;
    private String detalle;
    private MonedaVB monto;
    public PagoCobranzaVB() {
        fecha = new Date();
        responsable = "";
        detalle = "";
        monto = new MonedaVB();
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public MonedaVB getMonto() {
        return monto;
    }

    public void setMonto(MonedaVB monto) {
        this.monto = monto;
    }
}
