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
package org.nekorp.workflow.desktop.view;

import java.util.List;
import javax.swing.event.DocumentEvent;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasUsuario;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.nekorp.workflow.desktop.view.resource.imp.AlignRightStringCellRenderer;
import org.nekorp.workflow.desktop.view.resource.imp.NumberFormatCellRenderer;
import org.nekorp.workflow.desktop.view.resource.imp.ServicioTableModel;
import org.nekorp.workflow.desktop.view.resource.imp.StatusCobranzaCellRenderer;


/**
 * @author Nekorp
 * 
 */
public class BusquedaServicioView extends javax.swing.JDialog {
    private WorkflowApp application;
    private List<ServicioIndexVB> datos;
    private DialogFactory afterLoadDialog;
    private javax.swing.table.TableRowSorter sorter;
    private java.awt.Frame containingFrame;
    private ServicioTableModel tableModel;
    private int[] sizeColumn = new int[] {
        20,
        100,
        50,
        85,
        50,
        200,
        50,
        50,
        50
    };
    private boolean iniciado;
    
    /**
     * Creates new form BusquedaServicioView
     */
    public BusquedaServicioView(java.awt.Frame parent, boolean modal, WorkflowApp app, DialogFactory afterLoadDialog) {
        super(parent, modal);
        initComponents();
        containingFrame = parent;
        this.application = app;
        this.afterLoadDialog = afterLoadDialog;
    }
    
    public void inicializa() {
        PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        this.since.setText(preferencias.getFirstId().toString());
        this.filtro.setText(preferencias.getUltimoFiltro());
        this.datos = this.application.getIndexServicios(preferencias.getFirstId());
        this.setModeloTabla(datos);
        aplicaFiltro();
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
        javax.swing.RowSorter<ServicioTableModel> coso = new javax.swing.table.TableRowSorter<>(tableModel);
        tablaDatos.setRowSorter(coso);
        this.iniciado = true;
    }
    
    public boolean isIniciado() {
        return this.iniciado;
    }
    
    private void setModeloTabla(List<ServicioIndexVB> datos) {
        tableModel = new ServicioTableModel();
        tableModel.setDatos(datos);
        sorter = new javax.swing.table.TableRowSorter(tableModel);
        this.tablaDatos.setModel(tableModel);
        this.tablaDatos.setRowSorter(sorter);
        for (int i = 0; i < sizeColumn.length; i++) {
            this.tablaDatos.getColumnModel().getColumn(i).setPreferredWidth(sizeColumn[i]);
            this.tablaDatos.getColumnModel().getColumn(i).setMaxWidth(800);
        }
        this.tablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tablaDatos.getColumnModel().getColumn(6).setCellRenderer(new StatusCobranzaCellRenderer());
        tablaDatos.getColumnModel().getColumn(7).setCellRenderer(new AlignRightStringCellRenderer());
        tablaDatos.getColumnModel().getColumn(8).setCellRenderer(new NumberFormatCellRenderer());
        
    }
    
    private void aplicaFiltro() {
        String textoFiltro = filtro.getText().trim();
        if (textoFiltro.length() > 0) {
            this.sorter.setRowFilter(javax.swing.RowFilter.regexFilter(".*"+textoFiltro+".*"));
        } else {
            this.sorter.setRowFilter(null);
        }
    }
    
    private void updatePreferenciasFiltro() {
        PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        preferencias.setUltimoFiltro(filtro.getText());
        this.application.setPreferenciasUsuario(preferencias);
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
        since = new org.nekorp.workflow.desktop.view.resource.imp.EnteroJTextField();
        jLabel2 = new javax.swing.JLabel();
        reload = new javax.swing.JButton();

        setTitle("Busqueda Servicio");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
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

        jLabel2.setText("A partir del servicio:");

        reload.setText("Refresh");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(since, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reload))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aceptar)
                        .addGap(18, 18, 18)
                        .addComponent(cancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(since, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(reload))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        updatePreferenciasFiltro();
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        updatePreferenciasFiltro();
        if (this.tablaDatos.getSelectedRow() >= 0) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            ServicioIndexVB seleccion  = this.datos.get(
                this.tablaDatos.convertRowIndexToModel(this.tablaDatos.getSelectedRow()));
            this.application.cargaServicio(seleccion.getIdServicio());
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            this.dispose();
            afterLoadDialog.createDialog(containingFrame, true).setVisible(true);
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        if (evt.getClickCount() == 2) {
            if (this.tablaDatos.getSelectedRow() >= 0) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                ServicioIndexVB seleccion  = this.datos.get(
                    this.tablaDatos.convertRowIndexToModel(this.tablaDatos.getSelectedRow()));
                this.application.cargaServicio(seleccion.getIdServicio());
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
                this.dispose();
                afterLoadDialog.createDialog(containingFrame, true).setVisible(true);
            }
        }
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        Long sinceId = Long.parseLong(this.since.getText());
        preferencias.setFirstId(sinceId);
        this.application.setPreferenciasUsuario(preferencias);
        this.datos = this.application.getIndexServicios(preferencias.getFirstId());
        this.setModeloTabla(datos);
        aplicaFiltro();
        updatePreferenciasFiltro();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_reloadActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        updatePreferenciasFiltro();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reload;
    private javax.swing.JTextField since;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables

}
