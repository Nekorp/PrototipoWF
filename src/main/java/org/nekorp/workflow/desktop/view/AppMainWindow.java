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
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.control.LoginController;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.monitor.EditorMonitorManager;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioLoadedListMetadata;
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
    @Qualifier(value = "appLayoutView")
    private ApplicationView appLayoutView;
    @Autowired
    @Qualifier("LoginView")
    private ApplicationView loginView;
    @Autowired
    private WorkflowApp aplication;
    @Autowired
    private LoginController loginController;
    @Autowired
    private EditorMonitorManager editorManager;
    @Autowired
    private EdicionServicioMetadata servicioMetaData;
    //@Autowired
    //private LookAndFeelManager lookAndFeelManager;
    @Autowired
    private ServicioLoadedListMetadata servicioLoadedListMetadata;
    private boolean started = false;
    
    /**
     * map containing all global actions
     */
    private HashMap<KeyStroke, Action> actionMap = new HashMap<>();

    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.startAplicacion(..))")
    public void inicioAplicacion() {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.LoginController.start(..))")
    public void inicioLogin() {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.LoginController.finish(..))")
    public void loginEndPointcut() {
    }
    /*
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.ControlServicio.crearServicio(..)) || "
            + "execution(* org.nekorp.workflow.desktop.control.ControlServicio.cargaServicio(..)) || "
            + "execution(* org.nekorp.workflow.desktop.control.ControlServicio.cambiarServicio(..))")
    public void loadServicioPointCut() {
    }*/
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
        //lookAndFeelManager.setLookAndFeel();
        //initComponents();
        this.started = true;
        setMinimumSize(new java.awt.Dimension(1280, 720));
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        appLayoutView.iniciaVista();
        getContentPane().add((java.awt.Component) appLayoutView, java.awt.BorderLayout.CENTER);
        this.validate();
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
        //TODO activar nuevamente cuando funcionen los controles de edicion
        setupKeyShortcut();
        //final WindowTask windowTask = new WindowTask();
        //windowTask.setWindow(this);
        //java.awt.EventQueue.invokeLater(windowTask);
    }
    
    @Before("inicioLogin()")
    public void iniciaLogin() {
        this.getContentPane().removeAll();
        if (!this.started) {
            initComponents();
            loginView.iniciaVista();
        } else {
            setMinimumSize(new java.awt.Dimension(0, 0));
        }
        getContentPane().add((java.awt.Component) loginView, java.awt.BorderLayout.CENTER);
        this.validate();
        this.pack();
        setLocationRelativeTo(null);
        final WindowTask windowTask = new WindowTask();
        windowTask.setWindow(this);
        java.awt.EventQueue.invokeLater(windowTask);
    }
    
    @Before("loginEndPointcut()")
    public void endLogin() {
        this.setVisible(false);
        this.getContentPane().removeAll();
        if (started) {
            setMinimumSize(new java.awt.Dimension(1280, 720));
            getContentPane().add((java.awt.Component) appLayoutView, java.awt.BorderLayout.CENTER);
            this.validate();
            this.pack();
            setLocationRelativeTo(null);
            this.setVisible(true);
            
        }
    }
    /*@AfterReturning("loadServicioPointCut()")
    public void cargarServicio() {
        servicioView.setEditableStatus(true);
        //getContentPane().remove((java.awt.Component)inicioView);
        //getContentPane().add((java.awt.Component)servicioView, java.awt.BorderLayout.CENTER);
        this.validate();
        this.pack();
    }*/
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
                guardarServicio();
            }
        });
        key1 = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        actionMap.put(key1, new AbstractAction("deshacer") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorManager.getCurrentMonitor().undo();
            }
        });
        key1 = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
        actionMap.put(key1, new AbstractAction("rehacer") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorManager.getCurrentMonitor().redo();
            }
        });

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
    
    private void guardarServicio() {
        if (this.servicioMetaData.isEditado()) {
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            aplication.guardaServicio();
            this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (!this.started) {
            loginController.cancel();
        }
        boolean hayError = false;
        if (!servicioLoadedListMetadata.isEmpty()) {
            int n = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Guardar todos los servicios abiertos?",
                    "Guardar",
                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == javax.swing.JOptionPane.YES_OPTION) {
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                List<ServicioLoaded> servicios = new LinkedList<>();
                servicios.addAll(servicioLoadedListMetadata.getServicios());
                for (ServicioLoaded x: servicios) {
                    this.aplication.cambiarServicio(x);
                    if (servicioMetaData.isEditado()) {
                        if (this.aplication.guardaServicio()) {
                            this.aplication.cerrarServicio();
                        } else {
                            hayError = true;
                        }
                    }
                }
                List<ServicioLoaded> serviciosNuevos = new LinkedList<>();
                serviciosNuevos.addAll(servicioLoadedListMetadata.getServiciosNuevos());
                for (ServicioLoaded x: serviciosNuevos) {
                    this.aplication.cambiarServicio(x);
                    if (servicioMetaData.isEditado()) {
                        if (this.aplication.guardaServicio()) {
                            this.aplication.cerrarServicio();
                        } else {
                            hayError = true;
                        }
                    }
                }
                this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
            if (n == javax.swing.JOptionPane.CANCEL_OPTION || n == javax.swing.JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        if (!hayError) {
            aplication.closeAplicacion();
        }
    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
