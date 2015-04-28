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

import java.awt.Color;
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
        200,
        50,
        85,
        50,
        200,
        25,
        38,
        20
    };
    private boolean iniciado;
    
    /**
     * Creates new form BusquedaServicioView
     */
    public BusquedaServicioView(java.awt.Frame parent, boolean modal, WorkflowApp app, DialogFactory afterLoadDialog) {
        super(parent, modal);
        initComponents();
        jScrollPane2.getViewport().setBackground(Color.WHITE);
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
    
    @Override
    public void setEnabled(boolean value) {
        //super.setEnabled(value);
        this.filtro.setEnabled(value);
        this.since.setEnabled(value);
        this.reload.setEnabled(value);
        this.tablaDatos.setEnabled(value);
        this.aceptar.setEnabled(value);
        this.cancelar.setEnabled(value);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filtro = new javax.swing.JTextField();
        cancelar = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();
        reload = new javax.swing.JButton();
        since = new org.nekorp.workflow.desktop.view.resource.imp.EnteroJTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();

        setTitle("Busqueda Servicio");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Filtro");

        filtro.setBackground(new java.awt.Color(224, 230, 230));
        filtro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        filtro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        cancelar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        aceptar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        reload.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        reload.setText("Refresh");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });

        since.setBackground(new java.awt.Color(224, 230, 230));
        since.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        since.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 4));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("A partir del servicio");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaDatos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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
        tablaDatos.setFocusable(false);
        tablaDatos.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDatos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaDatos.setSelectionBackground(new java.awt.Color(224, 230, 230));
        tablaDatos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablaDatos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDatos);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 518, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(since, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reload))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelar)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reload)
                    .addComponent(since, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(aceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        updatePreferenciasFiltro();
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        updatePreferenciasFiltro();
        if (this.tablaDatos.getSelectedRowCount() >= 0) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            boolean cargoAlmenosUno = false;
            for (int x: this.tablaDatos.getSelectedRows()){
                ServicioIndexVB seleccion  = this.datos.get(this.tablaDatos.convertRowIndexToModel(x));
                this.setEnabled(false);
                if (!cargoAlmenosUno) {
                    cargoAlmenosUno = this.application.cargaServicio(seleccion.getIdServicio());
                } else {
                    this.application.cargaServicio(seleccion.getIdServicio());
                }
            }
            this.setEnabled(true);
            if (cargoAlmenosUno) {
                this.dispose();
            }
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        updatePreferenciasFiltro();
        if (evt.getClickCount() == 2) {
            if (this.tablaDatos.getSelectedRowCount() >= 0) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                boolean cargoAlmenosUno = false;
                for (int x: this.tablaDatos.getSelectedRows()){
                    ServicioIndexVB seleccion  = this.datos.get(this.tablaDatos.convertRowIndexToModel(x));
                    this.setEnabled(false);
                    if (!cargoAlmenosUno) {
                        cargoAlmenosUno = this.application.cargaServicio(seleccion.getIdServicio());
                    } else {
                        this.application.cargaServicio(seleccion.getIdServicio());
                    }
                }
                this.setEnabled(true);
                if (cargoAlmenosUno) {
                    this.dispose();
                }
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reload;
    private javax.swing.JTextField since;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables

}
