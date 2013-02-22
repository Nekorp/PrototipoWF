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
package org.nekorp.workflow.desktop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.apache.commons.beanutils.BeanUtils;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 
 */
public abstract class BitacoraView extends ApplicationView implements Bindable, EventoViewListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraView.class);
    
    private LinkedList<Object> ignore;
    private LinkedList<EventoVB> modelo;
    private Object target;
    private String property;
    @Autowired
    private EventoServicioFactory eventoFactory;
    
    public BitacoraView() {
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        //no hay cosas que inhabilitar
    }
    @Override
    public void iniciaVista() {
        initComponents();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            LinkedList<EventoVB> param = (LinkedList<EventoVB>) value;
            LinkedList<EventoVB> borrar = new LinkedList<>();
            for (EventoVB obj: modelo){
                borrar.add(obj);
            }
            for (int i=0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if(param.get(i).equals(this.modelo.get(i))){
                        borrar.remove(this.modelo.get(i));
                    } else {
                        this.modelo.add(i, param.get(i));
                        addEventoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addEventoView(this.modelo.get(i), i);
                }
            }
            for (EventoVB x: borrar) {
                removeEvento(x);
            }
            this.updateUI();
        }
    }
    
    private void addEventoView(EventoVB obj, int index) {
        EventoView entrada = null;
        if (obj instanceof EventoGeneralVB) {
            entrada = getEventoGeneralView();
        }
        if (obj instanceof EventoEntregaVB) {
            entrada = getEventoEntregaView();
        }
        if (obj instanceof EventoSistemaVB) {
            entrada = getEventoSistemaView();
        }
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            this.entradas.add(entrada, index);
        }
    }
    
    private void removeEvento(EventoVB obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        EventoView view = (EventoView) this.entradas.getComponent(index);
        view.disposeView();
        this.entradas.remove(view);
    }
    
    @Override
    public void deleteEvent(EventoVB ev){
        LinkedList<EventoVB> value = new LinkedList<>();
        for (EventoVB x: this.modelo) {
            value.add(x);
        }
        value.remove(ev);
        actualizaModelo(value);
    }
    
    private void actualizaModelo(LinkedList<EventoVB> value) {
        try {
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BitacoraView.LOGGER.error(ex);
        }
    }
    
    public abstract EventoView getEventoGeneralView();
    
    public abstract EventoView getEventoEntregaView();
    
    public abstract EventoView getEventoSistemaView();

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
        this.target = target;
        this.property = property;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        agregarBitacora = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        entradas = new javax.swing.JPanel();

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        agregarBitacora.setText("Nueva Entrada");
        agregarBitacora.setFocusable(false);
        agregarBitacora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarBitacora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBitacoraActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarBitacora);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        entradas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        entradas.setLayout(new javax.swing.BoxLayout(entradas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(entradas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBitacoraActionPerformed
        Object[] possibilities = {"Entrada de Auto", "Salida de Auto", "Otro"};
        String s = (String)javax.swing.JOptionPane.showInputDialog(
                            this,
                            "Eliga el evento",
                            "Seleccion evento",
                            javax.swing.JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            "Entrega Auto");
        if (s == null) {
            return;
        }
        LinkedList<EventoVB> value = new LinkedList<>();
        for (EventoVB x: this.modelo) {
            value.add(x);
        }
        if (s.compareTo("Otro") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoGeneralVB.class));
        } else {
            EventoEntregaVB nuevo = this.eventoFactory.creaEvento(EventoEntregaVB.class);
            nuevo.setNombreEvento(s);
            value.add(nuevo);
        }
        actualizaModelo(value);
        //javax.swing.Box.createRigidArea(new java.awt.Dimension(5,0));
    }//GEN-LAST:event_agregarBitacoraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBitacora;
    private javax.swing.JPanel entradas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
