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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.security.PermisosCostoView;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralRegistroCosto;
import org.nekorp.workflow.desktop.view.resource.imp.CostoServicioTableModel;
import org.nekorp.workflow.desktop.view.resource.imp.CustomJTableCostos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 *
 * 
 */
@Component("costosView")
public class CostoServicioView extends ApplicationView {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CostoServicioView.class);
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    private PermisosCostoView permisos;
    @Autowired
    private CostoServicioTableModel tableModel;
    @Autowired
    private ValidacionGeneralRegistroCosto validacionGeneralRegistroCosto;
    @Override
    public void iniciaVista() {
        initComponents();
        ((CustomJTableCostos)tablaCostos)
            .setValidacionGeneralRegistroCosto(validacionGeneralRegistroCosto);
        tablaCostos.setModel(tableModel);
        setDefaultColumnSize();
        setBindings();
        //pudieran o no funcionar
        //TODO requieren algo mas de trabajo
        //setShorcuts();
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.agregarHP.setEnabled(value);
        this.agregarM.setEnabled(value);
        this.otrosGastos.setEnabled(value);
        this.borrar.setEnabled(value);
        tableModel.setEditable(value);
    }
    
    private void setBindings() {
        bindingManager.registerBind(viewServicioModel, "costos", tableModel);
        bindingManager.registerBind(costosMetadata, "total", (Bindable)this.total);
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "puedeEditarCostos", permisosBind);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarHP);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarM);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)otrosGastos);
    }
    
    private void setShorcuts() {
        InputMap im = tablaCostos.getInputMap(JTable.WHEN_FOCUSED);
        ActionMap am = tablaCostos.getActionMap();
        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarActionPerformed(e);
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Delete");
        am.put("Delete", deleteAction);
        Action addAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarActionPerformed(e);
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "Add");
        am.put("Add", addAction);
    }
    
    private void setDefaultColumnSize() {
        int[] sizeColumns = new int[] {
            80,//tipo
            180,//concepto
            70,//cantidad
            70,//precio unitario
            70,//utilidad
            70,//precio cliente
            70//subtotal
        };
        TableColumn column;
        for (int i = 0; i < this.tablaCostos.getColumnCount() && i < sizeColumns.length; i++) {
            column = this.tablaCostos.getColumnModel().getColumn(i);
            column.setPreferredWidth(sizeColumns[i]);
        }
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

        jToolBar1 = new javax.swing.JToolBar();
        agregarHP = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        agregarM = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        otrosGastos = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        generarReporte = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCostos = new org.nekorp.workflow.desktop.view.resource.imp.CustomJTableCostos();
        jPanel2 = new javax.swing.JPanel();
        total = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel1 = new javax.swing.JLabel();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        agregarHP.setText("Hojalateria y Pintura");
        agregarHP.setFocusable(false);
        agregarHP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarHP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarHPActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarHP);
        jToolBar1.add(jSeparator1);

        agregarM.setText("Mecanica");
        agregarM.setFocusable(false);
        agregarM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarMActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarM);
        jToolBar1.add(jSeparator3);

        otrosGastos.setText("Otros Gastos");
        otrosGastos.setFocusable(false);
        otrosGastos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        otrosGastos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        otrosGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otrosGastosActionPerformed(evt);
            }
        });
        jToolBar1.add(otrosGastos);
        jToolBar1.add(filler1);

        generarReporte.setText("Generar reporte");
        generarReporte.setFocusable(false);
        generarReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarReporteActionPerformed(evt);
            }
        });
        jToolBar1.add(generarReporte);
        jToolBar1.add(jSeparator2);

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

        jPanel1.setLayout(new java.awt.BorderLayout());

        tablaCostos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaCostos.setRowSelectionAllowed(false);
        tablaCostos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaCostos);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        total.setEditable(false);
        total.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel1.setText("Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarHPActionPerformed
        this.tableModel.addRegistro("Hojalateria y Pintura");
    }//GEN-LAST:event_agregarHPActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        int index = this.tablaCostos.getSelectedRow();
        if (index >= 0) {
            this.tableModel.deleteRegistro(index);
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void agregarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMActionPerformed
        this.tableModel.addRegistro("Mecanica");
    }//GEN-LAST:event_agregarMActionPerformed

    private void otrosGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otrosGastosActionPerformed
        this.tableModel.addRegistro("Otros Gastos");
    }//GEN-LAST:event_otrosGastosActionPerformed

    private void generarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarReporteActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Hojas de cálculo", "xlsx");
            chooser.setFileFilter(filter);
            String homePath = System.getProperty("user.home");
            File f = new File(new File(homePath+"/Reporte-Servicio-"+this.viewServicioModel.getId()+".xlsx").getCanonicalPath());
            chooser.setSelectedFile(f);  
            int returnVal = chooser.showSaveDialog(this.mainFrame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               this.aplication.generaReporte(chooser.getSelectedFile());
            }
        } catch (IOException ex) {
            CostoServicioView.LOGGER.error(ex);
        }
    }//GEN-LAST:event_generarReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarHP;
    private javax.swing.JButton agregarM;
    private javax.swing.JButton borrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton generarReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton otrosGastos;
    private javax.swing.JTable tablaCostos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
    
}
