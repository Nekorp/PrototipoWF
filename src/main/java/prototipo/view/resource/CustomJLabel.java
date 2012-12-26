/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JLabel;
import prototipo.view.model.Bindable;

/**
 *
 * @author Marisa
 */
public class CustomJLabel extends JLabel implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setText(value + "");
    }
    
}
