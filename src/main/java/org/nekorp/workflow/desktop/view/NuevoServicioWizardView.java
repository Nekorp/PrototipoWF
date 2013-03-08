/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nekorp.workflow.desktop.view;

import org.nekorp.workflow.desktop.control.NuevoServicioWizard;

/**
 *
 */
public class NuevoServicioWizardView extends WizardView {

    private NuevoServicioWizard aplication;
    
    @Override
    public void iniciar() {
        aplication.inicia();
    }
    
    @Override
    public void cancelar() {
        this.getParentWindow().dispose();
        //aplication.reloadServicio();
    }

    @Override
    public void terminar() {
        aplication.nuevoServicio();
        this.getParentWindow().dispose();
    }

    public void setAplication(NuevoServicioWizard aplication) {
        this.aplication = aplication;
    }
}
