/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.binding.listener.BindingDocumentListener;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;

/**
 *
 * @author Nekorp
 */
public class MonedaBindableJTextField extends JTextField implements Bindable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SimpleBindableJTextField.class);
    private LinkedList<Object> ignore;
    private DocumentListener listener;
    
    public MonedaBindableJTextField() {
        super();
        ignore = new LinkedList<>();
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                char car = e.getKeyChar();
                if (car != '.') {
                    if((car<'0' || car>'9')) {
                        e.consume();
                    }
                } else {
                    if (StringUtils.contains(getText(), '.')) {
                        e.consume();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    public void updateModel(Object origen, String property, Object value) {
        try {
            if(!ignore.remove(value)){
                this.getDocument().removeDocumentListener(listener);
                this.setText(value.toString());
                this.getDocument().addDocumentListener(listener);
            }
        } catch (IllegalStateException e) {
            MonedaBindableJTextField.LOGGER.debug("quiere actualizar doble" + this);
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        this.ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        try {
            return MonedaVB.valueOf(this.getText());
        } catch (IllegalArgumentException e ) {
            String value = this.getText();
            int indexPoint = StringUtils.indexOf(value, ".");
            if (indexPoint == -1) {
                throw e;
            } else {
                if (indexPoint + 1 == value.length()) {
                    value = StringUtils.remove(value, '.');
                } else {
                    value = StringUtils.substring(value, 0, indexPoint + 3);
                }
            }
            return MonedaVB.valueOf(value);
        }
    }

    @Override
    public void bindListener(Object target, String property) {
        this.listener = new BindingDocumentListener(target, property, this);
        this.getDocument().addDocumentListener(this.listener);
    }
}
