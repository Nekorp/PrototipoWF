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
package org.nekorp.workflow.desktop.modelo.cliente;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
    private Long id;
    private String nombre;
    private String rfc;
    private DomicilioFiscal domicilio;
    private String contacto;
    private List<Telefono> telefonoContacto;
    
    public Cliente() {
        this.nombre = "";
        this.rfc = "";
        this.domicilio = new DomicilioFiscal();
        this.contacto= "";
        telefonoContacto = new LinkedList<>();
        telefonoContacto.add(new Telefono());
        telefonoContacto.add(new Telefono());
        telefonoContacto.add(new Telefono());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Telefono> getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(List<Telefono> telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
