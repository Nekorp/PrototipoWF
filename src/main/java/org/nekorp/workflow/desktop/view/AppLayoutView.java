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

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.joda.time.DateTime;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.reporte.global.ParametrosReporteGlobal;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.reporte.global.ParametrosReporteGlobalVB;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.skin.SkinMetadata;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.nekorp.workflow.desktop.view.resource.imp.JLabelButton;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/**
 *
 * @author Nekorp
 */
@Component("appLayoutView")
public class AppLayoutView extends ApplicationView  {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AppLayoutView.class);
    @Autowired
    @Qualifier(value = "servicioView")
    private ApplicationView servicioView;
    @Autowired
    @Qualifier(value = "busquedaServicioView")
    private ApplicationView busquedaServicioView;
    @Autowired
    @Qualifier(value = "navegadorServiciosView")
    private ApplicationView navegadorServiciosView;
    @Autowired
    @Qualifier(value = "servicioDatosGeneralesView")
    private ApplicationView servicioDatosGeneralesView;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="progrmacionWizardDialogFactory")
    private DialogFactory dialogFactoryWizardProgramacion;
    @Autowired
    @Qualifier(value="parametrosReporteGlobalDialogFactory")
    private DialogFactory parametrosReporteGlobalDialogFactory;
    @Autowired
    private ParametrosReporteGlobalVB parametrosReporteGlobal;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    private JLabelButton botonNuevo;
    private JLabelButton botonBuscar;
    private JLabelButton botonFormatoProgramacion;
    private JLabelButton botonReporteGlobal;
    @Autowired
    private SkinMetadata skinMetadata;
    /**
     * Creates new form NavegadorServicios
     */
    public AppLayoutView() {
        super();
    }
    
    @Override
    public void iniciaVista() {
        crearBotones();
        initComponents();
        servicioDatosGeneralesView.iniciaVista();
        this.datosGeneralesPanel.add(servicioDatosGeneralesView);
        navegadorServiciosView.iniciaVista();
        this.navigationPanel.add(navegadorServiciosView);
        this.servicioView.iniciaVista();
        this.contenidoServicio.add(servicioView);
        this.busquedaServicioView.iniciaVista();
        this.busqueda.add(busquedaServicioView);
        switchContenido("busqueda");
        busquedaServicioView.requestFocus();
        bindingManager.registerBind(servicioMetaData, "editando", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean cargado = (boolean) value;
                if (cargado) {
                    switchContenido("servicio");
                } else {
                    switchContenido("busqueda");
                    busquedaServicioView.requestFocus();
                }
            }
        });
        bindingManager.registerBind(skinMetadata, "info", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {                
                botonNuevo.setStyle(skinMetadata.getInfo().getMainButton());
                botonBuscar.setStyle(skinMetadata.getInfo().getMainButton());
                botonFormatoProgramacion.setStyle(skinMetadata.getInfo().getMainButton());
                botonReporteGlobal.setStyle(skinMetadata.getInfo().getMainButton());
            }
        });
    }
    
    private void crearBotones() {
        botonNuevo = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                aplication.crearServicio();
            }
        };
        botonBuscar = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                servicioMetaData.setEditando(false);
            }
        };
        botonFormatoProgramacion = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                dialogFactoryWizardProgramacion.createDialog(mainFrame, true).setVisible(true);
            }
        };
        botonReporteGlobal = new JLabelButton() {
            @Override
            protected void actionPerform(MouseEvent evt) {
                inicioReporteGlobal();
            }
        };
    }
    
    private void inicioReporteGlobal() {
        try {
            parametrosReporteGlobal.setFechaInicial(new Date());
            parametrosReporteGlobal.setFechaFinal(new Date());
            parametrosReporteGlobalDialogFactory.createDialog(mainFrame, true).setVisible(true);
            if (parametrosReporteGlobal.isEjecutar()) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Hojas de cálculo", "xlsx");
                chooser.setFileFilter(filter);
                String homePath = System.getProperty("user.home");
                File f = new File(new File(homePath+"/Reporte-Global" + ".xlsx").getCanonicalPath());
                chooser.setSelectedFile(f);
                int returnVal = chooser.showSaveDialog(this.mainFrame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                    ParametrosReporteGlobal param = new ParametrosReporteGlobal();
                    param.setDestination(chooser.getSelectedFile());
                    DateTime fechaInicial = new DateTime(parametrosReporteGlobal.getFechaInicial());
                    DateTime fechaFinal = new DateTime(parametrosReporteGlobal.getFechaFinal());
                    fechaFinal = new DateTime(fechaFinal.getYear(), fechaFinal.getMonthOfYear(), fechaFinal.getDayOfMonth(),
                        fechaFinal.hourOfDay().getMaximumValue(), fechaFinal.minuteOfHour().getMaximumValue(),
                        fechaFinal.secondOfMinute().getMaximumValue(), fechaFinal.millisOfSecond().getMaximumValue(),
                        fechaFinal.getZone());
                    param.setFechaInicial(fechaInicial);
                    param.setFechaFinal(fechaFinal);
                    this.aplication.generaReporteGlobal(param);
                }
            }
        } catch (IOException ex) {
            AppLayoutView.LOGGER.error("Exploto al tratar de generar el reporte global", ex);
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }
    
    private void switchContenido(String card) {
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(contenidoView.getLayout());
        cardLayout.show(contenidoView, card);
    }

    @Override
    public void setEditableStatus(boolean value) {
        
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nuevoLabel = botonNuevo;
        buscarLabel = botonBuscar;
        formatoProgramacionLabel = botonFormatoProgramacion;
        reporteGlobalLabel = botonReporteGlobal;
        contenidoView = new javax.swing.JPanel();
        servicioPanel = new javax.swing.JPanel();
        datosGeneralesPanel = new javax.swing.JPanel();
        contenidoServicio = new javax.swing.JPanel();
        busqueda = new javax.swing.JPanel();
        navigationPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        nuevoLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nuevoLabel.setForeground(new java.awt.Color(255, 255, 255));
        nuevoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nuevoLabel.setText("Nuevo");

        buscarLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        buscarLabel.setForeground(new java.awt.Color(255, 255, 255));
        buscarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buscarLabel.setText("Buscar");

        formatoProgramacionLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        formatoProgramacionLabel.setForeground(new java.awt.Color(255, 255, 255));
        formatoProgramacionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formatoProgramacionLabel.setText("Formato de Programación");

        reporteGlobalLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        reporteGlobalLabel.setForeground(new java.awt.Color(255, 255, 255));
        reporteGlobalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reporteGlobalLabel.setText("Reporte Global");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(nuevoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buscarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(formatoProgramacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reporteGlobalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nuevoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(buscarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(formatoProgramacionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(reporteGlobalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        contenidoView.setBackground(new java.awt.Color(255, 255, 255));
        contenidoView.setLayout(new java.awt.CardLayout());

        datosGeneralesPanel.setBackground(new java.awt.Color(51, 51, 51));
        datosGeneralesPanel.setLayout(new java.awt.BorderLayout());

        contenidoServicio.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout servicioPanelLayout = new javax.swing.GroupLayout(servicioPanel);
        servicioPanel.setLayout(servicioPanelLayout);
        servicioPanelLayout.setHorizontalGroup(
            servicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, servicioPanelLayout.createSequentialGroup()
                .addComponent(contenidoServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(datosGeneralesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        servicioPanelLayout.setVerticalGroup(
            servicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datosGeneralesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contenidoServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contenidoView.add(servicioPanel, "servicio");

        busqueda.setLayout(new java.awt.BorderLayout());
        contenidoView.add(busqueda, "busqueda");

        navigationPanel.setBackground(new java.awt.Color(51, 51, 51));
        navigationPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenidoView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(navigationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(contenidoView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buscarLabel;
    private javax.swing.JPanel busqueda;
    private javax.swing.JPanel contenidoServicio;
    private javax.swing.JPanel contenidoView;
    private javax.swing.JPanel datosGeneralesPanel;
    private javax.swing.JLabel formatoProgramacionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel navigationPanel;
    private javax.swing.JLabel nuevoLabel;
    private javax.swing.JLabel reporteGlobalLabel;
    private javax.swing.JPanel servicioPanel;
    // End of variables declaration//GEN-END:variables
}
