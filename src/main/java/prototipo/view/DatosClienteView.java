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

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.EdicionServicioMetadata;
import prototipo.view.model.cliente.ClienteVB;
import prototipo.view.resource.imp.ClienteSearchJListModel;
import prototipo.view.service.IconProvider;

/**
 *
 * 
 */
@Component("datosClienteView")
@Aspect
public class DatosClienteView extends ApplicationView {
    /**
     * el supuesto size de cada renglon en la lista de busqueda de clientes.
     */
    private int renglonSearchSize = 16;
    /**
     * el numero de renglones visibles en la lista de busqueda.
     */
    @Value("#{appConfig['app.view.cliente.searchVisibleSize']}")
    private int renglonesVisiblesSearch;
    /**
     * por que no...
     * para que no se activen los scrolls
     */
    private int constanteUniversalDeAjuste = 2;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    javax.swing.JFrame mainFrame;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ClienteVB viewClienteModel;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    
    private ClienteSearchJListModel searchModel;
    
    private boolean reacienCargado;
    
    @Autowired
    private IconProvider iconProvider;
    
    public DatosClienteView() {        
    }
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.loadCliente(..)) || execution(* prototipo.control.WorkflowApp.nuevoCliente(..))")  
    public void loadClientePointCut() {
    }
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.unloadCliente(..))")  
    public void unloadClientePointCut() {
    }
    
    @AfterReturning("loadClientePointCut()")
    public void loadCliente() {
        this.setEditableStatus(true);
    }
    
    @AfterReturning("unloadClientePointCut()")
    public void unloadCliente() {
        //this.setEditableStatus(false);
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        this.nombreCliente.setEditable(value);
        this.rfcCliente.setEditable(value);
        this.calleCliente.setEditable(value);
        this.numeroCasaCliente.setEditable(value);
        this.codigoPostalCliente.setEditable(value);
        this.coloniaCliente.setEditable(value);
        this.ciudadCliente.setEditable(value);
        
        this.contacto.setEditable(value);
        this.labelTelefonoUno.setEnabled(value);
        this.valorTelefonoUno.setEditable(value);
        this.labelTelefonoDos.setEnabled(value);
        this.valorTelefonoDos.setEditable(value);
        this.labelTelefonoTres.setEnabled(value);
        this.valorTelefonoTres.setEditable(value);
    }
    
    @Override
    public void iniciaVista() {
        initComponents();
        bindComponents();
        searchModel = new ClienteSearchJListModel();        
        search.setModel(searchModel);
        //esto resuelve el problema de regresar el foco a la caja de texto.
        search.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(search.hasFocus()) {
                    nombreCliente.requestFocus();
                }
            }
        });
        this.nombreCliente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizaListaSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizaListaSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizaListaSearch();
            }
        });
        this.cancelIcon.add(iconProvider.getIcon("close-16x16.png"));
        this.searchIcon.add(iconProvider.getIcon("find-16x16.png"));
    }
    
    private void actualizaListaSearch() {
        reacienCargado = false;
        searchModel.updateData(aplication.buscarCliente(nombreCliente.getText()));
        search.removeSelectionInterval(search.getSelectedIndex(), search.getSelectedIndex());
        if (!searchScroll.isVisible() && this.nombreCliente.hasFocus()) {
            searchScroll.setVisible(true);
        }
        int nuevaAltura = renglonSearchSize * search.getModel().getSize();
        if (nuevaAltura > 0) {
            nuevaAltura = nuevaAltura + constanteUniversalDeAjuste;
        }
        if (nuevaAltura > renglonSearchSize * renglonesVisiblesSearch + constanteUniversalDeAjuste) {
            nuevaAltura = renglonSearchSize * renglonesVisiblesSearch + constanteUniversalDeAjuste;
        }
        searchScroll.setSize(new Dimension(250,nuevaAltura));
    }
    
    private void calculaNuevaPosicionScroll(int indexSeleccion, int verticalScrollValue) {
        int diferencia = (indexSeleccion * renglonSearchSize) - searchScroll.getVerticalScrollBar().getValue();
        if (diferencia < renglonSearchSize * renglonesVisiblesSearch && diferencia > 0) {
            return; //si aun esta en una zona visible
        }
        this.searchScroll.getVerticalScrollBar().setValue(verticalScrollValue);
    }
    
    private void calculaNuevaPosicionScrollDown(int indexSeleccion) {
        int verticalScrollValue = (indexSeleccion - renglonesVisiblesSearch + 1) * renglonSearchSize;
        if (verticalScrollValue < this.searchScroll.getVerticalScrollBar().getMinimum()) {
            verticalScrollValue = this.searchScroll.getVerticalScrollBar().getMinimum();
        }
        if (verticalScrollValue > this.searchScroll.getVerticalScrollBar().getMaximum()) {
            verticalScrollValue = this.searchScroll.getVerticalScrollBar().getMaximum();
        }
        calculaNuevaPosicionScroll(indexSeleccion, verticalScrollValue);
    }
    
    private void calculaNuevaPosicionScrollUp(int indexSeleccion) {
        int verticalScrollValue = indexSeleccion * renglonSearchSize;
        if (verticalScrollValue < this.searchScroll.getVerticalScrollBar().getMinimum()) {
            verticalScrollValue = this.searchScroll.getVerticalScrollBar().getMinimum();
        }
        if (verticalScrollValue > this.searchScroll.getVerticalScrollBar().getMaximum()) {
            verticalScrollValue = this.searchScroll.getVerticalScrollBar().getMaximum();
        }
        calculaNuevaPosicionScroll(indexSeleccion, verticalScrollValue);
    }
    
    private void bindComponents() {
        bindingManager.registerBind(viewClienteModel, "id",(Bindable)this.numeroCliente);
        bindingManager.registerBind(viewClienteModel, "nombre",(Bindable)this.nombreCliente);
        bindingManager.registerBind(viewClienteModel, "rfc",(Bindable)this.rfcCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "calle",(Bindable)this.calleCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "numInterior",(Bindable)this.numeroCasaCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "codigoPostal",(Bindable)this.codigoPostalCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "colonia",(Bindable)this.coloniaCliente);
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "ciudad",(Bindable)this.ciudadCliente);
        
        bindingManager.registerBind(viewClienteModel, "contacto", (Bindable)this.contacto);
        bindingManager.registerBind(viewClienteModel.getTelefonoUno(), "label", (Bindable)this.labelTelefonoUno);
        bindingManager.registerBind(viewClienteModel.getTelefonoUno(), "valor", (Bindable)this.valorTelefonoUno);
        bindingManager.registerBind(viewClienteModel.getTelefonoDos(), "label", (Bindable)this.labelTelefonoDos);
        bindingManager.registerBind(viewClienteModel.getTelefonoDos(), "valor", (Bindable)this.valorTelefonoDos);
        bindingManager.registerBind(viewClienteModel.getTelefonoTres(), "label", (Bindable)this.labelTelefonoTres);
        bindingManager.registerBind(viewClienteModel.getTelefonoTres(), "valor", (Bindable)this.valorTelefonoTres);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        numeroCliente = new prototipo.view.binding.SimpleBindableJLabel();
        jLabel2 = new javax.swing.JLabel();
        searchIcon = new javax.swing.JPanel();
        cancelIcon = new javax.swing.JPanel();
        nombreCliente = new prototipo.view.binding.SimpleBindableJTextField();
        wrapperSearch = new javax.swing.JTextField();
        searchScroll = new javax.swing.JScrollPane();
        search = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        rfcCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jPanel1 = new javax.swing.JPanel();
        contacto = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelTelefonoUno = new prototipo.view.binding.SimpleBindableJComboBox();
        valorTelefonoUno = new prototipo.view.binding.SimpleBindableJTextField();
        labelTelefonoDos = new prototipo.view.binding.SimpleBindableJComboBox();
        labelTelefonoTres = new prototipo.view.binding.SimpleBindableJComboBox();
        valorTelefonoDos = new prototipo.view.binding.SimpleBindableJTextField();
        valorTelefonoTres = new prototipo.view.binding.SimpleBindableJTextField();
        domicioFiscal = new javax.swing.JPanel();
        calleCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel4 = new javax.swing.JLabel();
        numeroCasaCliente = new prototipo.view.binding.SimpleBindableJTextField();
        codigoPostalCliente = new prototipo.view.binding.SimpleBindableJTextField();
        coloniaCliente = new prototipo.view.binding.SimpleBindableJTextField();
        ciudadCliente = new prototipo.view.binding.SimpleBindableJTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setText("Número de Cliente:");
        jLabel1.setBounds(10, 20, 92, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        numeroCliente.setText(" ");
        numeroCliente.setBounds(110, 20, 250, 14);
        jLayeredPane1.add(numeroCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Nombre o razón social:");
        jLabel2.setBounds(10, 40, 109, 14);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        searchIcon.setOpaque(false);
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });
        searchIcon.setLayout(new java.awt.BorderLayout());
        searchIcon.setBounds(346, 42, 16, 16);
        jLayeredPane1.add(searchIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cancelIcon.setOpaque(false);
        cancelIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelIconMouseClicked(evt);
            }
        });
        cancelIcon.setLayout(new java.awt.BorderLayout());
        cancelIcon.setBounds(362, 42, 16, 16);
        jLayeredPane1.add(cancelIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);

        nombreCliente.setBorder(null);
        nombreCliente.setNextFocusableComponent(rfcCliente);
        nombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreClienteActionPerformed(evt);
            }
        });
        nombreCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreClienteFocusLost(evt);
            }
        });
        nombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreClienteKeyPressed(evt);
            }
        });
        nombreCliente.setBounds(131, 42, 216, 16);
        jLayeredPane1.add(nombreCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        wrapperSearch.setText("jTextField1");
        wrapperSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wrapperSearchFocusGained(evt);
            }
        });
        wrapperSearch.setBounds(130, 40, 250, 20);
        jLayeredPane1.add(wrapperSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);

        searchScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        searchScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        searchScroll.setViewportView(search);

        searchScroll.setBounds(130, 60, 250, 0);
        jLayeredPane1.add(searchScroll, javax.swing.JLayeredPane.POPUP_LAYER);

        jLabel3.setText("RFC:");
        jLabel3.setBounds(10, 70, 24, 14);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rfcCliente.setBounds(130, 70, 250, 20);
        jLayeredPane1.add(rfcCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Contacto"));

        jLabel9.setText("Contacto:");

        jLabel10.setText("Telefono:");

        labelTelefonoUno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        labelTelefonoDos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        labelTelefonoTres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorTelefonoTres)
                            .addComponent(valorTelefonoDos)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(valorTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(valorTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBounds(0, 290, 450, 147);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        domicioFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Fiscal"));

        jLabel4.setText("Calle:");

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
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(coloniaCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(codigoPostalCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calleCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciudadCliente))
                .addContainerGap(105, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        domicioFiscal.setBounds(0, 100, 450, 178);
        jLayeredPane1.add(domicioFiscal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (this.search.getModel().getSize() > 0) {
                if (this.search.isSelectionEmpty()) { //si no hay nada seleccionado
                    int nuevoIndex = this.search.getModel().getSize() - 1;
                    this.search.setSelectedIndex(nuevoIndex);
                    calculaNuevaPosicionScrollUp(nuevoIndex);
                } else {
                    int nuevoIndex = this.search.getSelectedIndex() - 1;
                    if (nuevoIndex < 0) {
                        nuevoIndex = this.search.getModel().getSize() - 1;
                    }
                    this.search.setSelectedIndex(nuevoIndex);
                    calculaNuevaPosicionScrollUp(nuevoIndex);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.search.getModel().getSize() > 0) {
                if (this.search.isSelectionEmpty()) { //si no hay nada seleccionado
                    this.search.setSelectedIndex(0);
                    calculaNuevaPosicionScrollDown(0);
                } else {
                    int nuevoIndex = this.search.getSelectedIndex() + 1;
                    if (nuevoIndex > this.search.getModel().getSize() - 1) {
                        nuevoIndex = 0;
                    }
                    this.search.setSelectedIndex(nuevoIndex);
                    calculaNuevaPosicionScrollDown(nuevoIndex);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.searchScroll.setVisible(false);
        }
    }//GEN-LAST:event_nombreClienteKeyPressed

    private void nombreClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreClienteFocusLost
        if(evt.getOppositeComponent() != this.search) {
            this.searchScroll.setVisible(false);
            if (!reacienCargado) {
                this.aplication.loadByName(this.nombreCliente.getText());
            }
        }
    }//GEN-LAST:event_nombreClienteFocusLost

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        if (!search.isSelectionEmpty()) {
            this.aplication.loadCliente(this.searchModel.getClientAt(this.search.getSelectedIndex()));
            reacienCargado = true;
        }
    }//GEN-LAST:event_searchMouseClicked

    private void nombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreClienteActionPerformed
        if (!search.isSelectionEmpty()) {
            this.aplication.loadCliente(this.searchModel.getClientAt(this.search.getSelectedIndex()));
            reacienCargado = true;
        }
    }//GEN-LAST:event_nombreClienteActionPerformed

    private void cancelIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelIconMouseClicked
        this.aplication.unloadCliente();
    }//GEN-LAST:event_cancelIconMouseClicked

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked
        BusquedaClienteView dialog = new BusquedaClienteView(mainFrame, true, aplication);
        dialog.setVisible(true);
    }//GEN-LAST:event_searchIconMouseClicked

    private void wrapperSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wrapperSearchFocusGained
        this.nombreCliente.requestFocus();
    }//GEN-LAST:event_wrapperSearchFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField calleCliente;
    private javax.swing.JPanel cancelIcon;
    private javax.swing.JTextField ciudadCliente;
    private javax.swing.JTextField codigoPostalCliente;
    private javax.swing.JTextField coloniaCliente;
    private javax.swing.JTextField contacto;
    private javax.swing.JPanel domicioFiscal;
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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox labelTelefonoDos;
    private javax.swing.JComboBox labelTelefonoTres;
    private javax.swing.JComboBox labelTelefonoUno;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JTextField numeroCasaCliente;
    private javax.swing.JLabel numeroCliente;
    private javax.swing.JTextField rfcCliente;
    private javax.swing.JList search;
    private javax.swing.JPanel searchIcon;
    private javax.swing.JScrollPane searchScroll;
    private javax.swing.JTextField valorTelefonoDos;
    private javax.swing.JTextField valorTelefonoTres;
    private javax.swing.JTextField valorTelefonoUno;
    private javax.swing.JTextField wrapperSearch;
    // End of variables declaration//GEN-END:variables
    
}
