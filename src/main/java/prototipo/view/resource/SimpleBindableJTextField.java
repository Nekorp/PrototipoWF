/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JTextField;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJTextField extends JTextField implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setText(value.toString());
    }
    
}
