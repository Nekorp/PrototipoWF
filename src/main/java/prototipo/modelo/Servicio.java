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
import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.bitacora.Bitacora;
import prototipo.modelo.costo.RegistroCosto;

public class Servicio {
    private String id;
    private Bitacora bitacora;
    private DatosAuto datosAuto;
    private String idCliente;
    private String descripcion;
    private List<RegistroCosto> costos;
    public Servicio() {
        this.id = "";
        this.bitacora = new Bitacora();
        this.datosAuto = new DatosAuto();
        this.idCliente = "";
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
