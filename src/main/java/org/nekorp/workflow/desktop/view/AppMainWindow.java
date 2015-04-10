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

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.servicio.EditorMonitor;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.resource.LookAndFeelManager;
import org.nekorp.workflow.desktop.view.resource.WindowTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Nekorp
 * 
 */
@Component("mainWindow")
@Aspect
public class AppMainWindow extends javax.swing.JFrame {

    //@Autowired()
    //@Qualifier(value = "inicioView")
    //private ApplicationView inicioView;
    @Autowired()
    @Qualifier(value = "servicioView")
    private ApplicationView servicioView;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private EditorMonitor editorMonitor;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    @Autowired
    private LookAndFeelManager lookAndFeelManager;
    /**
     * map containing all global actions
     */
    private HashMap<KeyStroke, Action> actionMap = new HashMap<>();

    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.startApliacion(..))")
    public void inicioAplicacion() {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.cargaServicio(..))")  
    public void loadServicioPointCut() {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.MensajesControl.reportaError(..))&&" + 
          "args(error,..)")
    public void mensajeError(String error) {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.MensajesControl.reportarAlerta(..))&&" + 
          "args(folio, mensaje,..)")
    public void reportarAlerta(Long folio, String mensaje) {
    }
    /*
     * hay notificaciones sin control al inicio de la aplicacion
     * por parte de los combobox de telefono, mientras
     * se arreglan se limpia el editor monitor despues de iniciar la gui.
     */
    //@Before("inicioAplicacion()")
    @Before("inicioAplicacion()")
    public void iniciaMainWindow() {
        lookAndFeelManager.setLookAndFeel();
        initComponents();
        servicioView.iniciaVista();
        servicioView.setEditableStatus(false);
        //inicioView.iniciaVista();
        getContentPane().add((java.awt.Component) servicioView, java.awt.BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        //TODO activar nuevamente cuando funcionen los controles de edicion
        //setupKeyShortcut();
        editorMonitor.clear();
        final WindowTask windowTask = new WindowTask();
        windowTask.setWindow(this);
        java.awt.EventQueue.invokeLater(windowTask);
    }
    @AfterReturning("loadServicioPointCut()")
    public void cargarServicio() {
        servicioView.setEditableStatus(true);
        //getContentPane().remove((java.awt.Component)inicioView);
        //getContentPane().add((java.awt.Component)servicioView, java.awt.BorderLayout.CENTER);
        this.validate();
        this.pack();
    }
    @Before("mensajeError(error)")
    public void reportaError(String error) {
        javax.swing.JOptionPane.showMessageDialog(this,
            error,
            "Mensaje de Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    @Before("reportarAlerta(folio, mensaje)")
    public void generaAlertaFolioVencido(Long folio, String mensaje) {
        Object[] options = {"Resolver ahora",
                    "Resolver más tarde", 
                    "Cerrar todas las alertas"};
        
        int n = javax.swing.JOptionPane.showOptionDialog(this,
            mensaje,
            "Alerta",
            javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE,
            javax.swing.UIManager.getIcon("OptionPane.warningIcon"),
            options,
            options[0]);
        if (n == javax.swing.JOptionPane.YES_OPTION) {
            this.aplication.cargaServicio(folio);
        }
        if (n == javax.swing.JOptionPane.CANCEL_OPTION) {
             this.aplication.cancelarAlertas();
        }
    }

    /**
     * call this somewhere in your GUI construction
     */
    private void setupKeyShortcut() {
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        actionMap.put(key1, new AbstractAction("guardar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editorMonitor.hasChange()) {
                    try {
                        aplication.guardaServicio();
                    } catch (IllegalArgumentException ex) {
                        //no lo guardo.
                    }
                }   
            }
        });
//        key1 = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
//        actionMap.put(key1, new AbstractAction("deshacer") {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (editorMonitor.hasChange()) {
//                    editorMonitor.undo();
//                }
//            }
//        });
//        key1 = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
//        actionMap.put(key1, new AbstractAction("rehacer") {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                editorMonitor.redo();
//            }
//        });
        // add more actions..

        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);
                if (actionMap.containsKey(keyStroke)) {
                    final Action a = actionMap.get(keyStroke);
                    final ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), null);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            a.actionPerformed(ae);
                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AUTO CONTROL ESPECIALIZADO MÉXICO");
        setMinimumSize(new java.awt.Dimension(1300, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (this.servicioMetaData.isEditado()) {
                int n = javax.swing.JOptionPane.showConfirmDialog(
                        this,
                        "¿Guardar Servicio?",
                        "Guardar",
                        javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == javax.swing.JOptionPane.YES_OPTION) {
                    this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                    this.aplication.guardaServicio();
                }
                if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }
            aplication.closeAplicacion();
        } catch (IllegalArgumentException e) {
            //TODO hacer algun dia algo... como... no se algo.
        } finally {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
