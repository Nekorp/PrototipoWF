/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding.listener;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.beanutils.BeanUtils;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class BindingDocumentListener implements DocumentListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BindingDocumentListener.class);
    
    private Object target;
    private String property;
    private Bindable source;

    public BindingDocumentListener(Object target, String property, Bindable source) {
        this.target = target;
        this.property = property;
        this.source = source;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            Object value = source.getValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException ex) {
            BindingDocumentListener.LOGGER.debug(ex.getMessage());
        } catch (InvocationTargetException ex) {
            BindingDocumentListener.LOGGER.debug("quiere actualizar doble target:" + target+ " property:" +property+" origen:"+source);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            Object value = source.getValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException ex) {
            BindingDocumentListener.LOGGER.debug(ex.getMessage());
        } catch (InvocationTargetException ex) {
            BindingDocumentListener.LOGGER.debug("quiere actualizar doble target:" + target+ " property:" +property+" origen:"+source);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            Object value = source.getValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException ex) {
            BindingDocumentListener.LOGGER.debug(ex.getMessage());
        } catch (InvocationTargetException ex) {
            BindingDocumentListener.LOGGER.debug("quiere actualizar doble target:" + target+ " property:" +property+" origen:"+source);
        }
    }
    
}
