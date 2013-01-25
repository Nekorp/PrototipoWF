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
package prototipo.view.model.bitacora;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventoVB implements Comparable<EventoVB> {

    private Date fechaCreacion;
    private List<EvidenciaVB> evidencia;
    
    public EventoVB() {
        this.fechaCreacion = new Date();
        this.evidencia = new LinkedList<>();
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

     public List<EvidenciaVB> getEvidencia() {
        List<EvidenciaVB> r = new LinkedList<>();
        for (EvidenciaVB x: this.evidencia) {
            r.add(x);
        }
        return r;
    }

    public void setEvidencia(List<EvidenciaVB> param) {
        this.evidencia = new LinkedList<>();
        for (EvidenciaVB x: param) {
            this.evidencia.add(x);
        }
    }

    @Override
    public int compareTo(EventoVB o) {
        return this.fechaCreacion.compareTo(o.getFechaCreacion());
    }

}
