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
package org.nekorp.workflow.desktop.view.binding;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
/**
 *
 * 
 */
public class SimpleBindableJDateChooser extends JDateChooser implements Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJDateChooser.class);
    private LinkedList<Object> ignore;
    private PropertyChangeListener listener;
    
    public SimpleBindableJDateChooser() {
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            Date val = (Date) value;
            this.removePropertyChangeListener(listener);
            this.setDate(val);
            this.addPropertyChangeListener(listener);
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
       return super.getDate();
    }

    @Override
    public void bindListener(final Object target, final String property) {
        listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    if (StringUtils.equals(evt.getPropertyName(), "date")) {
                        Object value = getModelValue();
                        ignoreUpdate(value);
                        BeanUtils.setProperty(target, property, value);
                    }
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    SimpleBindableJDateChooser.LOGGER.error("crash", ex);
                }
            }
        };
        this.addPropertyChangeListener(listener);
    }

}
