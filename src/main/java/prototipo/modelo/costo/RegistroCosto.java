/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.costo;

import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    private Double precioUnitario;
    private Double precioCliente;

    public RegistroCosto() {
        this.tipo = "";
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = 0d;
        this.precioCliente = 0d;
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getUtilidad() {
        BigDecimal d1 = new BigDecimal(this.precioCliente);
        BigDecimal d2 = new BigDecimal(this.precioUnitario);
        BigDecimal r = d1.subtract(d2);
        return r.doubleValue();
    }

    public Double getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(Double precioCliente) {
        this.precioCliente = precioCliente;
    }

    public Double getSubtotal() {
        BigDecimal d1 = new BigDecimal(this.precioCliente);
        BigDecimal d2 = new BigDecimal(this.cantidad);
        BigDecimal r = d1.multiply(d2);
        return r.doubleValue();
    }
}
