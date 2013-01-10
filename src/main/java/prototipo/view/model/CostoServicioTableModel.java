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
package prototipo.view.model;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.modelo.currency.Moneda;
import prototipo.servicio.RegistroCostoFactory;
import prototipo.servicio.imp.ProxyUtil;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 *
 */
@Component("costoServicioTableModel")
public class CostoServicioTableModel extends AbstractTableModel implements Bindable {
    private String[] nombresColumas = new String[]{
        "Tipo",
        "Concepto",
        "Cantidad",
        "Precio Unitario",
        "IVA",
        "Precio Cliente",
        "Utilidad",
        "Subtotal",
        "IVA"
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
    
    private List<String> metodosGet;
    
    private List<String> atributos;
    
    public CostoServicioTableModel() {
        this.ignore = new LinkedList<>();
        this.datos = new LinkedList<>();
        metodosGet = new LinkedList<>();
        metodosGet.add("getSubtipo");
        metodosGet.add("getConcepto");
        metodosGet.add("getCantidad");
        metodosGet.add("getPrecioUnitario");
        metodosGet.add("getIvaPrecioUnitario");
        metodosGet.add("getPrecioCliente");
        metodosGet.add("getUtilidad");
        metodosGet.add("getSubtotal");
        metodosGet.add("getIvaSubtotal");
        
        atributos = new LinkedList<>();
        atributos.add("subtipo");
        atributos.add("concepto");
        atributos.add("cantidad");
        atributos.add("precioUnitario");
        atributos.add("");
        atributos.add("precioCliente");
        atributos.add("");
        atributos.add("");
        atributos.add("");
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return MethodUtils.getAccessibleMethod(
                RegistroCosto.class, 
                metodosGet.get(columnIndex), 
                new Class[]{}).getReturnType();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 && this.datos.get(rowIndex).getTipo().equals("Otros Gastos")) {
            return false;
        }
        return !this.atributos.get(columnIndex).equals("");
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
            return MethodUtils.invokeMethod(this.datos.get(rowIndex), this.metodosGet.get(columnIndex), new Object[]{});
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos");
        } 
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        try {
            if (col > 1) {
                RegistroCosto dato = this.datos.get(row);
                if (col > 2) {
                    PropertyUtils.setProperty(dato, this.atributos.get(col), Moneda.valueOf((String)value));
                } else {
                    if(value != null) {
                        PropertyUtils.setProperty(dato, this.atributos.get(col), value);
                    } else {
                        PropertyUtils.setProperty(dato, this.atributos.get(col), Integer.valueOf(0));
                    }
                }
                fireTableCellUpdated(row, col);
                //columnas de utilidad 5 y subtotal 6
                //to weak code
                fireTableCellUpdated(row, 4);
                fireTableCellUpdated(row, 6);
                fireTableCellUpdated(row, 7);
                fireTableCellUpdated(row, 8);
            } else {
                RegistroCosto dato = this.datos.get(row);
                PropertyUtils.setProperty(dato, this.atributos.get(col), value);
                fireTableCellUpdated(row, col);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos");
        }
    }

    public void addRegistro(String tipo) {
        RegistroCosto nuevo = factory.getRegistroCosto();
        nuevo.setTipo(tipo);
        if (tipo.equals("Otros Gastos")) {
            nuevo.setSubtipo("Otros Gastos");
        }
        this.datos.add(nuevo);
        for (String property: this.atributos) {
            if (!property.equals("")) {
                this.bindingManager.registerBind(nuevo, property, this);
            }
        }
        viewServicioModel.setCostos(this.datos);
        //this.fireTableRowsInserted(this.datos.size(), this.datos.size());
    }

    public void deleteRegistro(int index) {
        RegistroCosto old = this.datos.remove(index);
        for (String property: this.atributos) {
            if (!property.equals("")) {
                this.bindingManager.removeBind(old, property, this);
            }
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
                        if (!prp.equals("")) {
                            this.bindingManager.registerBind(x, prp, this);
                        }
                    }
                }
                this.fireTableDataChanged();
            }
            if (origen instanceof RegistroCosto) {
                //actualizar un solo elemento
                int row = this.getIndexProxy((RegistroCosto)origen);
                int col = this.atributos.indexOf(property);
                fireTableCellUpdated(row, col);
                if (col > 1) {
                    //columnas de utilidad 5 y subtotal 6
                    //to weak code
                    fireTableCellUpdated(row, 4);
                    fireTableCellUpdated(row, 6);
                    fireTableCellUpdated(row, 7);
                    fireTableCellUpdated(row, 8);
                }
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