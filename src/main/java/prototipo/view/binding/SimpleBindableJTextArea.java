/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;
import prototipo.view.binding.listener.BindingDocumentListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJTextArea extends JTextArea implements Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJTextArea.class);
    private LinkedList<Object> ignore;
    private DocumentListener listener;
    
    public SimpleBindableJTextArea() {
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        try {
            if(!ignore.remove(value)){
                this.getDocument().removeDocumentListener(listener);
                this.setText(value.toString());
                this.getDocument().addDocumentListener(listener);
            }
        } catch (IllegalStateException e) {
            SimpleBindableJTextArea.LOGGER.debug("quiere actualizar doble" + this);
        } 
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
       return this.getText();
    }

    @Override
    public void bindListener(Object target, String property) {
        this.listener = new BindingDocumentListener(target, property, this);
        this.getDocument().addDocumentListener(this.listener);
    }

}
