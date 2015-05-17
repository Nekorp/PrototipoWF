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
package org.nekorp.workflow.desktop.control.delegate;

import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Nekorp
 */
@Controller
public class WarmupAppController {
    
    private static final Logger LOGGER = Logger.getLogger(WarmupAppController.class);
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ServicioIndexMetadata servicioIndexMetadata;
    @Autowired
    private ServicioIndexController controlServicioIndex;
    
    public void warmupCustomer() {
        try {
            WarmupAppController.LOGGER.debug("Inicializando el cache de clientes");
            this.customerDAO.consultaTodos();
            WarmupAppController.LOGGER.debug("Cache inicializado");
        } catch(Exception e) {
            WarmupAppController.LOGGER.error("error en el warmup" + e.getMessage());
        }
    }
    
    
    public void warmupAutoIndex() {
        try {
            WarmupAppController.LOGGER.debug("Inicializando el cache de autos");
            this.autoDAO.getIndiceAutos();
            WarmupAppController.LOGGER.debug("Cache inicializado");
        } catch(Exception e) {
            WarmupAppController.LOGGER.error("error en el warmup" + e.getMessage());
        }
    }
    
    
    public void warmupServicioIndex() {
        try {
            WarmupAppController.LOGGER.debug("Inicializando el cache servicios");
            this.servicioIndexMetadata.setIndex(controlServicioIndex.getIndexServicios());
            WarmupAppController.LOGGER.debug("Cache inicializado");
        } catch(Exception e) {
            WarmupAppController.LOGGER.error("error en el warmup" + e.getMessage());
        }
    }
}
