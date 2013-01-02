/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

/**
 *
 * esto es informacion sobre el modelo que usa el sistema
 * no es realmente propia del modelo del dominio.
 */
@Component
public class EdicionServicioMetadata implements Metadata {
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
    
    
}
