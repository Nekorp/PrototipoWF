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
import prototipo.modelo.currency.Moneda;
import prototipo.view.model.CostoServicioTableModel;

/**
 *
 * @author Marisa
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
