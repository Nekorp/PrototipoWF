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


public abstract class RegistroCostoVB implements Comparable<RegistroCostoVB> {
    
    private String id;
    private String subtipo;
    private String concepto;
    private Integer cantidad;
    private MonedaVB precioUnitario;
    private boolean precioUnitarioConIVA;
    private MonedaVB precioCliente;
    private boolean subtotalConIVA;

    public RegistroCostoVB() {
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = new MonedaVB();
        this.precioUnitarioConIVA = true;
        this.precioCliente = new MonedaVB();
        this.subtotalConIVA = true;
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

    public boolean isPrecioUnitarioConIVA() {
        return precioUnitarioConIVA;
    }

    public void setPrecioUnitarioConIVA(boolean precioUnitarioConIVA) {
        this.precioUnitarioConIVA = precioUnitarioConIVA;
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

    public boolean isSubtotalConIVA() {
        return subtotalConIVA;
    }

    public void setSubtotalConIVA(boolean subtotalConIVA) {
        this.subtotalConIVA = subtotalConIVA;
    }

    public abstract MonedaVB getIvaSubtotal();

    @Override
    public int compareTo(RegistroCostoVB o) {
        if (getSubtipoOrder(this.getSubtipo()) > getSubtipoOrder(o.getSubtipo())) {
            return -1;
        }
        if (getSubtipoOrder(this.getSubtipo()) < getSubtipoOrder(o.getSubtipo())) {
            return 1;
        }
        return this.id.compareTo(o.getId());
    }
    
    private int getSubtipoOrder(String sbtp) {
        if (StringUtils.equals(sbtp, "Mano de Obra")) {
            return 3;
        }
        if (StringUtils.equals(sbtp, "Refacciones")) {
            return 2;
        }
        if (StringUtils.equals(sbtp, "Insumo")) {
            return 1;
        }
        return 0;
    }
}
