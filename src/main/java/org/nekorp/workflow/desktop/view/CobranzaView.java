/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;
import org.nekorp.workflow.desktop.servicio.CobranzaPagoServicioFactory;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosCobranzaView;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Nekorp
 * 
 */
public abstract class CobranzaView extends ApplicationView implements Bindable, PagoCobranzaViewListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CobranzaView.class);
    
    private LinkedList<Object> ignore;
    private LinkedList<PagoCobranzaVB> modelo;
    private Object target;
    private String property;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private CobranzaPagoServicioFactory pagoFactory;
    @Autowired
    private PermisosCobranzaView permisos;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier(value = "cobranzaSaveSeparatorView")
    private EventoExtraGuardar extraGuardar;
    @Autowired
    private CobranzaMetadata cobranzaMetadata;
    @Autowired
    private javax.swing.JFrame mainFrame;
    private boolean editableStatus;
    
    public CobranzaView() {
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        editableStatus = value;
        this.agregar.setEnabled(value);
        this.facturado.setEnabled(value);
        if (facturado.isSelected() && editableStatus) {
            this.numeroFactura.setEnabled(true);
        } else {
            this.numeroFactura.setEnabled(false);
            if (!facturado.isSelected()) {
                numeroFactura.setText("");
            }
        }
        this.inicioCobranza.setEnabled(value);
        for (java.awt.Component x: this.entradas.getComponents()) {
            if (x instanceof ApplicationView) {
                ApplicationView y = (ApplicationView) x;
                y.setEditableStatus(value);
            }
        }
    }
    @Override
    public void iniciaVista() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        extraGuardar.iniciaVista();
        setBindings();
    }
    
    private void setBindings() {
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "modificarPagos", permisosBind);
        bindingManager.registerBind(viewServicioModel.getCobranza(),"facturado", (Bindable)this.facturado);
        bindingManager.registerBind(viewServicioModel.getCobranza(),"numeroFactura", (Bindable)this.numeroFactura);
        bindingManager.registerBind(viewServicioModel.getCobranza(),"inicio", (Bindable)this.inicioCobranza);
        bindingManager.registerBind(viewServicioModel.getCobranza(),"pagos", (Bindable)this);
        bindingManager.registerBind(cobranzaMetadata,"totalServicio", (Bindable)this.totalServicio);
        bindingManager.registerBind(cobranzaMetadata,"acuenta", (Bindable)this.aCuenta);
        bindingManager.registerBind(cobranzaMetadata,"saldo", (Bindable)this.saldo);
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
        bindingManager.registerBind(viewServicioModel.getCobranza(), "facturado", facturadoSelected);
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            LinkedList<PagoCobranzaVB> param = (LinkedList<PagoCobranzaVB>) value;
            LinkedList<PagoCobranzaVB> borrar = new LinkedList<>();
            for (PagoCobranzaVB obj: modelo) {
                if (!param.contains(obj)) {
                    borrar.add(obj);
                }
            }
            for (PagoCobranzaVB x: borrar) {
                removePago(x);
            }
            for (int i = 0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if (!param.get(i).equals(this.modelo.get(i))) {
                        this.modelo.add(i, param.get(i));
                        addPagoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addPagoView(this.modelo.get(i), i);
                }
            }
            this.updateUI();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
                }
            });
        }
    }
    
    private void addPagoView(PagoCobranzaVB obj, int index) {
        PagoCobranzaView entrada = null;
        int realIndex = index * 2;
        if (obj instanceof PagoCobranzaVB) {
            entrada = getPagoCobranzaView();
        }
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            entrada.setEditableStatus(true);
            eliminaViejoGuardar();
            this.entradas.add(entrada, realIndex);
            entrada.validate();
            entradas.add(this.extraGuardar, realIndex + 1);
            entrada.requestFocusOnMainInput();
        }
    }
    
    private void eliminaViejoGuardar() {
        java.awt.Component[] components = entradas.getComponents();
        int index = -1;
        for (int i = 0; i < components.length; i++) {
            if (components[i] == this.extraGuardar) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            entradas.remove(index);
            entradas.add(new EventoExtraSeparador(), index);
        }
    }
    
    private void removePago(PagoCobranzaVB obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        int viewIndex = index * 2;
        PagoCobranzaView view = (PagoCobranzaView) this.entradas.getComponent(viewIndex);
        PagoCobranzaView anterior = null;
        if (viewIndex > 0) {
            anterior = (PagoCobranzaView) this.entradas.getComponent(viewIndex - 2);
        }
        view.disposeView();
        if (this.entradas.getComponent(viewIndex + 1) == extraGuardar && anterior != null) {
            this.entradas.remove(viewIndex - 1);
        } else {
            this.entradas.remove(viewIndex + 1);
        }
        this.entradas.remove(view);
    }
    
    @Override
    public void deletePago(PagoCobranzaVB ev){
        int n = javax.swing.JOptionPane.showConfirmDialog(
                mainFrame,
                "¿Borrar Pago?",
                "Confirmación",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (n == javax.swing.JOptionPane.YES_OPTION) {
            LinkedList<PagoCobranzaVB> value = new LinkedList<>();
            for (PagoCobranzaVB x: this.modelo) {
                value.add(x);
            }
            value.remove(ev);
            actualizaModelo(value);
        }
    }
    
    private void actualizaModelo(LinkedList<PagoCobranzaVB> value) {
        try {
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            CobranzaView.LOGGER.error("exploto", ex);
        }
    }
    
    public abstract PagoCobranzaView getPagoCobranzaView();
    
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
        agregar = new javax.swing.JButton();
        headder = new javax.swing.JPanel();
        facturado = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJCheckBox();
        jLabel1 = new javax.swing.JLabel();
        numeroFactura = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel3 = new javax.swing.JLabel();
        inicioCobranza = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJSppiner();
        jLabel2 = new javax.swing.JLabel();
        totalServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        aCuenta = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel5 = new javax.swing.JLabel();
        saldo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        entradas = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        agregar.setBackground(new java.awt.Color(204, 204, 204));
        agregar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        agregar.setText("Agregar");
        agregar.setFocusable(false);
        agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jToolBar1.add(agregar);

        headder.setBackground(new java.awt.Color(255, 255, 255));

        facturado.setBackground(new java.awt.Color(255, 255, 255));
        facturado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        facturado.setText("Facturado s/n");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Número de Factura:");

        numeroFactura.setBackground(new java.awt.Color(224, 230, 230));
        numeroFactura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroFactura.setText("jTextField1");
        numeroFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Inicio de cobranza");

        inicioCobranza.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        inicioCobranza.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MONTH));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total del Servicio");

        totalServicio.setEditable(false);
        totalServicio.setBackground(new java.awt.Color(255, 255, 255));
        totalServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        totalServicio.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        totalServicio.setText("jTextField2");
        totalServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("A cuenta");

        aCuenta.setEditable(false);
        aCuenta.setBackground(new java.awt.Color(255, 255, 255));
        aCuenta.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aCuenta.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        aCuenta.setText("jTextField1");
        aCuenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Saldo");

        saldo.setEditable(false);
        saldo.setBackground(new java.awt.Color(255, 255, 255));
        saldo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        saldo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        saldo.setText("jTextField2");
        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));

        javax.swing.GroupLayout headderLayout = new javax.swing.GroupLayout(headder);
        headder.setLayout(headderLayout);
        headderLayout.setHorizontalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headderLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inicioCobranza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headderLayout.createSequentialGroup()
                        .addComponent(facturado)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headderLayout.createSequentialGroup()
                        .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(numeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        headderLayout.setVerticalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderLayout.createSequentialGroup()
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facturado)
                    .addComponent(jLabel1)
                    .addComponent(numeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(inicioCobranza, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(aCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        entradas.setBackground(new java.awt.Color(255, 255, 255));
        entradas.setLayout(new javax.swing.BoxLayout(entradas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(entradas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(headder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(headder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        LinkedList<PagoCobranzaVB> value = new LinkedList<>();
        for (PagoCobranzaVB x: this.modelo) {
            value.add(x);
        }
        PagoCobranzaVB pago = pagoFactory.creaPago();
        value.add(pago);
        actualizaModelo(value);
    }//GEN-LAST:event_agregarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aCuenta;
    private javax.swing.JButton agregar;
    private javax.swing.JPanel entradas;
    private javax.swing.JCheckBox facturado;
    private javax.swing.JPanel headder;
    private javax.swing.JSpinner inicioCobranza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField numeroFactura;
    private javax.swing.JTextField saldo;
    private javax.swing.JTextField totalServicio;
    // End of variables declaration//GEN-END:variables

}
