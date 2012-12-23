/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.util.LinkedList;
import prototipo.modelo.auto.DatosAuto;
import prototipo.modelo.auto.Equipamiento;

/**
 *
 * @author Marisa
 */
public class DatosAutoView extends javax.swing.JPanel {

    /**
     * Creates new form DatosAuto
     */
    public DatosAutoView() {
        modelEquipoAdicional = new javax.swing.DefaultListModel<String>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        grupoTransmision = new javax.swing.ButtonGroup();
        grupoElevadores = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        tipo = new javax.swing.JTextField();
        numeroSerie = new javax.swing.JTextField();
        version = new javax.swing.JTextField();
        modelo = new javax.swing.JTextField();
        color = new javax.swing.JTextField();
        placas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        estandar = new javax.swing.JRadioButton();
        automatico = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        manuales = new javax.swing.JRadioButton();
        electrico = new javax.swing.JRadioButton();
        aireAcondicionado = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        agregarEquipoAdicional = new javax.swing.JButton();
        borrarEquipoAdicional = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoAdicional = new javax.swing.JList();
        bolsasDeAire = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        kilometraje = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        combustible = new javax.swing.JTextField();

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

        jSlider1.setMinorTickSpacing(25);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, combustible, org.jdesktop.beansbinding.ELProperty.create("${text}"), jSlider1, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

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
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();
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
        modelEquipoAdicional.remove(equipoAdicional.getSelectedIndex());
    }//GEN-LAST:event_borrarEquipoAdicionalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarEquipoAdicional;
    private javax.swing.JCheckBox aireAcondicionado;
    private javax.swing.JRadioButton automatico;
    private javax.swing.JTextField bolsasDeAire;
    private javax.swing.JButton borrarEquipoAdicional;
    private javax.swing.JTextField color;
    private javax.swing.JTextField combustible;
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
    private javax.swing.JSlider jSlider1;
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
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    //mis atributos
    private javax.swing.DefaultListModel<String> modelEquipoAdicional;
    //mis metodos
    public DatosAuto getData() {
        DatosAuto r = new DatosAuto();
        r.setMarca(this.marca.getText());
        r.setTipo(this.tipo.getText());
        r.setVersion(this.version.getText());
        r.setModelo(this.modelo.getText());
        r.setNumeroSerie(this.numeroSerie.getText());
        r.setColor(this.color.getText());
        r.setPlacas(this.placas.getText());
        r.setKilometraje(this.kilometraje.getText());
        r.setCombustible(this.combustible.getText());
        Equipamiento rEquipo = new Equipamiento();
        if (this.estandar.isSelected()) {
            rEquipo.setTransmision("estandar");
        } else {
            rEquipo.setTransmision("automatico");
        }
        if (this.manuales.isSelected()) {
            rEquipo.setElevadores("manuales");
        } else {
            rEquipo.setElevadores("electricos");
        }
        rEquipo.setBolsasDeAire(this.bolsasDeAire.getText());
        rEquipo.setAireAcondicionado(this.aireAcondicionado.isSelected());
        javax.swing.ListModel model = this.equipoAdicional.getModel();
        LinkedList<String> rEquipoAdd = new LinkedList<>();
        for(int i = 0; i < model.getSize(); i++){
            rEquipoAdd.add(model.getElementAt(i).toString());
        }
        rEquipo.setEquipoAdicional(rEquipoAdd);
        r.setEquipamiento(rEquipo);
        return r;
    }

    public void loadData(DatosAuto datosAuto) {
        this.marca.setText(datosAuto.getMarca());
        this.tipo.setText(datosAuto.getTipo());
        this.version.setText(datosAuto.getVersion());
        this.modelo.setText(datosAuto.getModelo());
        this.numeroSerie.setText(datosAuto.getNumeroSerie());
        this.color.setText(datosAuto.getColor());
        this.placas.setText(datosAuto.getPlacas());
        this.kilometraje.setText(datosAuto.getKilometraje());
        if (datosAuto.getCombustible() != null && datosAuto.getCombustible().trim().length() > 0) {
            this.combustible.setText(datosAuto.getCombustible());
        }
        if (datosAuto.getEquipamiento() != null) {
            if (datosAuto.getEquipamiento().getTransmision().compareTo("estandar") == 0) {
                this.estandar.setSelected(true);
            } else {
                this.automatico.setSelected(true);
            }
            if (datosAuto.getEquipamiento().getElevadores().compareTo("manuales") == 0) {
                this.manuales.setSelected(true);
            } else {
                this.electrico.setSelected(true);
            }
            this.bolsasDeAire.setText(datosAuto.getEquipamiento().getBolsasDeAire());
            this.aireAcondicionado.setSelected(datosAuto.getEquipamiento().isAireAcondicionado());
            if (datosAuto.getEquipamiento().getEquipoAdicional() != null) {
                for(String obj : datosAuto.getEquipamiento().getEquipoAdicional()){
                    modelEquipoAdicional.addElement(obj);
                }
            }
        }
        this.updateUI();
    }
}