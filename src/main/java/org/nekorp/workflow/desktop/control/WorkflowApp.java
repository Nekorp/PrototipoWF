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
package org.nekorp.workflow.desktop.control;

import java.util.List;
import org.nekorp.workflow.desktop.modelo.reporte.ParametrosReporte;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;

public interface WorkflowApp extends ControlCliente, ControlAuto {
    /**
     * solicita el inicaio de la aplicacion.
     */
    void startApliacion();
    /**
     * inicia el cierre de la aplicacion
     */
    void closeAplicacion();
    
    /**
     * @return TODOS los servicios disponibles.
     */
    List<ServicioIndexVB> getIndexServicios();
    
    /**
     * solicita que se cargue un nuevo servicio.
     * @param idServicio el id del servicio.
     */
    void cargaServicio(Long idServicio);
    
    /**
     * guarda el servicio actualmente cargado.
     */
    void guardaServicio();
    
    /**
     * genera un reporte con los datos que se tienen cargados en la aplicacion.
     * @param destination la ruta donde se dejara el reporte.
     */
    void generaReporte(ParametrosReporte param);
    
    void generaOrdenServicio(ParametrosReporteOS param);
}
