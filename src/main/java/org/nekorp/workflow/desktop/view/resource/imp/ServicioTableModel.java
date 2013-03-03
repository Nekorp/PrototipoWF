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
package org.nekorp.workflow.desktop.view.resource.imp;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.nekorp.workflow.desktop.view.model.ServicioIndexVB;

/**
 *
 * 
 */
public class ServicioTableModel extends AbstractTableModel {

    private List<ServicioIndexVB> datos;
    
    private String formatoFecha = "dd/MM/yy HH:mm";
    
    private String[] nombresColumas = new String[]{
        "Nombre Cliente",
        "Placas",
        "Fecha Recepción",
        "Número Servicio",
        "Número Cliente",
        "Número Serie Auto"
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
        return this.datos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return datos.get(rowIndex).getNombreCliente();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getPlacasAuto();
        }
        if (columnIndex == 2) {
            if (datos.get(rowIndex).getFechaRecepcion() != null) {
                SimpleDateFormat df = new SimpleDateFormat(this.formatoFecha);
                return df.format(datos.get(rowIndex).getFechaRecepcion());
            } else {
                return null;
            }
        }
        if (columnIndex == 3) {
            return datos.get(rowIndex).getIdServicio();
        }
        if (columnIndex == 4) {
            return datos.get(rowIndex).getIdCliente();
        }
        if (columnIndex == 5) {
            return datos.get(rowIndex).getNumeroSerieAuto();
        }
        return "";
    }

    public void setDatos(List<ServicioIndexVB> datos) {
        this.datos = datos;
    }
    
    
}
