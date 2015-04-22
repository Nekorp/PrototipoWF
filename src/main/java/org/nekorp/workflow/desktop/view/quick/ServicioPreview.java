/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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

import org.nekorp.workflow.desktop.view.ApplicationView;
import org.nekorp.workflow.desktop.view.ViewValidIndicator;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.resource.imp.HistorialServicioDialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 */
@Component("servicioPreview")
public class ServicioPreview extends ApplicationView {

    @Autowired
    @Qualifier(value="bitacoraPreview")
    private ApplicationView bitacora;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB servicioVB;
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    private HistorialServicioDialogFactory historialServicioDialogFactory;
    private java.awt.Window parent;
    private boolean iniciado;
    /**
     * Creates new form ServicioPreview
     */
    public ServicioPreview() {
    }
    
    @Override
    public void iniciaVista() {
        if (!iniciado) {
            initComponents();
            bitacora.iniciaVista();
            bitacoraContainer.add(bitacora);
            bindComponents();
            iniciado = true;
        }
    }
    
    private void bindComponents() {
        //datos del cliente.
        bindingManager.registerBind(servicioVB.getCliente(), "nombre",(Bindable)nombre);
        bindingManager.registerBind(servicioVB.getCliente(), "contacto",(Bindable)contacto);
        bindingManager.registerBind(servicioVB.getCliente().getTelefonoUno(), "valor",(Bindable)telefono1);
        bindingManager.registerBind(servicioVB.getCliente(), "email",(Bindable)email);
        //datos del auto
        bindingManager.registerBind(servicioVB.getAuto(), "tipo",(Bindable)tipo);
        bindingManager.registerBind(servicioVB.getAuto(), "numeroSerie",(Bindable)numeroSerie);
        bindingManager.registerBind(servicioVB.getAuto(), "color",(Bindable)color);
        bindingManager.registerBind(servicioVB.getAuto(), "placas",(Bindable)placas);
        bindingManager.registerBind(servicioVB.getAuto(), "modelo",(Bindable)modelo);
        //datos del servicio
        bindingManager.registerBind(servicioVB, "descripcion",(Bindable)descripcionServicio);
        //bitacora
        bindingManager.registerBind(servicioVB.getBitacora(), "eventos",(Bindable)bitacora);
        //datos del costo
        bindingManager.registerBind(costosMetadata, "total",(Bindable)total);
    }

    @Override
    public void setEditableStatus(boolean value) {
        //no hacer nada aun
    }

    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    public void setParent(java.awt.Window parent) {
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel2 = new javax.swing.JLabel();
        contacto = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel3 = new javax.swing.JLabel();
        telefono1 = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        email = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tipo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel6 = new javax.swing.JLabel();
        numeroSerie = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel7 = new javax.swing.JLabel();
        color = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel8 = new javax.swing.JLabel();
        placas = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel9 = new javax.swing.JLabel();
        modelo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        historial = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();
        bitacoraContainer = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        total = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJLabel();
        jPanel7 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Nombre:");

        nombre.setEditable(false);
        nombre.setBackground(new java.awt.Color(224, 230, 230));
        nombre.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Contacto:");

        contacto.setEditable(false);
        contacto.setBackground(new java.awt.Color(224, 230, 230));
        contacto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        contacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Telefono1:");

        telefono1.setEditable(false);
        telefono1.setBackground(new java.awt.Color(224, 230, 230));
        telefono1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        telefono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("E-Mail:");

        email.setEditable(false);
        email.setBackground(new java.awt.Color(224, 230, 230));
        email.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombre)
                    .addComponent(contacto)
                    .addComponent(telefono1)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Tipo:");

        tipo.setEditable(false);
        tipo.setBackground(new java.awt.Color(224, 230, 230));
        tipo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Número de serie:");

        numeroSerie.setEditable(false);
        numeroSerie.setBackground(new java.awt.Color(224, 230, 230));
        numeroSerie.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroSerie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Color:");

        color.setEditable(false);
        color.setBackground(new java.awt.Color(224, 230, 230));
        color.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        color.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("Placas:");

        placas.setEditable(false);
        placas.setBackground(new java.awt.Color(224, 230, 230));
        placas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        placas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setText("Modelo:");

        modelo.setEditable(false);
        modelo.setBackground(new java.awt.Color(224, 230, 230));
        modelo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        historial.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        historial.setText("Historial");
        historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numeroSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(historial)
                    .addComponent(tipo)
                    .addComponent(color)
                    .addComponent(placas)
                    .addComponent(modelo))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numeroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(placas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historial)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("Descripción del Servicio");

        jScrollPane1.setBorder(null);

        descripcionServicio.setEditable(false);
        descripcionServicio.setBackground(new java.awt.Color(224, 230, 230));
        descripcionServicio.setColumns(20);
        descripcionServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        descripcionServicio.setLineWrap(true);
        descripcionServicio.setRows(8);
        jScrollPane1.setViewportView(descripcionServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bitacoraContainer.setBackground(new java.awt.Color(255, 255, 255));
        bitacoraContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bitacora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bitacoraContainer.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("Costo del Servicio:");

        total.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        total.setText("jLabel12");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(total))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        aceptar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptar)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(aceptar)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bitacoraContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bitacoraContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        javax.swing.JDialog dialog = historialServicioDialogFactory.createDialog(null, true);
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        dialog.setVisible(true);
    }//GEN-LAST:event_historialActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        this.parent.dispose();
    }//GEN-LAST:event_aceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JPanel bitacoraContainer;
    private javax.swing.JTextField color;
    private javax.swing.JTextField contacto;
    private javax.swing.JTextArea descripcionServicio;
    private javax.swing.JTextField email;
    private javax.swing.JButton historial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField numeroSerie;
    private javax.swing.JTextField placas;
    private javax.swing.JTextField telefono1;
    private javax.swing.JTextField tipo;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables

}
