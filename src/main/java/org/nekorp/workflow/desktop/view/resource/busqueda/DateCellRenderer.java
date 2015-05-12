/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Nekorp
 */
public class DateCellRenderer extends CustomBusquedaRender {
    private final String formatoFecha = "dd/MM/yy HH:mm";
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String stringValue = null;
        if (value instanceof Date) {
            Date fecha = (Date) value;
            SimpleDateFormat df = new SimpleDateFormat(this.formatoFecha);
            stringValue = df.format(fecha);
        }
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, stringValue, isSelected, hasFocus, row, col);
        return l;
    }
}
