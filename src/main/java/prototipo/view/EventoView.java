/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import prototipo.modelo.bitacora.Evento;

/**
 *
 * @author Marisa
 */
public abstract class EventoView extends ApplicationView {
    public abstract void setModel(Evento ev);
    public abstract void disposeView();
}
