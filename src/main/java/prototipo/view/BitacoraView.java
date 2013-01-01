/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import prototipo.modelo.bitacora.Evento;
import prototipo.modelo.bitacora.EventoEntrega;
import prototipo.modelo.bitacora.EventoGeneral;
import prototipo.servicio.EventoServicioFactory;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public abstract class BitacoraView extends ApplicationView implements Bindable, EventoViewListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraView.class);
    
    private LinkedList<Object> ignore;
    private LinkedList<Evento> modelo;
    private Object target;
    private String property;
    @Autowired
    private EventoServicioFactory eventoFactory;
    
    public BitacoraView() {
        this.ignore = new LinkedList<>();
        modelo = new LinkedList<>();
    }
    
    @Override
    public void setEditableStatus(boolean value) {
        //no hay cosas que inhabilitar
    }
    @Override
    public void iniciaVista() {
        initComponents();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            LinkedList<Evento> param = (LinkedList<Evento>) value;
            LinkedList<Evento> borrar = new LinkedList<>();
            for (Evento obj: modelo){
                borrar.add(obj);
            }
            for (int i=0; i < param.size(); i++) {
                if (this.modelo.size() > i) {
                    if(param.get(i).equals(this.modelo.get(i))){
                        borrar.remove(this.modelo.get(i));
                    } else {
                        this.modelo.add(i, param.get(i));
                        addEventoView(this.modelo.get(i), i);
                    }
                } else {
                    this.modelo.add(param.get(i));
                    addEventoView(this.modelo.get(i), i);
                }
            }
            for (Evento x: borrar) {
                removeEvento(x);
            }
            this.updateUI();
        }
    }
    
    private void addEventoView(Evento obj, int index) {
        EventoView entrada = null;
        if (obj instanceof EventoGeneral) {
            entrada = getEventoGeneralView();
        }
        if (obj instanceof EventoEntrega) {
            entrada = getEventoEntregaView();
        }
        if (entrada != null) {
            entrada.setModel(obj);
            entrada.iniciaVista();
            this.entradas.add(entrada, index);
        }
    }
    
    private void removeEvento(Evento obj){
        int index = modelo.indexOf(obj);
        this.modelo.remove(index);
        EventoView view = (EventoView) this.entradas.getComponent(index);
        view.disposeView();
        this.entradas.remove(view);
    }
    
    @Override
    public void deleteEvent(Evento ev){
        LinkedList<Evento> value = new LinkedList<>();
        for (Evento x: this.modelo) {
            value.add(x);
        }
        value.remove(ev);
        actualizaModelo(value);
    }
    
    private void actualizaModelo(LinkedList<Evento> value) {
        try {
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BitacoraView.LOGGER.error(ex);
        }
    }
    
    public abstract EventoView getEventoGeneralView();
    
    public abstract EventoView getEventoEntregaView();

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
        Object[] possibilities = {"Entrada de Auto", "Salida de Auto", "Otro"};
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
        LinkedList<Evento> value = new LinkedList<>();
        for (Evento x: this.modelo) {
            value.add(x);
        }
        if (s.compareTo("Otro") == 0) {
            value.add(this.eventoFactory.creaEvento(EventoGeneral.class));
        } else {
            EventoEntrega nuevo = this.eventoFactory.creaEvento(EventoEntrega.class);
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
