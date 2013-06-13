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
package org.nekorp.workflow.desktop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoDiagnosticoVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoFinServicioVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoObservacionesVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoReclamacionVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoSistemaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.security.PermisosBitacoraView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 
 */
public abstract class BitacoraView extends ApplicationView implements Bindable, EventoViewListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraView.class);
    
    private LinkedList<Object> ignore;
    private LinkedList<EventoVB> modelo;
    private Object target;
    private String property;
    @Autowired
    private EventoServicioFactory eventoFactory;
    @Autowired
    private PermisosBitacoraView permisos;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private EventoExtraGuardar extraGuardar;
    @Autowired
    private javax.swing.JFrame mainFrame;
    
    public BitacoraView() {
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        this.agregarBitacora.setEnabled(value);
        for (java.awt.Component x: this.entradas.getComponents()) {
            if (x instanceof ApplicationView) {
                ApplicationView y = (ApplicationView) x;
                y.setEditableStatus(value);
            }
        }
    }
    @Override
    public void iniciaVista() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        extraGuardar.iniciaVista();
        setBindings();
    }
    
    private void setBindings() {
        Bindable permisosBind = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                boolean valor = (boolean) value;
                setEditableStatus(valor);
            }
        };
        bindingManager.registerBind(permisos, "modificarEventos", permisosBind);
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            LinkedList<EventoVB> param = (LinkedList<EventoVB>) value;
            LinkedList<EventoVB> borrar = new LinkedList<>();
            for (EventoVB obj: modelo) {
                if (!param.contains(obj)) {
                    borrar.add(obj);
                }
            }
            for (EventoVB x: borrar) {
                removeEvento(x);
            }
            for (int i = 0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if (!param.get(i).equals(this.modelo.get(i))) {
                        this.modelo.add(i, param.get(i));
                        addEventoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addEventoView(this.modelo.get(i), i);
                }
            }
            this.updateUI();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
                }
            });
        }
    }
    
    private void addEventoView(EventoVB obj, int index) {
        EventoView entrada = null;
        int realIndex = index * 2;
        if (obj instanceof EventoGeneralVB) {
            entrada = getEventoGeneralView();
        }
        if (obj instanceof EventoEntregaVB) {
            entrada = getEventoEntregaView();
        }
        if (obj instanceof EventoSistemaVB) {
            entrada = getEventoSistemaView();
        }
        if (obj instanceof EventoReclamacionVB) {
            entrada = getEventoReclamacionView();
        }
        if (obj instanceof EventoFinServicioVB) {
            entrada = getEventoFinServicioView();
        }
        if (obj instanceof EventoDiagnosticoVB) {
            entrada = getEventoDiagnosticoView();
        }
        if (obj instanceof EventoObservacionesVB) {
            entrada = getEventoObservacionesView();
        }
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            entrada.setEditableStatus(true);
            eliminaViejoGuardar();
            this.entradas.add(entrada, realIndex);
            entrada.validate();
            if (StringUtils.isEmpty(obj.getId())) {
                entradas.add(this.extraGuardar, realIndex + 1);
            } else {
                entradas.add(new EventoExtraSeparador(), realIndex + 1);
            }
            entrada.requestFocusOnMainInput();
        }
    }
    
    private void eliminaViejoGuardar() {
        java.awt.Component[] components = entradas.getComponents();
        int index = -1;
        for (int i = 0; i < components.length; i++) {
            if (components[i] == this.extraGuardar) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            entradas.remove(index);
            entradas.add(new EventoExtraSeparador(), index);
        }
    }
    
    private void removeEvento(EventoVB obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        int viewIndex = index * 2;
        EventoView view = (EventoView) this.entradas.getComponent(viewIndex);
        EventoView anterior = null;
        if (viewIndex > 0) {
            anterior = (EventoView) this.entradas.getComponent(viewIndex - 2);
        }
        view.disposeView();
        if (this.entradas.getComponent(viewIndex + 1) == extraGuardar && anterior != null) {
            if (StringUtils.isEmpty(anterior.getModel().getId())) {
                this.entradas.remove(viewIndex - 1);
            } else {
                this.entradas.remove(viewIndex + 1);
            }
        } else {
            this.entradas.remove(viewIndex + 1);
        }
        this.entradas.remove(view);
    }
    
    @Override
    public void deleteEvent(EventoVB ev){
        int n = javax.swing.JOptionPane.showConfirmDialog(
                mainFrame,
                "¿Borrar Evento?",
                "Confirmación",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (n == javax.swing.JOptionPane.YES_OPTION) {
            LinkedList<EventoVB> value = new LinkedList<>();
            for (EventoVB x: this.modelo) {
                value.add(x);
            }
            value.remove(ev);
            actualizaModelo(value);
        }
    }
    
    private void actualizaModelo(LinkedList<EventoVB> value) {
        try {
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BitacoraView.LOGGER.error("exploto", ex);
        }
    }
    
    public abstract EventoView getEventoGeneralView();
    
    public abstract EventoView getEventoEntregaView();
    
    public abstract EventoView getEventoSistemaView();
    
    public abstract EventoView getEventoReclamacionView();
    
    public abstract EventoView getEventoFinServicioView();
    
    public abstract EventoView getEventoDiagnosticoView();
    
    public abstract EventoView getEventoObservacionesView();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        agregarBitacora = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        entradas = new javax.swing.JPanel();

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        agregarBitacora.setText("Nueva Entrada");
        agregarBitacora.setFocusable(false);
        agregarBitacora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarBitacora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBitacoraActionPerformed(evt);
            }
        });
        jToolBar1.add(agregarBitacora);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        entradas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        entradas.setLayout(new javax.swing.BoxLayout(entradas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(entradas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBitacoraActionPerformed
        Object[] possibilities = {
            "Entrada de Auto",
            "Diagnostico",
            "Observaciones",
            "Reclamaciones",
            "Otro",
            "Salida de Auto",
            "Cancelación",
            "Termino de servicio"
        };
        String s = (String)javax.swing.JOptionPane.showInputDialog(
                            this,
                            "Eliga el evento",
                            "Seleccion evento",
                            javax.swing.JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            "Entrega Auto");
        if (s == null) {
            return;
        }
        LinkedList<EventoVB> value = new LinkedList<>();
        for (EventoVB x: this.modelo) {
            value.add(x);
        }
        if (s.compareTo("Diagnostico") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoDiagnosticoVB.class));
        }
        if (s.compareTo("Observaciones") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoObservacionesVB.class));
        }
        if (s.compareTo("Otro") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoGeneralVB.class));
        }
        if (s.compareTo("Reclamaciones") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoReclamacionVB.class));
        }
        if (s.compareTo("Entrada de Auto") == 0 || s.compareTo("Salida de Auto") == 0) {
            EventoEntregaVB nuevo = this.eventoFactory.creaEvento(EventoEntregaVB.class);
            nuevo.setNombreEvento(s);
            value.add(nuevo);
        }
        if (s.compareTo("Termino de servicio") == 0) {
            BitacoraVB bitacora = (BitacoraVB) target;
            if (bitacora.tieneSalidaAuto()) {
                EventoFinServicioVB nuevo = this.eventoFactory.creaEvento(EventoFinServicioVB.class);
                nuevo.setNombreEvento(s);
                value.add(nuevo);
            } else {
                javax.swing.JOptionPane.showMessageDialog(mainFrame,
                    "No hay una salida de auto registrada.",
                    "Aviso",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        if (s.compareTo("Cancelación") == 0) {
            BitacoraVB bitacora = (BitacoraVB) target;
            if (bitacora.tieneEntradaAuto()) {
                EventoEntregaVB extra = this.eventoFactory.creaEvento(EventoEntregaVB.class);
                extra.setNombreEvento("Salida de Auto");
                extra.setDetalle("Salida Generada automaticamente por cancelación");
                extra.setResponsable("Sistema");
                extra.setFecha(new Date());
                value.add(extra);
            }
            EventoFinServicioVB nuevo = this.eventoFactory.creaEvento(EventoFinServicioVB.class);
            nuevo.setNombreEvento(s);
            value.add(nuevo);
        }
        actualizaModelo(value);
        //javax.swing.Box.createRigidArea(new java.awt.Dimension(5,0));
    }//GEN-LAST:event_agregarBitacoraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBitacora;
    private javax.swing.JPanel entradas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
