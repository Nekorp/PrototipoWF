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

package prototipo.view.resource.imp;

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import org.nekorp.workflow.desktop.modelo.cliente.Cliente;


/**
 *
 */
public class ClienteSearchJListModel extends AbstractListModel {

    private List<Cliente> datos;
    
    public ClienteSearchJListModel() {
        datos = new LinkedList<>();
    }
    
    @Override
    public int getSize() {
        return datos.size();
    }
    
    @Override
    public Object getElementAt(int i) {
        return datos.get(i).getNombre();
    }
    
    public void updateData(List<Cliente> data) {
        this.datos = data;
        this.fireContentsChanged(this, 0, datos.size());
    }
    
    public Cliente getClientAt(int i) {
        return datos.get(i);
    }
}
