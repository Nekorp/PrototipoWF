/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JComboBox;
import prototipo.view.binding.listener.BindingActionListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJComboBox extends JComboBox implements Bindable {

    private LinkedList<Object> ignore;
    
    public SimpleBindableJComboBox(){
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, Object value) {
        if(!ignore.remove(value)){
            this.setSelectedItem(value);
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
         this.ignore.add(value);
    }

    @Override
    public Object getValue() {
        return this.getSelectedItem();
    }

    @Override
    public void bindListener(Object target, String property) {
        this.addActionListener(new BindingActionListener(target, property, this));
    }
    
}
