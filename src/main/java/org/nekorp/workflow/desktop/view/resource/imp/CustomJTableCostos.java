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

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionGeneralRegistroCosto;

/**
 *
 *
 */
public class CustomJTableCostos extends JTable {

    private CostoServicioTableModel modelo;
    private ValidacionGeneralRegistroCosto validacionGeneralRegistroCosto;
    private JComboBox comboSubtipo;
    private DefaultCellEditor editorSubtipo;
    private MonedaTextField editorMontos;
    private MonedaTableCellRender renderMontos;
    private final KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0); 
    
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
        setDefaultRenderer(String.class, new CustomRender());
        setDefaultRenderer(Integer.class, new CustomIntegerRender());
        setDefaultRenderer(Boolean.class, new CustomBooleanRenderer());
    }

    @Override
    public void setModel(TableModel dataModel) {
        if (dataModel instanceof CostoServicioTableModel) {
            this.modelo = (CostoServicioTableModel) dataModel;
        }
        super.setModel(dataModel);
    }
    
    public void setValidacionGeneralRegistroCosto(ValidacionGeneralRegistroCosto validacionGeneralRegistroCosto) {
        this.validacionGeneralRegistroCosto = validacionGeneralRegistroCosto;
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
    
    @Override
    public void changeSelection(final int rowIndex, final int columnIndex, boolean toggle, boolean extend) { 
        int newRowIndex = rowIndex;
        int newColumnIndex = columnIndex;
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if(currentEvent instanceof KeyEvent){ 
            KeyEvent ke = (KeyEvent)currentEvent; 
            if (ke.getSource() != this) {
                return;
            }
            if (KeyStroke.getKeyStrokeForEvent(ke).equals(tabKeyStroke)) {
                newRowIndex = 0;
                newColumnIndex = 0;
                boolean siguiente = false;
                int offsetColumn = columnIndex;
                for (int i = rowIndex; i < getRowCount() && !siguiente; i++) {
                    for (int j = offsetColumn; j < getColumnCount() && !siguiente; j++) {
                        if (isCellEditable(i, j)) {
                            siguiente = true;
                            newRowIndex = i;
                            newColumnIndex = j;
                        }
                    }
                    offsetColumn = 0;
                }
                if(newRowIndex == 0 && newColumnIndex == 0 && validacionGeneralRegistroCosto.isValido()) {
                    if (getRowCount() > 0) {
                        RegistroCostoVB ultimo = modelo.getDatos().get(getRowCount() - 1);
                        modelo.addRegistro(ultimo.getTipo());
                        RegistroCostoVB nuevo = modelo.getDatos().get(getRowCount() - 1);
                        nuevo.setSubtipo(ultimo.getSubtipo());
                    }
                    newRowIndex = getRowCount() - 1;
                }
            }
        }
        super.changeSelection(newRowIndex, newColumnIndex, toggle, extend);
    }
}
