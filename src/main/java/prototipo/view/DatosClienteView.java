/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import org.springframework.stereotype.Component;
import prototipo.modelo.cliente.Cliente;
import prototipo.modelo.cliente.DomicilioFiscal;
import prototipo.servicio.imp.ModelControl;

/**
 *
 * @author Marisa
 */
@Component("datosClienteView")
public class DatosClienteView extends ApplicationView {

    @Override
    public void setEditableStatus(boolean value) {
    
    }
   
    @Override
    public void iniciaVista() {
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

        jToolBar1 = new javax.swing.JToolBar();
        nuevoCliente = new javax.swing.JButton();
        buscarCliente = new javax.swing.JButton();
        guardarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        numeroCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JTextField();
        rfcCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        domicioFiscal = new javax.swing.JPanel();
        calleCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numeroCasaCliente = new javax.swing.JTextField();
        codigoPostalCliente = new javax.swing.JTextField();
        coloniaCliente = new javax.swing.JTextField();
        ciudadCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        nuevoCliente.setText("Nuevo");
        nuevoCliente.setFocusable(false);
        nuevoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevoCliente);

        buscarCliente.setText("Buscar");
        buscarCliente.setFocusable(false);
        buscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(buscarCliente);

        guardarCliente.setText("Guardar");
        guardarCliente.setFocusable(false);
        guardarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarClienteActionPerformed(evt);
            }
        });
        jToolBar1.add(guardarCliente);

        jLabel1.setText("Número de Cliente:");

        numeroCliente.setText(" ");

        jLabel2.setText("Nombre o razón social:");

        nombreCliente.setEditable(false);

        rfcCliente.setEditable(false);

        jLabel3.setText("RFC:");

        domicioFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Fiscal"));

        calleCliente.setEditable(false);

        jLabel4.setText("Calle:");

        numeroCasaCliente.setEditable(false);

        codigoPostalCliente.setEditable(false);

        coloniaCliente.setEditable(false);

        ciudadCliente.setEditable(false);

        jLabel5.setText("Num. Interior:");

        jLabel6.setText("Código Postal:");

        jLabel7.setText("Colonia:");

        jLabel8.setText("Ciudad:");

        javax.swing.GroupLayout domicioFiscalLayout = new javax.swing.GroupLayout(domicioFiscal);
        domicioFiscal.setLayout(domicioFiscalLayout);
        domicioFiscalLayout.setHorizontalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, domicioFiscalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calleCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(codigoPostalCliente)
                    .addComponent(coloniaCliente)
                    .addComponent(ciudadCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        domicioFiscalLayout.setVerticalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(domicioFiscalLayout.createSequentialGroup()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoPostalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coloniaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(domicioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rfcCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                            .addComponent(nombreCliente)
                            .addComponent(numeroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numeroCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rfcCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(domicioFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteActionPerformed
        BusquedaClienteView dialog = new BusquedaClienteView(null, true);
        dialog.setModeloTabla(this.control.getClientes());
        dialog.setParent(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_buscarClienteActionPerformed

    private void guardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarClienteActionPerformed
//        if (this.cliente != null) {
//            this.cliente.setNombre(this.nombreCliente.getText());
//            this.cliente.setRfc(this.rfcCliente.getText());
//            DomicilioFiscal domicilio = new DomicilioFiscal();
//            domicilio.setCalle(this.calleCliente.getText());
//            domicilio.setNumInterior(this.numeroCasaCliente.getText());
//            domicilio.setCodigoPostal(this.codigoPostalCliente.getText());
//            domicilio.setColonia(this.coloniaCliente.getText());
//            domicilio.setCiudad(this.ciudadCliente.getText());
//            this.cliente.setDomicilio(domicilio);
//        }
//        this.parent.actualizaDatosCliente();
//        control.guardaClientes();
    }//GEN-LAST:event_guardarClienteActionPerformed

    private void nuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoClienteActionPerformed
        cliente = this.control.nuevoCliente();
        this.numeroCliente.setText(cliente.getId());
        this.nombreCliente.setText(cliente.getNombre());
        this.rfcCliente.setText(cliente.getRfc());
        if (cliente.getDomicilio() != null) {
            this.calleCliente.setText(cliente.getDomicilio().getCalle());
            this.numeroCasaCliente.setText(cliente.getDomicilio().getNumInterior());
            this.codigoPostalCliente.setText(cliente.getDomicilio().getCodigoPostal());
            this.coloniaCliente.setText(cliente.getDomicilio().getColonia());
            this.ciudadCliente.setText(cliente.getDomicilio().getCiudad());
        } else {
            this.calleCliente.setText("");
            this.numeroCasaCliente.setText("");
            this.codigoPostalCliente.setText("");
            this.coloniaCliente.setText("");
            this.ciudadCliente.setText("");
        }
        this.nombreCliente.setEditable(true);
        this.rfcCliente.setEditable(true);
        this.calleCliente.setEditable(true);
        this.numeroCasaCliente.setEditable(true);
        this.codigoPostalCliente.setEditable(true);
        this.coloniaCliente.setEditable(true);
        this.ciudadCliente.setEditable(true);
        this.updateUI();
    }//GEN-LAST:event_nuevoClienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarCliente;
    private javax.swing.JTextField calleCliente;
    private javax.swing.JTextField ciudadCliente;
    private javax.swing.JTextField codigoPostalCliente;
    private javax.swing.JTextField coloniaCliente;
    private javax.swing.JPanel domicioFiscal;
    private javax.swing.JButton guardarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JButton nuevoCliente;
    private javax.swing.JTextField numeroCasaCliente;
    private javax.swing.JLabel numeroCliente;
    private javax.swing.JTextField rfcCliente;
    // End of variables declaration//GEN-END:variables
    //mis atributos
    private ModelControl control;
    private Cliente cliente;
    private ServicioView parent;
    //mis metodos
    public String getData() {
        return this.numeroCliente.getText();
    }
    
    public void loadData(String idCliente) {
        this.numeroCliente.setText(idCliente);
        cliente = this.control.getCliente(idCliente);
        this.loadData(cliente);
    }

    private void loadData(Cliente cliente) {
//        if (cliente != null) {
//            this.nombreCliente.setText(cliente.getNombre());
//            this.rfcCliente.setText(cliente.getRfc());
//            if (cliente.getDomicilio() != null) {
//                this.calleCliente.setText(cliente.getDomicilio().getCalle());
//                this.numeroCasaCliente.setText(cliente.getDomicilio().getNumInterior());
//                this.codigoPostalCliente.setText(cliente.getDomicilio().getCodigoPostal());
//                this.coloniaCliente.setText(cliente.getDomicilio().getColonia());
//                this.ciudadCliente.setText(cliente.getDomicilio().getCiudad());
//            }
//            this.nombreCliente.setEditable(true);
//            this.rfcCliente.setEditable(true);
//            this.calleCliente.setEditable(true);
//            this.numeroCasaCliente.setEditable(true);
//            this.codigoPostalCliente.setEditable(true);
//            this.coloniaCliente.setEditable(true);
//            this.ciudadCliente.setEditable(true);
//        }
//        this.parent.actualizaDatosCliente();
//        this.updateUI();
    }

    public void seleccionCliente(int index) {
        if (index >= 0){
            cliente = this.control.getClientes().get(index);
            this.numeroCliente.setText(cliente.getId());
            this.loadData(cliente);
        }
    }
    
    public void setControl(ModelControl control) {
        this.control = control;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setParent(ServicioView parent) {
        this.parent = parent;
    }
    
    
}
