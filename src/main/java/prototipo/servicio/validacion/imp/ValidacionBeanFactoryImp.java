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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import prototipo.servicio.validacion.CalifacacionValidacion;
import prototipo.servicio.validacion.DetalleValidacion;
import prototipo.servicio.validacion.PoliticaValidacion;
import prototipo.servicio.validacion.ResultadoValidacion;
import prototipo.servicio.validacion.ValidacionBeanFactory;
import prototipo.servicio.validacion.ValidationContext;

/**
 *
 */
@Service
public class ValidacionBeanFactoryImp implements ValidacionBeanFactory {
    @Value("TODOVALIDO")
    private PoliticaValidacion politica;
    
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
}
