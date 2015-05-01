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


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.nekorp.workflow.desktop.control.ControlAuto;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindableListModel;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.auto.TipoElevadorVB;
import org.nekorp.workflow.desktop.view.model.auto.TipoTransmisionVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosAutoView;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.EstatusValidacion;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionDatosAuto;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralDatosAuto;
import org.nekorp.workflow.desktop.view.resource.IconProvider;
import org.nekorp.workflow.desktop.view.resource.imp.AutoSearchJListModel;
import org.nekorp.workflow.desktop.view.resource.imp.DocumentSizeValidator;
import org.nekorp.workflow.desktop.view.resource.imp.DocumentSizeValidatorMayusculas;
import org.springframework.util.StringUtils;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

/**
 *
 * @author Nekorp
 */
public class DatosAutoView extends ApplicationView {
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
    private AutoSearchJListModel searchModel;
    private ControlAuto aplication;
    private boolean activo;
    private javax.swing.JFrame mainFrame;
    private BindingManager<Bindable> bindingManager;
    private ServicioVB viewServicioModel;
    private ValidacionDatosAuto validacionDatosAuto;
    private ValidacionGeneralDatosAuto validacionGeneralDatosAuto;
    private IconProvider iconProvider;
    private String searchIconRaw;
    private String cancelSearchIconRaw;
    private BindableListModel<String> modelEquipoAdicional;
    private PermisosAutoView permisos;
    
