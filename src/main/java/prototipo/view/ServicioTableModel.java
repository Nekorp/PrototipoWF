/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import prototipo.modelo.ServicioIndex;

/**
 *
 * @author Marisa
 */
public class ServicioTableModel extends AbstractTableModel {

    private List<ServicioIndex> datos;
    
    private String formatoFecha = "dd/MM/yy";
    
    private String[] nombresColumas = new String[]{
        "Nombre Cliente",
        "Placas",
        "Fecha Recepción",
        "Número Servicio",
        "Número Cliente",
        "Número Serie Auto"
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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return datos.get(rowIndex).getNombreCliente();
        }
        if (columnIndex == 1) {
            return datos.get(rowIndex).getPlacasAuto();
        }
        if (columnIndex == 2) {
            if (datos.get(rowIndex).getFechaRecepcion() != null) {
                SimpleDateFormat df = new SimpleDateFormat(this.formatoFecha);
                return df.format(datos.get(rowIndex).getFechaRecepcion());
            } else {
                return null;
            }
        }
        if (columnIndex == 3) {
            return datos.get(rowIndex).getIdServicio();
        }
        if (columnIndex == 4) {
            return datos.get(rowIndex).getIdCliente();
        }
        if (columnIndex == 5) {
            return datos.get(rowIndex).getNumeroSerieAuto();
        }
        return "";
    }

    public void setDatos(List<ServicioIndex> datos) {
        this.datos = datos;
    }
    
    
}
