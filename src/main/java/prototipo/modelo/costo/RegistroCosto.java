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
package prototipo.modelo.costo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import prototipo.modelo.currency.Moneda;


@Component("registroCosto")
@Scope("prototype")
public class RegistroCosto {
    
    private String tipo;
    private String subtipo;
    private String concepto;
    private Integer cantidad;
    private Moneda precioUnitario;
    private Moneda precioCliente;

    public RegistroCosto() {
        this.tipo = "";
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = new Moneda();
        this.precioCliente = new Moneda();
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

    public Moneda getPrecioUnitario() {
        return precioUnitario;
    }
    
    public Moneda getIvaPrecioUnitario() {
        if (this.tipo.equals("Otros Gastos")) {
            return new Moneda();
        }
        return precioUnitario.multiplica(Moneda.valueOf("0.16"));
    }

    public void setPrecioUnitario(Moneda precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Moneda getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(Moneda precioCliente) {
        this.precioCliente = precioCliente;
    }
    
    public Moneda getUtilidad() {
        return precioCliente.resta(precioUnitario);
    }
    
    public Moneda getSubtotal() {
        return precioCliente.multiplica(cantidad);
    }
    
    public Moneda getIvaSubtotal() {
        if (this.tipo.equals("Otros Gastos")) {
            return new Moneda();
        }
        return precioCliente.multiplica(cantidad).multiplica(Moneda.valueOf("0.16"));
    }
}
