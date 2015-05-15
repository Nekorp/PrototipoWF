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

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.servicio.monitor.MonitorCatalog;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.icon.IconoGuardar;
import org.nekorp.workflow.desktop.view.icon.IconoImprimir;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.skin.SkinMetadata;
import org.nekorp.workflow.desktop.view.resource.imp.JLabelButton;
import org.nekorp.workflow.desktop.view.resource.imp.JLabelToggleButton;
import org.nekorp.workflow.desktop.view.resource.imp.ToggleGroup;
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
    private MonitoredApplicationView bitacora;
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
    private MonitoredApplicationView costos;
    @Autowired
    @Qualifier(value="inventarioDamageView")
    private MonitoredApplicationView inventarioDamage;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    @Autowired
    private EditorMonitorManager editorManager;
    private JLabelButton botonGuardar;
    private JLabelButton botonImprimir;
    private JLabelButton botonCerrar;
    private JLabelToggleButton botonCliente;
    private JLabelToggleButton botonAuto;
    private JLabelToggleButton botonBitacora;
    private JLabelToggleButton botonPresupuesto;
    private JLabelToggleButton botonInventario;
    private JLabelToggleButton botonCobranza;
    @Autowired
    private SkinMetadata skinMetadata;
    //private javax.swing.JTabbedPane tabDatos;
    
    @Override
    public void setEditableStatus(boolean value) {
        this.datos.setVisible(value);
        this.selectorContainer.setVisible(value);
        this.botonGuardar.setVisible(value);
        this.botonImprimir.setVisible(value);
        this.botonCerrar.setVisible(value);
        this.updateUI();
    }
    
    @Override
    public void iniciaVista() {
        iniciarBotones();
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
        //this.botonCliente.setSelected(true);
    }
    
    private void iniciarBotones() {
        ToggleGroup toggleGroup = new ToggleGroup();
        IconoGuardar iconoGuardar = new IconoGuardar(18, 18);
        botonGuardar = new JLabelButton(iconoGuardar) {
            @Override
            protected void actionPerform(MouseEvent evt) {
                setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                aplication.guardaServicio();
                setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        };
        IconoImprimir iconoImprimir = new IconoImprimir(20, 20);
        botonImprimir = new JLabelButton(iconoImprimir) {
            @Override
            protected void actionPerform(MouseEvent evt) {
                inicioOrdenDeServicio();
            }
        };
        botonCerrar = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                cerrarServicio();
            }
        };
        botonCliente = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "cliente");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("cliente");
                editorManager.selectMonitor(MonitorCatalog.CUSTOMER);
            }
        };
        botonCliente.setToggleGroup(toggleGroup);
        botonAuto = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "auto");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("auto");
                editorManager.selectMonitor(MonitorCatalog.AUTO);
            }
        };
        botonAuto.setToggleGroup(toggleGroup);
        botonBitacora = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "bitacora");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("bitacora");
                bitacora.activeMonitor();
            }
        };
        botonBitacora.setToggleGroup(toggleGroup);
        botonPresupuesto = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "presupuesto");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("presupuesto");
                costos.activeMonitor();
            }
        };
        botonPresupuesto.setToggleGroup(toggleGroup);
        botonInventario = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "inventario");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("inventario");
                inventarioDamage.activeMonitor();
            }
        };
        botonInventario.setToggleGroup(toggleGroup);
        botonCobranza = new JLabelToggleButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
                cardLayout.show(datos, "cobranza");
                servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentTab("cobranza");
                editorManager.selectMonitor(MonitorCatalog.COBRANZA);
            }
        };
        botonCobranza.setToggleGroup(toggleGroup);
    }
    
    private void inicioOrdenDeServicio() {
        try {
            if (servicioMetaData.isEditado()) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                boolean guardado =this.aplication.guardaServicio();
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
                if (!guardado) {
                    return;
                }
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
        } catch (IOException ex) {
            ServicioView.LOGGER.error("error al exportar orden de servicio", ex);
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }
    
    private void cerrarServicio() {
        if (this.servicioMetaData.isEditado()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Guardar Servicio?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                if (this.aplication.guardaServicio()) {
                    this.aplication.cerrarServicio();
                }
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        this.aplication.cerrarServicio();
    }
    
    private void bindComponents() {
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
                        editorManager.selectMonitor(MonitorCatalog.CUSTOMER);
                        botonCliente.setSelected(true);
                        break;
                    case "auto":
                        editorManager.selectMonitor(MonitorCatalog.AUTO);
                        botonAuto.setSelected(true);
                        break;
                    case "bitacora":
                        bitacora.activeMonitor();
                        botonBitacora.setSelected(true);
                        break;
                    case "presupuesto":
                        costos.activeMonitor();
                        botonPresupuesto.setSelected(true);
                        break;
                    case "inventario":
                        inventarioDamage.activeMonitor();
                        botonInventario.setSelected(true);
                        break;
                    case "cobranza":
                        editorManager.selectMonitor(MonitorCatalog.COBRANZA);
                        botonCobranza.setSelected(true);
                        break;
                }
            }
        });
        bindingManager.registerBind(viewServicioModel.getBitacora(), "eventos", (Bindable)this.bitacora);
        //bindings con el metadata del servicio
        bindingManager.registerBind(servicioMetaData, "editado", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean editado = (boolean) value;
                botonGuardar.setEnabled(editado);
            }
        });
        bindingManager.registerBind(servicioMetaData, "servicioCargado", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean cargado = (boolean) value;
                botonImprimir.setEnabled(cargado);
            }
        });
        //bindingManager.registerBind(servicioMetaData, "tieneUndo", (Bindable)this.deshacer);
        //bindingManager.registerBind(servicioMetaData, "tieneRedo", (Bindable)this.rehacer);
        bindingManager.registerBind(skinMetadata, "info", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {                
                botonGuardar.setStyle(skinMetadata.getInfo().getMainButton());
                botonImprimir.setStyle(skinMetadata.getInfo().getMainButton());
                botonCerrar.setStyle(skinMetadata.getInfo().getMainButton());
                botonCliente.setStyle(skinMetadata.getInfo().getMainTab());
                botonAuto.setStyle(skinMetadata.getInfo().getMainTab());
                botonBitacora.setStyle(skinMetadata.getInfo().getMainTab());
                botonPresupuesto.setStyle(skinMetadata.getInfo().getMainTab());
                botonInventario.setStyle(skinMetadata.getInfo().getMainTab());
                botonCobranza.setStyle(skinMetadata.getInfo().getMainTab());
            }
        });
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
        guardarLabel = botonGuardar;
        imprimirLabel = botonImprimir;
        cerrarLabel = botonCerrar;
        selectorContainer = new javax.swing.JPanel();
        clienteSelectorLabel = botonCliente;
        autoSelectorLabel = botonAuto;
        bitacoraSelectorLabel = botonBitacora;
        presupuestoSelectorLabel = botonPresupuesto;
        inventarioSelectorLabel = botonInventario;
        cobranzaSelectorLabel = botonCobranza;
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

        cerrarLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cerrarLabel.setForeground(new java.awt.Color(255, 255, 255));
        cerrarLabel.setText("X");

        javax.swing.GroupLayout menuContainerLayout = new javax.swing.GroupLayout(menuContainer);
        menuContainer.setLayout(menuContainerLayout);
        menuContainerLayout.setHorizontalGroup(
            menuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuContainerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(guardarLabel)
                .addGap(18, 18, 18)
                .addComponent(imprimirLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cerrarLabel)
                .addContainerGap())
        );
        menuContainerLayout.setVerticalGroup(
            menuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuContainerLayout.createSequentialGroup()
                .addGroup(menuContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imprimirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cerrarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
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

        selectorContainer.setBackground(new java.awt.Color(153, 153, 153));

        clienteSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        clienteSelectorLabel.setText("Cliente");

        autoSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        autoSelectorLabel.setText("Auto");

        bitacoraSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bitacoraSelectorLabel.setText("Bitacora");

        presupuestoSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        presupuestoSelectorLabel.setText("Presupuesto");

        inventarioSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inventarioSelectorLabel.setText("Inventario de daños");

        cobranzaSelectorLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cobranzaSelectorLabel.setText("Cobranza");

        javax.swing.GroupLayout selectorContainerLayout = new javax.swing.GroupLayout(selectorContainer);
        selectorContainer.setLayout(selectorContainerLayout);
        selectorContainerLayout.setHorizontalGroup(
            selectorContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectorContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clienteSelectorLabel)
                .addGap(24, 24, 24)
                .addComponent(autoSelectorLabel)
                .addGap(24, 24, 24)
                .addComponent(bitacoraSelectorLabel)
                .addGap(24, 24, 24)
                .addComponent(presupuestoSelectorLabel)
                .addGap(24, 24, 24)
                .addComponent(inventarioSelectorLabel)
                .addGap(24, 24, 24)
                .addComponent(cobranzaSelectorLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        selectorContainerLayout.setVerticalGroup(
            selectorContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clienteSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(bitacoraSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(autoSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(presupuestoSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventarioSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cobranzaSelectorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel autoPanel;
    private javax.swing.JLabel autoSelectorLabel;
    private javax.swing.JPanel bitacoraPanel;
    private javax.swing.JLabel bitacoraSelectorLabel;
    private javax.swing.JLabel cerrarLabel;
    private javax.swing.JPanel clientePanel;
    private javax.swing.JLabel clienteSelectorLabel;
    private javax.swing.JPanel cobranzaPanel;
    private javax.swing.JLabel cobranzaSelectorLabel;
    private javax.swing.JPanel datos;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JLabel guardarLabel;
    private javax.swing.JLabel imprimirLabel;
    private javax.swing.JPanel inventarioPanel;
    private javax.swing.JLabel inventarioSelectorLabel;
    private javax.swing.JPanel menuBackground;
    private javax.swing.JPanel menuContainer;
    private javax.swing.JPanel presupuestoPanel;
    private javax.swing.JLabel presupuestoSelectorLabel;
    private javax.swing.ButtonGroup selector;
    private javax.swing.JPanel selectorContainer;
    // End of variables declaration//GEN-END:variables

}
