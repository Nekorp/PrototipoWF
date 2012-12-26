/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import javax.swing.JLabel;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJLabel extends JLabel implements Bindable {

    @Override
    public void updateModel(Object value) {
        this.setText(value.toString());
    }

    @Override
    public Object getValue() {
        return this.getText();
    }

    @Override
    public void bindListener(Object target, String property) {
        //este componente no actualiza
    }
    
}
