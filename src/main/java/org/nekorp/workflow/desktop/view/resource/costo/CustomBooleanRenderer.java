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
package org.nekorp.workflow.desktop.view.resource.costo;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

/**
 * @author Nekorp
 */
public class CustomBooleanRenderer extends CustomCostosRender implements TableCellRenderer, UIResource {

    private JCheckBox viewComponent;

    public CustomBooleanRenderer() {
        super();
        viewComponent = new JCheckBox();
        viewComponent.setHorizontalAlignment(JLabel.CENTER);
        viewComponent.setBorderPainted(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        viewComponent.setSelected((value != null && ((Boolean) value).booleanValue()));
        applySelectionStyle(viewComponent, table.isCellEditable(row, column), isSelected, hasFocus);
        return viewComponent;
    }
}
