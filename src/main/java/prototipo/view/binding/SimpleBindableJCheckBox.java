/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JCheckBox;
import prototipo.view.binding.listener.BindingActionListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJCheckBox extends JCheckBox implements Bindable {
    //private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJCheckBox.class);
    private LinkedList<Object> ignore;
    
    public SimpleBindableJCheckBox() {
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object value) {
        if(!ignore.remove(value)){
            Boolean v = (Boolean) value;
            setSelected(v);
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getValue() {
       return isSelected();
    }

    @Override
    public void bindListener(Object target, String property) {
        this.addActionListener(new BindingActionListener(target, property, this));
    }

}
