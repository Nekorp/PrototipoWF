/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.control.ControlCliente;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.cliente.ClienteVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosClienteView;
import org.nekorp.workflow.desktop.view.model.validacion.EstatusValidacion;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionCliente;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralCliente;
import org.nekorp.workflow.desktop.view.resource.IconProvider;
import org.nekorp.workflow.desktop.view.resource.busqueda.ClienteSearchJListModel;
import org.nekorp.workflow.desktop.view.resource.imp.DocumentSizeValidator;
import org.nekorp.workflow.desktop.view.resource.imp.DocumentSizeValidatorMayusculas;
import technology.tikal.customers.model.ClienteMxPojo;
import technology.tikal.customers.model.Customer;
/**
 *
 * @author Nekorp
 */
//@Aspect
//@Component("datosClienteView")
public class DatosClienteView extends ApplicationView {
    /**
     * el supuesto size de cada renglon en la lista de busqueda de clientes.
     */
    private int renglonSearchSize;
    /**
     * el numero de renglones visibles en la lista de busqueda.
     */
    private int renglonesVisiblesSearch;
    /**
     * por que no...
     * para que no se activen los scrolls
     */
    private int constanteUniversalDeAjuste;
    private ControlCliente aplication;
    private javax.swing.JFrame mainFrame;
    private BindingManager<Bindable> bindingManager;
    private ClienteVB viewClienteModel;
    //private EdicionServicioMetadata servicioMetaData;
    private ValidacionCliente validacionCliente;
    private ValidacionGeneralCliente validacionGeneralCliente;
    private ClienteSearchJListModel searchModel;
    private boolean activo;
    private IconProvider iconProvider;
    private String searchIconRaw;
    private String cancelSearchIconRaw;
    private PermisosClienteView permisos;
    
    public DatosClienteView() {        
        //activo = true;
    }
    
    //@Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.loadCliente(..))"
    //    + " || execution(* org.nekorp.workflow.desktop.control.WorkflowApp.nuevoCliente(..))")  
    public void loadClientePointCut() {
    }
    
    //@Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.unloadCliente(..))")  
    public void unloadClientePointCut() {
    }
    
    //@AfterReturning("loadClientePointCut()")
    public void loadCliente() {
        this.setEditableStatus(true);
    }
    
