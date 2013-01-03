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
import javax.swing.JSlider;
import prototipo.view.binding.listener.BindingChangeListener;

/**
 *
 * 
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
