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
package org.nekorp.workflow.desktop.view.model.auto;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Nekorp
 */
public class AutoVB {
    @NotBlank
    private String marca;
    @NotBlank
    private String tipo;
    @NotBlank
    private String version;
    @NotBlank
    @Pattern(regexp="[\\p{Alnum}\\-]*")
    private String numeroSerie;
    @NotBlank
    private String modelo;
    @NotBlank
    private String color;
    @NotBlank
    private String placas;
    private EquipamientoVB equipamiento;

    public AutoVB() {
        marca = "";
        tipo = "";
        version = "";
        numeroSerie = "";
        modelo = "";
        color = "";
        placas = "";
    }    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public EquipamientoVB getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(EquipamientoVB equipamiento) {
        this.equipamiento = equipamiento;
    }

}
