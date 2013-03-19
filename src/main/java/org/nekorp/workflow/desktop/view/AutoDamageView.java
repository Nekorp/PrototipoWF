/**
 *   Copyright 2013 Nekorp
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

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosInventarioDamageView;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.nekorp.workflow.desktop.view.resource.ShapeView;
import org.nekorp.workflow.desktop.view.resource.imp.DamageDetailGraphicsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AutoDamageView extends ApplicationView {

    @Autowired
    @Qualifier("captureDetailDialogFactory")
    private DialogFactory dialogFactory;
    @Autowired
    javax.swing.JFrame mainFrame;
    @Autowired
    private DamageDetailsVB damageCaptura;
    @Autowired
    private PermisosInventarioDamageView permisos;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    private List<DamageDetailsVB> modelo;
    private List<DamageDetailGraphicsView> damageDetail;
    private ShapeView currentView;
    private int boundsX;
    private int boundsY;
    private boolean editable;
    /**
     * Creates new form AutoDamageView
     */
    public AutoDamageView() {
        super();
        modelo = new LinkedList<>();
        damageDetail = new LinkedList<>();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        setBindings();
    }
    
    private void setBindings() {
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "puedeEditar", permisosBind);
    }

    @Override
    public void setEditableStatus(boolean value) {
        editable = value;
    }

    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
    }
    
    public void changeView(ShapeView view) {
        if (currentView != null) {
            content.remove(currentView);
        }
        currentView = view;
        resizeCurrentView();
        content.add(currentView, 0, 0);
        //content.repaint();
    }
    
    public void setModelo(List<DamageDetailsVB> model) {
        this.modelo = model;
        for (DamageDetailGraphicsView x: damageDetail) {
            content.remove(x);
        }
        damageDetail = new LinkedList<>();
        for (DamageDetailsVB x: modelo) {
            DamageDetailGraphicsView obj = new DamageDetailGraphicsView();
            obj.setPosicion(new Point(x.getX(), x.getY()));
            obj.setContexto(new Point(boundsX, boundsY));
            obj.setBounds(0, 0, content.getWidth(), content.getHeight());
            //obj.setOrientacion(DamageDetailGraphicsView.InferiorIzquierda);
            if (x.getX() <= currentView.getShapeWidth() / 2) {
                if (x.getY() <= currentView.getShapeHeight() / 2) {
                    obj.setOrientacion(DamageDetailGraphicsView.SuperiorIzquierda);
                } else {
                    obj.setOrientacion(DamageDetailGraphicsView.InferiorIzquierda);
                }
            } else {
                if (x.getY() <= currentView.getShapeHeight() / 2) {
                    obj.setOrientacion(DamageDetailGraphicsView.SuperiorDerecha);
                } else {
                    obj.setOrientacion(DamageDetailGraphicsView.InferiorDerecha);
                }
            }
            obj.setTexto(x.toString());
            damageDetail.add(obj);
            content.add(obj,1,0);
        }
        content.repaint();
    }
    
    public List<DamageDetailsVB> getModelo() {
        return this.modelo;
    }
    
    private void resizeCurrentView() {
        boundsX = (content.getWidth() - currentView.getShapeWidth()) / 2;
        //boundsX = 60;
        boundsY = (content.getHeight() - currentView.getShapeHeight()) / 2;
        //boundsY = 50;
        currentView.setBounds(boundsX, boundsY, currentView.getShapeWidth(), currentView.getShapeHeight());
        for (DamageDetailGraphicsView x: damageDetail) {
            x.setBounds(0, 0, content.getWidth(), content.getHeight());
            x.setContexto(new Point(boundsX, boundsY));
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

        content = new javax.swing.JLayeredPane();

        content.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contentMouseClicked(evt);
            }
        });
        content.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                contentComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void contentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentMouseClicked
        if (currentView.shapeContains(evt.getX() - boundsX, evt.getY() - boundsY) && editable) {
            damageCaptura.setX(evt.getX() - boundsX);
            damageCaptura.setY(evt.getY() - boundsY);
            dialogFactory.createDialog(mainFrame, true).setVisible(true);
        }
    }//GEN-LAST:event_contentMouseClicked

    private void contentComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_contentComponentResized
        if (currentView != null) {
            resizeCurrentView();
        }
    }//GEN-LAST:event_contentComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane content;
    // End of variables declaration//GEN-END:variables

}
