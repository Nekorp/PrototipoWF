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

import org.apache.commons.beanutils.Converter;

/**
 *
 * 
 */
public class FormatedJLabel extends SimpleBindableJLabel {
    private Converter formatter;
    
    public FormatedJLabel(Converter formatter) {
        this.formatter = formatter;
    }
    
    @Override
    public Object getModelValue() {
        return this.formatter.convert(Object.class, super.getModelValue());
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        super.updateModel(origen, property, this.formatter.convert(String.class, value));
    }
}
