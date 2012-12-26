/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.model;

/**
 *
 * @author Marisa
 */
public interface BindingManager<T> {
    
    void registerBind(String property, T component);

    void updateModelBindings(String name, Object value);
}
