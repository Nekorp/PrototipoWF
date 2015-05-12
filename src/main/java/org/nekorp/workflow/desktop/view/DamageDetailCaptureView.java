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

import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.nekorp.workflow.desktop.view.resource.damage.DetailDamageCaptureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 */
@Component
public class DamageDetailCaptureView extends ApplicationView {

    @Autowired
    private DamageDetailsVB damageCaptura;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier("inventarioDamageView")
    private DetailDamageCaptureListener listener;
    private boolean iniciado;
    private java.awt.Window parent;
    /**
     * Creates new form DamageDetailCaptureView
     */
    public DamageDetailCaptureView() {
        super();
    }
    
    @Override
    public void iniciaVista() {
        if (!iniciado) {
            iniciado = true;
            initComponents();
            bindComponents();
        }
        damageCaptura.setCaracteristica("Tallón");
        damageCaptura.setCategoria("Minimo");
    }
    
    public void bindComponents() {
        bindingManager.registerBind(damageCaptura, "caracteristica",(Bindable)caracteristica);
        bindingManager.registerBind(damageCaptura, "categoria",(Bindable)minimo);
        bindingManager.registerBind(damageCaptura, "categoria",(Bindable)medio);
        bindingManager.registerBind(damageCaptura, "categoria",(Bindable)maximo);
    }

    @Override
    public void setEditableStatus(boolean value) {
        //no hacer nada aun.
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

        categoria = new javax.swing.ButtonGroup();
        caracteristica = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJComboBox();
        jPanel1 = new javax.swing.JPanel();
        minimo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton("Minimo");
        medio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton("Medio");
        maximo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton("Máximo");
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        caracteristica.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        caracteristica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tallón", "Hundimiento", "Rayon", "Dobles", "Daño estructural", "Rotura" }));
        caracteristica.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        minimo.setBackground(new java.awt.Color(255, 255, 255));
        categoria.add(minimo);
        minimo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        minimo.setText("Minimo");

        medio.setBackground(new java.awt.Color(255, 255, 255));
        categoria.add(medio);
        medio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        medio.setText("Medio");

        maximo.setBackground(new java.awt.Color(255, 255, 255));
        categoria.add(maximo);
        maximo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        maximo.setText("Máximo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimo)
                    .addComponent(medio)
                    .addComponent(maximo)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maximo))
        );

        aceptar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        cancelar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(caracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(aceptar))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        parent.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        listener.agregar();
        parent.dispose();
    }//GEN-LAST:event_aceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox caracteristica;
    private javax.swing.ButtonGroup categoria;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton maximo;
    private javax.swing.JRadioButton medio;
    private javax.swing.JRadioButton minimo;
    // End of variables declaration//GEN-END:variables

    public void setParentWindow(java.awt.Window param) {
        parent = param;
    }

}
