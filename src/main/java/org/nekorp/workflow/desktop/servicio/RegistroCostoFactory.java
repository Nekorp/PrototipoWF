/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.servicio;

import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;

/**
 * 
 * @author Nekorp
 */
public interface RegistroCostoFactory {
    RegistroCostoVB getRegistroCosto(String tipo);
    RegistroCostoVB copyRegistroCosto(RegistroCostoVB origen);
}
