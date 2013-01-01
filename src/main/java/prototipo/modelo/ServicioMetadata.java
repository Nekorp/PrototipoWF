/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

/**
 *
 * @author Marisa
 */
@Component
public class ServicioMetadata implements Metadata {
    /**
     * indica si el servicio esta editado en cualquiera
     * de sus componentes, incluye el cliente.
     */
    private boolean editado;
    /**
     * para ver si en especifico el cliente esta editado.
     */
    private boolean clienteEditado;

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
}
