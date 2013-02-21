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

import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.resource.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 */
@Component("eventoGeneralView")
@Scope("prototype")
public class EventoGeneralView extends EventoView {
    @Autowired
    private EvidenciaView evidenciaView;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EventoViewListener elListener;
    @Autowired
    private DateConverter dateConverter;
    private EventoGeneralVB modelo;
    /**
     * Creates new form EntradaBitacora
     */
    public EventoGeneralView() {
        super();
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
        this.modelo = (EventoGeneralVB) ev;
    }
    
    @Override
    public void disposeView() {
        this.removeBindings();
    }
    
    private void setBindings() {
        this.bindingManager.registerBind(modelo, "fechaCreacion", (Bindable) this.fechaCreacionLabel);
        this.bindingManager.registerBind(modelo, "detalle", (Bindable) this.detalle);
        this.bindingManager.registerBind(modelo, "fechaEvento", (Bindable) this.fechaEvento);
        this.bindingManager.registerBind(modelo, "etiquetas", (Bindable) this.etiquietas);
    }
    
    private void removeBindings() {
        this.bindingManager.removeBind(modelo, "fechaCreacion", (Bindable) this.fechaCreacionLabel);
        this.bindingManager.removeBind(modelo, "detalle", (Bindable) this.detalle);
        this.bindingManager.removeBind(modelo, "fechaEvento", (Bindable) this.fechaEvento);
        this.bindingManager.removeBind(modelo, "etiquetas", (Bindable) this.etiquietas);
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        detalle = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        evidencia = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fechaEvento = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJSppiner();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechaCreacionLabel = new org.nekorp.workflow.desktop.view.binding.FormatedJLabel(dateConverter);
        jLabel1 = new javax.swing.JLabel();
        etiquietas = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        detalle.setColumns(20);
        detalle.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        detalle.setLineWrap(true);
        detalle.setRows(2);
        jScrollPane2.setViewportView(detalle);

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        evidencia.setText("Evidencias");
        evidencia.setFocusable(false);
        evidencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        evidencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        evidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evidenciaActionPerformed(evt);
            }
        });
        jToolBar1.add(evidencia);

        borrar.setText("Borrar");
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jToolBar1.add(borrar);

        fechaEvento.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        jLabel4.setText("Fecha evento:");

        jLabel3.setText("Etiquetas:");

        fechaCreacionLabel.setText("12/12/2012");

        jLabel1.setText("Creado el dia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquietas, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaCreacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(fechaCreacionLabel)
                    .addComponent(jLabel1)
                    .addComponent(etiquietas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        this.elListener.deleteEvent(modelo);
    }//GEN-LAST:event_borrarActionPerformed

    private void evidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evidenciaActionPerformed
        this.evidenciaView.iniciaVista();//TODO Eliminar esto
        this.bindingManager.registerBind(modelo, "evidencia", evidenciaView);
        evidenciaView.setVisible(true);
        this.bindingManager.clearBindings(evidenciaView);
    }//GEN-LAST:event_evidenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JTextArea detalle;
    private javax.swing.JTextField etiquietas;
    private javax.swing.JButton evidencia;
    private javax.swing.JLabel fechaCreacionLabel;
    private javax.swing.JSpinner fechaEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    
}