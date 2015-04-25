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
import javax.annotation.PostConstruct;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.nekorp.workflow.desktop.servicio.RegistroCostoFactory;
import org.nekorp.workflow.desktop.servicio.binding.ValidacionBindable;
import org.nekorp.workflow.desktop.servicio.imp.ProxyUtil;
import org.nekorp.workflow.desktop.servicio.validacion.ValidacionBeanFactory;
import org.nekorp.workflow.desktop.servicio.validacion.imp.CampoObligatorioValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.imp.ValidacionRangoInteger;
import org.nekorp.workflow.desktop.servicio.validacion.imp.ValidacionRangoMoneda;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroOtrosGastosVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionRegistroCosto;
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
        "",
        "IVA",
        "Precio Cliente",
        "Utilidad",
        "Subtotal",
        "",
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
    
    @Autowired
    private ValidacionRegistroCosto validacionRegistroCosto;
    @Autowired
    private ValidacionBeanFactory factoryValidacion;
    
    private ValidacionBindable validacionConceptoBinding;
    private ValidacionBindable validacionCantidadBinding;
    private ValidacionBindable validacionPrecioUnitarioBinding;
    
    public CostoServicioTableModel() {
        this.ignore = new LinkedList<>();
        this.datos = new LinkedList<>();
        metodosGet = new LinkedList<>();
        metodosGet.add("getSubtipo");
        metodosGet.add("getConcepto");
        metodosGet.add("getCantidad");
        metodosGet.add("getPrecioUnitario");
        metodosGet.add("isPrecioUnitarioConIVA");
        metodosGet.add("getIvaPrecioUnitario");
        metodosGet.add("getPrecioCliente");
        metodosGet.add("getUtilidad");
        metodosGet.add("getSubtotal");
        metodosGet.add("isSubtotalConIVA");
        metodosGet.add("getIvaSubtotal");
        
        atributos = new LinkedList<>();
        atributos.add("subtipo");
        atributos.add("concepto");
        atributos.add("cantidad");
        atributos.add("precioUnitario");
        atributos.add("precioUnitarioConIVA");
        atributos.add("");
        atributos.add("precioCliente");
        atributos.add("");
        atributos.add("");
        atributos.add("subtotalConIVA");
        atributos.add("");
    }

    @PostConstruct
    public void inicializa() {
        validacionConceptoBinding = new ValidacionBindable();
        validacionConceptoBinding.setTarget(this.validacionRegistroCosto);
        validacionConceptoBinding.setValidationResult("conceptoOk");
        CampoObligatorioValidacion conceptoVld = new CampoObligatorioValidacion();
        conceptoVld.setFailMessage("concepto obligatorio");
        conceptoVld.setFactory(factoryValidacion);
        validacionConceptoBinding.setValidador(conceptoVld);
        
        validacionCantidadBinding = new ValidacionBindable();
        validacionCantidadBinding.setTarget(this.validacionRegistroCosto);
        validacionCantidadBinding.setValidationResult("cantidadOk");
        ValidacionRangoInteger cantidadVld = new ValidacionRangoInteger();
        cantidadVld.setMin(0);
        cantidadVld.setIncMin(true);
        cantidadVld.setOutOfRangeMessage("la cantidad debe ser mayor o igual a 0");
        cantidadVld.setFactory(factoryValidacion);
        validacionCantidadBinding.setValidador(cantidadVld);
        
        validacionPrecioUnitarioBinding = new ValidacionBindable();
        validacionPrecioUnitarioBinding.setTarget(this.validacionRegistroCosto);
        validacionPrecioUnitarioBinding.setValidationResult("precioUnitarioOk");
        ValidacionRangoMoneda precioUnitarioVld = new ValidacionRangoMoneda();
        precioUnitarioVld.setMin(0d);
        precioUnitarioVld.setIncMin(true);
        precioUnitarioVld.setOutOfRangeMessage("el precio unitario debe ser mayor o igual a 0");
        precioUnitarioVld.setFactory(factoryValidacion);
        validacionPrecioUnitarioBinding.setValidador(precioUnitarioVld);
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
        Class<?> r = MethodUtils.getAccessibleMethod(RegistroCostoVB.class, 
            metodosGet.get(columnIndex), new Class[]{}).getReturnType();
        if (r.isPrimitive() && r.getName().equals("boolean")) {
            return Boolean.class;
        }
        return r;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (!editable) {
            return false;
        }
        if (columnIndex == 0 && this.datos.get(rowIndex) instanceof RegistroOtrosGastosVB) {
            return false;
        }
        if (columnIndex == 6 ) {
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
                if (col == 2) {
                    if(value != null) {
                        PropertyUtils.setProperty(dato, this.atributos.get(col), value);
                    } else {
                        PropertyUtils.setProperty(dato, this.atributos.get(col), Integer.valueOf(0));
                    }
                }
                if (col == 3 || col == 6) {
                    PropertyUtils.setProperty(dato, this.atributos.get(col), MonedaVB.valueOf((String)value));
                }
                if (col == 4 || col == 9) {
                    PropertyUtils.setProperty(dato, this.atributos.get(col), value);
                }
                fireTableCellUpdated(row, col);
                //columnas de utilidad 5 y subtotal 6
                //to weak code
                fireTableCellUpdated(row, 5);
                fireTableCellUpdated(row, 7);
                fireTableCellUpdated(row, 8);
                fireTableCellUpdated(row, 10);
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
                if (datos.size() > 0) {
                    RegistroCostoVB ultimo = datos.get(datos.size() - 1);
                    bindingManager.clearBindings(validacionConceptoBinding);
                    bindingManager.clearBindings(validacionCantidadBinding);
                    bindingManager.clearBindings(validacionPrecioUnitarioBinding);
                    bindingManager.registerBind(ultimo, "concepto", validacionConceptoBinding);
                    bindingManager.registerBind(ultimo, "cantidad", validacionCantidadBinding);
                    bindingManager.registerBind(ultimo, "precioUnitario", validacionPrecioUnitarioBinding);
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
                    fireTableCellUpdated(row, 5);
                    fireTableCellUpdated(row, 7);
                    fireTableCellUpdated(row, 8);
                    fireTableCellUpdated(row, 10);
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
