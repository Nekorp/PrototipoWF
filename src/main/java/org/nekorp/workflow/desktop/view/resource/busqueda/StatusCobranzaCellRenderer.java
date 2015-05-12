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

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaWarningLevel;
import org.nekorp.workflow.desktop.view.model.cobranza.StatusCobranzaMetadata;

/**
 *
 * @author Nekorp
 */
public class StatusCobranzaCellRenderer extends CustomBusquedaRender {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String stringValue = null;
        CobranzaWarningLevel warnLevel = null;
        if (value instanceof StatusCobranzaMetadata) {
            StatusCobranzaMetadata status = (StatusCobranzaMetadata) value;
            stringValue = status.getStatusCobranza();
            warnLevel = status.getWarningLevel();
        }
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, stringValue, isSelected, hasFocus, row, col);
        if (warnLevel != null) {
            switch (warnLevel) {
                case info:
                    if (!isSelected) {
                        l.setBackground(Color.WHITE);
                    }
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
        return l;
    }
}
