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
package org.nekorp.workflow.desktop.servicio;

import java.util.List;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;

/**
 *
 * las formulas del excell :<
 */
public interface CostosCalculator {
 
    void calculaCosto(List<RegistroCostoVB> costos, CostoMetadata model);
}
