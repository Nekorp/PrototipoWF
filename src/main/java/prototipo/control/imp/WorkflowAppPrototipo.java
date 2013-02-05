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
package prototipo.control.imp;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import prototipo.control.WorkflowApp;
import prototipo.modelo.Servicio;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.EditorMonitor;
import prototipo.servicio.GeneradorReporte;
import prototipo.servicio.imp.ModelControl;
import prototipo.servicio.imp.ProxyUtil;
import prototipo.view.model.EdicionServicioMetadata;
import prototipo.view.model.ServicioIndex;
import prototipo.view.model.ServicioVB;
import prototipo.view.model.cliente.ClienteVB;

@Controller("application")
public class WorkflowAppPrototipo implements WorkflowApp{
    
    private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
    @Resource(name="application")
    private WorkflowAppPrototipo mySelf;
    @Autowired
    private GeneradorReporte generadorReporte;
    @Autowired
    private ModelControl modelControl;
    @Autowired
    private ServicioVB viewServicioModel;
    @Autowired
    private ClienteVB viewClienteModel;
    @Autowired
    private EdicionServicioMetadata metadataServicio;
    @Autowired
    private EditorMonitor editorMonitor;
    @Autowired
    private ProxyUtil proxyUtil;
    
    private String oldId;
    
    @Override
    public void startApliacion() {
        WorkflowAppPrototipo.LOGGER.debug("iniciando aplicacion");
    }

    @Override
    public void closeAplicacion() {
        System.exit(0);
    }

    @Override
    public void nuevoServicio() {
        WorkflowAppPrototipo.LOGGER.debug("Hacer un nuevo servicio");
        String folio = modelControl.getFolioServicio();
        viewServicioModel.setId(folio);
        if (viewClienteModel.getId().isEmpty()) {
            Cliente nuevo = this.modelControl.nuevoCliente();
            this.viewClienteModel.setId(nuevo.getId());
            this.viewServicioModel.setIdCliente(nuevo.getId());
            mySelf.guardarCliente();
        }
        mySelf.guardaServicio();
        metadataServicio.setServicioCargado(true);
    }

    @Override
    public void unloadServicio() {
        this.oldId = viewServicioModel.getId();
        Servicio nuevo = new Servicio();
        proxyUtil.copiarPropiedades(nuevo, viewServicioModel);
        mySelf.unloadCliente();
        metadataServicio.setServicioCargado(true);
        editorMonitor.clear();
    }
    
    @Override
    public void reloadServicio() {
        if (this.oldId != null && !this.oldId.isEmpty()) {
            mySelf.cargaServicio(oldId);
            oldId = null;
        } else {
            editorMonitor.clear();
        }
    }
    
    @Override
    public void guardaServicio() {
        Servicio datos = new Servicio();
        proxyUtil.copiarPropiedades(viewServicioModel, datos);
        modelControl.guardaServicio(datos);
        this.guardarCliente();
        editorMonitor.clear();
    }

    @Override
    public List<ServicioIndex> getIndexServicios() {
        return this.modelControl.getIndiceServicios();
    }

    @Override
    public void cargaServicio(String idServicio) {
        Servicio cargado = this.modelControl.cargaServicio(idServicio);
        proxyUtil.copiarPropiedades(cargado, this.viewServicioModel);
        Cliente cliente = this.modelControl.getCliente(cargado.getIdCliente());
        if (cliente != null) {
            mySelf.loadCliente(cliente);
        } else {
            mySelf.unloadCliente();
        }
        editorMonitor.clear();
        metadataServicio.setServicioCargado(true);
    }
    
    @Override
    public void loadCliente(Cliente origen) {
        proxyUtil.copiarPropiedades(origen, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(origen.getId());
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
    }
    
    @Override
    public void loadByName(String name) {
        Cliente busqueda = null;
        for (Cliente x: this.getClientes()) {
            if (x.getNombre().toLowerCase().equals(name.toLowerCase())) {
                busqueda = x;
            }
        }
        if (busqueda != null) {
            mySelf.loadCliente(busqueda);
        } else {
            mySelf.unloadCliente();
            this.viewClienteModel.setNombre(name);
        }
    }
    
    @Override
    public void unloadCliente() {
        Cliente vacio = new Cliente();
        proxyUtil.copiarPropiedades(vacio, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(vacio.getId());
    }

    @Override
    public void nuevoCliente() {
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
        Cliente nuevo = this.modelControl.nuevoCliente();
        proxyUtil.copiarPropiedades(nuevo, this.viewClienteModel);
        this.viewServicioModel.setIdCliente(nuevo.getId());
    }

    @Override
    public void guardarCliente() {
        Cliente cliente = this.modelControl.getCliente(this.viewClienteModel.getId());
        if (cliente != null) {
            proxyUtil.copiarPropiedades(this.viewClienteModel, cliente);
        }
        this.modelControl.guardaClientes();
        this.editorMonitor.clear(viewClienteModel);
        this.editorMonitor.clear(viewClienteModel.getDomicilio());
    }

    @Override
    public List<Cliente> getClientes() {
        return this.modelControl.getClientes();
    }
    
    @Override
    public List<Cliente> buscarCliente(String name) {
        LinkedList<Cliente> r = new LinkedList<>();
        if (name.length() == 0) {
            return r;
        }
        for (Cliente x: this.getClientes()) {
            if (x.getNombre().toLowerCase().equals(name.toLowerCase())) {
                return new LinkedList<>();
            }
            if (x.getNombre().toLowerCase().startsWith(name.toLowerCase())) {
                r.add(x);
            }
        }
        return r;
    }

    @Override
    public void generaReporte(File destination) {
        this.generadorReporte.generaReporte(destination);
    }
    
}
