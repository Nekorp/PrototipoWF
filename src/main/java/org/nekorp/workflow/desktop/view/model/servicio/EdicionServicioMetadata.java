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
package org.nekorp.workflow.desktop.view.model.servicio;

import org.nekorp.workflow.desktop.modelo.servicio.ServicioLoaded;
import org.nekorp.workflow.desktop.servicio.Metadata;
import org.springframework.stereotype.Component;

/**
 *
 * esto es informacion sobre el modelo que usa el sistema
 * no es realmente propia del modelo del dominio.
 * @author Nekorp
 */
@Component
public class EdicionServicioMetadata implements Metadata {
    /**
     * indica si el servicio se puede editar.
     */
    private boolean editable;
    /**
     * indica si el servicio esta editado en cualquiera
     * de sus componentes, incluye el cliente.
     */
    private boolean editado;
    /**
     * para ver si en especifico el cliente esta editado.
     */
    private boolean clienteEditado;
    
    /**
     * si se le pueden deshacer cambios
     */
    private boolean tieneUndo;
    
    /**
     * si se pueden rehacer cambios
     */
    private boolean tieneRedo;
    
    /**
     * si hay algun servicio cargado.
     */
    private boolean servicioCargado;
    
    private ServicioLoaded servicioActual;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public boolean isClienteEditado() {
        return clienteEditado;
    }

    public void setClienteEditado(boolean clienteEditado) {
        this.clienteEditado = clienteEditado;
    }

    public boolean isTieneUndo() {
        return tieneUndo;
    }

    public void setTieneUndo(boolean tieneUndo) {
        this.tieneUndo = tieneUndo;
    }

    public boolean isTieneRedo() {
        return tieneRedo;
    }

    public void setTieneRedo(boolean tieneRedo) {
        this.tieneRedo = tieneRedo;
    }

    public boolean isServicioCargado() {
        return servicioCargado;
    }

    public void setServicioCargado(boolean servicioCargado) {
        this.servicioCargado = servicioCargado;
    }

    public ServicioLoaded getServicioActual() {
        return servicioActual;
    }

    public void setServicioActual(ServicioLoaded servicioActual) {
        this.servicioActual = servicioActual;
    }
}
