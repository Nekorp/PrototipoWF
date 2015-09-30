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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.reporte.ParametrosReporte;
import org.nekorp.workflow.desktop.servicio.GrupoCostoFactory;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.servicio.monitor.MonitorCatalog;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.security.PermisosCostoView;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 *
 * @author Nekorp
 */
public abstract class CostoServicioView extends MonitoredApplicationView implements Bindable, GrupoCostoServicioViewListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CostoServicioView.class);
    private final LinkedList<Object> ignore;
    private final List<GrupoCostoVB> modelo;
    private Object target;
    private String property;
    @Autowired
    @Qualifier(value = "costoServicioSaveSeparatorView")
    private EventoExtraGuardar extraGuardar;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    @Autowired
    private PermisosCostoView permisos;
    @Autowired
    private GrupoCostoFactory grupoCostoFactory;
    @Autowired
    private EditorMonitorManager editorManager;
    
    public CostoServicioView() {
        this.ignore = new LinkedList<>();
        modelo = new ArrayList<>();
    }
    @Override
    public void iniciaVista() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        extraGuardar.iniciaVista();
        setBindings();
    }
    
    @Override
    public void activeMonitor() {
        editorManager.selectMonitor(MonitorCatalog.PRESUPUESTO);
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.agregarGrupo.setEnabled(value);
    }
    
    private void setBindings() {
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "puedeEditarCostos", permisosBind);
        bindingManager.registerBind(viewServicioModel, "gruposCosto", this);
        bindingManager.registerBind(costosMetadata, "subtotal", (Bindable)this.subtotal);
        bindingManager.registerBind(costosMetadata, "iva", (Bindable)this.iva);
        bindingManager.registerBind(costosMetadata, "totalServicio", (Bindable)this.total);
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            List<GrupoCostoVB> param = (List<GrupoCostoVB>) value;
            List<GrupoCostoVB> borrar = new LinkedList<>();
            for (int i = 0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if (!param.get(i).equals(this.modelo.get(i))) {
                        borrar.add(this.modelo.get(i));
                    }
                }
            }
            for (int i = param.size(); i < this.modelo.size(); i++) {
                borrar.add(this.modelo.get(i));
            }
            for (GrupoCostoVB x: borrar) {
                removeGrupo(x);
            }
            for (int i = 0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if (!param.get(i).equals(this.modelo.get(i))) {
                        this.modelo.add(i, param.get(i));
                        addGrupoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addGrupoView(this.modelo.get(i), i);
                }
            }
            this.updateUI();
        }
    }
    
    private void addGrupoView(GrupoCostoVB obj, int index) {
        GrupoCostoServicioView entrada = null;
        entrada = getGrupoCostoServicioView();
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            entrada.setEditableStatus(true);
            this.grupos.add(entrada, index);
            grupos.validate();
            entrada.requestFocusOnMainInput();
        }
    }
    
    private void removeGrupo(GrupoCostoVB obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        GrupoCostoServicioView view = (GrupoCostoServicioView) this.grupos.getComponent(index);
        view.disposeView();
        this.grupos.remove(view);
    }
    
    @Override
    public void deleteGrupo(GrupoCostoVB ev){
        ArrayList<GrupoCostoVB> value = new ArrayList<>();
        value.addAll(this.modelo);
        value.remove(ev);
        actualizaModelo(value);
    }

    @Override
    public void bajarGrupo(GrupoCostoVB ev) {
        ArrayList<GrupoCostoVB> value = new ArrayList<>();
        value.addAll(this.modelo);
        int index = value.indexOf(ev);
        GrupoCostoVB old = value.remove(index);
        try {
            value.add(index + 1, old);
        } catch (IndexOutOfBoundsException e) {
            value.add(old);
        }
        actualizaModelo(value);
    }

    @Override
    public void subirGrupo(GrupoCostoVB ev) {
        ArrayList<GrupoCostoVB> value = new ArrayList<>();
        value.addAll(this.modelo);
        int index = value.indexOf(ev);
        GrupoCostoVB old = value.remove(index);
        try {
            value.add(index - 1, old);
        } catch (IndexOutOfBoundsException e) {
            value.add(0, old);
        }
        actualizaModelo(value);
    }
    
    private void actualizaModelo(List<GrupoCostoVB> value) {
        try {
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            CostoServicioView.LOGGER.error("exploto", ex);
        }
    }
    
    public abstract GrupoCostoServicioView getGrupoCostoServicioView();
    
    @Override
    public void ignoreUpdate(Object value) {
         ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return this.modelo;
    }

    @Override
    public void bindListener(Object target, String property) {
        this.target = target;
        this.property = property;
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    private String getNuevoName(int suffix) {
        String r = "Grupo Nuevo";
        if (suffix != 0) {
            r = r + " " + suffix;
        }
        boolean repetido = false;
        for (GrupoCostoVB x: modelo) {
            if (StringUtils.equalsIgnoreCase(x.getGrupo(), r)) {
                repetido = true;
                break;
            }
        }
        if (repetido) {
            return getNuevoName(suffix + 1);
        }
        return r;
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
        agregarGrupo = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        generarReporte = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grupos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subtotal = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel3 = new javax.swing.JLabel();
        iva = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jLabel2 = new javax.swing.JLabel();
        total = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        agregarGrupo.setBackground(new java.awt.Color(153, 153, 153));
        agregarGrupo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        agregarGrupo.setForeground(new java.awt.Color(255, 255, 255));
        agregarGrupo.setText("Agregar Grupo");
        agregarGrupo.setFocusable(false);
        agregarGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarGrupo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGrupoActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarGrupo);
        jToolBar1.add(jSeparator1);

        generarReporte.setBackground(new java.awt.Color(153, 153, 153));
        generarReporte.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        generarReporte.setForeground(new java.awt.Color(255, 255, 255));
        generarReporte.setText("Generar reporte");
        generarReporte.setFocusable(false);
        generarReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generarReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarReporteActionPerformed(evt);
            }
        });
        jToolBar1.add(generarReporte);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        grupos.setBackground(new java.awt.Color(255, 255, 255));
        grupos.setLayout(new javax.swing.BoxLayout(grupos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(grupos);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Subtotal");

        subtotal.setEditable(false);
        subtotal.setBackground(new java.awt.Color(153, 153, 153));
        subtotal.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        subtotal.setForeground(new java.awt.Color(255, 255, 255));
        subtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        subtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Iva");

        iva.setEditable(false);
        iva.setBackground(new java.awt.Color(153, 153, 153));
        iva.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        iva.setForeground(new java.awt.Color(255, 255, 255));
        iva.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        iva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(" Total");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(153, 153, 153));
        total.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGrupoActionPerformed
        ArrayList<GrupoCostoVB> value = new ArrayList<>();
        for (GrupoCostoVB x: this.modelo) {
            value.add(x);
        }
        String grupo = getNuevoName(0);
        value.add(grupoCostoFactory.get(grupo));
        actualizaModelo(value);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            }
        });
    }//GEN-LAST:event_agregarGrupoActionPerformed

    private void generarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarReporteActionPerformed
        try {
            if (servicioMetaData.isEditado()) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                boolean guardado = this.aplication.guardaServicio();
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
                if (!guardado) {
                    return;
                }
            }
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Hojas de cálculo", "xlsx");
            chooser.setFileFilter(filter);
            String homePath = System.getProperty("user.home");
            File f = new File(new File(homePath+"/Reporte-Servicio-"+this.viewServicioModel.getId()+".xlsx").getCanonicalPath());
            chooser.setSelectedFile(f);  
            int returnVal = chooser.showSaveDialog(this.mainFrame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                ParametrosReporte param = new ParametrosReporte();
                param.setDestination(chooser.getSelectedFile());
                this.aplication.generaReporte(param);
            }
        } catch (IOException ex) {
            CostoServicioView.LOGGER.error(ex);
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_generarReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarGrupo;
    private javax.swing.JButton generarReporte;
    private javax.swing.JPanel grupos;
    private javax.swing.JTextField iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

}
