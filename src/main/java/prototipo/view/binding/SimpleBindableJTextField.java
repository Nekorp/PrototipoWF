/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import javax.swing.JTextField;
import prototipo.view.binding.listener.BindingDocumentListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJTextField extends JTextField implements Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJTextField.class);
    
    @Override
    public void updateModel(Object value) {
        try {
            this.setText(value.toString());
        } catch (IllegalStateException e) {
            SimpleBindableJTextField.LOGGER.debug("quiere actualizar doble" + this);
        }
    }
    
    @Override
    public Object getValue() {
        return this.getText();
    }
    
    @Override
    public void bindListener(Object target, String property) {
        this.getDocument().addDocumentListener(
                new BindingDocumentListener(target, property, this));
    }
}
