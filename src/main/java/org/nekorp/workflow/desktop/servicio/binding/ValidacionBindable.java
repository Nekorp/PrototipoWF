/**
 *   Copyright 2013 Nekorp
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

package org.nekorp.workflow.desktop.servicio.binding;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.apache.commons.beanutils.BeanUtils;
import org.nekorp.workflow.desktop.servicio.validacion.DetalleValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ResultadoValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.Validador;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.model.cliente.EstatusValidacion;


/**
 *
 */
public class ValidacionBindable implements Bindable {

    private LinkedList<Object> ignore;
    private Object target;
    private String validationResult;
    private Validador validador;
    public ValidacionBindable() {
        ignore = new LinkedList<>();
    }
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            try {
                ResultadoValidacion resVal = validador.valida(value);
                String detalle = "";
                for (DetalleValidacion x: resVal.getDetalle()) {
                    detalle = detalle + x.getMensaje();
                    //TODO formatear esta salida?
                }
                EstatusValidacion sts = new EstatusValidacion();
                sts.setValido(resVal.isValid());
                sts.setDetalle(detalle);
                BeanUtils.setProperty(target, validationResult, sts);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new IllegalArgumentException("error al evaluar",ex);
            }
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        throw new UnsupportedOperationException("Esta cosa no edita");
    }

    @Override
    public void bindListener(Object target, String property) {
        //este coso no modifica nada;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setValidador(Validador validador) {
        this.validador = validador;
    }

    public void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }
}
