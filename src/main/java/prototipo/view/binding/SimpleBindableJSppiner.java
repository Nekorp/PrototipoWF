/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JSpinner;
import prototipo.view.binding.listener.BindingChangeListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJSppiner extends JSpinner implements Bindable {
    //private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJCheckBox.class);
    private LinkedList<Object> ignore;
    private BindingChangeListener listener;
    
    public SimpleBindableJSppiner() {
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.removeChangeListener(listener);
            this.setValue(value);
            this.addChangeListener(listener);
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
       return super.getValue();
    }

    @Override
    public void bindListener(Object target, String property) {
        listener = new BindingChangeListener(target, property, this);
        this.addChangeListener(listener);
    }

}
