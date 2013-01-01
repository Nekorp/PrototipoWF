/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource.imp;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.view.model.CostoServicioTableModel;

/**
 *
 * @author Marisa
 */
public class CustomJTableCostos extends JTable {

    private JComboBox comboHP;
    private DefaultCellEditor editorHP;
    private JComboBox comboM;
    private DefaultCellEditor editorM;
    private CostoServicioTableModel modelo;
    
    public CustomJTableCostos() {
        comboHP = new JComboBox(new String[] {
            "Mano de Obra",
            "Insumo"
        });
        editorHP = new DefaultCellEditor(comboHP);
        comboM = new JComboBox(new String[] {
            "Mano de Obra",
            "Refacciones"
        });
        editorM = new DefaultCellEditor(comboM);
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
