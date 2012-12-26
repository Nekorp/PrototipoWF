/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import prototipo.view.binding.Bindable;

/**
 *
 * @author Marisa
 */
public class BindingActionListener implements ActionListener {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BindingActionListener.class);
    private Object target;
    private String property;
    private Bindable source;

    public BindingActionListener(Object target, String property, Bindable source) {
        this.target = target;
        this.property = property;
        this.source = source;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BeanUtils.setProperty(target, property, source.getValue());
        } catch (IllegalAccessException ex) {
            BindingActionListener.LOGGER.debug(ex.getMessage());
        } catch (InvocationTargetException ex) {
            BindingActionListener.LOGGER.debug("quiere actualizar doble target:" + target+ " property:" +property+" origen:"+source);
        }
    }
    
}
