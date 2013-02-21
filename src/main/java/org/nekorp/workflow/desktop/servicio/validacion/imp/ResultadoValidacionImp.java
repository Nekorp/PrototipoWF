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

package org.nekorp.workflow.desktop.servicio.validacion.imp;

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.servicio.validacion.CalifacacionValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.DetalleValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.PoliticaValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ResultadoValidacion;

/**
 *
 */
public class ResultadoValidacionImp implements ResultadoValidacion {

    private List<DetalleValidacion> detalle;
    private PoliticaValidacion politica;
    
    public ResultadoValidacionImp() {
        this.politica = PoliticaValidacion.TODOVALIDO; //default
        this.detalle = new LinkedList<>();
    }
    
    @Override
    public List<DetalleValidacion> getDetalle() {
        return detalle;
    }

    @Override
    public void setPoliticaValidacion(PoliticaValidacion politica) {
        this.politica = politica;
    }

    @Override
    public boolean isValid() {
        if (politica == PoliticaValidacion.TODOVALIDO) {
            for (DetalleValidacion x: detalle) {
                if(x.getCalificacion() != CalifacacionValidacion.VALIDO) {
                    return false;
                }
            }
        }
        if (politica == PoliticaValidacion.NADAINCORRECTO) {
            for (DetalleValidacion x: detalle) {
                if(x.getCalificacion() == CalifacacionValidacion.INVALIDO) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addDetalle(DetalleValidacion detalle) {
        this.detalle.add(detalle);
    }

    @Override
    public PoliticaValidacion getPoliticaValidacion() {
        return politica;
    }

}
