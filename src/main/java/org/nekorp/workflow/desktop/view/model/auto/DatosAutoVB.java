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
package org.nekorp.workflow.desktop.view.model.auto;

import org.hibernate.validator.constraints.NotBlank;
import org.nekorp.workflow.desktop.view.model.inventario.damage.InventarioDamageVB;

/**
 * @author Nekorp
 */
public class DatosAutoVB {

    @NotBlank
    private String kilometraje;
    @NotBlank
    private String combustible;
    private InventarioDamageVB damage;
    
    public DatosAutoVB() {
        kilometraje = "";
        combustible = "0";
        damage = new InventarioDamageVB();
    }

    public InventarioDamageVB getDamage() {
        return damage;
    }

    public void setDamage(InventarioDamageVB damage) {
        this.damage = damage;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
}
