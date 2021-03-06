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

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import org.nekorp.workflow.desktop.view.binding.listener.BindingListDataListener;
/**
 *
 * @author Nekorp 
 */
public class BindableListModel<T> extends AbstractListModel<T> implements Bindable {
    private BindingListDataListener listener;
    private LinkedList<Object> ignore;
    private List<T> datos;
    
    public BindableListModel() {
        this.datos = new LinkedList<>();
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.removeListDataListener(listener);
            List<T> param = (List<T>) value;
            int index = datos.size() -1;
            if (index < 0) {
                index = 0;
            }
            this.datos = new LinkedList<>();
            for (T dato: param) {
                this.datos.add(dato);
            }
            this.fireIntervalAdded(value, 0, index);
            this.addListDataListener(listener);
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return this.datos;
    }

    @Override
    public void bindListener(Object target, String property) {
       listener = new BindingListDataListener(target, property, this);
       this.addListDataListener(listener);
    }

    @Override
    public int getSize() {
        return datos.size();
    }

    @Override
    public T getElementAt(int index) {
        return datos.get(index);
    }
    
    public void addElement(T element){
        this.datos.add(element);
        this.fireIntervalAdded(this, this.datos.size()-1, this.datos.size()-1);
    }
    
    public void removeElement(T element) {
        int index = this.datos.indexOf(element);
        if(this.datos.remove(element)){
            this.fireIntervalRemoved(this, index, index);
        }
    }
    
    public int indexof(T element) {
        return this.datos.indexOf(element);
    }
}
