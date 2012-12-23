/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.util.List;
import javax.swing.event.DocumentEvent;
import prototipo.modelo.ServicioIndex;

/**
 *
 * @author Marisa
 */
public class BusquedaServicioView extends javax.swing.JDialog {

    /**
     * Creates new form BusquedaServicioView
     */
    public BusquedaServicioView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jLabel1 = new javax.swing.JLabel();
        filtro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Servicio");
        setModal(true);

        jLabel1.setText("Filtro:");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaDatos);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.tablaDatos.getSelectedRow() >= 0) {
            this.parent.seleccionServicio(this.tablaDatos.convertRowIndexToModel(this.tablaDatos.getSelectedRow()));
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
    //mis variables
    private javax.swing.table.TableRowSorter sorter;
    private ServicioView parent;
    //mis metodos
    public void setModeloTabla(List<ServicioIndex> datos) {
        ServicioTableModel tableModel = new ServicioTableModel();
        tableModel.setDatos(datos);
        sorter = new javax.swing.table.TableRowSorter(tableModel);
        this.tablaDatos.setModel(tableModel);
        this.tablaDatos.setRowSorter(sorter);
        this.filtro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicaFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicaFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicaFiltro();
            }
            
        });
    }
    
    private void aplicaFiltro() {
        String textoFiltro = filtro.getText().trim();
        if (textoFiltro.length() > 0) {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter(".*"+textoFiltro+".*"));
        } else {
            sorter.setRowFilter(null);
        }
    }
    
    public void setParent(ServicioView parent) {
        this.parent = parent;
    }
}
