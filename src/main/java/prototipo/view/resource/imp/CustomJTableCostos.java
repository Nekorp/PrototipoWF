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

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.modelo.currency.Moneda;
import prototipo.view.model.CostoServicioTableModel;

/**
 *
 *
 */
public class CustomJTableCostos extends JTable {

    private CostoServicioTableModel modelo;
    private JComboBox comboHP;
    private DefaultCellEditor editorHP;
    private String[] tiposHP = new String[] {
        "Mano de Obra",
        "Insumo"
    };
    private JComboBox comboM;
    private DefaultCellEditor editorM;
    private String[] tiposM = new String[] {
        "Mano de Obra",
        "Refacciones"
    };
    private MonedaTextField editorMontos;
    private MonedaTableCellRender renderMontos;
    public CustomJTableCostos() {
        this.comboHP = new JComboBox(this.tiposHP);
        this.editorHP = new DefaultCellEditor(comboHP);
        this.comboM = new JComboBox(this.tiposM);
        this.editorM = new DefaultCellEditor(comboM);
        editorMontos = new MonedaTextField();
        renderMontos = new MonedaTableCellRender();
        this.setDefaultEditor(Moneda.class, new DefaultCellEditor(editorMontos));
        this.setDefaultRenderer(Moneda.class, renderMontos);
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
            RegistroCosto registro = this.modelo.getDatos().get(row);
            if (registro.getTipo().equals("Hojalateria y Pintura")) {
                return this.editorHP;
            }
            if (registro.getTipo().equals("Mecanica")) {
                return this.editorM;
            }
        }
        return super.getCellEditor(row, column);
    }
    
}
