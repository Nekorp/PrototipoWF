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
package prototipo.view.resource.imp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
import prototipo.view.model.currency.MonedaVB;

/**
 *
 *
 */
public class CustomJTableCostos extends JTable {

    private CostoServicioTableModel modelo;
    private JComboBox comboSubtipo;
    private DefaultCellEditor editorSubtipo;
    private MonedaTextField editorMontos;
    private MonedaTableCellRender renderMontos;
    public CustomJTableCostos() {
        this.comboSubtipo = new JComboBox(new String[] {
            "Mano de Obra",
            "Refacciones",
            "Insumo"
        });
        this.editorSubtipo = new DefaultCellEditor(comboSubtipo);
        editorMontos = new MonedaTextField();
        renderMontos = new MonedaTableCellRender();
        this.setDefaultEditor(MonedaVB.class, new DefaultCellEditor(editorMontos));
        this.setDefaultRenderer(MonedaVB.class, renderMontos);
    }

    @Override
    public void setModel(TableModel dataModel) {
        if (dataModel instanceof CostoServicioTableModel) {
            this.modelo = (CostoServicioTableModel) dataModel;
        }
        super.setModel(dataModel);
    }
    
    
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if (column == 0 && this.modelo != null) {
            return this.editorSubtipo;
        }
        return super.getCellEditor(row, column);
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
        boolean result = super.editCellAt(row, column, e);
        setSelected(e);
        return result;
    }
    
    private void setSelected(EventObject e) {
        final Component editor = getEditorComponent();
        if (editor == null || !(editor instanceof JTextComponent)) {
            return;
        }

        if (e == null) {
            ((JTextComponent) editor).selectAll();
        }

        //  Typing in the cell was used to activate the editor

        if (e instanceof KeyEvent) {
            ((JTextComponent) editor).selectAll();
            return;
        }

        //  F2 was used to activate the editor

        if (e instanceof ActionEvent) {
            ((JTextComponent) editor).selectAll();
            return;
        }

        //  A mouse click was used to activate the editor.
        //  Generally this is a double click and the second mouse click is
        //  passed to the editor which would remove the text selection unless
        //  we use the invokeLater()

        if (e instanceof MouseEvent) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ((JTextComponent) editor).selectAll();
                }
            });
        }
    }
    
    
}
