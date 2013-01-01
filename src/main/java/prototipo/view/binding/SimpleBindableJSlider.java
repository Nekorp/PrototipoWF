/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JSlider;
import prototipo.view.binding.listener.BindingChangeListener;

/**
 *
 * @author Marisa
 */
public class SimpleBindableJSlider extends JSlider implements Bindable {

    private LinkedList<Object> ignore;
    
    public SimpleBindableJSlider(){
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            try {
                this.setValue(Integer.parseInt(value.toString()));
            } catch (NumberFormatException e) {
                this.setValue(0);
            }
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
         this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return super.getValue();
    }

    @Override
    public void bindListener(Object target, String property) {
        this.addChangeListener(new BindingChangeListener(target, property, this));
    }
    
}
