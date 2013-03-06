/**
 *   Copyright 2013 Nekorp
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

/**
 *
 */
public abstract class ReadOnlyBinding implements Bindable {

    private LinkedList<Object> ignore;
    public ReadOnlyBinding() {
        this.ignore = new LinkedList<>();
    }
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.notifyUpdate(origen,property,value);
        }
    }
    
    public abstract void notifyUpdate(Object origen, String property, Object value);

    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void bindListener(Object target, String property) {
        //read only no actualiza el modelo.
    }
}
