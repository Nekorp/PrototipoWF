/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import prototipo.modelo.costo.RegistroCosto;
import prototipo.servicio.RegistroCostoFactory;

/**
 *
 * @author Marisa
 */
public abstract class RegistroCostoFactoryImp implements RegistroCostoFactory {

    @Override
    public RegistroCosto getRegistroCosto() {
        return this.creaRegistroCosto();
    }
    
    public abstract RegistroCosto creaRegistroCosto();
}
