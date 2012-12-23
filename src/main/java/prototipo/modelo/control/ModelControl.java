/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.control;

import java.util.List;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioIndex;
import prototipo.modelo.cliente.Cliente;
import prototipo.servicio.DAOCliente;
import prototipo.servicio.DAOServicio;

/**
 *
 * @author Marisa
 */
public class ModelControl {
    private DAOServicio daoServicio = new DAOServicio();
    private DAOCliente daoCliente = new DAOCliente();
    
    public String getFolioServicio(){
        return this.daoServicio.getFolio();
    }

    public void guardaServicio(Servicio servicio) {
        this.daoServicio.guarda(servicio);
    }
    
    public List<ServicioIndex> getIndiceServicios() {
        return this.daoServicio.getIndiceServicios();
    }
    
    public Servicio cargaServicio(int index) {
        return this.daoServicio.carga(this.daoServicio.getIndiceServicios().get(index).getIdServicio());
    }
    
    public void init() {
        this.daoCliente.init();
        this.daoServicio.setDaoCliente(daoCliente);
        this.daoServicio.init();
    }

    public Cliente getCliente(String idCliente) {
        Cliente buscado = new Cliente();
        buscado.setId(idCliente);
        List<Cliente> clientes = this.daoCliente.getListaClientes();
        int index = clientes.indexOf(buscado);
        if (index >= 0) {
            return clientes.get(index);
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
