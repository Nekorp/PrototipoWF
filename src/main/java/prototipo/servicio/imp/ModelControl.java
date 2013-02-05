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
package prototipo.servicio.imp;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import prototipo.modelo.Servicio;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.DAOCliente;
import prototipo.servicio.DAOServicio;
import prototipo.view.model.ServicioIndex;

/**
 * 
 * 
 */
@Service
public class ModelControl {
    private DAOServicio daoServicio = new DAOServicio();
    private DAOCliente daoCliente = new DAOCliente();

    public String getFolioServicio() {
        return this.daoServicio.getFolio();
    }

    public void guardaServicio(Servicio servicio) {
        this.daoServicio.guarda(servicio);
    }

    public List<ServicioIndex> getIndiceServicios() {
        return this.daoServicio.getIndiceServicios();
    }

    public Servicio cargaServicio(String idServicio) {
        return this.daoServicio.carga(idServicio);
    }
    @PostConstruct
    public void init() {
        this.daoCliente.init();
        this.daoServicio.setDaoCliente(daoCliente);
        this.daoServicio.init();
    }

    public Cliente getCliente(String idCliente) {
        List<Cliente> clientes = this.daoCliente.getListaClientes();
        for (Cliente x: clientes) {
            if (x.getId().equals(idCliente)){
                return x;
            }
        }
        return null;
    }

    public List<Cliente> getClientes() {
        return this.daoCliente.getListaClientes();
    }

    public Cliente nuevoCliente() {
        return this.daoCliente.nuevoCliente();
    }

    public void guardaClientes() {
        this.daoCliente.guardar();
    }
}
