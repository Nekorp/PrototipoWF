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
package prototipo.view.model;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import prototipo.view.model.auto.DatosAutoVB;
import prototipo.view.model.bitacora.BitacoraVB;
import prototipo.view.model.costo.RegistroCostoVB;

@Component
public class ServicioVB {
    private String id;
    @Autowired
    private BitacoraVB bitacora;
    @Autowired
    private DatosAutoVB datosAuto;
    
    private String idCliente;
    private String contacto;
    
    @Autowired
    @Qualifier(value="telefonoUno")
    private TelefonoVB telefonoUno;
    
    @Autowired
    @Qualifier(value="telefonoDos")
    private TelefonoVB telefonoDos;
    
    @Autowired
    @Qualifier(value="telefonoTres")
    private TelefonoVB telefonoTres;
    
    private String descripcion;

    private List<RegistroCostoVB> costos;
    
    public ServicioVB() {
        this.id = "";
        this.bitacora = new BitacoraVB();
        this.datosAuto = new DatosAutoVB();
        this.idCliente = "";
        this.contacto = "";
        this.telefonoUno = new TelefonoVB();
        this.telefonoDos = new TelefonoVB();
        this.telefonoTres = new TelefonoVB();
        this.descripcion = "";
        this.costos = new LinkedList<>();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BitacoraVB getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraVB bitacora) {
        this.bitacora = bitacora;
    }

    public DatosAutoVB getDatosAuto() {
        return datosAuto;
    }

    public void setDatosAuto(DatosAutoVB datosAuto) {
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

    public TelefonoVB getTelefonoUno() {
        return telefonoUno;
    }

    public void setTelefonoUno(TelefonoVB telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    public TelefonoVB getTelefonoDos() {
        return telefonoDos;
    }

    public void setTelefonoDos(TelefonoVB telefonoDos) {
        this.telefonoDos = telefonoDos;
    }

    public TelefonoVB getTelefonoTres() {
        return telefonoTres;
    }

    public void setTelefonoTres(TelefonoVB telefonoTres) {
        this.telefonoTres = telefonoTres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RegistroCostoVB> getCostos() {
        LinkedList<RegistroCostoVB> r = new LinkedList<>();
        for (RegistroCostoVB x: this.costos) {
            r.add(x);
        }
        return r;
    }

    public void setCostos(List<RegistroCostoVB> param) {
        this.costos = new LinkedList<>();
        for (RegistroCostoVB x: param) {
            this.costos.add(x);
        }
    }
}