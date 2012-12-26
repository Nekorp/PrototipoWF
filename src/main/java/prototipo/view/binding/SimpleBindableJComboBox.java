/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import javax.swing.JComboBox;
import prototipo.view.binding.listener.BindingActionListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJComboBox extends JComboBox implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setSelectedItem(value);
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
