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
package org.nekorp.workflow.desktop.view.resource.busqueda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.cobranza.StatusCobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;

/**
 *
 * @author Nekorp
 */
public class ServicioTableModel extends AbstractTableModel {

    private List<ServicioIndexVB> datos;
    private final int[] sizeColumn = new int[] {
        10,
        50,
        200,
        50,
        70,
        50,
        200,
        40,
        50,
        50
    };
    private final String[] nombresColumas = new String[]{
        "Servicio",
        "Status",
        "Nombre Cliente",
        "Placas",
        "Fecha Recepción",
        "Tipo",
        "Descripción del servicio",
        "Cobranza",
        "Dias Adeudo",
        "Saldo"
    };

    public ServicioTableModel() {
        datos = new ArrayList<>();
    }
    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Long.class;
        }
        if (columnIndex == 4) {
            return Date.class;
        }
        if (columnIndex == 7) {
            return StatusCobranzaMetadata.class;
        }
        if (columnIndex == 8) {
            return Integer.class;
        }
        if (columnIndex == 9) {
            return Double.class;
        }
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
            return datos.get(rowIndex).getIdServicio();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getStatus();
        }
        if (columnIndex == 2) {
            return datos.get(rowIndex).getNombreCliente();
        }
        if (columnIndex == 3) {
            return datos.get(rowIndex).getPlacasAuto();
        }
        if (columnIndex == 4) {
            return datos.get(rowIndex).getFechaRecepcion();
        }
        if (columnIndex == 5) {
            return datos.get(rowIndex).getTipo();
        }
        if (columnIndex == 6) {
            String s = (String) datos.get(rowIndex).getDescripcion();;
            s = StringUtils.replace(s, "\n", " ");
            return s;
        }
        if (columnIndex == 7) {
            return datos.get(rowIndex).getCobranzaMetadata().getStatus();
        }
        if (columnIndex == 8) {
            return datos.get(rowIndex).getCobranzaMetadata().getDiasUltimoPago();
        }
        if (columnIndex == 9) {
            return datos.get(rowIndex).getCobranzaMetadata().getSaldo().doubleValue();
        }
        return "";
    }
    
    public int[] getSizeColumn() {
        return this.sizeColumn;
    }
    
    public void setDatos(List<ServicioIndexVB> datos) {
        this.datos = datos;
    }
}
