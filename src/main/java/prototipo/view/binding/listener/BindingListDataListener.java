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
 * 
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