    //@AfterReturning("unloadClientePointCut()")
    public void unloadCliente() {
        //this.setEditableStatus(false);
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        activo = value;
        if (!activo) {
            searchScroll.setVisible(value);
        }
        this.nombreCliente.setEnabled(value);
        this.cancelIcon.setVisible(value);
        this.rfcCliente.setEnabled(value);
        this.calleCliente.setEnabled(value);
        this.numeroCasaCliente.setEnabled(value);
        this.codigoPostalCliente.setEnabled(value);
        this.coloniaCliente.setEnabled(value);
        this.ciudadCliente.setEnabled(value);
        
        this.contacto.setEnabled(value);
        this.email.setEnabled(value);
        this.labelTelefonoUno.setEnabled(value);
        this.valorTelefonoUno.setEnabled(value);
        this.labelTelefonoDos.setEnabled(value);
        this.valorTelefonoDos.setEnabled(value);
        this.labelTelefonoTres.setEnabled(value);
        this.valorTelefonoTres.setEnabled(value);
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
                actualizarNombreCliente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarNombreCliente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarNombreCliente();
            }
        });
        this.cancelIcon.add(iconProvider.getIcon(cancelSearchIconRaw));
        this.searchIcon.add(iconProvider.getIcon(searchIconRaw));
    }
    
    private void actualizarNombreCliente() {
        if (!activo) {
            return;
        }
        aplication.buscarCliente(nombreCliente.getText(), new Callback<Customer[]>() {
            @Override
            public void execute(Customer[] param) {
                actualizaListaSearch(param);
            }
        });
    }
    
    private void actualizaListaSearch(Customer[] data) {
        searchModel.updateData(data);
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
        if (searchScroll.getVerticalScrollBar().isShowing()) {
            searchScroll.setSize(new Dimension(this.wrapperSearch.getWidth() - searchScroll.getVerticalScrollBar().getWidth(), nuevaAltura));
        } else {
            searchScroll.setSize(new Dimension(this.wrapperSearch.getWidth(), nuevaAltura));
        }
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
        bindingManager.registerBind(viewClienteModel, "rfc",this.rfcCliente.getTextField());
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "calle", this.calleCliente.getTextField());
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "numInterior", this.numeroCasaCliente.getTextField());
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "codigoPostal", this.codigoPostalCliente.getTextField());
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "colonia", this.coloniaCliente.getTextField());
        bindingManager.registerBind(viewClienteModel.getDomicilio(), "ciudad", this.ciudadCliente.getTextField());
        
        bindingManager.registerBind(viewClienteModel, "contacto", this.contacto.getTextField());
        bindingManager.registerBind(viewClienteModel, "email", this.email.getTextField());
        bindingManager.registerBind(viewClienteModel.getTelefonoUno(), "label", (Bindable)this.labelTelefonoUno);
        bindingManager.registerBind(viewClienteModel.getTelefonoUno(), "valor", this.valorTelefonoUno.getTextField());
        bindingManager.registerBind(viewClienteModel.getTelefonoDos(), "label", (Bindable)this.labelTelefonoDos);
        bindingManager.registerBind(viewClienteModel.getTelefonoDos(), "valor", this.valorTelefonoDos.getTextField());
        bindingManager.registerBind(viewClienteModel.getTelefonoTres(), "label", (Bindable)this.labelTelefonoTres);
        bindingManager.registerBind(viewClienteModel.getTelefonoTres(), "valor", this.valorTelefonoTres.getTextField());
        
        //binding de validaciones
        bindingManager.registerBind(validacionCliente, "nombreOk", new ReadOnlyBinding(){
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                EstatusValidacion statusValidacion = (EstatusValidacion) value;
                if (StringUtils.isEmpty(statusValidacion.getDetalle())) {
                    nombreCliente.setToolTipText(null);
                } else {
                    nombreCliente.setToolTipText(statusValidacion.getDetalle());
                }
            }
        });
        bindingManager.registerBind(validacionCliente, "nombreOk", this.wrapperSearch);
        bindingManager.registerBind(validacionCliente, "rfcOk", this.rfcCliente);
        bindingManager.registerBind(validacionCliente, "calleOk", this.calleCliente);
        bindingManager.registerBind(validacionCliente, "numInteriorOk", this.numeroCasaCliente);
        bindingManager.registerBind(validacionCliente, "codigoPostalOk", this.codigoPostalCliente);
        bindingManager.registerBind(validacionCliente, "coloniaOk", this.coloniaCliente);
        bindingManager.registerBind(validacionCliente, "ciudadOk", this.ciudadCliente);
        bindingManager.registerBind(validacionCliente, "contactoOk", this.contacto);
        bindingManager.registerBind(validacionCliente, "emailOk", this.email);
        bindingManager.registerBind(validacionCliente, "telefonoUnoOk", this.valorTelefonoUno);
        bindingManager.registerBind(validacionCliente, "telefonoDosOk", this.valorTelefonoDos);
        bindingManager.registerBind(validacionCliente, "telefonoTresOk", this.valorTelefonoTres);
        
        //permisos
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "puedeEditar", permisosBind);
        this.wrapperSearch.getTextField().setText("");
        this.wrapperSearch.setEnabled(false);
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return this.validacionGeneralCliente;
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
        numeroCliente = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJLabel();
        jLabel2 = new javax.swing.JLabel();
        searchIcon = new javax.swing.JPanel();
        cancelIcon = new javax.swing.JPanel();
        nombreCliente = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        ((javax.swing.text.AbstractDocument)this.nombreCliente.getDocument()).setDocumentFilter(new DocumentSizeValidator(83));
        wrapperSearch = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        searchScroll = new javax.swing.JScrollPane();
        search = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        rfcCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.rfcCliente.setDocumentFilter(new DocumentSizeValidatorMayusculas(13));
        domicioFiscal = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        calleCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.calleCliente.setDocumentFilter(new DocumentSizeValidator(100));
        jLabel5 = new javax.swing.JLabel();
        numeroCasaCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.numeroCasaCliente.setDocumentFilter(new DocumentSizeValidator(50));
        jLabel6 = new javax.swing.JLabel();
        codigoPostalCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.codigoPostalCliente.setDocumentFilter(new DocumentSizeValidator(50));
        jLabel7 = new javax.swing.JLabel();
        coloniaCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.coloniaCliente.setDocumentFilter(new DocumentSizeValidator(50));
        jLabel8 = new javax.swing.JLabel();
        ciudadCliente = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.ciudadCliente.setDocumentFilter(new DocumentSizeValidator(50));
        datosContacto = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        contacto = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.contacto.setDocumentFilter(new DocumentSizeValidator(83));
        emailLabel = new javax.swing.JLabel();
        email = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.email.setDocumentFilter(new DocumentSizeValidator(40));
        jLabel10 = new javax.swing.JLabel();
        labelTelefonoUno = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJComboBox();
        valorTelefonoUno = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.valorTelefonoUno.setDocumentFilter(new DocumentSizeValidator(40));
        labelTelefonoDos = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJComboBox();
        valorTelefonoDos = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.valorTelefonoDos.setDocumentFilter(new DocumentSizeValidator(40));
        labelTelefonoTres = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJComboBox();
        valorTelefonoTres = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.valorTelefonoTres.setDocumentFilter(new DocumentSizeValidator(40));

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Número de cliente");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(10, 10, 110, 14);

        numeroCliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroCliente.setText(" ");
        jLayeredPane1.add(numeroCliente);
        numeroCliente.setBounds(130, 10, 250, 14);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nombre o razón social");
        jLayeredPane1.add(jLabel2);
        jLabel2.setBounds(10, 40, 110, 14);

        searchIcon.setOpaque(false);
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });
        searchIcon.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(searchIcon);
        searchIcon.setBounds(130, 42, 16, 16);

        cancelIcon.setOpaque(false);
        cancelIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelIconMouseClicked(evt);
            }
        });
        cancelIcon.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(cancelIcon);
        cancelIcon.setBounds(588, 42, 16, 16);

        nombreCliente.setBackground(new java.awt.Color(224, 230, 230));
        nombreCliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        nombreCliente.setBorder(null);
        nombreCliente.setDisabledTextColor(new java.awt.Color(100, 100, 100));
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
        jLayeredPane1.add(nombreCliente);
        nombreCliente.setBounds(148, 42, 438, 16);

        wrapperSearch.setFocusable(false);
        jLayeredPane1.add(wrapperSearch);
        wrapperSearch.setBounds(130, 38, 458, 24);

        searchScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        search.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        searchScroll.setViewportView(search);

        jLayeredPane1.add(searchScroll);
        searchScroll.setBounds(130, 62, 448, 0);
        jLayeredPane1.setLayer(searchScroll, javax.swing.JLayeredPane.POPUP_LAYER);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("RFC");
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(100, 70, 20, 14);
        jLayeredPane1.add(rfcCliente);
        rfcCliente.setBounds(130, 70, 254, 24);

        domicioFiscal.setBackground(new java.awt.Color(255, 255, 255));
        domicioFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Domicilio Fiscal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Calle");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Num. Interior");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Código Postal");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Colonia");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("Ciudad");

        javax.swing.GroupLayout domicioFiscalLayout = new javax.swing.GroupLayout(domicioFiscal);
        domicioFiscal.setLayout(domicioFiscalLayout);
        domicioFiscalLayout.setHorizontalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, domicioFiscalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ciudadCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(coloniaCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codigoPostalCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numeroCasaCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(calleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        domicioFiscalLayout.setVerticalGroup(
            domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(domicioFiscalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(calleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(numeroCasaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(codigoPostalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(coloniaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(domicioFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(ciudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLayeredPane1.add(domicioFiscal);
        domicioFiscal.setBounds(10, 100, 620, 190);

        datosContacto.setBackground(new java.awt.Color(255, 255, 255));
        datosContacto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Datos de Contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setText("Contacto");

        emailLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        emailLabel.setText("E-Mail");

        jLabel10.setText("Telefono");

        labelTelefonoUno.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        labelTelefonoUno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));
        labelTelefonoUno.setBorder(null);

        labelTelefonoDos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        labelTelefonoDos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));
        labelTelefonoDos.setBorder(null);

        labelTelefonoTres.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        labelTelefonoTres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casa", "Oficina", "Movil", "Radio" }));
        labelTelefonoTres.setBorder(null);

        javax.swing.GroupLayout datosContactoLayout = new javax.swing.GroupLayout(datosContacto);
        datosContacto.setLayout(datosContactoLayout);
        datosContactoLayout.setHorizontalGroup(
            datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosContactoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosContactoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(datosContactoLayout.createSequentialGroup()
                                .addComponent(labelTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valorTelefonoUno, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                            .addGroup(datosContactoLayout.createSequentialGroup()
                                .addComponent(labelTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valorTelefonoTres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(datosContactoLayout.createSequentialGroup()
                                .addComponent(labelTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valorTelefonoDos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(317, 317, 317))
                    .addGroup(datosContactoLayout.createSequentialGroup()
                        .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailLabel)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(datosContactoLayout.createSequentialGroup()
                                .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(datosContactoLayout.createSequentialGroup()
                                .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(317, 317, 317))))))
        );
        datosContactoLayout.setVerticalGroup(
            datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(valorTelefonoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(datosContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTelefonoTres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.add(datosContacto);
        datosContacto.setBounds(10, 300, 620, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        //TODO responder algunas preguntas primero
        //si habra clientes con los mismos nombres por ejemplo
        if(evt.getOppositeComponent() != this.search) {
            this.searchScroll.setVisible(false);
        }
        //        if(evt.getOppositeComponent() != this.search && evt.getOppositeComponent() == this.rfcCliente) {
//            this.searchScroll.setVisible(false);
//            if (!recienCargado) {
//                recienCargado = true;
//                Cliente cliente = this.aplication.loadByName(this.nombreCliente.getText());
//                int n = javax.swing.JOptionPane.showConfirmDialog(
//                    mainFrame,
//                    "El cliente " + this.nombreCliente.getText() + " ya existe\n"
//                    + "¿Desea cargar sus datos?",
//                    "Cliente existente",
//                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
//                if (n == javax.swing.JOptionPane.YES_OPTION) {
//                    updateIgnoreCount = 2;
//                    this.aplication.loadCliente(cliente);
//                }
//                if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
//                    recienCargado = true;
//                }
//                this.actualizaListaSearch(new LinkedList<Cliente>());
//            }
//        }
    }//GEN-LAST:event_nombreClienteFocusLost

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        if (!search.isSelectionEmpty()) {
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            this.aplication.loadCliente(this.searchModel.getClientAt(this.search.getSelectedIndex()));
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            this.actualizaListaSearch(new Customer[0]);
            activo = true;
        }
    }//GEN-LAST:event_searchMouseClicked

    private void nombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreClienteActionPerformed
        if (!search.isSelectionEmpty()) {
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            this.aplication.loadCliente(this.searchModel.getClientAt(this.search.getSelectedIndex()));
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            this.actualizaListaSearch(new Customer[0]);
            activo = true;
        }
    }//GEN-LAST:event_nombreClienteActionPerformed

    private void cancelIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelIconMouseClicked
        this.aplication.loadCliente(new ClienteMxPojo());
    }//GEN-LAST:event_cancelIconMouseClicked

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked
        if (activo) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            BusquedaClienteView dialog = new BusquedaClienteView(mainFrame, true, aplication);
            dialog.validate();
            dialog.pack();
            dialog.setLocationRelativeTo(mainFrame);
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            dialog.setVisible(true);
            activo = true;
        }
    }//GEN-LAST:event_searchIconMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation calleCliente;
    private javax.swing.JPanel cancelIcon;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation ciudadCliente;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation codigoPostalCliente;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation coloniaCliente;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation contacto;
    private javax.swing.JPanel datosContacto;
    private javax.swing.JPanel domicioFiscal;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation email;
    private javax.swing.JLabel emailLabel;
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
    private javax.swing.JComboBox labelTelefonoDos;
    private javax.swing.JComboBox labelTelefonoTres;
    private javax.swing.JComboBox labelTelefonoUno;
    private javax.swing.JTextField nombreCliente;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation numeroCasaCliente;
    private javax.swing.JLabel numeroCliente;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation rfcCliente;
    private javax.swing.JList search;
    private javax.swing.JPanel searchIcon;
    private javax.swing.JScrollPane searchScroll;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation valorTelefonoDos;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation valorTelefonoTres;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation valorTelefonoUno;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation wrapperSearch;
    // End of variables declaration//GEN-END:variables

    public void setRenglonSearchSize(int renglonSearchSize) {
        this.renglonSearchSize = renglonSearchSize;
    }

    public void setRenglonesVisiblesSearch(int renglonesVisiblesSearch) {
        this.renglonesVisiblesSearch = renglonesVisiblesSearch;
    }

    public void setConstanteUniversalDeAjuste(int constanteUniversalDeAjuste) {
        this.constanteUniversalDeAjuste = constanteUniversalDeAjuste;
    }

    public void setAplication(ControlCliente aplication) {
        this.aplication = aplication;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setBindingManager(BindingManager<Bindable> bindingManager) {
        this.bindingManager = bindingManager;
    }

    public void setViewClienteModel(ClienteVB viewClienteModel) {
        this.viewClienteModel = viewClienteModel;
    }

    public void setValidacionCliente(ValidacionCliente validacionCliente) {
        this.validacionCliente = validacionCliente;
    }

    public void setValidacionGeneralCliente(ValidacionGeneralCliente validacionGeneralCliente) {
        this.validacionGeneralCliente = validacionGeneralCliente;
    }

    public void setIconProvider(IconProvider iconProvider) {
        this.iconProvider = iconProvider;
    }

    public void setSearchIconRaw(String searchIconRaw) {
        this.searchIconRaw = searchIconRaw;
    }

    public void setCancelSearchIconRaw(String cancelSearchIconRaw) {
        this.cancelSearchIconRaw = cancelSearchIconRaw;
    }

    public void setPermisos(PermisosClienteView permisos) {
        this.permisos = permisos;
    }
}
