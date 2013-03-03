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

import java.util.List;
import javax.swing.event.DocumentEvent;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.view.model.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.nekorp.workflow.desktop.view.resource.imp.ServicioTableModel;


/**
 *
 * 
 */
public class BusquedaServicioView extends javax.swing.JDialog {
    private WorkflowApp application;
    private List<ServicioIndexVB> datos;
    private DialogFactory afterLoadDialog;
    private javax.swing.table.TableRowSorter sorter;
    private java.awt.Frame containingFrame;
    
    /**
     * Creates new form BusquedaServicioView
     */
    public BusquedaServicioView(java.awt.Frame parent, boolean modal, WorkflowApp app, DialogFactory afterLoadDialog) {
        super(parent, modal);
        initComponents();
        containingFrame = parent;
        this.application = app;
        this.datos = this.application.getIndexServicios();
        this.setModeloTabla(datos);
        this.afterLoadDialog = afterLoadDialog;
    }
    
    private void setModeloTabla(List<ServicioIndexVB> datos) {
        ServicioTableModel tableModel = new ServicioTableModel();
        tableModel.setDatos(datos);
        sorter = new javax.swing.table.TableRowSorter(tableModel);
        this.tablaDatos.setModel(tableModel);
        this.tablaDatos.setRowSorter(sorter);
        this.filtro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicaFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicaFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicaFiltro();
            }
            
        });
    }
    
    private void aplicaFiltro() {
        String textoFiltro = filtro.getText().trim();
        if (textoFiltro.length() > 0) {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter(".*"+textoFiltro+".*"));
        } else {
            sorter.setRowFilter(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        filtro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Servicio");
        setModal(true);

        jLabel1.setText("Filtro:");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaDatos);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptar)
                .addGap(18, 18, 18)
                .addComponent(cancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        if (this.tablaDatos.getSelectedRow() >= 0) {
            ServicioIndexVB seleccion  = this.datos.get(
                this.tablaDatos.convertRowIndexToModel(this.tablaDatos.getSelectedRow()));
            this.application.cargaServicio(seleccion.getIdServicio());
        }
        this.dispose();
        afterLoadDialog.createDialog(containingFrame, true).setVisible(true);
    }//GEN-LAST:event_aceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables

}
