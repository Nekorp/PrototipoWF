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
package prototipo.modelo.cliente;


public class Cliente {
    private String id;
    private String nombre;
    private String rfc;
    private DomicilioFiscal domicilio;
    private String contacto;
    private Telefono telefonoUno;
    private Telefono telefonoDos;
    private Telefono telefonoTres;
    
    public Cliente() {
        this.id = "";
        this.nombre = "";
        this.rfc = "";
        this.domicilio = new DomicilioFiscal();
        this.contacto = "";
        this.telefonoUno = new Telefono();
        this.telefonoDos = new Telefono();
        this.telefonoTres = new Telefono();
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
}
