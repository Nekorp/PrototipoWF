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

import java.util.List;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.rest.util.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndex;

/**
 *
 * @author Nekorp
 */
@Controller
public class StartStopController {
    private static final Logger LOGGER = Logger.getLogger(StartStopController.class);
    
    private boolean cancelarAlertas;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private MensajesControl mensajesControl;
    @Autowired
    @Qualifier("taller-RestTemplateFactory")
    private RestTemplateFactory factoryAuto;
    @Autowired
    @Qualifier("customer-RestTemplateFactory")
    private RestTemplateFactory factoryCustomer;
    
    public void startAplicacion() {
        StartStopController.LOGGER.debug("iniciando aplicacion");
        this.cancelarAlertas = false;
        try {
            if(!cancelarAlertas) {
                List<ServicioIndex> vencidos = servicioDAO.getIndiceServiciosPorStatus("Vencido");
                for (ServicioIndex x: vencidos) {
                    if (cancelarAlertas) break;
                    String msg = "El folio " + x.getId() + " está vencido.";
                    mensajesControl.reportarAlerta(x.getId(), msg);
                }
            }
            if(!cancelarAlertas) {
                List<ServicioIndex> sinCerrar = servicioDAO.getIndiceServiciosPorStatus("SinCerrar");
                for (ServicioIndex x: sinCerrar) {
                    if (cancelarAlertas) break;
                    String msg = "Servicio " + x.getId() + " sin cerrar.";
                    mensajesControl.reportarAlerta(x.getId(), msg);
                }
            }
        } catch(ResourceAccessException e) {
            StartStopController.LOGGER.error("error al consultar alertas" + e.getMessage());
            this.mensajesControl.reportaError("Error al consultar alertas");
        }
    }

    
    public void closeAplicacion() {
        factoryAuto.shutdown();
        factoryCustomer.shutdown();
        System.exit(0);
    }
    
    public void cancelarAlertas() {
        this.cancelarAlertas = true;
    }
}
