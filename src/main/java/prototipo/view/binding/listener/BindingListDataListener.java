/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding.listener;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.apache.commons.beanutils.BeanUtils;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class BindingListDataListener implements ListDataListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BindingListDataListener.class);
    private Object target;
    private String property;
    private Bindable source;

    public BindingListDataListener(Object target, String property, Bindable source) {
        this.target = target;
        this.property = property;
        this.source = source;
    }
    
    @Override
    public void intervalAdded(ListDataEvent e) {
        try {
            Object value = source.getModelValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);            
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BindingListDataListener.LOGGER.error(ex);
        }
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        try {
            Object value = source.getModelValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);            
        } catch (IllegalAccessException | InvocationTargetException ex) {
            BindingListDataListener.LOGGER.error(ex);
        }
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        try {
            Object value = source.getModelValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);            
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(BindingListDataListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
