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
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.joda.time.DateTime;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.reporte.global.ParametrosReporteGlobal;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraMetaData;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaWarningLevel;
import org.nekorp.workflow.desktop.view.model.reporte.global.ParametrosReporteGlobalVB;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * @author Nekorp
 * 
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
    private BitacoraMetaData bitacoraMetaData;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    @Autowired
    private CobranzaMetadata cobranzaMetadata;
    @Autowired
    @Qualifier(value="nuevoServicioWizardDialogFactory")
    private DialogFactory dialogFactory;
    @Autowired
    @Qualifier(value="progrmacionWizardDialogFactory")
    private DialogFactory dialogFactoryWizardProgramacion;
    @Autowired
    @Qualifier(value="servicioPreviewDialogFactory")
    private DialogFactory servicioPreviewDialogFactory;
    @Autowired
    @Qualifier(value="parametrosReporteGlobalDialogFactory")
    private DialogFactory parametrosReporteGlobalDialogFactory;
    @Autowired
    private ParametrosReporteGlobalVB parametrosReporteGlobal;
    //@Autowired
    //private EditorMonitor editorMonitor;
    //private javax.swing.JTabbedPane tabDatos;
    private BusquedaServicioView busquedaDialog;
    
    
    @Override
    public void setEditableStatus(boolean value) {
        //this.datosGenerales.setVisible(value);
        this.selectorTool.setVisible(value);
        this.datos.setVisible(value);
        if (value) {
            java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
            cardLayout.show(datos, "presupuesto");
            this.presupuestoSelector.setSelected(value);
        }
        //el boton esta unido al metadata del servicio
        //this.generaReporte.setEnabled(value);
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
        //tabDatos = new javax.swing.JTabbedPane();
        //tabDatos.setBorder(null);
        bindComponents();
        busquedaDialog = new BusquedaServicioView(mainFrame, true, this.aplication, servicioPreviewDialogFactory);
    }
    
    public void bindComponents() {
        //bindings con el servicio
        bindingManager.registerBind(viewServicioModel, "id",(Bindable)this.numeroServicio);
        bindingManager.registerBind(viewServicioModel.getAuto(), "placas",(Bindable)this.placas);
        bindingManager.registerBind(viewServicioModel.getBitacora(), "eventos", (Bindable)this.bitacora);
        //bindings con el cliente
        bindingManager.registerBind(viewServicioModel.getCliente(), "nombre",(Bindable)this.nombreCliente);
        //bindings con el metadata de la bitacora
        bindingManager.registerBind(bitacoraMetaData, "fechaInicioServicio", (Bindable)this.inicioServicio);
        bindingManager.registerBind(bitacoraMetaData, "fechaFinServicio", (Bindable)this.finServicio);
        bindingManager.registerBind(bitacoraMetaData, "tiempoServicio", (Bindable)this.duracionServicio);
        
        bindingManager.registerBind(bitacoraMetaData, "fechaEntrada", (Bindable)this.ingreso);
        bindingManager.registerBind(bitacoraMetaData, "fechaSalidaAuto", (Bindable)this.salida);
        bindingManager.registerBind(bitacoraMetaData, "tiempoEstadia", (Bindable)this.tiempo);
        //bindings con el metadata del servicio
        bindingManager.registerBind(servicioMetaData, "editado", (Bindable)this.guardarServicio);
        bindingManager.registerBind(servicioMetaData, "servicioCargado", (Bindable)this.ordenServicio);
        //bindingManager.registerBind(servicioMetaData, "tieneUndo", (Bindable)this.deshacer);
        //bindingManager.registerBind(servicioMetaData, "tieneRedo", (Bindable)this.rehacer);
        //bindings al metadata de cobranza cobranzaMetadata
        bindingManager.registerBind(cobranzaMetadata, "saldo", (Bindable)this.saldo);
        Bindable saldoDecoratorBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                CobranzaWarningLevel warningLevel = (CobranzaWarningLevel) value;
                switch (warningLevel) {
                    case info:
                        saldo.setBackground(Color.GREEN);
                        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN, 4));
                    break;
                    
                    case warn:
                        saldo.setBackground(Color.YELLOW);
                        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(Color.YELLOW, 4));
                    break;
                         
                    case urgent:
                        saldo.setBackground(Color.RED);
                        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 4));
                    break;
                        
                    default:
                        saldo.setBackground(Color.GREEN);
                        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN, 4));
                    break;
                }
            }
        };
        bindingManager.registerBind(cobranzaMetadata, "warningLevel", saldoDecoratorBind);
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
        jToolBar1 = new javax.swing.JToolBar();
        nuevoServicio = new javax.swing.JButton();
        buscarServicio = new javax.swing.JButton();
        guardarServicio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        programacion = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        ordenServicio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        reporteGlobal = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        mensaje = new javax.swing.JLabel();
        datosGenerales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        numeroServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJLabel();
        jLabel1 = new javax.swing.JLabel();
        nombreCliente = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel3 = new javax.swing.JLabel();
        placas = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        inicioServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel8 = new javax.swing.JLabel();
        finServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel9 = new javax.swing.JLabel();
        duracionServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel5 = new javax.swing.JLabel();
        ingreso = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel7 = new javax.swing.JLabel();
        salida = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel6 = new javax.swing.JLabel();
        tiempo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel10 = new javax.swing.JLabel();
        saldo = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        selectorTool = new javax.swing.JToolBar();
        clienteSelector = new javax.swing.JToggleButton();
        autoSelector = new javax.swing.JToggleButton();
        bitacoraSelector = new javax.swing.JToggleButton();
        presupuestoSelector = new javax.swing.JToggleButton();
        inventarioSelector = new javax.swing.JToggleButton();
        cobranzaSelector = new javax.swing.JToggleButton();
        datos = new javax.swing.JPanel();
        clientePanel = new javax.swing.JPanel();
        autoPanel = new javax.swing.JPanel();
        bitacoraPanel = new javax.swing.JPanel();
        presupuestoPanel = new javax.swing.JPanel();
        inventarioPanel = new javax.swing.JPanel();
        cobranzaPanel = new javax.swing.JPanel();
        emptyPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(51, 51, 51));
        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        nuevoServicio.setBackground(new java.awt.Color(51, 51, 51));
        nuevoServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        nuevoServicio.setForeground(new java.awt.Color(255, 255, 255));
        nuevoServicio.setText("Nuevo");
        nuevoServicio.setFocusable(false);
        nuevoServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevoServicio);

        buscarServicio.setBackground(new java.awt.Color(51, 51, 51));
        buscarServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        buscarServicio.setForeground(new java.awt.Color(255, 255, 255));
        buscarServicio.setText("Buscar");
        buscarServicio.setFocusable(false);
        buscarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(buscarServicio);

        guardarServicio.setBackground(new java.awt.Color(51, 51, 51));
        guardarServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        guardarServicio.setForeground(new java.awt.Color(255, 255, 255));
        guardarServicio.setText("Guardar");
        guardarServicio.setFocusable(false);
        guardarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(guardarServicio);
        jToolBar1.add(jSeparator1);

        programacion.setBackground(new java.awt.Color(51, 51, 51));
        programacion.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        programacion.setForeground(new java.awt.Color(255, 255, 255));
        programacion.setText("formato de programación");
        programacion.setFocusable(false);
        programacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        programacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        programacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programacionActionPerformed(evt);
            }
        });
        jToolBar1.add(programacion);
        jToolBar1.add(jSeparator2);

        ordenServicio.setBackground(new java.awt.Color(51, 51, 51));
        ordenServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ordenServicio.setForeground(new java.awt.Color(255, 255, 255));
        ordenServicio.setText("Orden de servicio");
        ordenServicio.setFocusable(false);
        ordenServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ordenServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ordenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(ordenServicio);
        jToolBar1.add(jSeparator3);

        reporteGlobal.setBackground(new java.awt.Color(51, 51, 51));
        reporteGlobal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        reporteGlobal.setForeground(new java.awt.Color(255, 255, 255));
        reporteGlobal.setText("Reporte Global");
        reporteGlobal.setFocusable(false);
        reporteGlobal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reporteGlobal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reporteGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteGlobalActionPerformed(evt);
            }
        });
        jToolBar1.add(reporteGlobal);
        jToolBar1.add(filler1);

        mensaje.setText(" ");
        jToolBar1.add(mensaje);

        datosGenerales.setBackground(new java.awt.Color(51, 51, 51));
        datosGenerales.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Servicio");

        numeroServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroServicio.setForeground(new java.awt.Color(255, 255, 255));
        numeroServicio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        numeroServicio.setText(" ");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre o razón social");

        nombreCliente.setEditable(false);
        nombreCliente.setBackground(new java.awt.Color(102, 102, 102));
        nombreCliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        nombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        nombreCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Placas");

        placas.setEditable(false);
        placas.setBackground(new java.awt.Color(102, 102, 102));
        placas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        placas.setForeground(new java.awt.Color(255, 255, 255));
        placas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Inicio del servicio");

        inicioServicio.setEditable(false);
        inicioServicio.setBackground(new java.awt.Color(102, 102, 102));
        inicioServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        inicioServicio.setForeground(new java.awt.Color(255, 255, 255));
        inicioServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fin del servicio");

        finServicio.setEditable(false);
        finServicio.setBackground(new java.awt.Color(102, 102, 102));
        finServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        finServicio.setForeground(new java.awt.Color(255, 255, 255));
        finServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Duración del servicio");

        duracionServicio.setEditable(false);
        duracionServicio.setBackground(new java.awt.Color(102, 102, 102));
        duracionServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        duracionServicio.setForeground(new java.awt.Color(255, 255, 255));
        duracionServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de ingreso del auto");

        ingreso.setEditable(false);
        ingreso.setBackground(new java.awt.Color(102, 102, 102));
        ingreso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ingreso.setForeground(new java.awt.Color(255, 255, 255));
        ingreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de salida del auto");

        salida.setEditable(false);
        salida.setBackground(new java.awt.Color(102, 102, 102));
        salida.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        salida.setForeground(new java.awt.Color(255, 255, 255));
        salida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Estadia del auto");

        tiempo.setEditable(false);
        tiempo.setBackground(new java.awt.Color(102, 102, 102));
        tiempo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Saldo");

        saldo.setEditable(false);
        saldo.setBackground(new java.awt.Color(102, 102, 102));
        saldo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        saldo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        saldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 4));

        javax.swing.GroupLayout datosGeneralesLayout = new javax.swing.GroupLayout(datosGenerales);
        datosGenerales.setLayout(datosGeneralesLayout);
        datosGeneralesLayout.setHorizontalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCliente)
                    .addComponent(placas)
                    .addComponent(inicioServicio)
                    .addComponent(finServicio)
                    .addComponent(duracionServicio)
                    .addComponent(ingreso)
                    .addComponent(salida)
                    .addComponent(tiempo)
                    .addComponent(saldo)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, datosGeneralesLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(numeroServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        datosGeneralesLayout.setVerticalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeroServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(duracionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salida, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        selectorTool.setBackground(new java.awt.Color(153, 153, 153));
        selectorTool.setFloatable(false);
        selectorTool.setRollover(true);

        selector.add(clienteSelector);
        clienteSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        selector.add(autoSelector);
        autoSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        selector.add(bitacoraSelector);
        bitacoraSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        selector.add(presupuestoSelector);
        presupuestoSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        selector.add(inventarioSelector);
        inventarioSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        selector.add(cobranzaSelector);
        cobranzaSelector.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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
            .addGap(0, 490, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );

        datos.add(emptyPanel, "empty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(datosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectorTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(selectorTool, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(datosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoServicioActionPerformed
        try {
            if (this.servicioMetaData.isEditado()) {
                int n = javax.swing.JOptionPane.showConfirmDialog(
                        mainFrame,
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
            dialogFactory.createDialog(mainFrame, true).setVisible(true);
        } catch (IllegalArgumentException e) {
            //no lo guardo? como que hace falta una excepcion mas especifica... luego con mas calma
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_nuevoServicioActionPerformed

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

    private void buscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarServicioActionPerformed
        try {
            if (this.servicioMetaData.isEditado()) {
                int n = javax.swing.JOptionPane.showConfirmDialog(
                        mainFrame,
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
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            if (!busquedaDialog.isIniciado()) {
                busquedaDialog.inicializa();
            }
            busquedaDialog.validate();
            busquedaDialog.pack();
            busquedaDialog.setLocationRelativeTo(mainFrame);
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            busquedaDialog.setVisible(true);
        } catch (IllegalArgumentException e) {
            //por que se tragaria excepciones de este tipo sin que sean los que arroja al no poder guardar
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_buscarServicioActionPerformed

    private void programacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programacionActionPerformed
        dialogFactoryWizardProgramacion.createDialog(mainFrame, true).setVisible(true);
    }//GEN-LAST:event_programacionActionPerformed

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

    private void reporteGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteGlobalActionPerformed
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
            ServicioView.LOGGER.error("Exploto al tratar de generar el reporte global", ex);
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_reporteGlobalActionPerformed

    private void clienteSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "cliente");
    }//GEN-LAST:event_clienteSelectorActionPerformed

    private void autoSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "auto");
    }//GEN-LAST:event_autoSelectorActionPerformed

    private void bitacoraSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitacoraSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "bitacora");
    }//GEN-LAST:event_bitacoraSelectorActionPerformed

    private void presupuestoSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presupuestoSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "presupuesto");
    }//GEN-LAST:event_presupuestoSelectorActionPerformed

    private void inventarioSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "inventario");
    }//GEN-LAST:event_inventarioSelectorActionPerformed

    private void cobranzaSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobranzaSelectorActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(datos.getLayout());
        cardLayout.show(datos, "cobranza");
    }//GEN-LAST:event_cobranzaSelectorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel autoPanel;
    private javax.swing.JToggleButton autoSelector;
    private javax.swing.JPanel bitacoraPanel;
    private javax.swing.JToggleButton bitacoraSelector;
    private javax.swing.JButton buscarServicio;
    private javax.swing.JPanel clientePanel;
    private javax.swing.JToggleButton clienteSelector;
    private javax.swing.JPanel cobranzaPanel;
    private javax.swing.JToggleButton cobranzaSelector;
    private javax.swing.JPanel datos;
    private javax.swing.JPanel datosGenerales;
    private javax.swing.JTextField duracionServicio;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JTextField finServicio;
    private javax.swing.JButton guardarServicio;
    private javax.swing.JTextField ingreso;
    private javax.swing.JTextField inicioServicio;
    private javax.swing.JPanel inventarioPanel;
    private javax.swing.JToggleButton inventarioSelector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JButton nuevoServicio;
    private javax.swing.JLabel numeroServicio;
    private javax.swing.JButton ordenServicio;
    private javax.swing.JTextField placas;
    private javax.swing.JPanel presupuestoPanel;
    private javax.swing.JToggleButton presupuestoSelector;
    private javax.swing.JButton programacion;
    private javax.swing.JButton reporteGlobal;
    private javax.swing.JTextField saldo;
    private javax.swing.JTextField salida;
    private javax.swing.ButtonGroup selector;
    private javax.swing.JToolBar selectorTool;
    private javax.swing.JTextField tiempo;
    // End of variables declaration//GEN-END:variables

}
