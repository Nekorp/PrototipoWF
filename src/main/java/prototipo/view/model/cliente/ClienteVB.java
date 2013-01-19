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
package prototipo.view.model.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteVB {
    private String id;
    private String nombre;
    private String rfc;
    @Autowired
    private DomicilioFiscalVB domicilio;
    
    public ClienteVB() {
        this.id = "";
        this.nombre = "";
        this.rfc = "";
        this.domicilio = new DomicilioFiscalVB();
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
}
