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
