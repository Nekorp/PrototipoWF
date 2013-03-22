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

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("eventoEntregaView")
@Scope("prototype")
public class EventoEntregaView extends EventoView {
    @Autowired
    private EvidenciaView evidenciaView;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EventoViewListener elListener;
    //TODO implementar listener de verdad
    @Autowired
    private Converter dateConverter;
    
    private EventoEntregaVB modelo;
    /**
     * Creates new form EntradaAuto
     */
    public EventoEntregaView() {
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
        this.fecha.setEnabled(value);
        this.responsable.setEditable(value);
        this.detalle.setEnabled(value);
    }
    
    @Override
    public void setModel(EventoVB ev) {
       this.modelo = (EventoEntregaVB) ev;
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
        this.bindingManager.registerBind(modelo, "fecha", (Bindable)fecha);
    }
    
    public void removeBindings() {
        this.bindingManager.removeBind(modelo, "fechaCreacion", (Bindable)fechaCreacionLabel);
        this.bindingManager.removeBind(modelo, "nombreEvento", (Bindable)nombreEvento);
        this.bindingManager.removeBind(modelo, "detalle", (Bindable)detalle);
        this.bindingManager.removeBind(modelo, "responsable", (Bindable)responsable);
        this.bindingManager.removeBind(modelo, "fecha", (Bindable)fecha);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        responsable = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jToolBar1 = new javax.swing.JToolBar();
        evidencia = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        nombreEvento = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJLabel();
        jLabel6 = new javax.swing.JLabel();
        fechaCreacionLabel = new org.nekorp.workflow.desktop.view.binding.FormatedJLabel(dateConverter);
        fecha = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJSppiner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new org.nekorp.workflow.desktop.view.resource.imp.MouseFreeJScrollPane();
        detalle = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();

        setMaximumSize(new java.awt.Dimension(32767, 89));

        jLabel2.setText("Responsable");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        evidencia.setText("Evidencias");
        evidencia.setEnabled(false);
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

        nombreEvento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nombreEvento.setText("Entrega de Auto");

        jLabel6.setText("Creado el dia:");

        fechaCreacionLabel.setText("12/12/2012");

        fecha.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        jLabel4.setText("Fecha evento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(nombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responsable, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaCreacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreEvento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fechaCreacionLabel)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );

        jScrollPane1.setWheelScrollingEnabled(false);

        detalle.setColumns(20);
        detalle.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        detalle.setRows(2);
        jScrollPane1.setViewportView(detalle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
    private javax.swing.JButton evidencia;
    private javax.swing.JSpinner fecha;
    private javax.swing.JLabel fechaCreacionLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel nombreEvento;
    private javax.swing.JTextField responsable;
    // End of variables declaration//GEN-END:variables

}
