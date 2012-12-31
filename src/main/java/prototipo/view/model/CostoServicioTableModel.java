/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import prototipo.modelo.costo.RegistroCosto;

/**
 *
 * @author Marisa
 */
public class CostoServicioTableModel extends AbstractTableModel {

    private List<RegistroCosto> datos;
    
    private String[] nombresColumas = new String[]{
        "Tipo",
        "Concepto",
        "Cantidad",
        "Precio Unitario",
        "% Utilidad",
        "PrecioCliente",
        "Subtotal"
    };
    
    public CostoServicioTableModel() {
        this.datos = new LinkedList<>();
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    @Override
    public int getRowCount() {
        return this.datos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return datos.get(rowIndex).getTipo();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getConcepto();
        }
        if (columnIndex == 2) {
            return datos.get(rowIndex).getCantidad();
        }
        if (columnIndex == 3) {
            return datos.get(rowIndex).getPrecioUnitario();
        }
        if (columnIndex == 4) {
            return datos.get(rowIndex).getUtilidad();
        }
        if (columnIndex == 5) {
            return datos.get(rowIndex).getPrecioCliente();
        }
        if (columnIndex == 6) {
            return datos.get(rowIndex).getSubtotal();
        }
        return "";
    }

    public void addRegistro(RegistroCosto registro) {
        this.datos.add(registro);
        this.fireTableRowsInserted(this.datos.size(), this.datos.size());
    }
    
    
}
