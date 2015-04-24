/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.model.servicio;

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.Metadata;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component
public class ServicioLoadedListMetadata implements Metadata {
    private List<ServicioLoaded> serviciosNuevos;
    private List<ServicioLoaded> servicios;

    public ServicioLoadedListMetadata() {
        serviciosNuevos = new LinkedList<>();
        servicios = new LinkedList<>();
    }
    public List<ServicioLoaded> getServiciosNuevos() {
        return serviciosNuevos;
    }

    public void setServiciosNuevos(List<ServicioLoaded> serviciosNuevos) {
        this.serviciosNuevos = serviciosNuevos;
    }

    public List<ServicioLoaded> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioLoaded> servicios) {
        this.servicios = servicios;
    }
    
    public boolean isEmpty() {
        return serviciosNuevos.isEmpty() && servicios.isEmpty();
    }
}
