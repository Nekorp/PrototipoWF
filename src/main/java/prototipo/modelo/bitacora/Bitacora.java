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

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Bitacora {

    private List<Evento> eventos;

    public Bitacora() {
        eventos = new LinkedList<>();
    }
    public List<Evento> getEventos() {
        List<Evento> r = new LinkedList<>();
        for (Evento x: this.eventos) {
            r.add(x);
        }
        return r;
    }

    public void setEventos(List<Evento> param) {
        this.eventos = new LinkedList<>();
        for (Evento x: param) {
            this.eventos.add(x);
        }
        java.util.Collections.sort(this.eventos);
    }

}
