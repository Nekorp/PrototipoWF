/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.model.imp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import prototipo.view.model.Bindable;
import prototipo.view.model.BindingManager;

/**
 *
 * @author Marisa
 */
@Component
public class BindingManagerImp implements BindingManager<Bindable> {
    private static final Logger LOGGER = Logger.getLogger(BindingManagerImp.class);
    
    private Map<String,List<Bindable>> bindings;
    
    public BindingManagerImp() {
        bindings = new HashMap<>();
    }
    
    @Override
    public void registerBind(String property, Bindable component) {
        List<Bindable> bnds = this.bindings.get(property);
        if (bnds == null) {
            bnds = new LinkedList<>();
            this.bindings.put(property, bnds);
        }
        bnds.add(component);
    }

    @Override
    public void updateModelBindings(String name, Object value) {
        List<Bindable> lista = this.bindings.get(name);
        if (lista != null) {
            for (Bindable bind: lista) {
                bind.updateModel(value);
            }
        }
    }
}
