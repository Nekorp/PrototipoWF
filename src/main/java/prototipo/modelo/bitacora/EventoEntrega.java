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
package prototipo.modelo.bitacora;

import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("eventoEntrega")
@Scope("prototype")
public class EventoEntrega extends Evento {
    private String entrego;
    private String recibio;
    private Date fecha;
    private String nombreEvento;

    public EventoEntrega() {
        this.entrego = "";
        this.recibio = "";
        this.fecha = new Date();
        this.nombreEvento = "";
    }
    
    public String getEntrego() {
        return entrego;
    }

    public void setEntrego(String entrego) {
        this.entrego = entrego;
    }

    public String getRecibio() {
        return recibio;
    }

    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

}
