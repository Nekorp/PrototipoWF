/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

import org.nekorp.workflow.desktop.servicio.validacion.CalifacacionValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.DetalleValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.PoliticaValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ResultadoValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ValidacionBeanFactory;
import org.nekorp.workflow.desktop.servicio.validacion.ValidationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Nekorp
 */
@Service
public class ValidacionBeanFactoryImp implements ValidacionBeanFactory {
   
    @Value("TODOVALIDO")
    private PoliticaValidacion politica;
    
    public ValidacionBeanFactoryImp() {
        
    }

    public ValidacionBeanFactoryImp(PoliticaValidacion politica) {
        this.politica = politica;
    }
    
    @Override
    public DetalleValidacion buildDetalle() {
        return new DetalleValidacionImp();
    }
    
    @Override
    public DetalleValidacion buildDetalle(String mensaje, CalifacacionValidacion calificacion) {
        return new DetalleValidacionImp(mensaje, calificacion);
    }

    @Override
    public DetalleValidacion buildDetalle(CalifacacionValidacion calificacion) {
        return new DetalleValidacionImp("", calificacion);
    }

    @Override
    public ResultadoValidacion buildResultado() {
        ResultadoValidacion r = new ResultadoValidacionImp();
        r.setPoliticaValidacion(politica);
        return r;
    }

    @Override
    public ValidationContext buildContext() {
        return new ValidationContextImp();
    }

    public PoliticaValidacion getPolitica() {
        return politica;
    }

    public void setPolitica(PoliticaValidacion politica) {
        this.politica = politica;
    }
}
