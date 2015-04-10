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

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;

/**
 *
 * @author Nekorp
 */
public class ServicioTableModel extends AbstractTableModel {

    private List<ServicioIndexVB> datos;
    
    private String formatoFecha = "dd/MM/yy HH:mm";
    
    private String[] nombresColumas = new String[]{
        "Nombre Cliente",
        "Placas",
        "Fecha Recepción",
        "Tipo",
        "Número Serie Auto",
        "Descripción del servicio"
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
        return nombresColumas.length;
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
            return datos.get(rowIndex).getTipo();
        }
        if (columnIndex == 4) {
            return datos.get(rowIndex).getNumeroSerieAuto();
        }
        if (columnIndex == 5) {
            String s = datos.get(rowIndex).getDescripcion();
            s = StringUtils.replace(s, "\n", " / ");
            return s;
        }
        return "";
    }

    public void setDatos(List<ServicioIndexVB> datos) {
        this.datos = datos;
    }
    
    
}
