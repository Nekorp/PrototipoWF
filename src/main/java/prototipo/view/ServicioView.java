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
package prototipo.view;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.EdicionServicioMetadata;
import prototipo.view.model.ServicioVB;
import prototipo.view.model.bitacora.BitacoraMetaData;
import prototipo.view.model.cliente.ClienteVB;
import prototipo.view.service.DialogFactory;

/**
 *
 * 
 */
@Component("servicioView")
public class ServicioView extends ApplicationView {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ServicioView.class);
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="bitacoraView")
    private ApplicationView bitacora;
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
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ServicioVB viewServicioModel;
    @Autowired
    private ClienteVB viewClienteModel;
    @Autowired
    private BitacoraMetaData bitacoraMetaData;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    @Autowired
    @Qualifier(value="wizardDialogFactory")
    private DialogFactory dialogFactory;
    //@Autowired
    //private EditorMonitor editorMonitor;
    
    //private boolean tabInited;
    private javax.swing.JTabbedPane tabDatos;
    
    
    @Override
    public void setEditableStatus(boolean value) {
        this.descripcion.setEnabled(value);
        if (value) {
            tabDatos.add("Cliente", (java.awt.Component)datosCliente);
            //agrega un tab para los datos del auto
            tabDatos.add("Auto",(java.awt.Component)datosAuto);
            //agrega tab con la bitacora
            tabDatos.add("Bitacora", (java.awt.Component)bitacora);
            //agrega tab con costos
            tabDatos.add("Costos", (java.awt.Component)costos);
            this.datos.add(this.tabDatos);
            //this.tabInited = true;
            
        } else {
            this.datos.removeAll();
        }
        this.updateUI();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bitacora.iniciaVista();
        datosCliente.iniciaVista();
        datosAuto.iniciaVista();
        costos.iniciaVista();
        tabDatos = new javax.swing.JTabbedPane();
        bindComponents();
    }
    
    public void bindComponents() {
        //bindings con el servicio
        bindingManager.registerBind(viewServicioModel, "id",(Bindable)this.numeroServicio);
        bindingManager.registerBind(viewServicioModel.getDatosAuto(), "placas",(Bindable)this.placas);
        bindingManager.registerBind(viewServicioModel, "descripcion", (Bindable)this.descripcion);
        bindingManager.registerBind(viewServicioModel.getBitacora(), "eventos", (Bindable)this.bitacora);
        //bindings con el cliente
        bindingManager.registerBind(viewClienteModel, "nombre",(Bindable)this.nombreCliente);
        //bindings con el metadata de la bitacora
        bindingManager.registerBind(bitacoraMetaData, "fechaEntrada", (Bindable)this.ingreso);
        bindingManager.registerBind(bitacoraMetaData, "fechaSalidaAuto", (Bindable)this.salida);
        bindingManager.registerBind(bitacoraMetaData, "tiempoEstadia", (Bindable)this.tiempo);
        //bindings con el metadata del servicio
        bindingManager.registerBind(servicioMetaData, "editado", (Bindable)this.guardarServicio);
        //bindingManager.registerBind(servicioMetaData, "tieneUndo", (Bindable)this.deshacer);
        //bindingManager.registerBind(servicioMetaData, "tieneRedo", (Bindable)this.rehacer);
        bindingManager.registerBind(servicioMetaData, "servicioCargado", (Bindable)this.generaReporte);
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
        nuevoServicio = new javax.swing.JButton();
        buscarServicio = new javax.swing.JButton();
        guardarServicio = new prototipo.view.binding.CustomEnabledBindingJButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        generaReporte = new prototipo.view.binding.CustomEnabledBindingJButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        mensaje = new javax.swing.JLabel();
        datosGenerales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tiempo = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombreCliente = new prototipo.view.binding.SimpleBindableJTextField();
        placas = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new prototipo.view.binding.SimpleBindableJTextArea();
        numeroServicio = new prototipo.view.binding.SimpleBindableJLabel();
        ingreso = new prototipo.view.binding.SimpleBindableJTextField();
        salida = new prototipo.view.binding.SimpleBindableJTextField();
        datos = new javax.swing.JPanel();

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

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

        generaReporte.setText("Generar reporte");
        generaReporte.setFocusable(false);
        generaReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generaReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generaReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaReporteActionPerformed(evt);
            }
        });
        jToolBar1.add(generaReporte);
        jToolBar1.add(filler1);

        mensaje.setText(" ");
        jToolBar1.add(mensaje);

        datosGenerales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Servicio"));
        datosGenerales.setName(""); // NOI18N

        jLabel2.setText("Número:");

        jLabel1.setText("Nombre o razón social:");

        jLabel3.setText("Placas:");

        jLabel5.setText("Fecha Ingreso:");

        tiempo.setEditable(false);

        jLabel6.setText("Tiempo:");

        jLabel7.setText("Fecha Salida:");

        nombreCliente.setEditable(false);

        placas.setEditable(false);

        jLabel10.setText("Descripción del servicio:");

        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        numeroServicio.setText(" ");

        ingreso.setEditable(false);

        salida.setEditable(false);

        javax.swing.GroupLayout datosGeneralesLayout = new javax.swing.GroupLayout(datosGenerales);
        datosGenerales.setLayout(datosGeneralesLayout);
        datosGeneralesLayout.setHorizontalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCliente)
                    .addComponent(tiempo)
                    .addComponent(placas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ingreso)
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addComponent(salida))
                .addContainerGap())
        );
        datosGeneralesLayout.setVerticalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeroServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        datos.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoServicioActionPerformed
        if (this.servicioMetaData.isEditado()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    mainFrame,
                    "¿Guardar Servicio?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.aplication.guardaServicio();
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        dialogFactory.createDialog(mainFrame, true).setVisible(true);
    }//GEN-LAST:event_nuevoServicioActionPerformed

    private void guardarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarServicioActionPerformed
        this.aplication.guardaServicio();
    }//GEN-LAST:event_guardarServicioActionPerformed

    private void buscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarServicioActionPerformed
        if (this.servicioMetaData.isEditado()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    mainFrame,
                    "¿Guardar Servicio?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.aplication.guardaServicio();
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        BusquedaServicioView dialog = new BusquedaServicioView(mainFrame, true, this.aplication);
        dialog.setVisible(true);
    }//GEN-LAST:event_buscarServicioActionPerformed

    private void generaReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaReporteActionPerformed
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
            ServicioView.LOGGER.error(ex);
        }
    }//GEN-LAST:event_generaReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarServicio;
    private javax.swing.JPanel datos;
    private javax.swing.JPanel datosGenerales;
    private javax.swing.JTextArea descripcion;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton generaReporte;
    private javax.swing.JButton guardarServicio;
    private javax.swing.JTextField ingreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JButton nuevoServicio;
    private javax.swing.JLabel numeroServicio;
    private javax.swing.JTextField placas;
    private javax.swing.JTextField salida;
    private javax.swing.JTextField tiempo;
    // End of variables declaration//GEN-END:variables

}
