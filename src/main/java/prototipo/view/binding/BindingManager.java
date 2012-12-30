/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

/**
 *
 * @author Marisa
 */
public interface BindingManager<T> {
    /**
     * registra un nuevo binding
     * @param target el objeto del modelo al que se quiere hacer binding.
     * @param property la propiedad del objeto al que se quiere ligar.
     * @param component el componente de la vista que se quiere bindear. 
     */
    void registerBind(Object target, String property, T component);
    
    /**
     * quita un binding
     * @param target el objeto del modelo al que se quiere hacer binding.
     * @param property la propiedad del objeto al que se quiere ligar.
     * @param component el componente de la vista que se quiere bindear. 
     */
    void removeBind(Object target, String property, T component);
    
    /**
     * elimina todos los binding asociados entre el componente y el target
     * @param target el objeto con el que se tienen los bindings
     * @param component el componente que quiere liberar los bindings
     */
    void clearObjectBindings(Object target, T component);
    
    /**
     * elimina todos los binding que tenga el componente
     * @param component el componente que quiere liberar los bindings
     */
    void clearBindings(T component);
}
