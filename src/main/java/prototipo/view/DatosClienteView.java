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
package prototipo.view;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.modelo.EdicionServicioMetadata;
import prototipo.modelo.cliente.Cliente;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 * 
 */
@Component("datosClienteView")
@Aspect
public class DatosClienteView extends ApplicationView {
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    javax.swing.JFrame mainFrame;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private Cliente viewClienteModel;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.loadCliente(..)) || execution(* prototipo.control.WorkflowApp.nuevoCliente(..))")  
    public void loadClientePointCut() {
    }
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.unloadCliente(..))")  
    public void unloadClientePointCut() {
    }
    
    @AfterReturning("loadClientePointCut()")
    public void loadCliente() {
        this.setEditableStatus(true);
    }
    
    @AfterReturning("unloadClientePointCut()")
    public void unloadCliente() {
        this.setEditableStatus(false);
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        this.nombreCliente.setEditable(value);
        this.rfcCliente.setEditable(value);
        this.calleCliente.setEditable(value);
        this.numeroCasaCliente.setEditable(value);
        this.codigoPostalCliente.setEditable(value);
        this.coloniaCliente.setEditable(value);
        this.ciudadCliente.setEditable(value);
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bindComponents();
    }
    
    private void bindComponents() {
        bindingManager.registerBind(viewClienteModel, "id",(Bindable)this.numeroCliente);
        bindingManager.registerBind(viewClienteModel, "nombre",(Bindable)this.nombreCliente);
        bindingManager.registerBind(viewClienteModel, "rfc",(Bindable)this.rfcCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "calle",(Bindable)this.calleCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "numInterior",(Bindable)this.numeroCasaCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "codigoPostal",(Bindable)this.codigoPostalCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "colonia",(Bindable)this.coloniaCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "ciudad",(Bindable)this.ciudadCliente);
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
        nuevoCliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buscarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        numeroCliente = new prototipo.view.binding.SimpleBindableJLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreCliente = new prototipo.view.binding.SimpleBindableJTextField();
        rfcCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel3 = new javax.swing.JLabel();
        domicioFiscal = new javax.swing.JPanel();
        calleCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        numeroCasaCliente = new prototipo.view.binding.SimpleBindableJTextField();
        codigoPostalCliente = new prototipo.view.binding.SimpleBindableJTextField();
        coloniaCliente = new prototipo.view.binding.SimpleBindableJTextField();
        ciudadCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        nuevoCliente.setText("Nuevo");
        nuevoCliente.setFocusable(false);
        nuevoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevoCliente);
        jToolBar1.add(jSeparator1);

        buscarCliente.setText("Buscar");
        buscarCliente.setFocusable(false);
        buscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(buscarCliente);

        jLabel1.setText("Número de Cliente:");

        numeroCliente.setText(" ");

        jLabel2.setText("Nombre o razón social:");

        nombreCliente.setEditable(false);

        rfcCliente.setEditable(false);

        jLabel3.setText("RFC:");

        domicioFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Fiscal"));

        calleCliente.setEditable(false);

        jLabel4.setText("Calle:");

        numeroCasaCliente.setEditable(false);

        codigoPostalCliente.setEditable(false);

        coloniaCliente.setEditable(false);

        ciudadCliente.setEditable(false);

        jLabel5.setText("Num. Interior:");

        jLabel6.setText("Código Postal:");

        jLabel7.setText("Colonia:");

        jLabel8.setText("Ciudad:");

        javax.swing.GroupLayout domicioFiscalLayout = new javax.swing.GroupLayout(domicioFiscal);
        domicioFiscal.setLayout(domicioFiscalLayout);
        domicioFiscalLayout.setHorizontalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, domicioFiscalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calleCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(codigoPostalCliente)
                    .addComponent(coloniaCliente)
                    .addComponent(ciudadCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        domicioFiscalLayout.setVerticalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(domicioFiscalLayout.createSequentialGroup()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoPostalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coloniaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(domicioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rfcCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                            .addComponent(nombreCliente)
                            .addComponent(numeroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numeroCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rfcCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(domicioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteActionPerformed
        if (this.servicioMetaData.isClienteEditado()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    mainFrame,
                    "¿Guardar Cliente?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.aplication.guardarCliente();
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        BusquedaClienteView dialog = new BusquedaClienteView(mainFrame, true, aplication);
        dialog.setVisible(true);
    }//GEN-LAST:event_buscarClienteActionPerformed

    private void nuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoClienteActionPerformed
        if (this.servicioMetaData.isClienteEditado()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    mainFrame,
                    "¿Guardar Cliente?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.aplication.guardarCliente();
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        aplication.nuevoCliente();
    }//GEN-LAST:event_nuevoClienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarCliente;
    private javax.swing.JTextField calleCliente;
    private javax.swing.JTextField ciudadCliente;
    private javax.swing.JTextField codigoPostalCliente;
    private javax.swing.JTextField coloniaCliente;
    private javax.swing.JPanel domicioFiscal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JButton nuevoCliente;
    private javax.swing.JTextField numeroCasaCliente;
    private javax.swing.JLabel numeroCliente;
    private javax.swing.JTextField rfcCliente;
    // End of variables declaration//GEN-END:variables
    
}
