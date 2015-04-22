/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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
import org.nekorp.workflow.desktop.view.model.bitacora.EdicionEventoEvidenciaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 */
@Component("eventoFinServicioView")
@Scope("prototype")
public class EventoFinServicioView extends EventoView {
    @Autowired
    private EdicionEventoEvidenciaVB edicionEventoEvidencia;
    @Autowired
    @Qualifier("evidenciaViewDialogFactory")
    private DialogFactory dialogFactory;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EventoViewListener elListener;
    //TODO implementar listener de verdad
    @Autowired
    private Converter dateConverter;
    private EventoFinServicioVB modelo;
    private boolean edicionStatus;
    
    public EventoFinServicioView() {
        super();
    }

    @Override
    public void iniciaVista() {
        initComponents();
        setBindings();
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.borrar.setEnabled(value);
        this.responsable.setEditable(value);
        this.detalle.setEnabled(value);
        edicionStatus = value;
    }
    
    @Override
    public void setModel(EventoVB ev) {
       this.modelo = (EventoFinServicioVB) ev;
    }

    @Override
    public EventoVB getModel() {
       return this.modelo;
    }
    
    @Override
    public void disposeView() {
        this.removeBindings();
    }
    
    private void setBindings() {
        this.bindingManager.registerBind(modelo, "fechaCreacion", (Bindable)fechaCreacionLabel);
        this.bindingManager.registerBind(modelo, "nombreEvento", (Bindable)nombreEvento);
        this.bindingManager.registerBind(modelo, "detalle", (Bindable)detalle);
        this.bindingManager.registerBind(modelo, "responsable", (Bindable)responsable);
    }
    
    public void removeBindings() {
        this.bindingManager.removeBind(modelo, "fechaCreacion", (Bindable)fechaCreacionLabel);
        this.bindingManager.registerBind(modelo, "nombreEvento", (Bindable)nombreEvento);
        this.bindingManager.removeBind(modelo, "detalle", (Bindable)detalle);
        this.bindingManager.removeBind(modelo, "responsable", (Bindable)responsable);
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    @Override
    public void requestFocusOnMainInput() {
        this.detalle.requestFocusInWindow();
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
        jToolBar1 = new javax.swing.JToolBar();
        evidencia = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        fechaCreacionLabel = new org.nekorp.workflow.desktop.view.binding.FormatedJLabel(dateConverter);
        jLabel2 = new javax.swing.JLabel();
        responsable = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jScrollPane1 = new org.nekorp.workflow.desktop.view.resource.imp.MouseFreeJScrollPane();
        detalle = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(32767, 91));

        nombreEvento.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        nombreEvento.setText("Cancelación");

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        evidencia.setBackground(new java.awt.Color(255, 255, 255));
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

        borrar.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Fecha :");

        fechaCreacionLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        fechaCreacionLabel.setText("12/12/2012");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Responsable");

        responsable.setBackground(new java.awt.Color(224, 230, 230));
        responsable.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        responsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jScrollPane1.setBorder(null);
        jScrollPane1.setWheelScrollingEnabled(false);

        detalle.setBackground(new java.awt.Color(224, 230, 230));
        detalle.setColumns(20);
        detalle.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        detalle.setRows(2);
        jScrollPane1.setViewportView(detalle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaCreacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(responsable, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nombreEvento)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fechaCreacionLabel)
                    .addComponent(jLabel2)
                    .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void evidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evidenciaActionPerformed
        edicionEventoEvidencia.setEvento(modelo);
        edicionEventoEvidencia.setEdicionStatus(edicionStatus);
        dialogFactory.createDialog(mainFrame, true).setVisible(true);
        edicionEventoEvidencia.setEvento(null);
    }//GEN-LAST:event_evidenciaActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        this.elListener.deleteEvent(modelo);
    }//GEN-LAST:event_borrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JTextArea detalle;
    private javax.swing.JButton evidencia;
    private javax.swing.JLabel fechaCreacionLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel nombreEvento;
    private javax.swing.JTextField responsable;
    // End of variables declaration//GEN-END:variables

}
