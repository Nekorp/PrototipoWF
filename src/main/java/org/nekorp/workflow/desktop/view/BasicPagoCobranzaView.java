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

import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * @author Nekorp
 */
@Component("basicPagoCobranzaView")
@Scope("prototype")
public class BasicPagoCobranzaView extends PagoCobranzaView {
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private PagoCobranzaViewListener elListener;
    private PagoCobranzaVB modelo;
    private boolean editableStatus;
    /**
     * Creates new form EntradaBitacora
     */
    public BasicPagoCobranzaView() {
        super();
    }
    @Override
    public void iniciaVista() {
        initComponents();
        this.setBindings();
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.borrar.setEnabled(value);
        this.fechaPago.setEnabled(value);
        this.responsable.setEditable(value);
        this.detalle.setEnabled(value);
        this.facturado.setEnabled(value);
        editableStatus = value;
        if (facturado.isSelected() && editableStatus) {
            this.numeroFactura.setEnabled(true);
        } else {
            this.numeroFactura.setEnabled(false);
            if (!facturado.isSelected()) {
                numeroFactura.setText("");
            }
        }
        if (StringUtils.equals(this.tipo.getSelectedItem()+"", "Otro") && editableStatus) {
            otroTipo.setEnabled(true);
        } else {
            otroTipo.setEnabled(false);
            if (editableStatus) {
                otroTipo.setText("");
            }
        }
        this.monto.setEnabled(value);
        this.tipo.setEnabled(value);
    }
    @Override
    public void setModel(PagoCobranzaVB ev) {
        this.modelo = ev;
    }

    @Override
    public PagoCobranzaVB getModel() {
       return this.modelo;
    }
    
    @Override
    public void disposeView() {
        this.removeBindings();
    }
    
    private void setBindings() {
        this.bindingManager.registerBind(modelo, "monto", (Bindable) this.monto);
        this.bindingManager.registerBind(modelo, "detalle", (Bindable) this.detalle);
        this.bindingManager.registerBind(modelo, "fecha", (Bindable) this.fechaPago);
        this.bindingManager.registerBind(modelo,"facturado", (Bindable)this.facturado);
        this.bindingManager.registerBind(modelo,"numeroFactura", (Bindable)this.numeroFactura);
        this.bindingManager.registerBind(modelo,"tipo", (Bindable)this.tipo);
        this.bindingManager.registerBind(modelo,"otroTipo", (Bindable)this.otroTipo);
        this.bindingManager.registerBind(modelo, "responsable", (Bindable) this.responsable);
        Bindable facturadoSelected = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                if (valor && editableStatus) {
                    numeroFactura.setEnabled(true);
                } else {
                    numeroFactura.setEnabled(false);
                    if (!valor) {
                        numeroFactura.setText("");
                    }
                }
            }
        };
        bindingManager.registerBind(modelo, "facturado", facturadoSelected);
        Bindable tipoBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                String tipoRaw = (String) value;
                if (StringUtils.equals(tipoRaw, "Otro") && editableStatus) {
                    otroTipo.setEnabled(true);
                } else {
                    otroTipo.setEnabled(false);
                    if (editableStatus) {
                        otroTipo.setText("");
                    }
                }
            }
        };
        bindingManager.registerBind(modelo, "tipo", tipoBind);
    }
    
    private void removeBindings() {
        this.bindingManager.removeBind(modelo, "monto", (Bindable) this.monto);
        this.bindingManager.removeBind(modelo, "detalle", (Bindable) this.detalle);
        this.bindingManager.removeBind(modelo, "fecha", (Bindable) this.fechaPago);
        this.bindingManager.removeBind(modelo, "responsable", (Bindable) this.responsable);
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
        jLabel1 = new javax.swing.JLabel();
        monto = new org.nekorp.workflow.desktop.view.binding.MonedaBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        fechaPago = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJSppiner();
        jLabel5 = new javax.swing.JLabel();
        tipo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJComboBox();
        jLabel6 = new javax.swing.JLabel();
        otroTipo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jToolBar1 = new javax.swing.JToolBar();
        borrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        facturado = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJCheckBox();
        jLabel3 = new javax.swing.JLabel();
        numeroFactura = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel2 = new javax.swing.JLabel();
        responsable = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jScrollPane2 = new org.nekorp.workflow.desktop.view.resource.imp.MouseFreeJScrollPane();
        detalle = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(32767, 101));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("monto");

        monto.setBackground(new java.awt.Color(224, 230, 230));
        monto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        monto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Fecha pago");

        fechaPago.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        fechaPago.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Forma de pago");

        tipo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Transferencia", "Cheque", "Otro" }));
        tipo.setBorder(null);

        jLabel6.setText("Otro");

        otroTipo.setBackground(new java.awt.Color(224, 230, 230));
        otroTipo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        otroTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        borrar.setBackground(new java.awt.Color(255, 255, 255));
        borrar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(otroTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4)
                .addComponent(fechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5)
                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addComponent(otroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        facturado.setBackground(new java.awt.Color(255, 255, 255));
        facturado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        facturado.setText("Facturado s/n");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Número de Factura");

        numeroFactura.setBackground(new java.awt.Color(224, 230, 230));
        numeroFactura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Responsable");

        responsable.setBackground(new java.awt.Color(224, 230, 230));
        responsable.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        responsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(facturado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responsable))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(facturado)
                .addComponent(jLabel3)
                .addComponent(numeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setWheelScrollingEnabled(false);

        detalle.setBackground(new java.awt.Color(224, 230, 230));
        detalle.setColumns(20);
        detalle.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        detalle.setLineWrap(true);
        detalle.setRows(2);
        jScrollPane2.setViewportView(detalle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        this.elListener.deletePago(modelo);
    }//GEN-LAST:event_borrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JTextArea detalle;
    private javax.swing.JCheckBox facturado;
    private javax.swing.JSpinner fechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField monto;
    private javax.swing.JTextField numeroFactura;
    private javax.swing.JTextField otroTipo;
    private javax.swing.JTextField responsable;
    private javax.swing.JComboBox tipo;
    // End of variables declaration//GEN-END:variables
    
}