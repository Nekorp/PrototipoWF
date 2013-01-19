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
package prototipo.view.model.costo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import prototipo.view.model.currency.MonedaVB;


@Component("registroCosto")
@Scope("prototype")
public class RegistroCostoVB {
    
    private String tipo;
    private String subtipo;
    private String concepto;
    private Integer cantidad;
    private MonedaVB precioUnitario;
    private MonedaVB precioCliente;

    public RegistroCostoVB() {
        this.tipo = "";
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = new MonedaVB();
        this.precioCliente = new MonedaVB();
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    
    public MonedaVB getIvaPrecioUnitario() {
        if (this.tipo.equals("Otros Gastos")) {
            return new MonedaVB();
        }
        return precioUnitario.multiplica(MonedaVB.valueOf("0.16"));
    }

    public void setPrecioUnitario(MonedaVB precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public MonedaVB getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(MonedaVB precioCliente) {
        this.precioCliente = precioCliente;
    }
    
    public MonedaVB getUtilidad() {
        return precioCliente.resta(precioUnitario);
    }
    
    public MonedaVB getSubtotal() {
        return precioCliente.multiplica(cantidad);
    }
    
    public MonedaVB getIvaSubtotal() {
        if (this.tipo.equals("Otros Gastos")) {
            return new MonedaVB();
        }
        return precioCliente.multiplica(cantidad).multiplica(MonedaVB.valueOf("0.16"));
    }
}
