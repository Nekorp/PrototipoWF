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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;
import org.nekorp.workflow.desktop.servicio.RegistroCostoFactory;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.servicio.monitor.MonitorCatalog;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.costo.CostoClipBoard;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosCostoView;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralRegistroCosto;
import org.nekorp.workflow.desktop.view.resource.costo.CostoServicioTableModel;
import org.nekorp.workflow.desktop.view.resource.costo.CustomJTableCostos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author Nekorp
 */
@Scope("prototype")
@Component("grupoCostoView")
public class GrupoCostoServicioView extends AbstractCostoView {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(GrupoCostoServicioView.class);
    private GrupoCostoVB grupo;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private PermisosCostoView permisos;
    @Autowired
    private CostoServicioTableModel tableModel;
    @Autowired
    private ValidacionGeneralRegistroCosto validacionGeneralRegistroCosto;
    @Autowired
    private CostoClipBoard clipboard;
    @Autowired
    RegistroCostoFactory registroCostoFactory;
    @Autowired
    private EditorMonitorManager editorManager;
    @Autowired
    private GrupoCostoServicioViewListener elListener;
    private Bindable permisosBind;
    private Bindable grupoViewUpdate;
    @Override
    public void iniciaVista() {
        initComponents();
        this.subirGrupo.setText("\u25B2");
        this.bajarGrupo.setText("\u25BC");
        //this.subirGrupo.setText("\u25B4");
        //this.bajarGrupo.setText("\u25BE");
        tableModel.setGrupo(grupo);
        ((CustomJTableCostos)tablaCostos)
            .setValidacionGeneralRegistroCosto(validacionGeneralRegistroCosto);
        tablaCostos.setModel(tableModel);
        this.jPanel1.add(tablaCostos.getTableHeader(), java.awt.BorderLayout.PAGE_START);
        setDefaultColumnSize();
        //this.validate();
        setBindings();
        //TODO requieren de mas trabajo
        setShorcuts();
    }
    
    @Override
    public void activeMonitor() {
        editorManager.selectMonitor(MonitorCatalog.PRESUPUESTO);
        this.tablaCostos.requestFocus();
    }

    @Override
    public void setEditableStatus(boolean value) {
        this.grupoEdit.setEnabled(value);
        this.agregarHP.setEnabled(value);
        this.agregarM.setEnabled(value);
        this.otrosGastos.setEnabled(value);
        this.borrar.setEnabled(value);
        this.tableModel.setEditable(value);
        this.subirGrupo.setEnabled(value);
        this.bajarGrupo.setEnabled(value);
    }
    
