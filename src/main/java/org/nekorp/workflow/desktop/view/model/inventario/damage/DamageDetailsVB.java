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

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DamageDetailsVB {

    private String id;
    private String caracteristica;
    private String categoria;
    private int x;
    private int y;
    
    public DamageDetailsVB() {
        caracteristica = "Tallón";
        categoria = "Minimo";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        if(caracteristica.compareTo("Daño estructural") == 0){
            return "Estructural:" + StringUtils.substring(categoria, 0, 3);
        }
        return caracteristica + ":" + StringUtils.substring(categoria, 0, 3);
    }
}
