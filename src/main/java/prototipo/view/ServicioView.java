/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.view.model.ServicioViewModel;

/**
 *
 * @author Marisa
 */
@Component("servicioView")
@Aspect
public class ServicioView extends ApplicationView {

    @Autowired
    private WorkflowApp aplication;
    @Autowired
    @Qualifier(value="bitacoraView")
    private ApplicationView bitacora;
    @Autowired
    @Qualifier(value="datosClienteView")
    private ApplicationView datosCliente;
    @Autowired
    @Qualifier(value="datosAutoView")
    private ApplicationView datosAuto;
    @Autowired
    private ServicioViewModel viewModel;
    
    //private boolean tabInited;
    private javax.swing.JTabbedPane tabDatos;
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.nuevoServicio(..))")  
    public void nuevoServicio() {
    }
    @AfterReturning("nuevoServicio()")
    public void enableEdition() {
        this.setEditableStatus(true);
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        this.descripcion.setEnabled(value);
        this.contacto.setEditable(value);
        this.labelTelefonoUno.setEnabled(value);
        this.valorTelefonoUno.setEditable(value);
        this.labelTelefonoDos.setEnabled(value);
        this.valorTelefonoDos.setEditable(value);
        this.labelTelefonoTres.setEnabled(value);
        this.valorTelefonoTres.setEditable(value);
        if (value) {
            tabDatos.add("Cliente", datosCliente);
            //agrega un tab para los datos del auto
            tabDatos.add("Auto",datosAuto);
            //agrega tab con la bitacora
            tabDatos.add("Bitacora", bitacora);
            this.datos.add(this.tabDatos);
            //this.tabInited = true;
        } else {
            this.datos.removeAll();
        }
        this.updateUI();
    }
    public void updateAllModel() {
//        this.numeroServicio.setText(model.getServicio().getId());
//        this.placas.setText(model.getServicio().getDatosAuto().getPlacas());
//        
//        this.descripcion.setText(model.getServicio().getDescripcion());
//        
//        this.ingreso.setText(model.getFechaEntrada());
//        this.salida.setText(model.getFechaSalida());
//        this.tiempo.setText(model.getTiempoEstadia());
//        
//        this.contacto.setText(model.getServicio().getContacto());
//        this.labelTelefonoUno.setSelectedItem(model.getServicio().getTelefonoUno().getLabel());
//        this.valorTelefonoUno.setText(model.getServicio().getTelefonoUno().getValor());
//        
//        this.labelTelefonoDos.setSelectedItem(model.getServicio().getTelefonoDos().getLabel());
//        this.valorTelefonoDos.setText(model.getServicio().getTelefonoDos().getValor());
//        
//        this.labelTelefonoTres.setSelectedItem(model.getServicio().getTelefonoTres().getLabel());
//        this.valorTelefonoTres.setText(model.getServicio().getTelefonoTres().getValor());
//        
//        if (!tabInited) {
//            tabDatos.add("Cliente", datosCliente);
//            //agrega un tab para los datos del auto
//            tabDatos.add("Auto",datosAuto);
//            //agrega tab con la bitacora
//            tabDatos.add("Bitacora", bitacora);
//            this.datos.add(this.tabDatos);
//            this.tabInited = true;
//        }
//        this.updateUI();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bitacora.iniciaVista();
        datosCliente.iniciaVista();
        datosAuto.iniciaVista();
        tabDatos = new javax.swing.JTabbedPane();
        viewModel.bind("setId",this.numeroServicio);
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
        nuevoServicio = new javax.swing.JButton();
        buscarServicio = new javax.swing.JButton();
        guardarServicio = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        mensaje = new javax.swing.JLabel();
        datosGenerales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ingreso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tiempo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        salida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        contacto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        labelTelefonoUno = new javax.swing.JComboBox();
        valorTelefonoUno = new javax.swing.JTextField();
        labelTelefonoDos = new javax.swing.JComboBox();
        valorTelefonoDos = new javax.swing.JTextField();
        labelTelefonoTres = new javax.swing.JComboBox();
        valorTelefonoTres = new javax.swing.JTextField();
        nombreCliente = new javax.swing.JTextField();
        placas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        numeroServicio = new prototipo.view.resource.CustomJLabel();
        datos = new javax.swing.JPanel();

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        nuevoServicio.setText("Nuevo");
        nuevoServicio.setFocusable(false);
        nuevoServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(nuevoServicio);

        buscarServicio.setText("Buscar");
        buscarServicio.setFocusable(false);
        buscarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(buscarServicio);

        guardarServicio.setText("Guardar");
        guardarServicio.setFocusable(false);
        guardarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(guardarServicio);
        jToolBar1.add(filler1);

        mensaje.setText(" ");
        jToolBar1.add(mensaje);

        datosGenerales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Servicio"));
        datosGenerales.setName(""); // NOI18N

        jLabel2.setText("Número:");

        jLabel1.setText("Nombre o razón social:");

        jLabel3.setText("Placas:");

        ingreso.setEditable(false);

        jLabel5.setText("Fecha Ingreso:");

        tiempo.setEditable(false);

        jLabel6.setText("Tiempo:");

        salida.setEditable(false);

        jLabel7.setText("Fecha Salida:");

        jLabel8.setText("Contacto:");

        jLabel9.setText("Telefono:");

        labelTelefonoUno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        labelTelefonoDos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        labelTelefonoTres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        nombreCliente.setEditable(false);

        placas.setEditable(false);

        jLabel10.setText("Descripción del servicio:");

        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        numeroServicio.setText(" ");

        javax.swing.GroupLayout datosGeneralesLayout = new javax.swing.GroupLayout(datosGenerales);
        datosGenerales.setLayout(datosGeneralesLayout);
        datosGeneralesLayout.setHorizontalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCliente)
                    .addComponent(tiempo)
                    .addComponent(salida)
                    .addComponent(ingreso)
                    .addComponent(contacto)
                    .addComponent(placas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addGroup(datosGeneralesLayout.createSequentialGroup()
                                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelTelefonoUno, 0, 79, Short.MAX_VALUE)
                                    .addComponent(labelTelefonoDos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelTelefonoTres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(valorTelefonoUno, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(valorTelefonoDos)
                                    .addComponent(valorTelefonoTres))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        datosGeneralesLayout.setVerticalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numeroServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        datos.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoServicioActionPerformed
        aplication.nuevoServicio();
    }//GEN-LAST:event_nuevoServicioActionPerformed

    private void guardarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarServicioActionPerformed
//        if (this.bitacora != null) {
//            Servicio servicio = new Servicio();
//            servicio.setId(this.numeroServicio.getText());
//            servicio.setBitacora(this.bitacora.getData());
//            servicio.setDatosAuto(this.datosAuto.getData());
//            this.placas.setText(servicio.getDatosAuto().getPlacas());
//            servicio.setIdCliente(this.datosCliente.getData());
//            servicio.setDescripcion(this.descripcion.getText());
//            servicio.setContacto(this.contacto.getText());
//            Telefono tel = new Telefono();
//            tel.setLabel(this.labelTelefonoUno.getSelectedItem().toString());
//            tel.setValor(this.valorTelefonoUno.getText());
//            servicio.setTelefonoUno(tel);
//            tel = new Telefono();
//            tel.setLabel(this.labelTelefonoDos.getSelectedItem().toString());
//            tel.setValor(this.valorTelefonoDos.getText());
//            servicio.setTelefonoDos(tel);
//            tel = new Telefono();
//            tel.setLabel(this.labelTelefonoTres.getSelectedItem().toString());
//            tel.setValor(this.valorTelefonoTres.getText());
//            servicio.setTelefonoTres(tel);
//            buscarFechas(servicio);
//            control.guardaServicio(servicio);
//            this.control.guardaClientes();
//        }
        this.aplication.guardaServicio();
    }//GEN-LAST:event_guardarServicioActionPerformed

    private void buscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarServicioActionPerformed
