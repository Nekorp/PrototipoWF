/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo;

import org.springframework.stereotype.Component;
import prototipo.modelo.aop.ServicioAop;
import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.bitacora.Bitacora;

/**
 * 
 * @author Marisa
 */
@Component
public class Servicio implements ServicioAop {
    private String id;
    private Bitacora bitacora;
    private DatosAuto datosAuto;
    private String idCliente;
    private String contacto;
    private Telefono telefonoUno;
    private Telefono telefonoDos;
    private Telefono telefonoTres;
    private String descripcion;

    public Servicio() {
        this.id = "";
        this.bitacora = new Bitacora();
        this.datosAuto = new DatosAuto();
        this.idCliente = "";
        this.contacto = "";
        this.telefonoUno = new Telefono();
        this.telefonoDos = new Telefono();
        this.telefonoTres = new Telefono();
        this.descripcion = "";
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Bitacora getBitacora() {
        return bitacora;
    }

    @Override
    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    @Override
    public DatosAuto getDatosAuto() {
        return datosAuto;
    }

    @Override
    public void setDatosAuto(DatosAuto datosAuto) {
        this.datosAuto = datosAuto;
    }

    @Override
    public String getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String getContacto() {
        return contacto;
    }

    @Override
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public Telefono getTelefonoUno() {
        return telefonoUno;
    }

    @Override
    public void setTelefonoUno(Telefono telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    @Override
    public Telefono getTelefonoDos() {
        return telefonoDos;
    }

    @Override
    public void setTelefonoDos(Telefono telefonoDos) {
        this.telefonoDos = telefonoDos;
    }

    @Override
    public Telefono getTelefonoTres() {
        return telefonoTres;
    }

    @Override
    public void setTelefonoTres(Telefono telefonoTres) {
        this.telefonoTres = telefonoTres;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
