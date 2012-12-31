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
import prototipo.modelo.Servicio;
import prototipo.modelo.bitacora.BitacoraMetaData;
import prototipo.modelo.cliente.Cliente;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

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
    @Qualifier(value="costosView")
    private ApplicationView costos;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private Servicio viewServicioModel;
    @Autowired
    private Cliente viewClienteModel;
    @Autowired
    private BitacoraMetaData bitacoraMetaData;
    
    //private boolean tabInited;
    private javax.swing.JTabbedPane tabDatos;
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.nuevoServicio(..)) || execution(* prototipo.control.WorkflowApp.cargaServicio(..))")  
    public void loadServicioPointCut() {
    }
    @AfterReturning("loadServicioPointCut()")
    public void loadServicio() {
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
            //agrega tab con costos
            tabDatos.add("Costos", costos);
            this.datos.add(this.tabDatos);
            //this.tabInited = true;
            
        } else {
            this.datos.removeAll();
        }
        this.updateUI();
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bitacora.iniciaVista();
        datosCliente.iniciaVista();
        datosAuto.iniciaVista();
        costos.iniciaVista();
        tabDatos = new javax.swing.JTabbedPane();
        bindComponents();
    }
    
    public void bindComponents() {
        //bindings con el servicio
        bindingManager.registerBind(viewServicioModel, "id",(Bindable)this.numeroServicio);
        bindingManager.registerBind(viewServicioModel.getDatosAuto(), "placas",(Bindable)this.placas);
        bindingManager.registerBind(viewServicioModel, "contacto", (Bindable)this.contacto);
        bindingManager.registerBind(viewServicioModel, "descripcion", (Bindable)this.descripcion);
        bindingManager.registerBind(viewServicioModel.getTelefonoUno(), "label", (Bindable)this.labelTelefonoUno);
        bindingManager.registerBind(viewServicioModel.getTelefonoUno(), "valor", (Bindable)this.valorTelefonoUno);
        bindingManager.registerBind(viewServicioModel.getTelefonoDos(), "label", (Bindable)this.labelTelefonoDos);
        bindingManager.registerBind(viewServicioModel.getTelefonoDos(), "valor", (Bindable)this.valorTelefonoDos);
        bindingManager.registerBind(viewServicioModel.getTelefonoTres(), "label", (Bindable)this.labelTelefonoTres);
        bindingManager.registerBind(viewServicioModel.getTelefonoTres(), "valor", (Bindable)this.valorTelefonoTres);
        bindingManager.registerBind(viewServicioModel.getBitacora(), "eventos", (Bindable)this.bitacora);
        //bindings con el cliente
        bindingManager.registerBind(viewClienteModel, "nombre",(Bindable)this.nombreCliente);
        //bindings con el metadata de la bitacora
        bindingManager.registerBind(bitacoraMetaData, "fechaEntrada", (Bindable)this.ingreso);
        bindingManager.registerBind(bitacoraMetaData, "fechaSalidaAuto", (Bindable)this.salida);
        bindingManager.registerBind(bitacoraMetaData, "tiempoEstadia", (Bindable)this.tiempo);
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
        jLabel5 = new javax.swing.JLabel();
        tiempo = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        contacto = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel9 = new javax.swing.JLabel();
        labelTelefonoUno = new prototipo.view.binding.SimpleBindableJComboBox();
        valorTelefonoUno = new prototipo.view.binding.SimpleBindableJTextField();
        labelTelefonoDos = new prototipo.view.binding.SimpleBindableJComboBox();
        valorTelefonoDos = new prototipo.view.binding.SimpleBindableJTextField();
        labelTelefonoTres = new prototipo.view.binding.SimpleBindableJComboBox();
        valorTelefonoTres = new prototipo.view.binding.SimpleBindableJTextField();
        nombreCliente = new prototipo.view.binding.SimpleBindableJTextField();
        placas = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new prototipo.view.binding.SimpleBindableJTextArea();
        numeroServicio = new prototipo.view.binding.SimpleBindableJLabel();
        ingreso = new prototipo.view.binding.SimpleBindableJTextField();
        salida = new prototipo.view.binding.SimpleBindableJTextField();
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

        jLabel5.setText("Fecha Ingreso:");

        tiempo.setEditable(false);

        jLabel6.setText("Tiempo:");

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

        ingreso.setEditable(false);

        salida.setEditable(false);

        javax.swing.GroupLayout datosGeneralesLayout = new javax.swing.GroupLayout(datosGenerales);
        datosGenerales.setLayout(datosGeneralesLayout);
        datosGeneralesLayout.setHorizontalGroup(
            datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosGeneralesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCliente)
                    .addComponent(tiempo)
                    .addComponent(contacto)
                    .addComponent(placas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(datosGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ingreso)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(salida))
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
        this.aplication.guardaServicio();
    }//GEN-LAST:event_guardarServicioActionPerformed

    private void buscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarServicioActionPerformed
        BusquedaServicioView dialog = new BusquedaServicioView(null, true, this.aplication);
        dialog.setVisible(true);
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

}
