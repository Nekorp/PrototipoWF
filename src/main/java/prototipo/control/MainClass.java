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
package prototipo.control;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * @author Marisa
 */
public class MainClass {

    private static Logger LOGGER = Logger.getLogger(MainClass.class);

    public static void main(String arg[]) {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext(
                    "spring/applicationContext.xml");
            ctx.getBean(WorkflowApp.class).startApliacion();
        } catch (Exception e) {
            MainClass.LOGGER.error("No se logro inicializar la aplicacion", e);
            System.exit(1);
        }
    }
}
