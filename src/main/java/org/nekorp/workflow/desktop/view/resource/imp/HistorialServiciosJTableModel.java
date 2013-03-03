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

package org.nekorp.workflow.desktop.view.resource.imp;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.nekorp.workflow.desktop.view.model.historial.RegistoHistoriaServicioVB;

/**
 *
 */
public class HistorialServiciosJTableModel extends AbstractTableModel {

    private String formatoFecha = "dd/MM/yy HH:mm";
    
    private List<RegistoHistoriaServicioVB> datos;
    
    private String[] nombresColumas = new String[]{
        "Número de servicio",
        "Descripción",
        "Fecha de inicio de servicio",
        "Costo"
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
        return this.nombresColumas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return datos.get(rowIndex).getNumeroServicio();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getDescripcionServicio();
        }
        if (columnIndex == 2) {
            if (datos.get(rowIndex).getFechaInicio() != null) {
                SimpleDateFormat df = new SimpleDateFormat(this.formatoFecha);
                return df.format(datos.get(rowIndex).getFechaInicio());
            } else {
                return null;
            }
        }
        if (columnIndex == 3) {
            return datos.get(rowIndex).getCosto().toString();
        }
        return "";
    }

    public void setDatos(List<RegistoHistoriaServicioVB> datos) {
        this.datos = datos;
    }
}
