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
package org.nekorp.workflow.desktop.view.model.cliente;

import javax.validation.constraints.Pattern;

/**
 * 
 * //se crearan varias instancias de este objeto con el xml
 * @author Nekorp
 */
public class TelefonoVB {
    private String label;
    @Pattern(regexp="([\\w\\p{IsLatin}\\p{Punct}]?)|(^[\\p{IsLatin}\\w\\(\\)\\.]+([ \\-\\*][\\p{IsLatin}\\w\\(\\)\\.]+)*)")
    private String valor;

    public TelefonoVB() {
        label = "Casa";
        valor = "";
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
