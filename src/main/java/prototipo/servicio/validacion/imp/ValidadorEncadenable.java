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

package prototipo.servicio.validacion.imp;

import prototipo.servicio.validacion.CalifacacionValidacion;
import prototipo.servicio.validacion.DetalleValidacion;
import prototipo.servicio.validacion.PoliticaValidacion;
import prototipo.servicio.validacion.ResultadoValidacion;
import prototipo.servicio.validacion.ValidacionBeanFactory;
import prototipo.servicio.validacion.Validador;
import prototipo.servicio.validacion.ValidationContext;

/**
 *
 */
public abstract class ValidadorEncadenable implements Validador {

    private Validador siguiente;
    
    private ValidacionBeanFactory factory;
    
    @Override
    public ResultadoValidacion valida(Object o) {
        return this.valida(o,this.factory.buildResultado());
    }
    
    @Override
    public ResultadoValidacion valida(Object o, ResultadoValidacion acarreo) {
        return this.valida(o,this.factory.buildResultado(), factory.buildContext());
    }

    @Override
    public ResultadoValidacion valida(Object o, ResultadoValidacion acarreo, ValidationContext ctx) {
        DetalleValidacion detalle = this.factory.buildDetalle();
        acarreo.addDetalle(detalle);
        doValidacion(o, detalle, ctx);
        if (acarreo.getPoliticaValidacion() == PoliticaValidacion.TODOVALIDO && detalle.getCalificacion() != CalifacacionValidacion.VALIDO) {
            return acarreo;
        }
        if (acarreo.getPoliticaValidacion() == PoliticaValidacion.NADAINCORRECTO && detalle.getCalificacion() == CalifacacionValidacion.INVALIDO) {
            return acarreo;
        }
        if (siguiente != null) {
            return siguiente.valida(o, acarreo, ctx);
        }
        return acarreo;
    }
    
    public abstract void doValidacion(Object o, DetalleValidacion detalle, ValidationContext ctx);
    
    public Validador getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Validador siguiente) {
        this.siguiente = siguiente;
    }

    public ValidacionBeanFactory getFactory() {
        return factory;
    }

    public void setFactory(ValidacionBeanFactory factory) {
        this.factory = factory;
    }
}