//        BusquedaServicioView dialog = new BusquedaServicioView(null, true);
//        dialog.setModeloTabla(this.control.getIndiceServicios());
//        dialog.setParent(this);
//        dialog.setVisible(true);
    }//GEN-LAST:event_buscarServicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarServicio;
    private javax.swing.JTextField contacto;
    private javax.swing.JPanel datos;
    private javax.swing.JPanel datosGenerales;
    private javax.swing.JTextArea descripcion;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton guardarServicio;
    private javax.swing.JTextField ingreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox labelTelefonoDos;
    private javax.swing.JComboBox labelTelefonoTres;
    private javax.swing.JComboBox labelTelefonoUno;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JButton nuevoServicio;
    private javax.swing.JLabel numeroServicio;
    private javax.swing.JTextField placas;
    private javax.swing.JTextField salida;
    private javax.swing.JTextField tiempo;
    private javax.swing.JTextField valorTelefonoDos;
    private javax.swing.JTextField valorTelefonoTres;
    private javax.swing.JTextField valorTelefonoUno;
    // End of variables declaration//GEN-END:variables
    //mis variables
    //mis metodos
    
//    public void seleccionServicio(int index) {
//        if (index >= 0) {
//            Servicio servicio = this.control.cargaServicio(index);
//            this.loadData(servicio);
//        }
//    }
//    
//    public void loadData(Servicio servicio) {
//        this.datos.removeAll();
//        this.numeroServicio.setText(servicio.getId());
//        this.descripcion.setText(servicio.getDescripcion());
//        this.contacto.setText(servicio.getContacto());
//        if (servicio.getTelefonoUno() != null) {
//            this.labelTelefonoUno.setSelectedItem(servicio.getTelefonoUno().getLabel());
//            this.valorTelefonoUno.setText(servicio.getTelefonoUno().getValor());
//        } else {
//            this.labelTelefonoUno.setSelectedIndex(0);
//            this.valorTelefonoUno.setText("");
//        }
//        if (servicio.getTelefonoDos() != null) {
//            this.labelTelefonoDos.setSelectedItem(servicio.getTelefonoDos().getLabel());
//            this.valorTelefonoDos.setText(servicio.getTelefonoDos().getValor());
//        } else {
//            this.labelTelefonoDos.setSelectedIndex(0);
//            this.valorTelefonoDos.setText("");
//        }
//        if (servicio.getTelefonoTres() != null) {
//            this.labelTelefonoTres.setSelectedItem(servicio.getTelefonoTres().getLabel());
//            this.valorTelefonoTres.setText(servicio.getTelefonoTres().getValor());
//        } else {
//            this.labelTelefonoTres.setSelectedIndex(0);
//            this.valorTelefonoTres.setText("");
//        }
//        javax.swing.JTabbedPane tabbedPane = new JTabbedPane();
//        //agrega tab con los datos del cliente
//        this.datosCliente = new DatosClienteView();
//        this.datosCliente.setControl(this.control);
//        this.datosCliente.setParent(this);
//        this.datosCliente.loadData(servicio.getIdCliente());
//        tabbedPane.add("Cliente", datosCliente);
//        //agrega un tab para los datos del auto
//        this.datosAuto = new DatosAutoView();
//        this.datosAuto.loadData(servicio.getDatosAuto());
//        if (servicio.getDatosAuto() != null) {
//            this.placas.setText(servicio.getDatosAuto().getPlacas());
//        } else {
//            this.placas.setText("");
//        }
//        tabbedPane.add("Auto",datosAuto);
//        //agrega tab con la bitacora
//        this.bitacora = new BitacoraView();
//        this.bitacora.loadData(servicio.getBitacora());
//        tabbedPane.add("Bitacora", bitacora);
//        //agrega tab panel a la ventana.
//        this.datos.add(tabbedPane);
//        buscarFechas(servicio);
//        this.updateUI();
//    }
//
//    public void actualizaDatosCliente() {
//        Cliente cliente = this.datosCliente.getCliente();
//        if (cliente != null) {
//            this.nombreCliente.setText(cliente.getNombre());
//        } else {
//            this.nombreCliente.setText("");
//        }
//    }
//
}
