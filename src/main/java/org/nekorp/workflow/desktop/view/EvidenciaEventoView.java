/**
 *   Copyright 2013 Nekorp
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.servicio.ImageService;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.bitacora.EdicionEventoEvidenciaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EvidenciaVB;
import org.nekorp.workflow.desktop.view.resource.ThumbViewListener;
import org.nekorp.workflow.desktop.view.resource.imp.ImagenViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 
 */
@Component("evidenciaEventoView")
public class EvidenciaEventoView extends ApplicationView implements Bindable, ThumbViewListener {

    private static final Logger LOGGER = Logger.getLogger(EvidenciaEventoView.class);
    private LinkedList<Object> ignore;
    private Object target;
    private String property;
    @Autowired
    private EdicionEventoEvidenciaVB edicionEventoEvidencia;
    private List<EvidenciaVB> modelo;
    private List<ThumbnailView> thumbs;
    private java.awt.Frame mainFrame; 
    @Autowired
    private ImageService imageService;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    private boolean inicializado;
    private boolean editable;
    private ThumbnailView selected;
    /**
     * Creates new form EvidenciaEventoView
     */
    public EvidenciaEventoView() {
        super();
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
        thumbs = new LinkedList<>();
    }
    
    @Override
    public void iniciaVista() {
        if (!inicializado) {
            this.initComponents();
            this.inicializado=true;
            Bindable modelBind = new ReadOnlyBinding() {
                @Override
                public void notifyUpdate(Object origen, String property, Object value) {
                    EventoVB dato = (EventoVB) value;
                    clearBinding();
                    if (dato != null) {
                        setBinding(dato);
                    }
                }
            };
            Bindable edicionStatusBind = new ReadOnlyBinding() {
                @Override
                public void notifyUpdate(Object origen, String property, Object value) {
                    Boolean dato = (Boolean) value;
                    setEditableStatus(dato);
                }
            };
            bindingManager.registerBind(edicionEventoEvidencia, "evento", modelBind);
            bindingManager.registerBind(edicionEventoEvidencia, "edicionStatus", edicionStatusBind);
        }
    }

    private void setBinding(EventoVB evento) {
        bindingManager.registerBind(evento, "evidencia", this);
    }
    
    private void clearBinding() {
        bindingManager.clearBindings(this);
    }
    
    
    @Override
    public void setEditableStatus(boolean value) {
        this.editable = value;
        this.nueva.setEnabled(value);
        for (ThumbnailView x: thumbs) {
            x.setEditableStatus(value);
        }
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            modelo = (List<EvidenciaVB>) value;
            thumbs = new LinkedList<>();
            this.image.removeAll();
            this.previewContent.removeAll();
            for (EvidenciaVB x: modelo) {
                ThumbnailView thumbview = new ThumbnailView(
                    imageService.cargarImagen(x.getThumbnail()), this);
                thumbview.setEditableStatus(editable);
                thumbs.add(thumbview);
                this.previewContent.add(thumbview);
            }
            selected = null;
            this.validate();
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return this.modelo;
    }

    @Override
    public void bindListener(Object target, String property) {
        this.target = target;
        this.property = property;
    }

    @Override
    public void selectEvent(ThumbnailView target) {
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        int index = this.thumbs.indexOf(target);
        this.selected = target;
        EvidenciaVB seleccion = this.modelo.get(index);
        this.image.removeAll();
        this.image.add(new ImagenViewer(imageService.cargarImagen(seleccion.getImage())));
        this.validate();
        this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void deleteEvent(ThumbnailView target) {
        int borrado = this.thumbs.indexOf(target);
        this.modelo.remove(borrado);
        this.thumbs.remove(target);
        this.previewContent.remove(target);
        if (target == this.selected) {
            this.image.removeAll();
        }
        this.ignore.add(modelo);
        actualizaModelo();
        image.repaint();
        previewContent.repaint();
        this.validate();
    }
    
    private void actualizaModelo() {
        try {
            BeanUtils.setProperty(this.target, property, modelo);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            EvidenciaEventoView.LOGGER.error("Error al notificar actualización", ex);
        }
    }
    
    @Override
    public ViewValidIndicator getValidInidicator() {
        return null;
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
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        nueva = new javax.swing.JButton();
        navigate = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        previewContent = new javax.swing.JPanel();
        image = new javax.swing.JPanel();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(filler1);

        nueva.setText("Nueva Imagen");
        nueva.setFocusable(false);
        nueva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nueva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaActionPerformed(evt);
            }
        });
        jToolBar1.add(nueva);

        navigate.setMaximumSize(new java.awt.Dimension(230, 600));
        navigate.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        previewContent.setLayout(new javax.swing.BoxLayout(previewContent, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(previewContent);

        navigate.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        image.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image.setMaximumSize(new java.awt.Dimension(900, 600));
        image.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navigate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigate, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
            .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Imagen", "jpg", "jpeg", "png");
            chooser.setFileFilter(filter);
            String homePath = System.getProperty("user.home");
            File f = new File(new File(homePath).getCanonicalPath());
            chooser.setSelectedFile(f);
            int returnVal = chooser.showOpenDialog(this.mainFrame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                BufferedImage img = ImageIO.read(chooser.getSelectedFile());
                img = imageService.resizeToStandarSize(img);
                BufferedImage thumb = imageService.getThumbnail(img);
                EvidenciaVB nuevaEvidencia = new EvidenciaVB();
                nuevaEvidencia.setImage(imageService.guardarImagen(img));
                nuevaEvidencia.setThumbnail(imageService.guardarImagen(thumb));
                ThumbnailView thumbview = new ThumbnailView(thumb, this);
                thumbview.setEditableStatus(editable);
                thumbs.add(thumbview);
                modelo.add(nuevaEvidencia);
                previewContent.add(thumbview);
                this.ignore.add(modelo);
                actualizaModelo();
                selectEvent(thumbview);
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        } catch (IOException ex) {
            EvidenciaEventoView.LOGGER.error("Error al seleccionar archivo de imagen", ex);
        }
    }//GEN-LAST:event_nuevaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel image;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel navigate;
    private javax.swing.JButton nueva;
    private javax.swing.JPanel previewContent;
    // End of variables declaration//GEN-END:variables

}
