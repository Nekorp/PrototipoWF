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

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.icon.IconoGuardar;
import org.nekorp.workflow.desktop.view.icon.IconoImprimir;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * @author Nekorp
 */
@Component("servicioView")
public class ServicioView extends ApplicationView {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ServicioView.class);
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="bitacoraView")
    private ApplicationView bitacora;
    @Autowired
    @Qualifier(value="cobranzaView")
    private ApplicationView cobranza;
    @Autowired
    @Qualifier(value="datosClienteView")
    private ApplicationView datosCliente;
    @Autowired
    @Qualifier(value="datosAutoView")
    private ApplicationView datosAuto;
    @Autowired
    @Qualifier(value="costosView")
    private ApplicationView costos;
    @Autowired
    @Qualifier(value="inventarioDamageView")
    private ApplicationView inventarioDamage;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    //@Autowired
    //private EditorMonitor editorMonitor;
    //private javax.swing.JTabbedPane tabDatos;
    
    @Override
    public void setEditableStatus(boolean value) {
        this.datos.setVisible(value);
        this.selectorContainer.setVisible(value);
        this.guardarServicio.setVisible(value);
        this.ordenServicio.setVisible(value);
        this.cerrarServicio.setVisible(value);
        this.updateUI();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bitacora.iniciaVista();
        this.bitacoraPanel.add(bitacora);
        cobranza.iniciaVista();
        this.cobranzaPanel.add(cobranza);
        datosCliente.iniciaVista();
        this.clientePanel.add(datosCliente);
        datosAuto.iniciaVista();
        this.autoPanel.add(datosAuto);
        costos.iniciaVista();
        this.presupuestoPanel.add(costos);
        inventarioDamage.iniciaVista();
        this.inventarioPanel.add(inventarioDamage);
        bindComponents();
        this.clienteSelector.setSelected(true);
    }
    
    public void bindComponents() {
        //bindings al metatada del servicio
        bindingManager.registerBind(servicioMetaData, "servicioCargado", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean cargado = (boolean) value;
                setEditableStatus(cargado);
            }
        });
        this.bindingManager.registerBind(servicioMetaData, "servicioActual", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                ServicioLoaded servicio = (ServicioLoaded) value;
                if (servicio == null) {
                    return;
                }
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, servicio.getPreferenciasEdicion().getCurrentTab());
                switch(servicio.getPreferenciasEdicion().getCurrentTab()) {
                    case "cliente": 
                        clienteSelector.setSelected(true);
                        break;
                    case "auto":
                        autoSelector.setSelected(true);
                        break;
                    case "bitacora":
                        bitacoraSelector.setSelected(true);
                        break;
                    case "presupuesto":
                        presupuestoSelector.setSelected(true);
                        break;
                    case "inventario":
                        inventarioSelector.setSelected(true);
                        break;
                    case "cobranza":
                        cobranzaSelector.setSelected(true);
                        break;
                }
            }
        });
        bindingManager.registerBind(viewServicioModel.getBitacora(), "eventos", (Bindable)this.bitacora);
        //bindings con el metadata del servicio
        bindingManager.registerBind(servicioMetaData, "editado", (Bindable)this.guardarServicio);
        bindingManager.registerBind(servicioMetaData, "servicioCargado", (Bindable)this.ordenServicio);
        //bindingManager.registerBind(servicioMetaData, "tieneUndo", (Bindable)this.deshacer);
        //bindingManager.registerBind(servicioMetaData, "tieneRedo", (Bindable)this.rehacer);
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

        selector = new javax.swing.ButtonGroup();
        menuBackground = new javax.swing.JPanel();
        menuContainer = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        guardarServicio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        ordenServicio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        cerrarServicio = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        selectorContainer = new javax.swing.JPanel();
        selectorTool = new javax.swing.JToolBar();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        clienteSelector = new javax.swing.JToggleButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        autoSelector = new javax.swing.JToggleButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        bitacoraSelector = new javax.swing.JToggleButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        presupuestoSelector = new javax.swing.JToggleButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        inventarioSelector = new javax.swing.JToggleButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        cobranzaSelector = new javax.swing.JToggleButton();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        datos = new javax.swing.JPanel();
        clientePanel = new javax.swing.JPanel();
        autoPanel = new javax.swing.JPanel();
        bitacoraPanel = new javax.swing.JPanel();
        presupuestoPanel = new javax.swing.JPanel();
        inventarioPanel = new javax.swing.JPanel();
        cobranzaPanel = new javax.swing.JPanel();
        emptyPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        menuBackground.setBackground(new java.awt.Color(102, 102, 102));

        menuContainer.setBackground(new java.awt.Color(51, 51, 51));

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(filler4);

        guardarServicio.setBackground(new java.awt.Color(51, 51, 51));
        guardarServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        guardarServicio.setForeground(new java.awt.Color(255, 255, 255));
        guardarServicio.setIcon(new IconoGuardar(18, 18, new java.awt.Color(255, 255, 255), new java.awt.Color(51, 51, 51)));
        guardarServicio.setToolTipText("Guardar Servicio");
        guardarServicio.setFocusable(false);
        guardarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(guardarServicio);
        jToolBar1.add(filler5);

        ordenServicio.setBackground(new java.awt.Color(51, 51, 51));
        ordenServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ordenServicio.setForeground(new java.awt.Color(255, 255, 255));
        ordenServicio.setIcon(new IconoImprimir(18, 18, new java.awt.Color(255, 255, 255), new java.awt.Color(51, 51, 51)));
        ordenServicio.setToolTipText("Imprimir Orden de Servicio");
        ordenServicio.setFocusable(false);
        ordenServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ordenServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ordenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(ordenServicio);
        jToolBar1.add(filler1);

        cerrarServicio.setBackground(new java.awt.Color(51, 51, 51));
        cerrarServicio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cerrarServicio.setForeground(new java.awt.Color(255, 255, 255));
        cerrarServicio.setText("X");
        cerrarServicio.setToolTipText("Cerrar");
        cerrarServicio.setFocusable(false);
        cerrarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cerrarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cerrarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(cerrarServicio);
        jToolBar1.add(filler2);

        javax.swing.GroupLayout menuContainerLayout = new javax.swing.GroupLayout(menuContainer);
        menuContainer.setLayout(menuContainerLayout);
        menuContainerLayout.setHorizontalGroup(
            menuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuContainerLayout.setVerticalGroup(
            menuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuContainerLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuBackgroundLayout = new javax.swing.GroupLayout(menuBackground);
        menuBackground.setLayout(menuBackgroundLayout);
        menuBackgroundLayout.setHorizontalGroup(
            menuBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuBackgroundLayout.setVerticalGroup(
            menuBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        selectorTool.setBackground(new java.awt.Color(153, 153, 153));
        selectorTool.setFloatable(false);
        selectorTool.setRollover(true);
        selectorTool.add(filler11);

        selector.add(clienteSelector);
        clienteSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clienteSelector.setText("Cliente");
        clienteSelector.setFocusable(false);
        clienteSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clienteSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clienteSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(clienteSelector);
        selectorTool.add(filler6);

        selector.add(autoSelector);
        autoSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        autoSelector.setText("Auto");
        autoSelector.setFocusable(false);
        autoSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        autoSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        autoSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(autoSelector);
        selectorTool.add(filler7);

        selector.add(bitacoraSelector);
        bitacoraSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        bitacoraSelector.setText("Bitacora");
        bitacoraSelector.setFocusable(false);
        bitacoraSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bitacoraSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bitacoraSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitacoraSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(bitacoraSelector);
        selectorTool.add(filler8);

        selector.add(presupuestoSelector);
        presupuestoSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        presupuestoSelector.setText("Presupuesto");
        presupuestoSelector.setFocusable(false);
        presupuestoSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presupuestoSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        presupuestoSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presupuestoSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(presupuestoSelector);
        selectorTool.add(filler9);

        selector.add(inventarioSelector);
        inventarioSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        inventarioSelector.setText("Inventario de daños");
        inventarioSelector.setFocusable(false);
        inventarioSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inventarioSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inventarioSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(inventarioSelector);
        selectorTool.add(filler10);

        selector.add(cobranzaSelector);
        cobranzaSelector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cobranzaSelector.setText("Cobranza");
        cobranzaSelector.setFocusable(false);
        cobranzaSelector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cobranzaSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cobranzaSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobranzaSelectorActionPerformed(evt);
            }
        });
        selectorTool.add(cobranzaSelector);
        selectorTool.add(filler12);

        javax.swing.GroupLayout selectorContainerLayout = new javax.swing.GroupLayout(selectorContainer);
        selectorContainer.setLayout(selectorContainerLayout);
        selectorContainerLayout.setHorizontalGroup(
            selectorContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectorTool, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectorContainerLayout.setVerticalGroup(
            selectorContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectorTool, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        datos.setBackground(new java.awt.Color(255, 255, 255));
        datos.setLayout(new java.awt.CardLayout());

        clientePanel.setLayout(new java.awt.BorderLayout());
        datos.add(clientePanel, "cliente");

        autoPanel.setLayout(new java.awt.BorderLayout());
        datos.add(autoPanel, "auto");

        bitacoraPanel.setLayout(new java.awt.BorderLayout());
        datos.add(bitacoraPanel, "bitacora");

        presupuestoPanel.setLayout(new java.awt.BorderLayout());
        datos.add(presupuestoPanel, "presupuesto");

        inventarioPanel.setLayout(new java.awt.BorderLayout());
        datos.add(inventarioPanel, "inventario");

        cobranzaPanel.setLayout(new java.awt.BorderLayout());
        datos.add(cobranzaPanel, "cobranza");

        javax.swing.GroupLayout emptyPanelLayout = new javax.swing.GroupLayout(emptyPanel);
        emptyPanel.setLayout(emptyPanelLayout);
        emptyPanelLayout.setHorizontalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        datos.add(emptyPanel, "empty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(selectorContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(selectorContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarServicioActionPerformed
        try {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            this.aplication.guardaServicio();
        } catch (IllegalArgumentException e) {
            //mmm si una excepcion especial para este caso
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_guardarServicioActionPerformed

    private void ordenServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenServicioActionPerformed
        try {
            if (servicioMetaData.isEditado()) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                this.aplication.guardaServicio();
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
            ParametrosReporteOS param = new ParametrosReporteOS();
            Object[] options = {"Evaluación",
                                "Presupuesto"};
            int n = javax.swing.JOptionPane.showOptionDialog(mainFrame,
                "¿Qué tipo de reporte desea generar?",
                "Tipo de reporte",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
            if (n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
            param.setConCosto(!(n == javax.swing.JOptionPane.YES_OPTION));
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivo PDF", "pdf");
            chooser.setFileFilter(filter);
            String homePath = System.getProperty("user.home");
            String prefijo;
            if (param.isConCosto()) {
                prefijo = "/Orden-Servicio-presupuesto-";
            } else {
                prefijo = "/Orden-Servicio-evaluacion-";
            }
            File f = new File(new File(homePath + prefijo + this.viewServicioModel.getId() + ".pdf").getCanonicalPath());
            chooser.setSelectedFile(f);  
            int returnVal = chooser.showSaveDialog(this.mainFrame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                param.setDestination(chooser.getSelectedFile());
                this.aplication.generaOrdenServicio(param);
            }
        } catch (IllegalArgumentException e) {
            //no lo guardo por que tenia horribles errores... tambien especializar la excepcion
        } catch (IOException ex) {
            ServicioView.LOGGER.error("error al exportar orden de servicio", ex);
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_ordenServicioActionPerformed

    private void clienteSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "cliente");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("cliente");
    }//GEN-LAST:event_clienteSelectorActionPerformed

    private void autoSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "auto");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("auto");
    }//GEN-LAST:event_autoSelectorActionPerformed

    private void bitacoraSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitacoraSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "bitacora");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("bitacora");
    }//GEN-LAST:event_bitacoraSelectorActionPerformed

    private void presupuestoSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presupuestoSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "presupuesto");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("presupuesto");
    }//GEN-LAST:event_presupuestoSelectorActionPerformed

    private void inventarioSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "inventario");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("inventario");
    }//GEN-LAST:event_inventarioSelectorActionPerformed

    private void cobranzaSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobranzaSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "cobranza");
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("cobranza");
    }//GEN-LAST:event_cobranzaSelectorActionPerformed

    private void cerrarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarServicioActionPerformed
        try {
            if (this.servicioMetaData.isEditado()) {
                int n = javax.swing.JOptionPane.showConfirmDialog(
                        this,
                        "¿Guardar Servicio?",
                        "Guardar",
                        javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == javax.swing.JOptionPane.YES_OPTION) {
                    this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                    this.aplication.guardaServicio();
                }
                if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }
            this.aplication.cerrarServicio();
        } catch (IllegalArgumentException e) {
            //por ahora nada
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_cerrarServicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel autoPanel;
    private javax.swing.JToggleButton autoSelector;
    private javax.swing.JPanel bitacoraPanel;
    private javax.swing.JToggleButton bitacoraSelector;
    private javax.swing.JButton cerrarServicio;
    private javax.swing.JPanel clientePanel;
    private javax.swing.JToggleButton clienteSelector;
    private javax.swing.JPanel cobranzaPanel;
    private javax.swing.JToggleButton cobranzaSelector;
    private javax.swing.JPanel datos;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton guardarServicio;
    private javax.swing.JPanel inventarioPanel;
    private javax.swing.JToggleButton inventarioSelector;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel menuBackground;
    private javax.swing.JPanel menuContainer;
    private javax.swing.JButton ordenServicio;
    private javax.swing.JPanel presupuestoPanel;
    private javax.swing.JToggleButton presupuestoSelector;
    private javax.swing.ButtonGroup selector;
    private javax.swing.JPanel selectorContainer;
    private javax.swing.JToolBar selectorTool;
    // End of variables declaration//GEN-END:variables

}
