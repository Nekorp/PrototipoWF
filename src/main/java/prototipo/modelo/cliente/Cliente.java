/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Marisa
 */
@Component
public class Cliente {
    private String id;
    private String nombre;
    private String rfc;
    @Autowired
    private DomicilioFiscal domicilio;
    
    public Cliente() {
        this.id = "";
        this.nombre = "";
        this.rfc = "";
        this.domicilio = new DomicilioFiscal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public DomicilioFiscal getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioFiscal domicilio) {
        this.domicilio = domicilio;
    }
}
