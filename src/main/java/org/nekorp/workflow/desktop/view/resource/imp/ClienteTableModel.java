/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.resource.imp;

import javax.swing.table.AbstractTableModel;
import technology.tikal.customers.model.ClienteMx;
import technology.tikal.customers.model.Customer;
import technology.tikal.customers.model.name.OrganizationName;

/**
 *
 * @author Nekorp 
 */
public class ClienteTableModel extends AbstractTableModel {

    private Customer[] datos;
    
    private String[] nombresColumas = new String[]{
        "Número Cliente",
        "Nombre Cliente",
        "RFC"
    };

    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return this.datos.length;
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClienteMx pojo = (ClienteMx) datos[rowIndex];
        if (columnIndex == 0) {
            return pojo.getId();
        }
        if (columnIndex == 1) {
            OrganizationName name = (OrganizationName) pojo.getName();
            return name.getName();
        }
        if (columnIndex == 2) {
            return pojo.getRfc();
        }
        return "";
    }

    public void setDatos(Customer[] datos) {
        this.datos = datos;
    }
    
    
}
