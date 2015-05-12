/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.servicio.monitor.MonitorCatalog;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.resource.ShapeView;
import org.nekorp.workflow.desktop.view.resource.damage.DetailDamageCaptureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 */
@Component("inventarioDamageView")
public class InventarioDamageView extends MonitoredApplicationView implements DetailDamageCaptureListener, Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(InventarioDamageView.class);
    private LinkedList<Object> ignore;
    private Object target;
    private String property;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private DamageDetailsVB damageCaptura;
    @Autowired
    @Qualifier("autoCuatroRightView")
    private ShapeView autoRightView;
    @Autowired
    @Qualifier("autoCuatroLeftView")
    private ShapeView autoLeftView;
    @Autowired
    @Qualifier("autoCuatroFrontView")
    private ShapeView autoFrontView;
    @Autowired
    @Qualifier("autoCuatroRearView")
    private ShapeView autoRearView;
    @Autowired
    private AutoDamageView damageView;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    private String lastSide;
    @Autowired
    private EditorMonitorManager monitorManager;
    
    /**
     * Creates new form InventarioDamage
     */
    public InventarioDamageView() {
        ignore = new LinkedList();
        
    }
    
    @Override
    public void activeMonitor() {
        this.selectMonitor(lastSide);
    }
    
    public void selectMonitor(String side) {
        if (StringUtils.equals(side, "derecha")) {
            monitorManager.selectMonitor(MonitorCatalog.INVENTARIO_DERECHA);
        }
        if (StringUtils.equals(side, "izquierda")) {
            monitorManager.selectMonitor(MonitorCatalog.INVENTARIO_IZQUIERDA);
        }
        if (StringUtils.equals(side, "frontal")) {
            monitorManager.selectMonitor(MonitorCatalog.INVENTARIO_FRONTAL);
        }
        if (StringUtils.equals(side, "trasera")) {
            monitorManager.selectMonitor(MonitorCatalog.INVENTARIO_TRASERA);
        }
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        damageView.iniciaVista();
        this.content.add(damageView);
        this.bindingManager.registerBind(servicioMetaData, "servicioActual", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                ServicioLoaded servicio = (ServicioLoaded) value;
                if (servicio != null) {
                    setBindings(servicio.getPreferenciasEdicion().getCurrentDamageTab());
                }
            }
        });
    }

    @Override
    public void setEditableStatus(boolean value) {
        //aun nada
    }

    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    public void setBindings(String side) {
        lastSide = side;
        bindingManager.clearBindings(this);
        derecha.setSelected(false);
        izquierda.setSelected(false);
        frontal.setSelected(false);
        trasera.setSelected(false);
        if (StringUtils.equals(side, "derecha")) {
            damageView.changeView(autoRightView);
            derecha.setSelected(true);
            bindingManager.registerBind(servicioVB.getDatosAuto().getDamage(), side, this);
        }
        if (StringUtils.equals(side, "izquierda")) {
            damageView.changeView(autoLeftView);
            izquierda.setSelected(true);
            bindingManager.registerBind(servicioVB.getDatosAuto().getDamage(), side, this);
        }
        if (StringUtils.equals(side, "frontal")) {
            damageView.changeView(autoFrontView);
            frontal.setSelected(true);
            bindingManager.registerBind(servicioVB.getDatosAuto().getDamage(), side, this);
        }
        if (StringUtils.equals(side, "trasera")) {
            damageView.changeView(autoRearView);
            trasera.setSelected(true);
            bindingManager.registerBind(servicioVB.getDatosAuto().getDamage(), side, this);
        }
        selectMonitor(side);
        servicioMetaData.getServicioActual().getPreferenciasEdicion().setCurrentDamageTab(side);
    }

    @Override
    public void agregar() {
        try {
            List<DamageDetailsVB> value = damageView.getModelo();
            DamageDetailsVB nuevo = new DamageDetailsVB();
            BeanUtils.copyProperties(nuevo, damageCaptura);
            value.add(nuevo);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            InventarioDamageView.LOGGER.error(ex);
        }
    }
    
    @Override
    public void borrar(DamageDetailsVB borrado) {
        try {
            List<DamageDetailsVB> value = damageView.getModelo();
            value.remove(borrado);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            InventarioDamageView.LOGGER.error(ex);
        }
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            if (StringUtils.equals(lastSide, property)) {
                damageView.setModelo((List<DamageDetailsVB>) value);
            }
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return damageView.getModelo();
    }

    @Override
    public void bindListener(Object target, String property) {
        this.target = target;
        this.property = property;
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
        izquierda = new javax.swing.JToggleButton();
        derecha = new javax.swing.JToggleButton();
        frontal = new javax.swing.JToggleButton();
        trasera = new javax.swing.JToggleButton();
        content = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        izquierda.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        izquierda.setText("Izquierda");
        izquierda.setFocusable(false);
        izquierda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        izquierda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        izquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izquierdaActionPerformed(evt);
            }
        });
        jToolBar1.add(izquierda);

        derecha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        derecha.setText("Derecha");
        derecha.setFocusable(false);
        derecha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        derecha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        derecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derechaActionPerformed(evt);
            }
        });
        jToolBar1.add(derecha);

        frontal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        frontal.setText("Frente");
        frontal.setFocusable(false);
        frontal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        frontal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        frontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontalActionPerformed(evt);
            }
        });
        jToolBar1.add(frontal);

        trasera.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        trasera.setText("Atrás");
        trasera.setFocusable(false);
        trasera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        trasera.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        trasera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traseraActionPerformed(evt);
            }
        });
        jToolBar1.add(trasera);

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void izquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izquierdaActionPerformed
        this.setBindings("izquierda");
    }//GEN-LAST:event_izquierdaActionPerformed

    private void derechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derechaActionPerformed
        this.setBindings("derecha");
    }//GEN-LAST:event_derechaActionPerformed

    private void frontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frontalActionPerformed
        this.setBindings("frontal");
    }//GEN-LAST:event_frontalActionPerformed

    private void traseraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traseraActionPerformed
        this.setBindings("trasera");
    }//GEN-LAST:event_traseraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JToggleButton derecha;
    private javax.swing.JToggleButton frontal;
    private javax.swing.JToggleButton izquierda;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToggleButton trasera;
    // End of variables declaration//GEN-END:variables

}
