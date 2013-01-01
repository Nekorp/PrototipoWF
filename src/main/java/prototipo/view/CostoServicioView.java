/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.CostoServicioTableModel;

/**
 *
 * @author Marisa
 */
@Component("costosView")
public class CostoServicioView extends ApplicationView {
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    javax.swing.JFrame mainFrame;
    @Autowired
    private Servicio viewServicioModel;
    @Autowired
    private CostoServicioTableModel tableModel;
    @Override
    public void iniciaVista() {
        initComponents();
        this.tablaCostos.setModel(tableModel);
        setDefaultColumnSize();
        setBindings();
        //pudieran o no funcionar
        //TODO requieren algo mas de trabajo
        //setShorcuts();
    }

    @Override
    public void setEditableStatus(boolean value) {
        //no hacer nada
    }
    
    private void setBindings() {
        bindingManager.registerBind(viewServicioModel, "costos", tableModel);
    }
    
    private void setShorcuts() {
        InputMap im = tablaCostos.getInputMap(JTable.WHEN_FOCUSED);
        ActionMap am = tablaCostos.getActionMap();
        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarActionPerformed(e);
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Delete");
        am.put("Delete", deleteAction);
        Action addAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarActionPerformed(e);
            }
        };
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "Add");
        am.put("Add", addAction);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        agregarHP = new javax.swing.JButton();
        agregarM = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCostos = new prototipo.view.resource.imp.CustomJTableCostos();
        jPanel2 = new javax.swing.JPanel();
        total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

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

        borrar.setText("Borrar");
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jToolBar1.add(borrar);

        jPanel1.setLayout(new java.awt.BorderLayout());

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
        tablaCostos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaCostos);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        total.setEditable(false);

        jLabel1.setText("Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarHPActionPerformed
        this.tableModel.addRegistro("Hojalateria y Pintura");
//        TableColumn sportColumn = this.tablaCostos.getColumnModel().getColumn(1).get;
//        JComboBox comboBox = new JComboBox();
//        comboBox.addItem("Snowboarding");
//        comboBox.addItem("Rowing");
//        comboBox.addItem("Chasing toddlers");
//        comboBox.addItem("Speed reading");
//        comboBox.addItem("Teaching high school");
//        comboBox.addItem("None");
//        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }//GEN-LAST:event_agregarHPActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        int index = this.tablaCostos.getSelectedRow();
        if (index >= 0) {
            this.tableModel.deleteRegistro(index);
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void agregarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMActionPerformed
        this.tableModel.addRegistro("Mecanica");
    }//GEN-LAST:event_agregarMActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarHP;
    private javax.swing.JButton agregarM;
    private javax.swing.JButton borrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tablaCostos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

}
