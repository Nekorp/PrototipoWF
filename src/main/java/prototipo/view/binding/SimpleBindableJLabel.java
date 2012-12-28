/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJLabel extends JLabel implements Bindable {
    private LinkedList<Object> ignore;
    public SimpleBindableJLabel() {
        this.ignore = new LinkedList<>();
    }
    @Override
    public void updateModel(Object value) {
        if(!ignore.remove(value)){
            this.setText(value.toString());
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
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
