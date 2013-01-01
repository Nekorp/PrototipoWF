/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.model;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.servicio.RegistroCostoFactory;
import prototipo.servicio.imp.ProxyUtil;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 * @author Marisa
 */
@Component("costoServicioTableModel")
public class CostoServicioTableModel extends AbstractTableModel implements Bindable {
    private String[] nombresColumas = new String[]{
        "Tipo",
        "Concepto",
        "Cantidad",
        "Precio Unitario",
        "% Utilidad",
        "PrecioCliente",
        "Subtotal"
    };
    @Autowired
    private RegistroCostoFactory factory;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ProxyUtil proxyUtil;
    @Autowired
    private Servicio viewServicioModel;
    private LinkedList<Object> ignore;
    
    private List<RegistroCosto> datos;
    
    private List<String> atributos;
    
    public CostoServicioTableModel() {
        this.ignore = new LinkedList<>();
        this.datos = new LinkedList<>();
        atributos = new LinkedList<>();
        atributos.add("subtipo");
        atributos.add("concepto");
        atributos.add("cantidad");
        atributos.add("precioUnitario");
        atributos.add("utilidad");
        atributos.add("precioCliente");
        atributos.add("subtotal");
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        try {
            RegistroCosto example = new RegistroCosto();
            return PropertyUtils.getPropertyType(example, this.atributos.get(columnIndex));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos");
        }
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
        return nombresColumas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return PropertyUtils.getProperty(datos.get(rowIndex), this.atributos.get(columnIndex));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos");
        } 
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        try {
            RegistroCosto dato = this.datos.get(row);
            PropertyUtils.setProperty(dato, this.atributos.get(col), value);
            fireTableCellUpdated(row, col);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos");
        }
    }

    public void addRegistro(String tipo) {
        RegistroCosto nuevo = factory.getRegistroCosto();
        nuevo.setTipo(tipo);
        this.datos.add(nuevo);
        for (String property: this.atributos) {
            this.bindingManager.registerBind(nuevo, property, this);
        }
        viewServicioModel.setCostos(this.datos);
        //this.fireTableRowsInserted(this.datos.size(), this.datos.size());
    }

    public void deleteRegistro(int index) {
        RegistroCosto old = this.datos.remove(index);
        for (String property: this.atributos) {
            this.bindingManager.removeBind(old, property, this);
        }
        viewServicioModel.setCostos(this.datos);
        this.fireTableRowsDeleted(index, index);
    }
    
    private int getIndexProxy(RegistroCosto origen) {
        for (RegistroCosto proxy: this.datos) {
            if (proxyUtil.getTarget(proxy) == origen) {
                return this.datos.indexOf(proxy);
            }
        }
        return -1;
    }

    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            if (origen instanceof Servicio) {
                this.bindingManager.clearBindings(this);
                List<RegistroCosto> datosOrigen = (List<RegistroCosto>) value;
                this.datos = new LinkedList<>();
                for (RegistroCosto x: datosOrigen) {
                    this.datos.add(x);
                    for (String prp: this.atributos) {
                        this.bindingManager.registerBind(x, prp, this);
                    }
                }
                this.fireTableDataChanged();
            }
            if (origen instanceof RegistroCosto) {
                //actualizar un solo elemento
                int row = this.getIndexProxy((RegistroCosto)origen);
                int col = this.atributos.indexOf(property);
                fireTableCellUpdated(row, col);
            }
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void bindListener(Object target, String property) {
        //esto se delega a los metodos de actualizacion de la tabla
    }
    
    public List<RegistroCosto> getDatos() {
        return this.datos;
    }

}
