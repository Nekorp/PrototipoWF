/**
 *   Copyright 2012-2013 Nekorp
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */
package org.nekorp.workflow.desktop.view.binding.listener;

import java.lang.reflect.InvocationTargetException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.beanutils.BeanUtils;
import org.nekorp.workflow.desktop.view.binding.Bindable;
/**
 *
 * 
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
            Object value = source.getModelValue();
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
            Object value = source.getModelValue();
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
            Object value = source.getModelValue();
            source.ignoreUpdate(value);
            BeanUtils.setProperty(target, property, value);
        } catch (IllegalAccessException ex) {
            BindingDocumentListener.LOGGER.debug(ex.getMessage());
        } catch (InvocationTargetException ex) {
            BindingDocumentListener.LOGGER.debug("quiere actualizar doble target:" + target+ " property:" +property+" origen:"+source);
        }
    }
    
}
