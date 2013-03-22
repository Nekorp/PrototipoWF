/**
 *   Copyright 2013 Nekorp
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
package org.nekorp.workflow.desktop.view.quick;

import java.util.LinkedList;
import org.nekorp.workflow.desktop.view.ApplicationView;
import org.nekorp.workflow.desktop.view.EventoExtraSeparador;
import org.nekorp.workflow.desktop.view.EventoView;
import org.nekorp.workflow.desktop.view.ViewValidIndicator;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;

/**
 *
 */
public abstract class BitacoraPreview extends ApplicationView implements Bindable {
    private LinkedList<Object> ignore;
    private LinkedList<EventoVB> modelo;
    /**
     * Creates new form BitacoraPreview
     */
    public BitacoraPreview() {
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    }

    @Override
    public void setEditableStatus(boolean value) {
        //no hacer nada aun.
    }

    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            LinkedList<EventoVB> param = (LinkedList<EventoVB>) value;
            LinkedList<EventoVB> borrar = new LinkedList<>();
            for (EventoVB obj: modelo) {
                if (!param.contains(obj)) {
                    borrar.add(obj);
                }
            }
            for (EventoVB x: borrar) {
                removeEvento(x);
            }
            for (int i = 0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if (!param.get(i).equals(this.modelo.get(i))) {
                        this.modelo.add(i, param.get(i));
                        addEventoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addEventoView(this.modelo.get(i), i);
                }
            }
            this.updateUI();
        }
    }
    
    private void addEventoView(EventoVB obj, int index) {
        EventoView entrada = null;
        int realIndex = index * 2;
        if (obj instanceof EventoGeneralVB) {
            entrada = getEventoGeneralView();
        }
        if (obj instanceof EventoEntregaVB) {
            entrada = getEventoEntregaView();
        }
        if (obj instanceof EventoSistemaVB) {
            entrada = getEventoSistemaView();
        }
        if (obj instanceof EventoReclamacionVB) {
            entrada = getEventoReclamacionView();
        }
        if (obj instanceof EventoFinServicioVB) {
            entrada = getEventoFinServicioView();
        }
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            this.entradas.add(entrada, realIndex);
            entrada.validate();
            entradas.add(new EventoExtraSeparador(), realIndex + 1);
        }
    }
    
    private void removeEvento(EventoVB obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        int viewIndex = index * 2;
        EventoView view = (EventoView) this.entradas.getComponent(viewIndex);
        view.disposeView();
        this.entradas.remove(viewIndex + 1);
        this.entradas.remove(view);
    }
    
    public abstract EventoView getEventoGeneralView();
    
    public abstract EventoView getEventoEntregaView();
    
    public abstract EventoView getEventoSistemaView();
    
    public abstract EventoView getEventoReclamacionView();
    
    public abstract EventoView getEventoFinServicioView();

    @Override
    public void ignoreUpdate(Object value) {
         ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return this.modelo;
    }

    @Override
    public void bindListener(Object target, String property) {
        //esta cosa no actualiza
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        entradas = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        entradas.setLayout(new javax.swing.BoxLayout(entradas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(entradas);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel entradas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
