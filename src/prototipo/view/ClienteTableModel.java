/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import prototipo.modelo.cliente.Cliente;

/**
 *
 * @author Marisa
 */
public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> datos;
    
    private String[] nombresColumas = new String[]{
        "Número Cliente",
        "Nombre Cliente",
        "RFC"
    };

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
        return false;
    }
    
    @Override
    public int getRowCount() {
        return this.datos.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return datos.get(rowIndex).getId();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getNombre();
        }
        if (columnIndex == 2) {
            return datos.get(rowIndex).getRfc();
        }
        return "";
    }

    public void setDatos(List<Cliente> datos) {
        this.datos = datos;
    }
    
    
}
