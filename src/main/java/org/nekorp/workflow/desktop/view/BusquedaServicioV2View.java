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
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.preferencias.PreferenciasUsuario;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.icon.IconoRefresh;
import org.nekorp.workflow.desktop.view.model.cobranza.StatusCobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.nekorp.workflow.desktop.view.model.skin.SkinMetadata;
import org.nekorp.workflow.desktop.view.resource.busqueda.AlignRightStringCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.BusquedaServicioRowFilter;
import org.nekorp.workflow.desktop.view.resource.busqueda.CustomBusquedaRender;
import org.nekorp.workflow.desktop.view.resource.busqueda.DateCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.LongStringCustomRender;
import org.nekorp.workflow.desktop.view.resource.busqueda.NumberFormatCellRenderer;
import org.nekorp.workflow.desktop.view.resource.busqueda.ServicioTableModel;
import org.nekorp.workflow.desktop.view.resource.busqueda.StatusCobranzaCellRenderer;
import org.nekorp.workflow.desktop.view.resource.imp.JLabelButton;
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
    private JLabelButton botonRefresh;
    private JLabelButton botonAbrir;
    @Autowired
    private SkinMetadata skinMetadata; 
    /**
     * Creates new form BusquedaServicioV2View
     */
    public BusquedaServicioV2View() {
        super();
    }
    @Override
    public void iniciaVista() {
        iniciaBotones();
        initComponents();
        tableModel = new ServicioTableModel();
        sorter = new javax.swing.table.TableRowSorter(tableModel);
        this.tablaDatos.setModel(tableModel);
        this.tablaDatos.setRowSorter(sorter);
        for (int i = 0; i < tableModel.getSizeColumn().length; i++) {
            this.tablaDatos.getColumnModel().getColumn(i).setPreferredWidth(tableModel.getSizeColumn()[i]);
            this.tablaDatos.getColumnModel().getColumn(i).setMaxWidth(800);
        }
        this.tablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tablaDatos.setDefaultRenderer(String.class, new CustomBusquedaRender());
        tablaDatos.setDefaultRenderer(Double.class, new NumberFormatCellRenderer());
        tablaDatos.setDefaultRenderer(Integer.class, new AlignRightStringCellRenderer());
        tablaDatos.setDefaultRenderer(Long.class, new AlignRightStringCellRenderer());
        tablaDatos.setDefaultRenderer(Date.class, new DateCellRenderer());
        tablaDatos.setDefaultRenderer(StatusCobranzaMetadata.class, new StatusCobranzaCellRenderer());
        tablaDatos.getColumnModel().getColumn(6).setCellRenderer(new LongStringCustomRender());
        
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
                tableModel.setDatos(datos);
                aplicaFiltro();
            }
        });
        bindingManager.registerBind(skinMetadata, "info", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {                
                botonRefresh.setStyle(skinMetadata.getInfo().getMainButton());
                botonAbrir.setStyle(skinMetadata.getInfo().getMainButton());
            }
        });
    }
    
    private void iniciaBotones() {
        IconoRefresh iconoRefresh = new IconoRefresh(20, 20);
        botonRefresh = new JLabelButton(iconoRefresh) {
            @Override
            protected void actionPerform(MouseEvent evt) {
                reloadIndex();
            }
        };
        botonAbrir = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                abrirServicios();
            }
        };
    }
    
    private void aplicaFiltro() {
        String textoFiltro = filtro.getText();
        if (textoFiltro.length() > 0) {
            this.sorter.setRowFilter(new BusquedaServicioRowFilter(textoFiltro));
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
        this.refreshButton.setEnabled(value);
        this.tablaDatos.setEnabled(value);
        this.abrirButton.setEnabled(value);
    }

    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    private void reloadIndex() {
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        //PreferenciasUsuario preferencias = this.application.getPreferenciasUsuario();
        //Long sinceId = Long.parseLong(this.since.getText());
        //preferencias.setFirstId(sinceId);
        //this.application.setPreferenciasUsuario(preferencias);
        this.servicioIndexMetadata.setIndex(this.application.getIndexServicios());
        //this.setModeloTabla(datos);
        //updatePreferenciasFiltro();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    private void abrirServicios() {
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
        refreshButton = botonRefresh;
        filtro = new javax.swing.JTextField();
        abrirButton = botonAbrir;
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        refreshButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        refreshButton.setToolTipText("Refresh");

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

        abrirButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        abrirButton.setForeground(new java.awt.Color(255, 255, 255));
        abrirButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abrirButton.setText("Abrir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(abrirButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        if (evt.getClickCount() == 2) {
            this.abrirServicios();
        }
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void filtroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            this.tablaDatos.dispatchEvent(evt);
        }
    }//GEN-LAST:event_filtroKeyPressed

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        this.abrirServicios();
    }//GEN-LAST:event_filtroActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        this.filtro.requestFocus();
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abrirButton;
    private javax.swing.JTextField filtro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel refreshButton;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables

}
