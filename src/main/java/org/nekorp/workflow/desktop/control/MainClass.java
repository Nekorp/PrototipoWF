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
package org.nekorp.workflow.desktop.control;

import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.view.AppStartingView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * @author Nekorp
 */
public class MainClass {

    private static Logger LOGGER = Logger.getLogger(MainClass.class);

    public static void main(String arg[]) {
        try {
            ApplicationContext mini = new ClassPathXmlApplicationContext(
                    "spring/mini-appContext.xml");
            AppStartingView appStartView = mini.getBean(AppStartingView.class);
            appStartView.init();
            appStartView.pushMsg("Iniciando la aplicaci\u00F3n...");
            ApplicationContext ctx = new ClassPathXmlApplicationContext(
                    "spring/applicationContext.xml");
            WorkflowApp app = ctx.getBean(WorkflowApp.class);
            appStartView.pushMsg("Cargando cat\u00E1logo de clientes...");
            app.warmupCustomer();
            appStartView.pushMsg("Cargando cat\u00E1logo de autos...");
            app.warmupAutoIndex();
            appStartView.pushMsg("Cargando cat\u00E1logo de servicios...");
            app.warmupServicioIndex();
            appStartView.pushMsg("Iniciando la interfaz...");
            app.startAplicacion();
            appStartView.dispose();
        } catch (Exception e) {
            MainClass.LOGGER.error("No se logro inicializar la aplicacion", e);
            System.exit(1);
        }
    }
}
