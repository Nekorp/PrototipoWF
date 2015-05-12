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

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasUsuario;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.cobranza.StatusCobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.resource.busqueda.AlignRightStringCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.CustomBusquedaRender;
import org.nekorp.workflow.desktop.view.resource.busqueda.DateCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.NumberFormatCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.ServicioTableModel;
import org.nekorp.workflow.desktop.view.resource.busqueda.StatusCobranzaCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component("busquedaServicioView")
public class BusquedaServicioV2View extends ApplicationView {

    @Autowired
    private WorkflowApp application;
    @Autowired
    private ServicioIndexMetadata servicioIndexMetadata;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    private javax.swing.table.TableRowSorter sorter;
    private ServicioTableModel tableModel;
    private final int[] sizeColumn = new int[] {
        10,
        50,
        200,
        50,
        70,
        50,
        200,
        40,
        50,
        50
    };
    /**
     * Creates new form BusquedaServicioV2View
     */
    public BusquedaServicioV2View() {
        super();
    }
    @Override
    public void iniciaVista() {
        initComponents();
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        this.filtro.setText(preferencias.getUltimoFiltro());
        //this.datos = this.application.getIndexServicios(preferencias.getFirstId());
        //this.setModeloTabla(datos);
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
        bindingManager.registerBind(servicioIndexMetadata, "index", new ReadOnlyBinding() {

            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                List<ServicioIndexVB> datos = (List<ServicioIndexVB>) value;
                setModeloTabla(datos);
                aplicaFiltro();
            }
        });
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
        tablaDatos.setDefaultRenderer(String.class, new CustomBusquedaRender());
        tablaDatos.setDefaultRenderer(Double.class, new NumberFormatCellRenderer());
        tablaDatos.setDefaultRenderer(Integer.class, new AlignRightStringCellRenderer());
        tablaDatos.setDefaultRenderer(Long.class, new AlignRightStringCellRenderer());
        tablaDatos.setDefaultRenderer(Date.class, new DateCellRenderer());
        tablaDatos.setDefaultRenderer(StatusCobranzaMetadata.class, new StatusCobranzaCellRenderer());
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
    public void setEditableStatus(boolean value) {
        this.filtro.setEnabled(value);
        this.reload.setEnabled(value);
        this.tablaDatos.setEnabled(value);
        this.aceptar.setEnabled(value);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filtro = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        reload = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        aceptar = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Filtro");

        filtro.setBackground(new java.awt.Color(102, 102, 102));
        filtro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        filtro.setForeground(new java.awt.Color(255, 255, 255));
        filtro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));
        filtro.setCaretColor(new java.awt.Color(255, 255, 255));
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filtroKeyPressed(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        reload.setBackground(new java.awt.Color(51, 51, 51));
        reload.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        reload.setForeground(new java.awt.Color(255, 255, 255));
        reload.setText("Refresh");
        reload.setFocusable(false);
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });
        jToolBar1.add(reload);
        jToolBar1.add(filler1);

        aceptar.setBackground(new java.awt.Color(51, 51, 51));
        aceptar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aceptar.setForeground(new java.awt.Color(255, 255, 255));
        aceptar.setText("Abrir");
        aceptar.setFocusable(false);
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        jToolBar1.add(aceptar);
        jToolBar1.add(filler2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

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
        tablaDatos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDatos);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        //PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        //Long sinceId = Long.parseLong(this.since.getText());
        //preferencias.setFirstId(sinceId);
        //this.application.setPreferenciasUsuario(preferencias);
        this.servicioIndexMetadata.setIndex(this.application.getIndexServicios());
        //this.setModeloTabla(datos);
        //updatePreferenciasFiltro();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_reloadActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        //updatePreferenciasFiltro();
        if (evt.getClickCount() == 2) {
            if (this.tablaDatos.getSelectedRowCount() >= 0) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                boolean cargoAlmenosUno = false;
                for (int x: this.tablaDatos.getSelectedRows()){
                    ServicioIndexVB seleccion  = this.servicioIndexMetadata.getIndex().get(this.tablaDatos.convertRowIndexToModel(x));
                    this.setEditableStatus(false);
                    if (!cargoAlmenosUno) {
                        cargoAlmenosUno = this.application.cargaServicio(seleccion.getIdServicio());
                    } else {
                        this.application.cargaServicio(seleccion.getIdServicio());
                    }
                }
                this.setEditableStatus(true);
                if (cargoAlmenosUno) {
                }
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        }
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        //updatePreferenciasFiltro();
        if (this.tablaDatos.getSelectedRowCount() >= 0) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            boolean cargoAlmenosUno = false;
            for (int x: this.tablaDatos.getSelectedRows()){
                ServicioIndexVB seleccion  = this.servicioIndexMetadata.getIndex().get(this.tablaDatos.convertRowIndexToModel(x));
                this.setEditableStatus(false);
                if (!cargoAlmenosUno) {
                    cargoAlmenosUno = this.application.cargaServicio(seleccion.getIdServicio());
                } else {
                    this.application.cargaServicio(seleccion.getIdServicio());
                }
            }
            this.setEditableStatus(true);
            if (cargoAlmenosUno) {
            }
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        } else {
        }
    }//GEN-LAST:event_aceptarActionPerformed

    private void filtroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            this.tablaDatos.dispatchEvent(evt);
        }
    }//GEN-LAST:event_filtroKeyPressed

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        aceptarActionPerformed(evt);
    }//GEN-LAST:event_filtroActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        this.filtro.requestFocus();
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton reload;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables

}
