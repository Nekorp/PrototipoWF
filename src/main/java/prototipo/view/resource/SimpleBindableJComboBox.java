/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JComboBox;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJComboBox extends JComboBox implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setSelectedItem(value);
    }
    
}
