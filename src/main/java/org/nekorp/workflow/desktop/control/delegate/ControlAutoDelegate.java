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
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;
import technology.tikal.taller.automotriz.model.auto.Auto;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

/**
 *
 * @author Nekorp
 */
@Controller
public class ControlAutoDelegate {
    private static final Logger LOGGER = Logger.getLogger(ControlAutoDelegate.class);
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private MensajesControl mensajesControl;
    @Autowired
    private AutoBridge autoBridge;
    
    public void loadAuto(ServicioIndexAutoData origen) {
        try {
            if (origen == null) {
                autoBridge.load(new Auto(), servicioVB.getAuto());
            } else {
                autoBridge.load(this.autoDAO.cargar(origen.getNumeroSerie()), servicioVB.getAuto());
            }
        } catch(ResourceAccessException e) {
            ControlAutoDelegate.LOGGER.error("error al cargar autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    
    public List<ServicioIndexAutoData> getAutos() {
        try {
            return autoDAO.getIndiceAutos();
        } catch(ResourceAccessException e) {
            ControlAutoDelegate.LOGGER.error("error al cargar todos los autos" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new LinkedList<>();
        }
    }

    public void buscarAuto(String numeroSerie, Callback<List<ServicioIndexAutoData>> cmd) {
        try {
            autoDAO.buscar(numeroSerie, cmd);
        } catch(ResourceAccessException e) {
            ControlAutoDelegate.LOGGER.error("error al buscar un auto por numero de serie" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
}
