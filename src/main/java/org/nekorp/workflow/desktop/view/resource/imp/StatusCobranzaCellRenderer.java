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
package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaWarningLevel;

/**
 *
 * @author Nekorp
 */
public class StatusCobranzaCellRenderer extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        //Get the status for the current row.
        if (!isSelected) {
            ServicioTableModel tableModel = (ServicioTableModel) table.getModel();
            CobranzaWarningLevel warnLevel = (CobranzaWarningLevel) tableModel.getValueAt(table.convertRowIndexToModel(row), 9);
            switch (warnLevel) {
                case info:
                    l.setBackground(Color.WHITE);
                break;

                case warn:
                    l.setBackground(Color.YELLOW);
                break;

                case urgent:
                    l.setBackground(Color.RED);
                break;

                default:
                    l.setBackground(Color.WHITE);
                break;
            }
        }
        //Return the JLabel which renders the cell.
        return l;
    }
}
