/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nekorp.workflow.desktop.data.access.rest;

import java.util.List;
import org.nekorp.workflow.desktop.data.access.ClienteDAO;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;
import org.nekorp.workflow.desktop.modelo.pagination.PaginaCliente;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ClienteDAOImp extends RestDAOTemplate implements ClienteDAO {
    
    @Override
    public void save(Cliente dato) {
    }

    @Override
    public List<Cliente> consultaClientes() {
        PaginaCliente r = getTemplate().getForObject(getRootUlr() + "/clientes", PaginaCliente.class);
        return r.getItems();
    }

}
