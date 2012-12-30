/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding.listener;

import java.lang.reflect.InvocationTargetException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.beanutils.BeanUtils;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class BindingChangeListener implements ChangeListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BindingChangeListener.class);
    private Object target;
    private String property;
    private Bindable source;

    public BindingChangeListener(Object target, String property, Bindable source) {
        this.target = target;
        this.property = property;
        this.source = source;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            Object value = source.getValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BindingChangeListener.LOGGER.error(ex);
        }
    }
}
