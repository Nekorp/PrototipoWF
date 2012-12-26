/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.aop;

import prototipo.modelo.Telefono;
import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.bitacora.Bitacora;

/**
 *
 * @author Marisa
 */
public interface ServicioAop {
    
    public String getId();

    public void setId(String id);

    public Bitacora getBitacora();

    public void setBitacora(Bitacora bitacora);

    public DatosAuto getDatosAuto();

    public void setDatosAuto(DatosAuto datosAuto);

    public String getIdCliente();

    public void setIdCliente(String idCliente);

    public String getContacto();

    public void setContacto(String contacto);

    public Telefono getTelefonoUno();

    public void setTelefonoUno(Telefono telefonoUno);

    public Telefono getTelefonoDos();

    public void setTelefonoDos(Telefono telefonoDos);

    public Telefono getTelefonoTres();

    public void setTelefonoTres(Telefono telefonoTres);

    public String getDescripcion();

    public void setDescripcion(String descripcion);
}
