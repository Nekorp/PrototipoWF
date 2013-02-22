/**
 *   Copyright 2012-2013 Nekorp
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
package org.nekorp.workflow.desktop.view.model.costo;

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;


public abstract class RegistroCostoVB {
    
    private String id;
    private String subtipo;
    private String concepto;
    private Integer cantidad;
    private MonedaVB precioUnitario;
    private MonedaVB precioCliente;

    public RegistroCostoVB() {
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = new MonedaVB();
        this.precioCliente = new MonedaVB();
    }
    
    public abstract String getTipo();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }
    
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public MonedaVB getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(MonedaVB precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public abstract MonedaVB getIvaPrecioUnitario();

    public MonedaVB getPrecioCliente() {
        if (StringUtils.equals("Insumo", this.getSubtipo())) {
            return new MonedaVB();
        }
        return precioCliente;
    }

    public void setPrecioCliente(MonedaVB precioCliente) {
        this.precioCliente = precioCliente;
    }
    
    
    public MonedaVB getUtilidad() {
        if (StringUtils.equals("Insumo", this.getSubtipo())) {
            return new MonedaVB();
        }
        return precioCliente.resta(precioUnitario);
    }
    
    public MonedaVB getSubtotal() {
        if (StringUtils.equals("Insumo", this.getSubtipo())) {
            return precioUnitario.multiplica(cantidad);
        }
        return precioCliente.multiplica(cantidad);
    }
    
    public abstract MonedaVB getIvaSubtotal();
}
