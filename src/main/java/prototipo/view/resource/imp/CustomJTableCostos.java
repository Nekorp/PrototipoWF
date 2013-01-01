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

    private CostoServicioTableModel modelo;
    private String[] tiposHP = new String[] {
        "Mano de Obra",
        "Insumo"
    };
    private String[] tiposM = new String[] {
        "Mano de Obra",
        "Refacciones"
    };
    public CustomJTableCostos() {
        super();
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
            JComboBox combo = null;
            if (registro.getTipo().equals("Hojalateria y Pintura")) {
                combo = new JComboBox(tiposHP);
            }
            if (registro.getTipo().equals("Mecanica")) {
                combo = new JComboBox(tiposM);
            }
            DefaultCellEditor editor = new DefaultCellEditor(combo);
            return editor;
        }
        return super.getCellEditor(row, column);
    }
    
}
