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
package org.nekorp.workflow.desktop.view.model.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.nekorp.workflow.desktop.view.model.auto.AutoVB;
import org.nekorp.workflow.desktop.view.model.auto.DatosAutoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraVB;
import org.nekorp.workflow.desktop.view.model.cliente.ClienteVB;
import org.nekorp.workflow.desktop.view.model.cobranza.DatosCobranzaVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
/**
 * @author Nekorp
 */
public class ServicioVB {
    private String id;
    private BitacoraVB bitacora;
    private AutoVB auto;
    private DatosAutoVB datosAuto;
    private ClienteVB cliente;
    @NotBlank
    private String descripcion;
    private List<RegistroCostoVB> costos;
    private Date fechaInicio;
    private String status;
    private DatosCobranzaVB cobranza;
    private List<GrupoCostoVB> gruposCosto;
    
    public ServicioVB() {
        this.id = "";
        this.descripcion = "";
        this.costos = new LinkedList<>();
        fechaInicio = new Date();
        status = "Activo";
        gruposCosto = new ArrayList<>();
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

    public AutoVB getAuto() {
        return auto;
    }

    public void setAuto(AutoVB auto) {
        this.auto = auto;
    }

    public DatosAutoVB getDatosAuto() {
        return datosAuto;
    }

    public void setDatosAuto(DatosAutoVB datosAuto) {
        this.datosAuto = datosAuto;
    }

    public ClienteVB getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVB cliente) {
        this.cliente = cliente;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DatosCobranzaVB getCobranza() {
        return cobranza;
    }

    public void setCobranza(DatosCobranzaVB cobranza) {
        this.cobranza = cobranza;
    }

    public List<GrupoCostoVB> getGruposCosto() {
        ArrayList<GrupoCostoVB> r = new ArrayList<>();
        for (GrupoCostoVB x: this.gruposCosto) {
            r.add(x);
        }
        return r;
    }

    public void setGruposCosto(List<GrupoCostoVB> gruposCosto) {
        this.gruposCosto = new LinkedList<>();
        for (GrupoCostoVB x: gruposCosto) {
            this.gruposCosto.add(x);
        }
    }
}
