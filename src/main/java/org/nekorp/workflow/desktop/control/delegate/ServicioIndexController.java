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

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioIndexBridge;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author Nekorp
 */
@Controller
public class ServicioIndexController {
    private static final Logger LOGGER = Logger.getLogger(ServicioIndexController.class);
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioIndexBridge servicioIndexBridge;
    @Autowired
    private MensajesControl mensajesControl;
    
    public List<ServicioIndexVB> getIndexServicios() {
        try {
            List<ServicioIndexVB> respuesta = new LinkedList<>();
            servicioIndexBridge.load(this.servicioDAO.getIndiceServicios(), respuesta);
            return respuesta;
        } catch(ResourceAccessException e) {
            ServicioIndexController.LOGGER.error("error al cargar el indice de los servicios" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }
    
    
    public List<ServicioIndexVB> getIndexServicios(Long sinceId) {
        try {
            List<ServicioIndexVB> respuesta = new LinkedList<>();
            servicioIndexBridge.load(this.servicioDAO.getIndiceServicios(sinceId), respuesta);
            return respuesta;
        } catch(ResourceAccessException e) {
            ServicioIndexController.LOGGER.error("error al cargar el indice de los servicios" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }
}
