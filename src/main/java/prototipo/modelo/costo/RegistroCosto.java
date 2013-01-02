/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.costo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import prototipo.modelo.currency.Moneda;

/**
 *
 * @author Marisa
 */
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
}
