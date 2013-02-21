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
package org.nekorp.workflow.desktop.view.model.bitacora;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BitacoraVB {

    private List<EventoVB> eventos;

    public BitacoraVB() {
        eventos = new LinkedList<>();
    }
    public List<EventoVB> getEventos() {
        List<EventoVB> r = new LinkedList<>();
        for (EventoVB x: this.eventos) {
            r.add(x);
        }
        return r;
    }

    public void setEventos(List<EventoVB> param) {
        this.eventos = new LinkedList<>();
        for (EventoVB x: param) {
            this.eventos.add(x);
        }
        java.util.Collections.sort(this.eventos);
    }

}