    private void setBindings() {
        bindingManager.registerBind(grupo, "grupo", (Bindable)grupoEdit);
        bindingManager.registerBind(grupo, "grupo", (Bindable)subTotalSuffix);
        grupoViewUpdate = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                validate();
            }
        };
        bindingManager.registerBind(grupo, "grupo", grupoViewUpdate);
        bindingManager.registerBind(viewServicioModel, "costos", tableModel);
        bindingManager.registerBind(grupo.getMetadata(), "subtotal", (Bindable)this.subtotal);
        permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "puedeEditarCostos", permisosBind);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarHP);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarM);
        bindingManager.registerBind(validacionGeneralRegistroCosto, "valido", (Bindable)otrosGastos);
    }
    
    private void removeBindings() {
        this.bindingManager.removeBind(grupo, "grupo", (Bindable)grupoEdit);
        this.bindingManager.removeBind(grupo, "grupo", (Bindable)subTotalSuffix);
        this.bindingManager.removeBind(grupo, "grupo", grupoViewUpdate);
        this.bindingManager.removeBind(viewServicioModel, "costos", tableModel);
        this.bindingManager.removeBind(grupo.getMetadata(), "subtotal", (Bindable)this.subtotal);
        this.bindingManager.removeBind(permisos, "puedeEditarCostos", permisosBind);
        this.bindingManager.removeBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarHP);
        this.bindingManager.removeBind(validacionGeneralRegistroCosto, "valido", (Bindable)agregarM);
        this.bindingManager.removeBind(validacionGeneralRegistroCosto, "valido", (Bindable)otrosGastos);
    }
    
    private void setShorcuts() {
        InputMap im = tablaCostos.getInputMap(JTable.WHEN_FOCUSED);
        ActionMap am = tablaCostos.getActionMap();
        /*Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tablaCostos.isEditing()) {
                    borrarActionPerformed(e);
                }
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Delete");
        am.put("Delete", deleteAction);
        Action addAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //borrarActionPerformed(e);
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "Add");
        am.put("Add", addAction);*/
        
        Action copyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tablaCostos.isEditing()) {
                    int[] selected = tablaCostos.getSelectedRows();
                    List<RegistroCostoVB> registros = new LinkedList<>();
                    for (int x: selected) {
                        registros.add(tableModel.getDatos().get(x));
                    }
                    clipboard.setCopyData(registros);
                }
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "Copy");
        am.put("Copy", copyAction);
        
        Action pasteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tablaCostos.isEditing()) {
                    List<RegistroCostoVB> copiados = clipboard.getCopyData();
                    if (copiados != null && copiados.size() > 0){
                        editorManager.getCurrentMonitor().setEncendido(false);
                        List<RegistroCostoVB> actuales = viewServicioModel.getCostos();
                        for (RegistroCostoVB x: copiados) {
                            RegistroCostoVB nuevo = registroCostoFactory.copyRegistroCosto(x);
                            nuevo.setGrupo(grupo);
                            actuales.add(nuevo);
                        }
                        editorManager.getCurrentMonitor().setEncendido(true);
                        viewServicioModel.setCostos(actuales);
                    }
                }
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "Paste");
        am.put("Paste", pasteAction);
    }
    
    private void setDefaultColumnSize() {
        int[] sizeColumns = new int[] {
            80,//tipo
            180,//concepto
            70,//cantidad
            70,//precio unitario
            70,//utilidad
            70,//precio cliente
            70//subtotal
        };
        TableColumn column;
        for (int i = 0; i < this.tablaCostos.getColumnCount() && i < sizeColumns.length; i++) {
            column = this.tablaCostos.getColumnModel().getColumn(i);
            column.setPreferredWidth(sizeColumns[i]);
        }
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    @Override
    public void setModel(GrupoCostoVB ev) {
        this.grupo = ev;
    }

    @Override
    public GrupoCostoVB getModel() {
       return this.grupo;
    }
    
    @Override
    public void disposeView() {
        this.removeBindings();
    }
    
    @Override
    public void requestFocusOnMainInput() {
        //nada
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoLabel = new javax.swing.JLabel();
        grupoEdit = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        jToolBar1 = new javax.swing.JToolBar();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jToolBar2 = new javax.swing.JToolBar();
        subirGrupo = new javax.swing.JButton();
        bajarGrupo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        agregarHP = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        agregarM = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        otrosGastos = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        borrarGrupo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tablaCostos = new org.nekorp.workflow.desktop.view.resource.costo.CustomJTableCostos();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subTotalSuffix = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        subtotal = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();

        setBackground(new java.awt.Color(204, 204, 204));

        grupoLabel.setText("Grupo: ");

        grupoEdit.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        grupoEdit.setText("jTextField1");
        grupoEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToolBar1MouseClicked(evt);
            }
        });
        jToolBar1.add(filler2);

        jToolBar2.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar2.setBorder(null);
        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar2.setRollover(true);

        subirGrupo.setBackground(new java.awt.Color(204, 204, 204));
        subirGrupo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        subirGrupo.setText("+");
        subirGrupo.setToolTipText("Subir Grupo");
        subirGrupo.setBorder(null);
        subirGrupo.setBorderPainted(false);
        subirGrupo.setFocusable(false);
        subirGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        subirGrupo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        subirGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirGrupoActionPerformed(evt);
            }
        });
        jToolBar2.add(subirGrupo);

        bajarGrupo.setBackground(new java.awt.Color(204, 204, 204));
        bajarGrupo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        bajarGrupo.setText("-");
        bajarGrupo.setToolTipText("Bajar Grupo");
        bajarGrupo.setBorder(null);
        bajarGrupo.setBorderPainted(false);
        bajarGrupo.setFocusable(false);
        bajarGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bajarGrupo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bajarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajarGrupoActionPerformed(evt);
            }
        });
        jToolBar2.add(bajarGrupo);

        jToolBar1.add(jToolBar2);
        jToolBar1.add(jSeparator4);

        agregarHP.setBackground(new java.awt.Color(204, 204, 204));
        agregarHP.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        agregarHP.setText("Hojalateria y Pintura");
        agregarHP.setFocusable(false);
        agregarHP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarHP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarHPActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarHP);
        jToolBar1.add(jSeparator1);

        agregarM.setBackground(new java.awt.Color(204, 204, 204));
        agregarM.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        agregarM.setText("Mecanica");
        agregarM.setFocusable(false);
        agregarM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarMActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarM);
        jToolBar1.add(jSeparator3);

        otrosGastos.setBackground(new java.awt.Color(204, 204, 204));
        otrosGastos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        otrosGastos.setText("Otros Gastos");
        otrosGastos.setFocusable(false);
        otrosGastos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        otrosGastos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        otrosGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otrosGastosActionPerformed(evt);
            }
        });
        jToolBar1.add(otrosGastos);
        jToolBar1.add(filler1);

        borrarGrupo.setBackground(new java.awt.Color(204, 204, 204));
        borrarGrupo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        borrarGrupo.setText("Borrar Grupo");
        borrarGrupo.setFocusable(false);
        borrarGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrarGrupo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarGrupoActionPerformed(evt);
            }
        });
        jToolBar1.add(borrarGrupo);
        jToolBar1.add(jSeparator2);

        borrar.setBackground(new java.awt.Color(204, 204, 204));
        borrar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        borrar.setText("Borrar Registro");
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jToolBar1.add(borrar);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new java.awt.BorderLayout());

        tablaCostos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tablaCostos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCostos.setGridColor(new java.awt.Color(255, 255, 255));
        tablaCostos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaCostos.setRowHeight(18);
        tablaCostos.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tablaCostos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablaCostos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaCostos.getTableHeader().setReorderingAllowed(false);
        jPanel1.add(tablaCostos, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Subtotal");

        subTotalSuffix.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        subTotalSuffix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        subtotal.setEditable(false);
        subtotal.setBackground(new java.awt.Color(255, 255, 255));
        subtotal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        subtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subTotalSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(subTotalSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grupoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grupoLabel)
                            .addComponent(grupoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarHPActionPerformed
        this.tableModel.addRegistro("Hojalateria y Pintura");
    }//GEN-LAST:event_agregarHPActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        int[] selected = this.tablaCostos.getSelectedRows();
        List<RegistroCostoVB> registros = new LinkedList<>();
        for (int x: selected) {
            registros.add(tableModel.getDatos().get(x));
        }
        for(RegistroCostoVB x: registros) {
            this.tableModel.deleteRegistro(tableModel.getDatos().indexOf(x));
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void agregarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMActionPerformed
        this.tableModel.addRegistro("Mecanica");
    }//GEN-LAST:event_agregarMActionPerformed

    private void otrosGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otrosGastosActionPerformed
        this.tableModel.addRegistro("Otros Gastos");
    }//GEN-LAST:event_otrosGastosActionPerformed

    private void borrarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarGrupoActionPerformed
        int n = javax.swing.JOptionPane.showConfirmDialog(
                mainFrame,
                "¿Borrar Grupo?",
                "Confirmación",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (n == javax.swing.JOptionPane.YES_OPTION) {
            this.editorManager.getMonitor(MonitorCatalog.PRESUPUESTO).setEncendido(false);
            this.elListener.deleteGrupo(this.grupo);
            this.tableModel.deleteAll();
            this.editorManager.getMonitor(MonitorCatalog.PRESUPUESTO).setEncendido(true);
        }
    }//GEN-LAST:event_borrarGrupoActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        this.tablaCostos.requestFocus();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jToolBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToolBar1MouseClicked
        this.tablaCostos.requestFocus();
    }//GEN-LAST:event_jToolBar1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        this.tablaCostos.requestFocus();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void subirGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirGrupoActionPerformed
        this.elListener.subirGrupo(grupo);
    }//GEN-LAST:event_subirGrupoActionPerformed

    private void bajarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajarGrupoActionPerformed
        this.elListener.bajarGrupo(grupo);
    }//GEN-LAST:event_bajarGrupoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarHP;
    private javax.swing.JButton agregarM;
    private javax.swing.JButton bajarGrupo;
    private javax.swing.JButton borrar;
    private javax.swing.JButton borrarGrupo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField grupoEdit;
    private javax.swing.JLabel grupoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton otrosGastos;
    private javax.swing.JTextField subTotalSuffix;
    private javax.swing.JButton subirGrupo;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tablaCostos;
    // End of variables declaration//GEN-END:variables

}
