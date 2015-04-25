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
import org.nekorp.workflow.desktop.view.resource.imp.DetailDamageCaptureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 */
@Component
public class AutoDamageView extends ApplicationView {

    @Autowired
    @Qualifier("captureDetailDialogFactory")
    private DialogFactory dialogFactory;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    private DamageDetailsVB damageCaptura;
    @Autowired
    private PermisosInventarioDamageView permisos;
    @Autowired
    @Qualifier("inventarioDamageView")
    private DetailDamageCaptureListener listener;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    private List<DamageDetailsVB> modelo;
    private List<DamageDetailGraphicsView> damageDetail;
    private ShapeView currentView;
    private int boundsX;
    private int boundsY;
    private boolean editable;
    private DamageDetailGraphicsView currentEdited;
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
                    //obj.setOrientacion(DamageDetailGraphicsView.SuperiorDerecha);
                } else {
                    obj.setOrientacion(DamageDetailGraphicsView.InferiorIzquierda);
                    //obj.setOrientacion(DamageDetailGraphicsView.InferiorDerecha);
                }
            } else {
                if (x.getY() <= currentView.getShapeHeight() / 2) {
                    obj.setOrientacion(DamageDetailGraphicsView.SuperiorDerecha);
                    //obj.setOrientacion(DamageDetailGraphicsView.SuperiorIzquierda);
                } else {
                    obj.setOrientacion(DamageDetailGraphicsView.InferiorDerecha);
                    //obj.setOrientacion(DamageDetailGraphicsView.InferiorIzquierda);
                }
            }
            obj.setCategoria(x.getCategoria());
            obj.setCaracteristica(x.getCaracteristica());
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        borrar = new javax.swing.JMenuItem();
        content = new javax.swing.JLayeredPane();

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(borrar);

        setBackground(new java.awt.Color(255, 255, 255));

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                contentMouseReleased(evt);
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

    private void contentComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_contentComponentResized
        if (currentView != null) {
            resizeCurrentView();
        }
    }//GEN-LAST:event_contentComponentResized

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if (currentEdited != null) {
            listener.borrar(this.modelo.get(this.damageDetail.indexOf(currentEdited)));
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void contentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentMouseReleased
        if(evt.isPopupTrigger()) {
            for (DamageDetailGraphicsView x: damageDetail) {
                Point punto = new Point(evt.getX(), evt.getY());
                if (x.shapeContains(punto) && editable){
                    this.currentEdited = x;
                    this.jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                    break;
                }
            }
        } else{
            if (currentView.shapeContains(evt.getX() - boundsX, evt.getY() - boundsY) && editable) {
                damageCaptura.setX(evt.getX() - boundsX);
                damageCaptura.setY(evt.getY() - boundsY);
                dialogFactory.createDialog(mainFrame, true).setVisible(true);
            }
        }
    }//GEN-LAST:event_contentMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem borrar;
    private javax.swing.JLayeredPane content;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables

}
