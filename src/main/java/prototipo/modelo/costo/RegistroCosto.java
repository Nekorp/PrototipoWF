/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.costo;

import org.springframework.stereotype.Component;

/**
 *
 * @author Marisa
 */
@Component
public class RegistroCosto {
    private String tipo;
    private String subtipo;
    private String concepto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double utilidad;
    private Double precioCliente;
    private Double subtotal;

    public RegistroCosto() {
        this.tipo = "";
        this.subtipo = "";
        this.concepto = "";
        this.cantidad = 0;
        this.precioUnitario = 0d;
        this.utilidad = 0d;
        this.precioCliente = 0d;
        this.subtotal = 0d;
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
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    public Double getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(Double precioCliente) {
        this.precioCliente = precioCliente;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