    @Override
    public void setEditableStatus(boolean value) {
        activo = value;
        if (!activo) {
            searchScroll.setVisible(value);
        }
        this.cancelIcon.setVisible(value);
        this.marca.setEnabled(value);
        this.version.setEnabled(value);
        this.modelo.setEnabled(value);
        this.placas.setEnabled(value);
        this.tipo.setEnabled(value);
        this.numeroSerie.setEnabled(value);
        this.color.setEnabled(value);
        this.kilometraje.setEnabled(value);
        this.descripcionServicio.setEnabled(value);
        this.combustible.setEnabled(value);
        this.combustibleSlide.setEnabled(value);
        this.estandar.setEnabled(value);
        this.automatico.setEnabled(value);
        this.manuales.setEnabled(value);
        this.electrico.setEnabled(value);
        this.bolsasDeAire.setEnabled(value);
        this.aireAcondicionado.setEnabled(value);
        this.agregarEquipoAdicional.setEnabled(value);
        this.borrarEquipoAdicional.setEnabled(value);
        this.equipoAdicional.setEnabled(value);
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
        searchModel = new AutoSearchJListModel();        
        search.setModel(searchModel);
        //esto resuelve el problema de regresar el foco a la caja de texto.
        search.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(search.hasFocus()) {
                    numeroSerie.requestFocus();
                }
            }
        });
        this.numeroSerie.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarNumeroSerie();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarNumeroSerie();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarNumeroSerie();
            }
        });
        this.cancelIcon.add(iconProvider.getIcon(cancelSearchIconRaw));
        this.searchIcon.add(iconProvider.getIcon(searchIconRaw));
    }
    
    private void actualizarNumeroSerie() {
        if (!activo) {
            return;
        }
        aplication.buscarAuto(numeroSerie.getText(), new Callback<List<ServicioIndexAutoData>>() {
            @Override
            public void execute(List<ServicioIndexAutoData> param) {
                actualizaListaSearch(param);
            }
        });
    }
    
    private void actualizaListaSearch(List<ServicioIndexAutoData> data) {
        searchModel.updateData(data);
        search.removeSelectionInterval(search.getSelectedIndex(), search.getSelectedIndex());
        if (!searchScroll.isVisible() && this.numeroSerie.hasFocus()) {
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
    
    public void bindComponents() {
        bindingManager.registerBind(viewServicioModel, "descripcion", (Bindable)this.descripcionServicio);
        
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"marca", marca.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"tipo", tipo.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"version", version.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"numeroSerie", (Bindable)numeroSerie);
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"modelo", modelo.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"color", color.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto(),"placas", placas.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"kilometraje", kilometraje.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"combustible", combustible.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getDatosAuto(),"combustible", (Bindable)combustibleSlide);
        
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"transmision", (Bindable)estandar);
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"transmision", (Bindable)automatico);
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"elevadores", (Bindable)manuales);
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"elevadores", (Bindable)electrico);
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"bolsasDeAire", bolsasDeAire.getTextField());
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"aireAcondicionado", (Bindable)aireAcondicionado);
        this.bindingManager.registerBind(viewServicioModel.getAuto().getEquipamiento(),"equipoAdicional", (Bindable)modelEquipoAdicional);
        
        //binding validaciones
        this.bindingManager.registerBind(validacionDatosAuto, "marca", marca);
        this.bindingManager.registerBind(validacionDatosAuto, "tipo", tipo);
        this.bindingManager.registerBind(validacionDatosAuto, "version", version);
        this.bindingManager.registerBind(validacionDatosAuto, "numeroSerie", wrapperSearch);
        bindingManager.registerBind(validacionDatosAuto, "numeroSerie", new ReadOnlyBinding(){
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                EstatusValidacion statusValidacion = (EstatusValidacion) value;
                if (org.apache.commons.lang.StringUtils.isEmpty(statusValidacion.getDetalle())) {
                    numeroSerie.setToolTipText(null);
                } else {
                    numeroSerie.setToolTipText(statusValidacion.getDetalle());
                }
            }
        });
        this.bindingManager.registerBind(validacionDatosAuto, "modelo", modelo);
        this.bindingManager.registerBind(validacionDatosAuto, "color", color);
        this.bindingManager.registerBind(validacionDatosAuto, "placas", placas);
        this.bindingManager.registerBind(validacionDatosAuto, "kilometraje", kilometraje);
        this.bindingManager.registerBind(validacionDatosAuto, "descripcionServicio", new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                EstatusValidacion status = (EstatusValidacion) value;
                if (status.isValido()) {
                    descripcionServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 2));
                } else {
                    descripcionServicio.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 2));
                }
                if (StringUtils.isEmpty(status.getDetalle())) {
                    descripcionServicio.setToolTipText(null);
                } else {
                    descripcionServicio.setToolTipText(status.getDetalle());
                }
            }
        });
        
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
        return validacionGeneralDatosAuto;
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        numeroSerieLabel = new javax.swing.JLabel();
        numeroSerie = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextField();
        ((javax.swing.text.AbstractDocument)numeroSerie.getDocument()).setDocumentFilter(new DocumentSizeValidatorMayusculas(17));
        searchIcon = new javax.swing.JPanel();
        cancelIcon = new javax.swing.JPanel();
        wrapperSearch = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        searchScroll = new javax.swing.JScrollPane();
        search = new javax.swing.JList();
        marcaLabel = new javax.swing.JLabel();
        marca = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        tipoLabel = new javax.swing.JLabel();
        tipo = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        versionLabel = new javax.swing.JLabel();
        version = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        modeloLabel = new javax.swing.JLabel();
        modelo = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        colorLabel = new javax.swing.JLabel();
        color = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        placasLabel = new javax.swing.JLabel();
        placas = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        this.placas.setDocumentFilter(new DocumentSizeValidatorMayusculas(10));
        kilometrajeLabel = new javax.swing.JLabel();
        kilometraje = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        combustibleLabel = new javax.swing.JLabel();
        combustible = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        combustibleSlide = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJSlider();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionServicio = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJTextArea();
        ((javax.swing.text.AbstractDocument)descripcionServicio.getDocument()).setDocumentFilter(new DocumentSizeValidator(230));
        datosEquipamiento = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        estandar = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton(TipoTransmisionVB.estandar);
        automatico = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton(TipoTransmisionVB.automatico);
        jPanel3 = new javax.swing.JPanel();
        manuales = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton(TipoElevadorVB.manuales);
        electrico = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJRadioButton(TipoElevadorVB.electricos);
        jLabel8 = new javax.swing.JLabel();
        bolsasDeAire = new org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation();
        aireAcondicionado = new org.nekorp.workflow.desktop.view.binding.SimpleBindableJCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        agregarEquipoAdicional = new javax.swing.JButton();
        borrarEquipoAdicional = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoAdicional = new javax.swing.JList();

        setBackground(new java.awt.Color(255, 255, 255));

        numeroSerieLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroSerieLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        numeroSerieLabel.setText("Número de serie");
        jLayeredPane1.add(numeroSerieLabel);
        numeroSerieLabel.setBounds(10, 10, 82, 14);

        numeroSerie.setBackground(new java.awt.Color(224, 230, 230));
        numeroSerie.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numeroSerie.setBorder(null);
        numeroSerie.setDisabledTextColor(new java.awt.Color(100, 100, 100));
        numeroSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroSerieActionPerformed(evt);
            }
        });
        numeroSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numeroSerieFocusLost(evt);
            }
        });
        numeroSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numeroSerieKeyPressed(evt);
            }
        });
        jLayeredPane1.add(numeroSerie);
        numeroSerie.setBounds(118, 12, 148, 16);

        searchIcon.setOpaque(false);
        searchIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIconMouseClicked(evt);
            }
        });
        searchIcon.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(searchIcon);
        searchIcon.setBounds(102, 12, 16, 16);

        cancelIcon.setOpaque(false);
        cancelIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelIconMouseClicked(evt);
            }
        });
        cancelIcon.setLayout(new java.awt.BorderLayout());
        jLayeredPane1.add(cancelIcon);
        cancelIcon.setBounds(270, 12, 16, 16);

        wrapperSearch.setFocusable(false);
        jLayeredPane1.add(wrapperSearch);
        wrapperSearch.setBounds(100, 8, 170, 24);

        searchScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        searchScroll.setPreferredSize(new java.awt.Dimension(170, 130));

        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        searchScroll.setViewportView(search);

        jLayeredPane1.add(searchScroll);
        searchScroll.setBounds(100, 30, 170, 0);
        jLayeredPane1.setLayer(searchScroll, javax.swing.JLayeredPane.POPUP_LAYER);

        marcaLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        marcaLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        marcaLabel.setText("Marca");
        jLayeredPane1.add(marcaLabel);
        marcaLabel.setBounds(300, 10, 80, 14);
        jLayeredPane1.add(marca);
        marca.setBounds(390, 10, 170, 24);

        tipoLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tipoLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tipoLabel.setText("Tipo");
        jLayeredPane1.add(tipoLabel);
        tipoLabel.setBounds(10, 40, 80, 14);
        jLayeredPane1.add(tipo);
        tipo.setBounds(100, 40, 170, 24);

        versionLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        versionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        versionLabel.setText("Versión");
        jLayeredPane1.add(versionLabel);
        versionLabel.setBounds(300, 40, 80, 14);
        jLayeredPane1.add(version);
        version.setBounds(390, 40, 170, 24);

        modeloLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        modeloLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        modeloLabel.setText("Modelo");
        jLayeredPane1.add(modeloLabel);
        modeloLabel.setBounds(10, 70, 80, 14);
        jLayeredPane1.add(modelo);
        modelo.setBounds(100, 70, 170, 24);

        colorLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        colorLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        colorLabel.setText("Color");
        jLayeredPane1.add(colorLabel);
        colorLabel.setBounds(309, 70, 70, 14);
        jLayeredPane1.add(color);
        color.setBounds(390, 70, 170, 24);

        placasLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        placasLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        placasLabel.setText("Placas");
        jLayeredPane1.add(placasLabel);
        placasLabel.setBounds(10, 100, 80, 14);
        jLayeredPane1.add(placas);
        placas.setBounds(100, 100, 170, 24);

        kilometrajeLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        kilometrajeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        kilometrajeLabel.setText("Kilometraje");
        jLayeredPane1.add(kilometrajeLabel);
        kilometrajeLabel.setBounds(297, 100, 80, 14);
        jLayeredPane1.add(kilometraje);
        kilometraje.setBounds(390, 100, 170, 24);

        combustibleLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        combustibleLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        combustibleLabel.setText("Combustible");
        jLayeredPane1.add(combustibleLabel);
        combustibleLabel.setBounds(30, 130, 58, 14);

        combustible.setEditable(true);
        jLayeredPane1.add(combustible);
        combustible.setBounds(100, 130, 40, 24);

        combustibleSlide.setBackground(new java.awt.Color(255, 255, 255));
        combustibleSlide.setMinorTickSpacing(25);
        combustibleSlide.setPaintLabels(true);
        combustibleSlide.setPaintTicks(true);
        jLayeredPane1.add(combustibleSlide);
        combustibleSlide.setBounds(140, 130, 130, 31);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("Descripción del Servicio");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        descripcionServicio.setBackground(new java.awt.Color(224, 230, 230));
        descripcionServicio.setColumns(40);
        descripcionServicio.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        descripcionServicio.setLineWrap(true);
        descripcionServicio.setRows(8);
        descripcionServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 230, 230), 2));
        descripcionServicio.setDisabledTextColor(new java.awt.Color(100, 100, 100));
        jScrollPane1.setViewportView(descripcionServicio);

        datosEquipamiento.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Equipamiento");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Transmision", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        estandar.setBackground(new java.awt.Color(255, 255, 255));
        grupoTransmision.add(estandar);
        estandar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        estandar.setSelected(true);
        estandar.setText("Estandar");

        automatico.setBackground(new java.awt.Color(255, 255, 255));
        grupoTransmision.add(automatico);
        automatico.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Elevadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        manuales.setBackground(new java.awt.Color(255, 255, 255));
        grupoElevadores.add(manuales);
        manuales.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        manuales.setSelected(true);
        manuales.setText("Manuales");

        electrico.setBackground(new java.awt.Color(255, 255, 255));
        grupoElevadores.add(electrico);
        electrico.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("Bolsas de aire");

        aireAcondicionado.setBackground(new java.awt.Color(255, 255, 255));
        aireAcondicionado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        aireAcondicionado.setText("Aire Acondicionado");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Caracteristicas del Auto");

        jToolBar2.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        agregarEquipoAdicional.setBackground(new java.awt.Color(204, 204, 204));
        agregarEquipoAdicional.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        borrarEquipoAdicional.setBackground(new java.awt.Color(204, 204, 204));
        borrarEquipoAdicional.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(23, 79));

        equipoAdicional.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        equipoAdicional.setModel(modelEquipoAdicional);
        equipoAdicional.setRequestFocusEnabled(false);
        equipoAdicional.setSelectionBackground(new java.awt.Color(224, 230, 230));
        equipoAdicional.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(equipoAdicional);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout datosEquipamientoLayout = new javax.swing.GroupLayout(datosEquipamiento);
        datosEquipamiento.setLayout(datosEquipamientoLayout);
        datosEquipamientoLayout.setHorizontalGroup(
            datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(datosEquipamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosEquipamientoLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(datosEquipamientoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bolsasDeAire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(aireAcondicionado)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datosEquipamientoLayout.setVerticalGroup(
            datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosEquipamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datosEquipamientoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(datosEquipamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(bolsasDeAire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(aireAcondicionado))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(datosEquipamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datosEquipamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        if (!search.isSelectionEmpty() && activo) {
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            this.aplication.loadAuto(this.searchModel.getAutoAt(this.search.getSelectedIndex()));
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            actualizaListaSearch(new LinkedList<ServicioIndexAutoData>());
            activo = true;
        }
    }//GEN-LAST:event_searchMouseClicked

    private void numeroSerieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroSerieKeyPressed
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
    }//GEN-LAST:event_numeroSerieKeyPressed

    private void numeroSerieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numeroSerieFocusLost
        if(evt.getOppositeComponent() != this.search) {
            this.searchScroll.setVisible(false);
        }
    }//GEN-LAST:event_numeroSerieFocusLost

    private void numeroSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroSerieActionPerformed
        if (!search.isSelectionEmpty()) {
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            this.aplication.loadAuto(this.searchModel.getAutoAt(this.search.getSelectedIndex()));
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            actualizaListaSearch(new LinkedList<ServicioIndexAutoData>());
            activo = true;
        }
    }//GEN-LAST:event_numeroSerieActionPerformed

    private void searchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIconMouseClicked
        if (activo) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            BusquedaAutoView dialog = new BusquedaAutoView(mainFrame, true, aplication);
            dialog.validate();
            dialog.pack();
            dialog.setLocationRelativeTo(mainFrame);
            activo = false;
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            dialog.setVisible(true);
            activo = true;
        }
    }//GEN-LAST:event_searchIconMouseClicked

    private void cancelIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelIconMouseClicked
        this.aplication.loadAuto(null);
    }//GEN-LAST:event_cancelIconMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarEquipoAdicional;
    private javax.swing.JCheckBox aireAcondicionado;
    private javax.swing.JRadioButton automatico;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation bolsasDeAire;
    private javax.swing.JButton borrarEquipoAdicional;
    private javax.swing.JPanel cancelIcon;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation color;
    private javax.swing.JLabel colorLabel;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation combustible;
    private javax.swing.JLabel combustibleLabel;
    private javax.swing.JSlider combustibleSlide;
    private javax.swing.JPanel datosEquipamiento;
    private javax.swing.JTextArea descripcionServicio;
    private javax.swing.JRadioButton electrico;
    private javax.swing.JList equipoAdicional;
    private javax.swing.JRadioButton estandar;
    private javax.swing.ButtonGroup grupoElevadores;
    private javax.swing.ButtonGroup grupoTransmision;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar2;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation kilometraje;
    private javax.swing.JLabel kilometrajeLabel;
    private javax.swing.JRadioButton manuales;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation marca;
    private javax.swing.JLabel marcaLabel;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation modelo;
    private javax.swing.JLabel modeloLabel;
    private javax.swing.JTextField numeroSerie;
    private javax.swing.JLabel numeroSerieLabel;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation placas;
    private javax.swing.JLabel placasLabel;
    private javax.swing.JList search;
    private javax.swing.JPanel searchIcon;
    private javax.swing.JScrollPane searchScroll;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation tipo;
    private javax.swing.JLabel tipoLabel;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation version;
    private javax.swing.JLabel versionLabel;
    private org.nekorp.workflow.desktop.view.binding.JTextFieldWithValidation wrapperSearch;
    // End of variables declaration//GEN-END:variables

    public void setBindingManager(BindingManager<Bindable> bindingManager) {
        this.bindingManager = bindingManager;
    }

    public void setViewServicioModel(ServicioVB viewServicioModel) {
        this.viewServicioModel = viewServicioModel;
    }

    public void setValidacionDatosAuto(ValidacionDatosAuto validacionDatosAuto) {
        this.validacionDatosAuto = validacionDatosAuto;
    }

    public void setValidacionGeneralDatosAuto(ValidacionGeneralDatosAuto validacionGeneralDatosAuto) {
        this.validacionGeneralDatosAuto = validacionGeneralDatosAuto;
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
    
    public void setRenglonSearchSize(int renglonSearchSize) {
        this.renglonSearchSize = renglonSearchSize;
    }

    public void setRenglonesVisiblesSearch(int renglonesVisiblesSearch) {
        this.renglonesVisiblesSearch = renglonesVisiblesSearch;
    }

    public void setConstanteUniversalDeAjuste(int constanteUniversalDeAjuste) {
        this.constanteUniversalDeAjuste = constanteUniversalDeAjuste;
    }

    public void setAplication(ControlAuto aplication) {
        this.aplication = aplication;
    }
    
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setPermisos(PermisosAutoView permisos) {
        this.permisos = permisos;
    }
    
}
