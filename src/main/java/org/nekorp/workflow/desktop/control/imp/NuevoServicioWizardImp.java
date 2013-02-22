/**
 *   Copyright 2013 Nekorp
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

import java.util.List;
import org.nekorp.workflow.desktop.control.NuevoServicioWizard;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.modelo.auto.Auto;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.bridge.AutoBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ClienteBridge;
import org.nekorp.workflow.desktop.servicio.bridge.ServicioBridge;
import org.nekorp.workflow.desktop.view.model.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("nuevoServicioWizard")
public class NuevoServicioWizardImp implements NuevoServicioWizard {

    @Autowired
    @Qualifier(value="w-servicio")
    private ServicioVB servicio;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    private ServicioBridge servicioBridge;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ClienteBridge clienteBridge;
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private AutoBridge autoBridge;
    
    @Override
    public void loadCliente(Cliente origen) {
        clienteBridge.load(origen, servicio.getCliente());
    }

//    @Override
//    public Cliente loadByName(final String name) {
//        return clienteDAO.buscarUnico(name);
//    }

    @Override
    public void unloadCliente() {
        Cliente cliente = new Cliente();
        clienteBridge.load(cliente, this.servicio.getCliente());
    }

    @Override
    public void nuevoCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardarCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteDAO.consultaTodos();
    }

    @Override
    public void buscarCliente(final String name, final Callback<List<Cliente>> cmd) {
        clienteDAO.buscar(name, cmd);
    }

    @Override
    public void inicia() {
        //Se inician todos los datos en blanco
        //datos del servicio
        Servicio nuevo = new Servicio();
        servicioBridge.load(nuevo, servicio);
        //los datos de costo y bitacora no se editan en el wizard
        //cliente
        Cliente cliente = new Cliente();
        clienteBridge.load(cliente, servicio.getCliente());
        //auto
        Auto auto = new Auto();
        autoBridge.load(auto, servicio.getDatosAuto());
    }
    
    @Override
    public void nuevoServicio() {
        //como es parte del wizard se asume que el servicio es nuevo.
        Servicio nuevoServicio = new Servicio();
        servicioBridge.unload(servicio, nuevoServicio);
        //primero se tratan de guardar los datos del cliente y auto.
        Cliente nuevoCliente = new Cliente();
        clienteBridge.unload(servicio.getCliente(), nuevoCliente);
        clienteDAO.guardar(nuevoCliente);
        //el cliente nuevo o no, al terminar ya debe tener id
        nuevoServicio.setIdCliente(nuevoCliente.getId());
        //datos del auto
        Auto nuevoAuto = new Auto();
        autoBridge.unload(servicio.getDatosAuto(), nuevoAuto);
        autoDAO.guardar(nuevoAuto);
        nuevoServicio.setIdAuto(nuevoAuto.getNumeroSerie());
        //se guarda el nuevo servicio
        servicioDAO.guardar(nuevoServicio);
    }
}
