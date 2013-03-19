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

package org.nekorp.workflow.desktop.view.model.inventario.damage;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class InventarioDamageVB {

    private List<DamageDetailsVB> derecha;
    private List<DamageDetailsVB> izquierda;
    private List<DamageDetailsVB> frontal;
    private List<DamageDetailsVB> trasera;
    
    public InventarioDamageVB() {
        derecha = new LinkedList<>();
        izquierda = new LinkedList<>();
        frontal = new LinkedList<>();
        trasera = new LinkedList<>();
    }

    public List<DamageDetailsVB> getDerecha() {
        List<DamageDetailsVB> respuesta = new LinkedList<>();
        for (DamageDetailsVB x: derecha) {
            respuesta.add(x);
        }
        return respuesta;
    }

    public void setDerecha(List<DamageDetailsVB> derecha) {
        this.derecha = new LinkedList<>();
        for (DamageDetailsVB x: derecha) {
            this.derecha.add(x);
        }
    }

    public List<DamageDetailsVB> getIzquierda() {
        List<DamageDetailsVB> respuesta = new LinkedList<>();
        for (DamageDetailsVB x: izquierda) {
            respuesta.add(x);
        }
        return respuesta;
    }

    public void setIzquierda(List<DamageDetailsVB> izquierda) {
        this.izquierda = new LinkedList<>();
        for (DamageDetailsVB x: izquierda) {
            this.izquierda.add(x);
        }
    }

    public List<DamageDetailsVB> getFrontal() {
        List<DamageDetailsVB> respuesta = new LinkedList<>();
        for (DamageDetailsVB x: frontal) {
            respuesta.add(x);
        }
        return respuesta;
    }

    public void setFrontal(List<DamageDetailsVB> frontal) {
        this.frontal = new LinkedList<>();
        for (DamageDetailsVB x: frontal) {
            this.frontal.add(x);
        }
    }

    public List<DamageDetailsVB> getTrasera() {
        List<DamageDetailsVB> respuesta = new LinkedList<>();
        for (DamageDetailsVB x: trasera) {
            respuesta.add(x);
        }
        return respuesta;
    }

    public void setTrasera(List<DamageDetailsVB> trasera) {
        this.trasera = new LinkedList<>();
        for (DamageDetailsVB x: trasera) {
            this.trasera.add(x);
        }
    }
}
