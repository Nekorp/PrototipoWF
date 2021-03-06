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

import java.awt.Window;
import java.util.List;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;

/**
 * esta mugre solo funciona con mas de un elemento en su lista de paginas.
 * @author Nekorp
 */
public abstract class WizardView extends ApplicationView {

    private List<ApplicationView> paginas;
    private int indexPaginaActual;
    private String controlesMostrados;
    private java.awt.Window parent;
    private BindingManager<Bindable> bindingManager;
    private boolean iniciado = false;
    
    /**
     * Creates new form NuevoServicioWizardView
     */
    public WizardView() {
    }
    
    @Override
    public void iniciaVista() {
        this.removeAll();
        initComponents();
        if (!iniciado) {
            for(ApplicationView x: paginas) {
                x.iniciaVista();
            }
            iniciado = true;
        }
        iniciar();
        this.changePage(0);
    }

    @Override
    public void setEditableStatus(boolean value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void registerComponents() {
        if (controlesMostrados.equals("inicio")) {
            ViewValidIndicator validInidicator = this.paginas.get(indexPaginaActual).getValidInidicator();
            if (validInidicator != null) {
                bindingManager.registerBind(validInidicator, "valido", (Bindable)this.siguienteInicio);
            }
        }
        if (controlesMostrados.equals("intermedio")) {
            ViewValidIndicator validInidicator = this.paginas.get(indexPaginaActual).getValidInidicator();
            if (validInidicator != null) {
                bindingManager.registerBind(validInidicator, "valido", (Bindable)this.siguienteIntermedio);
            }
        }
        if (controlesMostrados.equals("final")) {
            ViewValidIndicator validInidicator = this.paginas.get(indexPaginaActual).getValidInidicator();
            if (validInidicator != null) {
                bindingManager.registerBind(validInidicator, "valido", (Bindable)this.finalizar);
            }
        }
    }
    
    private void removeRegisterComponents() {
        if (this.controlesMostrados == null || this.controlesMostrados.isEmpty()) {
            return;
        }
        if (controlesMostrados.equals("inicio")) {
            bindingManager.clearBindings((Bindable)this.siguienteInicio);
        }
        if (controlesMostrados.equals("intermedio")) {
            bindingManager.clearBindings((Bindable)this.siguienteIntermedio);
        }
        if (controlesMostrados.equals("final")) {
            bindingManager.clearBindings((Bindable)this.finalizar);
        }
    }
    
    private void changePage(int index) {
        if (index >= this.paginas.size()) {
            throw new IllegalArgumentException("Contenido invalido wizard");
        }
        this.indexPaginaActual = index;
        this.contenido.removeAll();
        //this.paginas.get(index).setEditableStatus(true);
        this.contenido.add((java.awt.Component)this.paginas.get(index));
        removeRegisterComponents();
        changeControls();
        registerComponents();
        this.validate();
        this.parent.pack();
        parent.setLocationRelativeTo(parent.getOwner());
    }
    
    private void changeControls() {
        java.awt.CardLayout cardLayout = (java.awt.CardLayout)(controles.getLayout());
        if (this.indexPaginaActual == 0) {
            controlesMostrados = "inicio";
            cardLayout.show(controles, controlesMostrados);
            return;
        }
        if (this.indexPaginaActual == (this.paginas.size() - 1)) {
            controlesMostrados = "final";
            cardLayout.show(controles, controlesMostrados);
            return;
        }
        controlesMostrados = "intermedio";
        cardLayout.show(controles, controlesMostrados);
    }

    public void setPaginas(List<ApplicationView> paginas) {
        this.paginas = paginas;
    }

    public void setParentWindow(java.awt.Window parent) {
        this.parent = parent;
    }

    public Window getParentWindow() {
        return parent;
    }

    public void setBindingManager(BindingManager<Bindable> bindingManager) {
        this.bindingManager = bindingManager;
    }

    public abstract void iniciar();
    
    public abstract void cancelar();
    
    public abstract void terminar();
    
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

        contenido = new javax.swing.JPanel();
        controles = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cancelarInicio = new javax.swing.JButton();
        siguienteInicio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        jPanel3 = new javax.swing.JPanel();
        cancelarIntermedio = new javax.swing.JButton();
        siguienteIntermedio = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        anteriorIntermedio = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cancelarFinal = new javax.swing.JButton();
        finalizar = new org.nekorp.workflow.desktop.view.binding.CustomEnabledBindingJButton();
        atrasFinal = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setLayout(new java.awt.BorderLayout());

        controles.setBackground(new java.awt.Color(255, 255, 255));
        controles.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cancelarInicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cancelarInicio.setText("Cancelar");
        cancelarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarInicioActionPerformed(evt);
            }
        });

        siguienteInicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        siguienteInicio.setText("Siguiente >");
        siguienteInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(siguienteInicio)
                .addGap(18, 18, 18)
                .addComponent(cancelarInicio)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarInicio)
                    .addComponent(siguienteInicio))
                .addContainerGap())
        );

        controles.add(jPanel2, "inicio");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cancelarIntermedio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cancelarIntermedio.setText("Cancelar");
        cancelarIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarIntermedioActionPerformed(evt);
            }
        });

        siguienteIntermedio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        siguienteIntermedio.setText("Siguiente >");
        siguienteIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteIntermedioActionPerformed(evt);
            }
        });

        anteriorIntermedio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        anteriorIntermedio.setText("< Anterior");
        anteriorIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorIntermedioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(anteriorIntermedio)
                .addGap(18, 18, 18)
                .addComponent(siguienteIntermedio)
                .addGap(18, 18, 18)
                .addComponent(cancelarIntermedio)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarIntermedio)
                    .addComponent(siguienteIntermedio)
                    .addComponent(anteriorIntermedio))
                .addContainerGap())
        );

        controles.add(jPanel3, "intermedio");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        cancelarFinal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cancelarFinal.setText("Cancelar");
        cancelarFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarFinalActionPerformed(evt);
            }
        });

        finalizar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        finalizar.setText("Finalizar");
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });

        atrasFinal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        atrasFinal.setText("< Atras");
        atrasFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasFinalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(atrasFinal)
                .addGap(18, 18, 18)
                .addComponent(finalizar)
                .addGap(18, 18, 18)
                .addComponent(cancelarFinal)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarFinal)
                    .addComponent(finalizar)
                    .addComponent(atrasFinal))
                .addContainerGap())
        );

        controles.add(jPanel4, "final");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controles, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarInicioActionPerformed
        cancelar();
    }//GEN-LAST:event_cancelarInicioActionPerformed

    private void siguienteInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteInicioActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        this.changePage(1);
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_siguienteInicioActionPerformed

    private void cancelarIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarIntermedioActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        cancelar();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_cancelarIntermedioActionPerformed

    private void siguienteIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteIntermedioActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        this.changePage(this.indexPaginaActual + 1);
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_siguienteIntermedioActionPerformed

    private void anteriorIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorIntermedioActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        this.changePage(this.indexPaginaActual - 1);
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_anteriorIntermedioActionPerformed

    private void cancelarFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarFinalActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        cancelar();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_cancelarFinalActionPerformed

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        terminar();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_finalizarActionPerformed

    private void atrasFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasFinalActionPerformed
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        this.changePage(this.indexPaginaActual - 1);
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_atrasFinalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anteriorIntermedio;
    private javax.swing.JButton atrasFinal;
    private javax.swing.JButton cancelarFinal;
    private javax.swing.JButton cancelarInicio;
    private javax.swing.JButton cancelarIntermedio;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel controles;
    private javax.swing.JButton finalizar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton siguienteInicio;
    private javax.swing.JButton siguienteIntermedio;
    // End of variables declaration//GEN-END:variables
}
