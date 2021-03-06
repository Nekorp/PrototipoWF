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
package org.nekorp.workflow.desktop.view.model.cobranza;

import org.nekorp.workflow.desktop.servicio.Metadata;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component
public class StatusCobranzaMetadata implements Metadata {
    private CobranzaWarningLevel warningLevel;
    private String statusCobranza;
    public StatusCobranzaMetadata() {
        warningLevel = CobranzaWarningLevel.info;
    }

    public CobranzaWarningLevel getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(CobranzaWarningLevel warningLevel) {
        this.warningLevel = warningLevel;
    }

    public String getStatusCobranza() {
        return statusCobranza;
    }

    public void setStatusCobranza(String statusCobranza) {
        this.statusCobranza = statusCobranza;
    }

    @Override
    public String toString() {
        //esto se usa en el filtro de la busqueda de servicios
        return statusCobranza;
    }
}
