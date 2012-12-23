/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prototipo.modelo.cliente.Cliente;

/**
 * 
 * @author Marisa
 */
public class DAOCliente {
    private List<Cliente> clientes = new LinkedList<>();

    public Cliente nuevoCliente() {
        Cliente response = new Cliente();
        if (this.clientes.size() > 0) {
            int last = Integer.parseInt(this.clientes.get(clientes.size() - 1)
                    .getId());
            last = last + 1;
            response.setId(last + "");
        } else {
            response.setId("1");
        }
        this.clientes.add(response);
        return response;
    }

    public void guardar() {
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream("system/cliente.xml")));
            for (Cliente obj : clientes) {
                e.writeObject(obj);
            }
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void init() {
        XMLDecoder d = null;
        Cliente cliente;
        try {
            d = new XMLDecoder(new BufferedInputStream(new FileInputStream(
                    "system/cliente.xml")));
            while (true) {
                cliente = (Cliente) d.readObject();
                this.clientes.add(cliente);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOServicio.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getMessage();
        } finally {
            if (d != null) {
                d.close();
            }
        }
    }

    public List<Cliente> getListaClientes() {
        List<Cliente> response = new LinkedList<>();
        for (Cliente obj : clientes) {
            response.add(obj);
        }
        return response;
    }
}
