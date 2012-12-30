/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Marisa
 */
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
