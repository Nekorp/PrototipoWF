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

import java.util.Date;
import java.util.Objects;

public class ServicioIndex {
    private String idServicio;
    private String idCliente;
    private String numeroSerieAuto;
    private Date fechaRecepcion;
    private String placasAuto;
    private String nombreCliente;

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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
        final ServicioIndex other = (ServicioIndex) obj;
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        return true;
    }

}
