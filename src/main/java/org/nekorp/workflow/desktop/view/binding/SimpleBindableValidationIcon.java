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
import javax.swing.JPanel;
import org.nekorp.workflow.desktop.view.model.validacion.EstatusValidacion;
/**
 *
 * 
 */
public class SimpleBindableValidationIcon extends JPanel implements Bindable {
    
    private JPanel okImage;
    private JPanel errorImage;
    private LinkedList<Object> ignore;
    
    public SimpleBindableValidationIcon(JPanel ok, JPanel error) {
        ignore = new LinkedList<>();
        this.okImage = ok;
        this.errorImage = error;
        setLayout(new java.awt.BorderLayout());
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            EstatusValidacion v = (EstatusValidacion) value;
            if (v.isValido()) {
                changeImage(okImage);
            } else {
                changeImage(errorImage);
            }
            if (v.getDetalle().isEmpty()) {
                this.setToolTipText(null);
            } else {
                this.setToolTipText(v.getDetalle());
            }
            this.repaint();
            this.validate();
        }
    }
    
    private void changeImage(JPanel nueva) {
        if(this.getComponents().length > 0) {
            if (this.getComponent(0) != nueva) {
                this.removeAll();
                this.add(nueva);
                this.validate();
            }
        } else {
            this.add(nueva);
            this.validate();
        }
    }
    
    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
       throw new UnsupportedOperationException("Esta cosa no edita");
    }

    @Override
    public void bindListener(Object target, String property) {
        //este coso no modifica nada;
    }
}
