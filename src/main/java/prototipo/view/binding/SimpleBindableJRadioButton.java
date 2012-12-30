/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JRadioButton;
import prototipo.view.binding.listener.BindingActionListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJRadioButton extends JRadioButton implements Bindable {
    private LinkedList<Object> ignore;
    private Object value;
    public SimpleBindableJRadioButton(Object value) {
        this.value = value;
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object value) {
        if (!ignore.remove(value)) {
            this.setSelected(value.equals(this.value));
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }
    
    @Override
    public Object getValue() {
        return this.value;
    }
    
    @Override
    public void bindListener(Object target, String property) {
        this.addActionListener(new BindingActionListener(target, property, this));
    }
}
