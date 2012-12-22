/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo;

import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.bitacora.Bitacora;

/**
 *
 * @author Marisa
 */
public class Servicio {
    private String id;
    private Bitacora bitacora;
    private DatosAuto datosAuto;
    private String idCliente;
    private String contacto;
    private Telefono telefonoUno;
    private Telefono telefonoDos;
    private Telefono telefonoTres;
    private String descripcion;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Bitacora getBitacora() {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public DatosAuto getDatosAuto() {
        return datosAuto;
    }

    public void setDatosAuto(DatosAuto datosAuto) {
        this.datosAuto = datosAuto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Telefono getTelefonoUno() {
        return telefonoUno;
    }

    public void setTelefonoUno(Telefono telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    public Telefono getTelefonoDos() {
        return telefonoDos;
    }

    public void setTelefonoDos(Telefono telefonoDos) {
        this.telefonoDos = telefonoDos;
    }

    public Telefono getTelefonoTres() {
        return telefonoTres;
    }

    public void setTelefonoTres(Telefono telefonoTres) {
        this.telefonoTres = telefonoTres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
