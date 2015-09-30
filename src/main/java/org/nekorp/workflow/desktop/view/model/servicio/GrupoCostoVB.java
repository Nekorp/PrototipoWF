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

import org.nekorp.workflow.desktop.view.model.costo.GrupoCostoMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component("grupoCosto")
@Scope("prototype")
public class GrupoCostoVB {

    @Autowired
    @Qualifier(value = "grupoCostoMetadata")
    private GrupoCostoMetadata metadata;
    private String grupo;
    public GrupoCostoVB() {
        this.grupo = "";
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public GrupoCostoMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(GrupoCostoMetadata metadata) {
        this.metadata = metadata;
    }
}
