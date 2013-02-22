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

package org.nekorp.workflow.desktop.view.model.validacion;

import org.nekorp.workflow.desktop.servicio.Metadata;
/**
 * validacion de los datos del auto, esto esta orientado a como es la pantalla no el modelo
 */
public class ValidacionDatosAuto extends ValidacionParticular implements Metadata {

    private ValidacionGeneralDatosAuto validacionGeneral;
    private EstatusValidacion marca;
    private EstatusValidacion tipo;
    private EstatusValidacion version;
    private EstatusValidacion numeroSerie;
    private EstatusValidacion modelo;
    private EstatusValidacion color;
    private EstatusValidacion placas;
    private EstatusValidacion kilometraje;
    private EstatusValidacion descripcionServicio;
    
    public ValidacionDatosAuto() {
        this.marca = new EstatusValidacion();
        this.tipo = new EstatusValidacion();
        this.version = new EstatusValidacion();
        this.numeroSerie = new EstatusValidacion();
        this.modelo = new EstatusValidacion();
        this.color = new EstatusValidacion();
        this.placas = new EstatusValidacion();
        this.kilometraje = new EstatusValidacion();
        this.descripcionServicio = new EstatusValidacion();
    }

    public EstatusValidacion getMarca() {
        return marca;
    }

    public void setMarca(EstatusValidacion marca) {
        this.marca = marca;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getTipo() {
        return tipo;
    }

    public void setTipo(EstatusValidacion tipo) {
        this.tipo = tipo;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getVersion() {
        return version;
    }

    public void setVersion(EstatusValidacion version) {
        this.version = version;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(EstatusValidacion numeroSerie) {
        this.numeroSerie = numeroSerie;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getModelo() {
        return modelo;
    }

    public void setModelo(EstatusValidacion modelo) {
        this.modelo = modelo;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getColor() {
        return color;
    }

    public void setColor(EstatusValidacion color) {
        this.color = color;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getPlacas() {
        return placas;
    }

    public void setPlacas(EstatusValidacion placas) {
        this.placas = placas;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(EstatusValidacion kilometraje) {
        this.kilometraje = kilometraje;
        evaluaTodo(validacionGeneral);
    }

    public EstatusValidacion getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(EstatusValidacion descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
        evaluaTodo(validacionGeneral);
    }

    public void setValidacionGeneral(ValidacionGeneralDatosAuto validacionGeneral) {
        this.validacionGeneral = validacionGeneral;
    }
}
