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
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.bridge.customers.CustomerBridge;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;
import technology.tikal.customers.model.Customer;

/**
 *
 * @author Nekorp
 */
@Controller
public class ControlClienteDelegate {
    private static final Logger LOGGER = Logger.getLogger(ControlClienteDelegate.class);
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CustomerBridge customerBridge;
    @Autowired
    private MensajesControl mensajesControl;
    
    public void loadCliente(Customer origen) {
        try {
            if (origen.getId() != null) {
                Customer customer = customerDAO.cargar(origen.getId());
                customerBridge.load(customer, servicioVB.getCliente());
            } else {
                customerBridge.load(origen, servicioVB.getCliente());
            }
        } catch(ResourceAccessException e) {
            ControlClienteDelegate.LOGGER.error("error al cargar un cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
    
    public Customer[] getClientes() {
        try {
            return customerDAO.consultaTodos();
        } catch(ResourceAccessException e) {
            ControlClienteDelegate.LOGGER.error("error al cargar todos los cliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
            return new Customer[0];
        }
    }
    
    public void buscarCliente(String name, final Callback cmd) {
        try {
            customerDAO.buscar(name, cmd);
        } catch(ResourceAccessException e) {
            ControlClienteDelegate.LOGGER.error("error al buscar uncliente" + e.getMessage());
            this.mensajesControl.reportaError("Error de comunicacion con el servidor");
        }
    }
}
