/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.DAOCliente;
import prototipo.servicio.DAOServicio;

/**
 * 
 * @author Marisa
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

    public Servicio cargaServicio(ServicioIndex index) {
        return this.daoServicio.carga(index.getIdServicio());
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
