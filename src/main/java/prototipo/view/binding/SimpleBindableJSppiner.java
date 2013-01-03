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
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.JSpinner;
import prototipo.view.binding.listener.BindingChangeListener;

/**
 *
 * 
 */
public class SimpleBindableJSppiner extends JSpinner implements Bindable {
    //private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJCheckBox.class);
    private LinkedList<Object> ignore;
    private BindingChangeListener listener;
    
    public SimpleBindableJSppiner() {
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.removeChangeListener(listener);
            this.setValue(value);
            this.addChangeListener(listener);
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
       return super.getValue();
    }

    @Override
    public void bindListener(Object target, String property) {
        listener = new BindingChangeListener(target, property, this);
        this.addChangeListener(listener);
    }

}
