/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.control.imp;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nekorp.workflow.desktop.control.StartupController;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.view.start.AppStartingView;
import org.nekorp.workflow.desktop.view.start.TaskStatusView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Nekorp
 */
public class StartupControllerImp implements StartupController {

    private AppStartingView appStartView;
    private WorkflowApp app;
    
    @Override
    public void startup() {
        appStartView.init();
        //iniciando el contexto de spring
        iniciaContexto();
        //iniciando los catalogos
        phaseCatalogos();
        //inicia GUI
        faseGui();
    }
    
    private void iniciaContexto() {
        TaskStatusView taskViewInicio = new TaskStatusView("Iniciando la aplicaci\u00F3n");
        appStartView.addTask(taskViewInicio);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring/applicationContext.xml");
        this.app = ctx.getBean(WorkflowApp.class);
        taskViewInicio.setCompleted(true);
        appStartView.recalculateMsg();
    }
    
    private void phaseCatalogos() {
        try {
            Thread warmupCustomer = new Thread(new Runnable() {
                @Override
                public void run() {
                    TaskStatusView taskViewClientes = new TaskStatusView("Cargando cat\u00E1logo de clientes");
                    appStartView.addTask(taskViewClientes);
                    app.warmupCustomer();
                    taskViewClientes.setCompleted(true);
                    appStartView.recalculateMsg();
                }
            });
            warmupCustomer.start();
            
            Thread warmupAuto = new Thread(new Runnable() {
                @Override
                public void run() {
                    TaskStatusView taskViewAutos = new TaskStatusView("Cargando cat\u00E1logo de autos");
                    appStartView.addTask(taskViewAutos);
                    app.warmupAutoIndex();
                    taskViewAutos.setCompleted(true);
                    appStartView.recalculateMsg();
                }
            });
            warmupAuto.start();
            
            warmupCustomer.join();
            warmupAuto.join();
            TaskStatusView taskViewServicios = new TaskStatusView("Cargando cat\u00E1logo de servicios");
            appStartView.addTask(taskViewServicios);
            app.warmupServicioIndex();
            taskViewServicios.setCompleted(true);
            appStartView.recalculateMsg();
        } catch (InterruptedException ex) {
            Logger.getLogger(StartupControllerImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void faseGui() {
        TaskStatusView taskViewGui = new TaskStatusView("Iniciando la interfaz");
        appStartView.addTask(taskViewGui);
        app.startAplicacion();
        taskViewGui.setCompleted(true);
        appStartView.recalculateMsg();
        appStartView.dispose();
    }

    public void setAppStartView(AppStartingView appStartView) {
        this.appStartView = appStartView;
    }
}
