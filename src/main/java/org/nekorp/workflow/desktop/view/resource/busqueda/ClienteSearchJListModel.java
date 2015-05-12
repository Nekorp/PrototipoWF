/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

package org.nekorp.workflow.desktop.view.resource.busqueda;

import javax.swing.AbstractListModel;
import technology.tikal.customers.model.Customer;
import technology.tikal.customers.model.name.OrganizationName;


/**
 * @author Nekorp
 */
public class ClienteSearchJListModel extends AbstractListModel {

    private Customer[] datos;
    
    public ClienteSearchJListModel() {
        datos = new Customer[0];
    }
    
    @Override
    public int getSize() {
        return datos.length;
    }
    
    @Override
    public Object getElementAt(int i) {
        OrganizationName name = (OrganizationName) datos[i].getName();
        return name.getName();
    }
    
    public void updateData(Customer[] data) {
        this.datos = data;
        this.fireContentsChanged(this, 0, datos.length);
    }
    
    public Customer getClientAt(int i) {
        return datos[i];
    }
}
