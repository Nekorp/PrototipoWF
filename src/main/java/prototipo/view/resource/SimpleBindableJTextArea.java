/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JTextArea;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJTextArea extends JTextArea implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setText(value.toString());
    }
    
}
