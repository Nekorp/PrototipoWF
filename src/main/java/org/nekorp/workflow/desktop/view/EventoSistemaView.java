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
package org.nekorp.workflow.desktop.view;

import org.apache.commons.beanutils.Converter;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component("eventoSistemaView")
@Scope("prototype")
public class EventoSistemaView extends EventoView {

    @Autowired
    private Converter dateConverter;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    private EventoSistemaVB modelo;
    
    /**
     * Creates new form EventoSistemaView
     */
    public EventoSistemaView() {
        initComponents();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        this.setBindings();
    }

    @Override
    public void setEditableStatus(boolean value) {
        //nada que hacer.
    }
    @Override
    public void setModel(EventoVB ev) {
        this.modelo = (EventoSistemaVB) ev;
    }
    
    @Override
    public void disposeView() {
        this.removeBindings();
    }
    
    private void setBindings() {
        this.bindingManager.registerBind(modelo, "fechaCreacion", (Bindable) this.fechaCreacionLabel);
        this.bindingManager.registerBind(modelo, "nombre", (Bindable) this.nombreEvento);
    }
    
    private void removeBindings() {
        this.bindingManager.removeBind(modelo, "fechaCreacion", (Bindable) this.fechaCreacionLabel);
        this.bindingManager.removeBind(modelo, "nombre", (Bindable) this.nombreEvento);
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    @Override
    public void requestFocusOnMainInput() {
        //no hacer nada
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreEvento = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJLabel();
        fechaCreacionLabel = new org.nekorp.workflow.desktop.view.binding.FormatedJLabel(dateConverter);
        jSeparator1 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(32767, 41));

        nombreEvento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nombreEvento.setText("  ");

        fechaCreacionLabel.setText("12/12/2012");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaCreacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreEvento)
                    .addComponent(fechaCreacionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fechaCreacionLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nombreEvento;
    // End of variables declaration//GEN-END:variables
}
