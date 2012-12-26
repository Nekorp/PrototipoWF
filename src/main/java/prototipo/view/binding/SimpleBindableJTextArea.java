/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import javax.swing.JTextArea;
import prototipo.view.binding.listener.BindingDocumentListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJTextArea extends JTextArea implements Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJTextArea.class);
    
    @Override
    public void updateModel(Object value) {
        try {
            this.setText(value.toString());
        } catch (IllegalStateException e) {
            SimpleBindableJTextArea.LOGGER.debug("quiere actualizar doble" + this);
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
