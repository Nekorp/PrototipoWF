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
package org.nekorp.workflow.desktop.control.imp;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.WorkflowApp;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.rest.util.Callback;
import org.nekorp.workflow.desktop.servicio.EditorMonitor;
import org.nekorp.workflow.desktop.servicio.EventoServicioFactory;
import org.nekorp.workflow.desktop.servicio.GeneradorReporte;
import org.nekorp.workflow.desktop.servicio.imp.ProxyUtil;
import org.nekorp.workflow.desktop.view.model.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.ServicioIndex;
import org.nekorp.workflow.desktop.view.model.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("application")
public class WorkflowAppPrototipo implements WorkflowApp{
    
    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Resource(name="application")
    private WorkflowAppPrototipo mySelf;
    @Autowired
    private GeneradorReporte generadorReporte;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private EdicionServicioMetadata metadataServicio;
    @Autowired
    private EditorMonitor editorMonitor;
    @Autowired
    private ProxyUtil proxyUtil;
    @Autowired
    private EventoServicioFactory eventoFactory;
    
    private String oldId;
    
    @Override
    public void startApliacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    //@Override
    public void nuevoServicio() {
        throw new UnsupportedOperationException("Not supported yet.");
//        WorkflowAppPrototipo.LOGGER.debug("Hacer un nuevo servicio");
//        String folio = modelControl.getFolioServicio();
//        viewServicioModel.setId(folio);
//        if (viewClienteModel.getId().isEmpty()) { //se supone paso por el wizard
//            //Cliente nuevo = this.modelControl.nuevoCliente();
//            mySelf.guardarCliente();
//            //this.viewClienteModel.setId(nuevo.getId());
//            this.viewServicioModel.setIdCliente(nuevo.getId());
//        }
//        addEventoCreacion();
//        mySelf.guardaServicio();
//        metadataServicio.setServicioCargado(true);
    }
    
    private void addEventoCreacion() {
        throw new UnsupportedOperationException("Not supported yet.");
//        EventoSistemaVB eventoDeCreacion = eventoFactory.creaEvento(EventoSistemaVB.class);
//        eventoDeCreacion.setNombre("Inicio del Servicio");
//        List<EventoVB> eventos = viewServicioModel.getBitacora().getEventos();
//        eventos.add(eventoDeCreacion);
//        viewServicioModel.getBitacora().setEventos(eventos);
    }

    @Override
    public void unloadServicio() {
        throw new UnsupportedOperationException("Not supported yet.");
//        this.oldId = viewServicioModel.getId();
//        Servicio nuevo = new Servicio();
//        proxyUtil.copiarPropiedades(nuevo, viewServicioModel);
//        mySelf.unloadCliente();
//        metadataServicio.setServicioCargado(false);
//        editorMonitor.clear();
    }
    
    @Override
    public void reloadServicio() {
        throw new UnsupportedOperationException("Not supported yet.");
//        if (this.oldId != null && !this.oldId.isEmpty()) {
//            mySelf.cargaServicio(oldId);
//            oldId = null;
//        } else {
//            metadataServicio.setServicioCargado(false);
//            editorMonitor.clear();
//        }
    }
    
    @Override
    public void guardaServicio() {
        throw new UnsupportedOperationException("Not supported yet.");
//        Servicio datos = new Servicio();
//        proxyUtil.copiarPropiedades(viewServicioModel, datos);
//        modelControl.guardaServicio(datos);
//        this.guardarCliente();
//        editorMonitor.clear();
    }

    @Override
    public List<ServicioIndex> getIndexServicios() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return this.modelControl.getIndiceServicios();
        //return null;
    }

    @Override
    public void cargaServicio(String idServicio) {
        throw new UnsupportedOperationException("Not supported yet.");
//        Servicio cargado = this.modelControl.cargaServicio(idServicio);
//        proxyUtil.copiarPropiedades(cargado, this.viewServicioModel);
//        Cliente cliente = this.modelControl.getCliente(cargado.getIdCliente());
//        if (cliente != null) {
//            mySelf.loadCliente(cliente);
//        } else {
//            mySelf.unloadCliente();
//        }
//        editorMonitor.clear();
//        metadataServicio.setServicioCargado(true);
    }
    
    @Override
    public void loadCliente(Cliente origen) {
        throw new UnsupportedOperationException("Not supported yet.");
//        proxyUtil.copiarPropiedades(origen, this.viewServicioModel.getCliente());
//        //this.viewServicioModel.setIdCliente(origen.getId());
//        this.editorMonitor.clear(this.viewServicioModel.getCliente());
//        this.editorMonitor.clear(this.viewServicioModel.getCliente().getDomicilio());
    }
    
//    @Override
//    public Cliente loadByName(String name) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    
    @Override
    public void unloadCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
//        Cliente vacio = new Cliente();
//        proxyUtil.copiarPropiedades(vacio, this.viewClienteModel);
//        this.viewServicioModel.setIdCliente(vacio.getId());
    }

    @Override
    public void nuevoCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
//        this.editorMonitor.clear(viewClienteModel);
//        this.editorMonitor.clear(viewClienteModel.getDomicilio());
//        Cliente nuevo = this.modelControl.nuevoCliente();
//        proxyUtil.copiarPropiedades(nuevo, this.viewClienteModel);
//        this.viewServicioModel.setIdCliente(nuevo.getId());
    }

    @Override
    public void guardarCliente() {
        throw new UnsupportedOperationException("Not supported yet.");
//        Cliente cliente = new Cliente();// = this.modelControl.getCliente(this.viewClienteModel.getId());
//        //if (cliente != null) {
//            proxyUtil.copiarPropiedades(this.viewClienteModel, cliente);
//        //}
//        this.modelControl.guardaClientes();
//        this.editorMonitor.clear(viewClienteModel);
//        this.editorMonitor.clear(viewClienteModel.getDomicilio());
    }

    @Override
    public List<Cliente> getClientes() {
        throw new UnsupportedOperationException("Not supported yet.");
        //return clienteDAO.consultaTodos();
    }
    
    @Override
    public void buscarCliente(String name, final Callback cmd) {
        throw new UnsupportedOperationException("Not supported yet.");
//        LinkedList<Cliente> r = new LinkedList<>();
//        if (name.length() == 0) {
//            return r;
//        }
//        for (Cliente x: this.getClientes()) {
//            if (x.getNombre().toLowerCase().equals(name.toLowerCase())) {
//                return new LinkedList<>();
//            }
//            if (x.getNombre().toLowerCase().startsWith(name.toLowerCase())) {
//                r.add(x);
//            }
//        }
//        return r;
    }

    @Override
    public void generaReporte(File destination) {
        throw new UnsupportedOperationException("Not supported yet.");
        //this.generadorReporte.generaReporte(destination);
    }
    
}
