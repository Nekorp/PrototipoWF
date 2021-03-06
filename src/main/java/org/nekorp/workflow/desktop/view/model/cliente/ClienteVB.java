/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.model.cliente;


import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 
 * @author Nekorp
 */
public class ClienteVB {
    private String id;
    @NotBlank
    @Pattern(regexp="([\\w\\p{IsLatin}\\p{Punct}]?)|(^[\\w\\p{IsLatin}\\p{Punct}]+( [\\w\\p{IsLatin}\\p{Punct}]+)*)")
    private String nombre;
    @Pattern(regexp="([\\w\\p{IsLatin}\\p{Punct}]?)|(^[\\w]+([ -][\\w]+)*)")
    private String rfc;
    private DomicilioFiscalVB domicilio;
    @Email
    private String email;
    @Pattern(regexp="([\\w\\p{IsLatin}\\p{Punct}]?)|(^[\\w\\p{IsLatin}\\p{Punct}]+( [\\w\\p{IsLatin}\\p{Punct}]+)*)")
    private String contacto;
    private TelefonoVB telefonoUno;
    private TelefonoVB telefonoDos;
    private TelefonoVB telefonoTres;
    
    public ClienteVB() {
        this.id = "";
        this.nombre = "";
        this.rfc = "";
        this.email = "";
        this.contacto = "";
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

    public DomicilioFiscalVB getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioFiscalVB domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
