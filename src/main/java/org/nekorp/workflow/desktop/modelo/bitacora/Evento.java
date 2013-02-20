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
package org.nekorp.workflow.desktop.modelo.bitacora;

import java.util.Date;
import java.util.List;

public class Evento implements Comparable<Evento> {

    private Long id;
    /**
     * el tipo de evento.
     */
    private String tipo;
    /**
     * el nombre del evento.
     */
    private String nombre;
    /**
     * la fecha en la que fue creado el evento.
     */
    private Date fechaCreacion;
    /**
     * el responsable de crear el evento.
     */
    private String responsable;
    /**
     * la evidencia anexada al evento
     */
    private List<Evidencia> evidencia;
    /**
     * la fecha en la que sucedio el evento.
     */
    private Date fecha;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<Evidencia> getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(List<Evidencia> evidencia) {
        this.evidencia = evidencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Evento o) {
        return this.fechaCreacion.compareTo(o.getFechaCreacion());
    }

}
