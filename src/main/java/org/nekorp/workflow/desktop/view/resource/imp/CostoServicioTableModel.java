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

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.nekorp.workflow.desktop.servicio.RegistroCostoFactory;
import org.nekorp.workflow.desktop.servicio.imp.ProxyUtil;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroOtrosGastosVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    private LinkedList<Object> ignore;
    
    private List<RegistroCostoVB> datos;
    
    private List<String> metodosGet;
    
    private List<String> atributos;
    
    private boolean editable;
    
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

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumas[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return MethodUtils.getAccessibleMethod(
                RegistroCostoVB.class, 
                metodosGet.get(columnIndex), 
                new Class[]{}).getReturnType();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (!editable) {
            return false;
        }
        if (columnIndex == 0 && this.datos.get(rowIndex) instanceof RegistroOtrosGastosVB) {
            return false;
        }
        if (columnIndex == 5 ) {
            if (StringUtils.equals("Insumo", this.datos.get(rowIndex).getSubtipo())) {
                return false;
            }
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
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos", ex);
        } 
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        //TODO esto es solo un parche
        //encontrar por que esta pidiendo actualizar una columna que ya no existe
        if (row >= this.datos.size()) {
            return;
        }
        try {
            if (col > 1) {
                RegistroCostoVB dato = this.datos.get(row);
                if (col > 2) {
                    PropertyUtils.setProperty(dato, this.atributos.get(col), MonedaVB.valueOf((String)value));
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
                RegistroCostoVB dato = this.datos.get(row);
                PropertyUtils.setProperty(dato, this.atributos.get(col), value);
                fireTableCellUpdated(row, col);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalArgumentException("Mal configurado el modelo de la tabla costos", ex);
        }
    }

    public void addRegistro(String tipo) {
        RegistroCostoVB nuevo = factory.getRegistroCosto(tipo);
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
        RegistroCostoVB old = this.datos.remove(index);
        for (String property: this.atributos) {
            if (!property.equals("")) {
                this.bindingManager.removeBind(old, property, this);
            }
        }
        viewServicioModel.setCostos(this.datos);
        this.fireTableRowsDeleted(index, index);
    }
    
    private int getIndexProxy(RegistroCostoVB origen) {
        for (RegistroCostoVB proxy: this.datos) {
            if (proxyUtil.getTarget(proxy) == origen) {
                return this.datos.indexOf(proxy);
            }
        }
        return -1;
    }

    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            if (origen instanceof ServicioVB) {
                this.bindingManager.clearBindings(this);
                List<RegistroCostoVB> datosOrigen = (List<RegistroCostoVB>) value;
                this.datos = new LinkedList<>();
                for (RegistroCostoVB x: datosOrigen) {
                    this.datos.add(x);
                    for (String prp: this.atributos) {
                        if (!prp.equals("")) {
                            this.bindingManager.registerBind(x, prp, this);
                        }
                    }
                }
                this.fireTableDataChanged();
            }
            if (origen instanceof RegistroCostoVB) {
                //actualizar un solo elemento
                int row = this.getIndexProxy((RegistroCostoVB)origen);
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
    
    public List<RegistroCostoVB> getDatos() {
        return this.datos;
    }

}
