/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * aqui no se hace bind para actualizar el valor que se muestra
 * sino para activar o desactivar un boton de la vista.
 */
public class CustomEnabledBindingJButton extends JButton implements Bindable {
    private LinkedList<Object> ignore;
    public CustomEnabledBindingJButton() {
        this.ignore = new LinkedList<>();
    }
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            Boolean param = (Boolean) value;
            this.setEnabled(param.booleanValue());
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        throw new UnsupportedOperationException("Este componente no actualiza");
    }

    @Override
    public void bindListener(Object target, String property) {
        //este componente no actualiza
    }
    
}
