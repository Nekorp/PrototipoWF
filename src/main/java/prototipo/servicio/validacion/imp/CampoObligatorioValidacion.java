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
import prototipo.servicio.validacion.ValidationContext;

/**
 *
 */
public class CampoObligatorioValidacion extends PropertyValidator {

    private String failMessage;
    @Override
    public void doFinalValidation(Object o, DetalleValidacion detalle, ValidationContext ctx) {
        try {
            String dato = (String) o;
            if (dato == null || dato.isEmpty()) {
                detalle.setCalificacion(CalifacacionValidacion.INVALIDO);
                detalle.setMensaje(failMessage);
            } else {
                detalle.setCalificacion(CalifacacionValidacion.VALIDO);
                detalle.setMensaje("");
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Esto no es un string" , e);
        } catch (NullPointerException e) {
            detalle.setCalificacion(CalifacacionValidacion.INDETERMINADO);
        }
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    
}
