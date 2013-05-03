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

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.bitacora.EdicionEventoEvidenciaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoObservacionesVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.resource.DateConverter;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("eventoObservacionesView")
@Scope("prototype")
public class EventoObservacionesView extends EventoView {

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
    @Autowired
    private DateConverter dateConverter;
    private EventoObservacionesVB modelo;
    /**
     * Creates new form EventoDiagnosticoView
     */
    public EventoObservacionesView() {
        super();
    }

    @Override
    public void iniciaVista() {
        initComponents();
        setBindings();
        //TODO quitar esta cosa apestosa
        if (StringUtils.isEmpty(modelo.getId())) {
            this.setEditableStatus(true);
        } else {
            this.setEditableStatus(false);
        }
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.borrar.setEnabled(value);
        this.responsable.setEditable(value);
        this.observaciones.setEnabled(value);
    }
    
    @Override
    public void setModel(EventoVB ev) {
       this.modelo = (EventoObservacionesVB) ev;
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
        this.bindingManager.registerBind(modelo, "observaciones", (Bindable)observaciones);
        this.bindingManager.registerBind(modelo, "responsable", (Bindable)responsable);
    }
    
    public void removeBindings() {
        this.bindingManager.removeBind(modelo, "fechaCreacion", (Bindable)fechaCreacionLabel);
        this.bindingManager.removeBind(modelo, "observaciones", (Bindable)observaciones);
        this.bindingManager.removeBind(modelo, "responsable", (Bindable)responsable);
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    @Override
    public void requestFocusOnMainInput() {
        this.observaciones.requestFocusInWindow();
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
        evidencias = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        responsable = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        fechaCreacionLabel = new org.nekorp.workflow.desktop.view.binding.FormatedJLabel(dateConverter);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new org.nekorp.workflow.desktop.view.resource.imp.MouseFreeJScrollPane();
        observaciones = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();

        setMaximumSize(new java.awt.Dimension(32767, 65));
        setPreferredSize(new java.awt.Dimension(425, 65));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        evidencias.setText("Evidencias");
        evidencias.setFocusable(false);
        evidencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        evidencias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        evidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evidenciasActionPerformed(evt);
            }
        });
        jToolBar1.add(evidencias);

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

        jLabel1.setText("Responsable:");

        fechaCreacionLabel.setText("12/12/2012");

        jLabel2.setText("Creado el dia:");

        jLabel3.setText("Observaciones:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setWheelScrollingEnabled(false);

        observaciones.setColumns(20);
        observaciones.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        observaciones.setLineWrap(true);
        observaciones.setRows(2);
        jScrollPane2.setViewportView(observaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(responsable, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaCreacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fechaCreacionLabel)
                        .addComponent(jLabel2))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void evidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evidenciasActionPerformed
        edicionEventoEvidencia.setEvento(modelo);
        dialogFactory.createDialog(mainFrame, true).setVisible(true);
        edicionEventoEvidencia.setEvento(null);
    }//GEN-LAST:event_evidenciasActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        this.elListener.deleteEvent(modelo);
    }//GEN-LAST:event_borrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JButton evidencias;
    private javax.swing.JLabel fechaCreacionLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField responsable;
    // End of variables declaration//GEN-END:variables
}
