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
package org.nekorp.workflow.desktop.view.resource.imp;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Nekorp
 */
public class CustomRender extends DefaultTableCellRenderer {

    private Border selectionBorder;
    private Border empyBorder;
    
    public CustomRender() {
        selectionBorder = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0));
        empyBorder = javax.swing.BorderFactory.createEmptyBorder();
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent r = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.applySelectionStyle(r, table.isCellEditable(row, column), isSelected, hasFocus);
        return r;
    }
    
    public void applySelectionStyle(JComponent viewComponent, boolean editable, boolean isSelected, boolean hasFocus) {
        viewComponent.setForeground(Color.BLACK);
        if (!editable) {
            viewComponent.setBorder(empyBorder);
            if (isSelected) {
                viewComponent.setBackground(new Color(200,230,230));
            } else {
                viewComponent.setBackground(new Color(240,240,240));
            }
        } else {
            if (hasFocus) {
                viewComponent.setBorder(selectionBorder);
            } else {
                viewComponent.setBorder(empyBorder);
            }
            if (isSelected) {
                viewComponent.setBackground(new Color(230,246,246));
            } else {
                viewComponent.setBackground(Color.WHITE);
            }
        }
    }
}
