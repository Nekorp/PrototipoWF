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


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindableListModel;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioLoadedListMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author Nekorp
 */
@Component("navegadorServiciosView")
public class NavegadorServiciosView extends ApplicationView  {
    @Autowired
    private ServicioLoadedListMetadata servicioLoadedListMetadata;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    private final BindableListModel<ServicioLoaded> modeloServicioNuevoList;
    private final BindableListModel<ServicioLoaded> modeloServicioList;
    private boolean internalUpdateOnProcess;
    /**
     * Creates new form NavegadorServicios
     */
    public NavegadorServiciosView() {
        super();
        modeloServicioNuevoList = new BindableListModel<>();
        modeloServicioList = new BindableListModel<>();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bindingManager.registerBind(servicioLoadedListMetadata, "serviciosNuevos", modeloServicioNuevoList);
        bindingManager.registerBind(servicioLoadedListMetadata, "servicios", modeloServicioList);
        this.bindingManager.registerBind(servicioMetaData, "editando", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                internalUpdateOnProcess = true;
                boolean editando = (boolean) value;
                //System.out.println("-------------------cambio edicion:" + editando);
                if(!editando) {
                    //System.out.println("no esta editando, limpiando la seleccion");
                    listaServicioNuevo.clearSelection();
                    listaServicioCargado.clearSelection();
                } else {
                    if(servicioMetaData.isServicioCargado()){
                        //System.out.println("hay servicio cargado");
                        if(servicioMetaData.getServicioActual().isNuevo()) {
                            //System.out.println("el servicio es nuevo, limpiando seleccion de los cargados");
                            listaServicioCargado.clearSelection();
                            //System.out.println("seleccionando el servicio actual");
                            listaServicioNuevo.setSelectedIndex(modeloServicioNuevoList.indexof(servicioMetaData.getServicioActual()));
                        } else {
                            //System.out.println("el servicio ya es viejo, limpiando seleccion de los viejos");
                            listaServicioNuevo.clearSelection();
                            //System.out.println("seleccionando el servicio actual");
                            listaServicioCargado.setSelectedIndex(modeloServicioList.indexof(servicioMetaData.getServicioActual()));
                        }
                    } else {
                        //System.out.println("esta editando pero no hay nada cargado, limpiando la seleccion");
                        listaServicioNuevo.clearSelection();
                        listaServicioCargado.clearSelection();
                    }
                }
                internalUpdateOnProcess = false;
            }
        });
        this.listaServicioNuevo.addListSelectionListener(new ListSelectionListener (){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listaServicioNuevo.getSelectedIndex() < modeloServicioNuevoList.getSize() && listaServicioNuevo.getSelectedIndex() > -1) {
                    if (!internalUpdateOnProcess && !e.getValueIsAdjusting() && !listaServicioNuevo.isSelectionEmpty()) {
                        aplication.cambiarServicio(modeloServicioNuevoList.getElementAt(listaServicioNuevo.getSelectedIndex()));
                    }
                }
            }
        });
        
        this.listaServicioCargado.addListSelectionListener(new ListSelectionListener (){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listaServicioCargado.getSelectedIndex() < modeloServicioList.getSize() && listaServicioCargado.getSelectedIndex() > -1) {
                    if (!internalUpdateOnProcess && !e.getValueIsAdjusting() && !listaServicioCargado.isSelectionEmpty()) {
                        aplication.cambiarServicio(modeloServicioList.getElementAt(listaServicioCargado.getSelectedIndex()));
                    }
                }
            }
        });
    }

    @Override
    public void setEditableStatus(boolean value) {
        ///
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

        navigation = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollListaNuevos = new javax.swing.JScrollPane();
        listaServicioNuevo = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scrollListaCargados = new javax.swing.JScrollPane();
        listaServicioCargado = new javax.swing.JList();

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Servicios Nuevos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        scrollListaNuevos.setBorder(null);
        scrollListaNuevos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaServicioNuevo.setBackground(new java.awt.Color(102, 102, 102));
        listaServicioNuevo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        listaServicioNuevo.setForeground(new java.awt.Color(255, 255, 255));
        listaServicioNuevo.setModel(modeloServicioNuevoList);
        listaServicioNuevo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaServicioNuevo.setRequestFocusEnabled(false);
        listaServicioNuevo.setSelectionBackground(new java.awt.Color(224, 230, 230));
        listaServicioNuevo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        scrollListaNuevos.setViewportView(listaServicioNuevo);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Servicios abiertos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        scrollListaCargados.setBorder(null);
        scrollListaCargados.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaServicioCargado.setBackground(new java.awt.Color(102, 102, 102));
        listaServicioCargado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        listaServicioCargado.setForeground(new java.awt.Color(255, 255, 255));
        listaServicioCargado.setModel(modeloServicioList);
        listaServicioCargado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaServicioCargado.setFocusable(false);
        listaServicioCargado.setSelectionBackground(new java.awt.Color(224, 230, 230));
        listaServicioCargado.setSelectionForeground(new java.awt.Color(0, 0, 0));
        scrollListaCargados.setViewportView(listaServicioCargado);

        javax.swing.GroupLayout navigationLayout = new javax.swing.GroupLayout(navigation);
        navigation.setLayout(navigationLayout);
        navigationLayout.setHorizontalGroup(
            navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollListaNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navigationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollListaCargados, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navigationLayout.setVerticalGroup(
            navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navigationLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollListaNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollListaCargados, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JList listaServicioCargado;
    private javax.swing.JList listaServicioNuevo;
    private javax.swing.JPanel navigation;
    private javax.swing.JScrollPane scrollListaCargados;
    private javax.swing.JScrollPane scrollListaNuevos;
    // End of variables declaration//GEN-END:variables
}
