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

import java.util.Date;
import java.util.Objects;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaMetadata;
/**
 * 
 * @author Nekorp
 */
public class ServicioIndexVB {
    private String status;
    private Long idServicio;
    private String descripcion;
    private String idCliente;
    private String numeroSerieAuto;
    private Date fechaRecepcion;
    private String placasAuto;
    private String tipo;
    private String nombreCliente;
    private CobranzaMetadata cobranzaMetadata;
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroSerieAuto() {
        return numeroSerieAuto;
    }

    public void setNumeroSerieAuto(String numeroSerieAuto) {
        this.numeroSerieAuto = numeroSerieAuto;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getPlacasAuto() {
        return placasAuto;
    }

    public void setPlacasAuto(String placasAuto) {
        this.placasAuto = placasAuto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public CobranzaMetadata getCobranzaMetadata() {
        return cobranzaMetadata;
    }

    public void setCobranzaMetadata(CobranzaMetadata cobranzaMetadata) {
        this.cobranzaMetadata = cobranzaMetadata;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idServicio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicioIndexVB other = (ServicioIndexVB) obj;
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        return true;
    }

}
