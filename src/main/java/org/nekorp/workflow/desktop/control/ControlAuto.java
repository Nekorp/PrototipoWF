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

package org.nekorp.workflow.desktop.control;

import java.util.List;
import org.nekorp.workflow.desktop.rest.util.Callback;
import technology.tikal.taller.automotriz.model.auto.Auto;

/**
 * @author Nekorp
 */
public interface ControlAuto {

    /**
     * carga un auto a la aplicacion para trabajar con el.
     * @param origen el auto a cargar.
     */
    void loadAuto(Auto origen);
    
    /**
     * consulta todos los autos que se tienen registrados.
     * @return los Autos.
     */
    List<Auto> getAutos();
    
    /**
     * busca un auto por el numero de serie.
     * @param numeroSerie el numero de serie a buscar.
     * @param cmd
     * @return 
     */
    void buscarAuto(String numeroSerie, Callback<List<Auto>> cmd);
}
