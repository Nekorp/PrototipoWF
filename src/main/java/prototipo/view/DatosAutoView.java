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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindableListModel;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.ServicioVB;

/**
 *
 * 
 */
@Component("datosAutoView")
public class DatosAutoView extends ApplicationView {

    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ServicioVB viewServicioModel;
    
    private BindableListModel<String> modelEquipoAdicional;
    
    @Override
    public void setEditableStatus(boolean value) {
    
    }
    /**
     * Creates new form DatosAuto
     */
    public DatosAutoView() {
        modelEquipoAdicional = new BindableListModel<>();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bindComponents();
    }
    
    public void bindComponents() {
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"marca", (Bindable)marca);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"tipo", (Bindable)tipo);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"version", (Bindable)version);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"numeroSerie", (Bindable)numeroSerie);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"modelo", (Bindable)modelo);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"color", (Bindable)color);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"placas", (Bindable)placas);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"kilometraje", (Bindable)kilometraje);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"combustible", (Bindable)combustible);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"combustible", (Bindable)combustibleSlide);
        
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"transmision", (Bindable)estandar);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"transmision", (Bindable)automatico);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"elevadores", (Bindable)manuales);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"elevadores", (Bindable)electrico);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"bolsasDeAire", (Bindable)bolsasDeAire);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"aireAcondicionado", (Bindable)aireAcondicionado);
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto().getEquipamiento(),"equipoAdicional", (Bindable)modelEquipoAdicional);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTransmision = new javax.swing.ButtonGroup();
        grupoElevadores = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        marca = new prototipo.view.binding.SimpleBindableJTextField();
        tipo = new prototipo.view.binding.SimpleBindableJTextField();
        numeroSerie = new prototipo.view.binding.SimpleBindableJTextField();
        version = new prototipo.view.binding.SimpleBindableJTextField();
        modelo = new prototipo.view.binding.SimpleBindableJTextField();
        color = new prototipo.view.binding.SimpleBindableJTextField();
        placas = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        estandar = new prototipo.view.binding.SimpleBindableJRadioButton("estandar");
        automatico = new prototipo.view.binding.SimpleBindableJRadioButton("automatico");
        jPanel3 = new javax.swing.JPanel();
        manuales = new prototipo.view.binding.SimpleBindableJRadioButton("manuales");
        electrico = new prototipo.view.binding.SimpleBindableJRadioButton("electricos");
        aireAcondicionado = new prototipo.view.binding.SimpleBindableJCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        agregarEquipoAdicional = new javax.swing.JButton();
        borrarEquipoAdicional = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoAdicional = new javax.swing.JList();
        bolsasDeAire = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel9 = new javax.swing.JLabel();
        kilometraje = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel10 = new javax.swing.JLabel();
        combustibleSlide = new prototipo.view.binding.SimpleBindableJSlider();
        combustible = new prototipo.view.binding.SimpleBindableJTextField();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel1.setText("Marca:");

        jLabel2.setText("Tipo:");

        jLabel3.setText("Versión:");

        jLabel4.setText("Número de serie:");

        jLabel5.setText("Modelo:");

        jLabel6.setText("Color:");

        jLabel7.setText("Placas:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamiento"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Transmision"));

        grupoTransmision.add(estandar);
        estandar.setSelected(true);
        estandar.setText("Estandar");

        grupoTransmision.add(automatico);
        automatico.setText("Automatico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estandar)
                    .addComponent(automatico))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(estandar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(automatico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Elevadores"));

        grupoElevadores.add(manuales);
        manuales.setSelected(true);
        manuales.setText("Manuales");

        grupoElevadores.add(electrico);
        electrico.setText("Eléctricos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manuales)
                    .addComponent(electrico))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(manuales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(electrico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        aireAcondicionado.setText("Aire Acondicionado");

        jLabel8.setText("Bolsas de aire:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamiento Adicional"));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        agregarEquipoAdicional.setText("Agregar");
        agregarEquipoAdicional.setFocusable(false);
        agregarEquipoAdicional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarEquipoAdicional.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarEquipoAdicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEquipoAdicionalActionPerformed(evt);
            }
        });
        jToolBar2.add(agregarEquipoAdicional);

        borrarEquipoAdicional.setText("Borrar");
        borrarEquipoAdicional.setFocusable(false);
        borrarEquipoAdicional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrarEquipoAdicional.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrarEquipoAdicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarEquipoAdicionalActionPerformed(evt);
            }
        });
        jToolBar2.add(borrarEquipoAdicional);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        equipoAdicional.setModel(modelEquipoAdicional);
        jScrollPane2.setViewportView(equipoAdicional);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bolsasDeAire, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(aireAcondicionado))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(bolsasDeAire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aireAcondicionado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Kilometraje:");

        jLabel10.setText("Combustible:");

        combustibleSlide.setMinorTickSpacing(25);
        combustibleSlide.setPaintLabels(true);
        combustibleSlide.setPaintTicks(true);

        combustible.setEditable(false);
        combustible.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(placas)
                    .addComponent(modelo)
                    .addComponent(version)
                    .addComponent(marca, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combustible, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combustibleSlide, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(color)
                    .addComponent(numeroSerie)
                    .addComponent(tipo)
                    .addComponent(kilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(placas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(kilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(combustibleSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarEquipoAdicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEquipoAdicionalActionPerformed
        String s = (String)javax.swing.JOptionPane.showInputDialog(
                            this,
                            "Especifique el equipamiento adicional",
                            "Equipamiento Adicional",
                            javax.swing.JOptionPane.PLAIN_MESSAGE);
        modelEquipoAdicional.addElement(s);
    }//GEN-LAST:event_agregarEquipoAdicionalActionPerformed

    private void borrarEquipoAdicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarEquipoAdicionalActionPerformed
        modelEquipoAdicional.removeElement((String)equipoAdicional.getSelectedValue());
    }//GEN-LAST:event_borrarEquipoAdicionalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarEquipoAdicional;
    private javax.swing.JCheckBox aireAcondicionado;
    private javax.swing.JRadioButton automatico;
    private javax.swing.JTextField bolsasDeAire;
    private javax.swing.JButton borrarEquipoAdicional;
    private javax.swing.JTextField color;
    private javax.swing.JTextField combustible;
    private javax.swing.JSlider combustibleSlide;
    private javax.swing.JRadioButton electrico;
    private javax.swing.JList equipoAdicional;
    private javax.swing.JRadioButton estandar;
    private javax.swing.ButtonGroup grupoElevadores;
    private javax.swing.ButtonGroup grupoTransmision;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField kilometraje;
    private javax.swing.JRadioButton manuales;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField numeroSerie;
    private javax.swing.JTextField placas;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField version;
    // End of variables declaration//GEN-END:variables
    
}
