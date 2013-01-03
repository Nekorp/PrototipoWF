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
package prototipo.modelo;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.bitacora.Bitacora;
import prototipo.modelo.costo.RegistroCosto;

@Component
public class Servicio {
    private String id;
    @Autowired
    private Bitacora bitacora;
    @Autowired
    private DatosAuto datosAuto;
    
    private String idCliente;
    private String contacto;
    
    @Autowired
    @Qualifier(value="telefonoUno")
    private Telefono telefonoUno;
    
    @Autowired
    @Qualifier(value="telefonoDos")
    private Telefono telefonoDos;
    
    @Autowired
    @Qualifier(value="telefonoTres")
    private Telefono telefonoTres;
    
    private String descripcion;

    private List<RegistroCosto> costos;
    
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
        this.costos = new LinkedList<>();
    }
    
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

    public List<RegistroCosto> getCostos() {
        LinkedList<RegistroCosto> r = new LinkedList<>();
        for (RegistroCosto x: this.costos) {
            r.add(x);
        }
        return r;
    }

    public void setCostos(List<RegistroCosto> param) {
        this.costos = new LinkedList<>();
        for (RegistroCosto x: param) {
            this.costos.add(x);
        }
    }
}
