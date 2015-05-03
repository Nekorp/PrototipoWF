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
package org.nekorp.workflow.desktop.servicio.monitor.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;
import org.nekorp.workflow.desktop.view.binding.Bindable;

/**
 *
 * @author Nekorp
 */
public class PropertyEditorMonitor implements Bindable {
    private enum EditorMonitorOperation {
        addUndo, doUndo, doRedo, clear, desactiva, activa, getValue
    }
    private boolean activo;
    private Object target;
    private String property;
    private final Stack<Object> undoStack;
    private final Stack<Object> redoStack;
    private final List<Object> ignoreList;
    
    
    public PropertyEditorMonitor() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        ignoreList = new LinkedList<>();
    }
    
    private synchronized Object doOperation(Object value, EditorMonitorOperation operation) {
        if (activo) {
            if (operation == EditorMonitorOperation.addUndo) {
                this.undoStack.add(value);
                this.redoStack.clear();
            }
            if (operation == EditorMonitorOperation.doUndo) {
                try {
                    if (!undoStack.empty()) {
                        Object actualValue = PropertyUtils.getProperty(target, property);
                        this.redoStack.push(actualValue);
                        Object oldValue = this.undoStack.pop();
                        this.ignoreUpdate(oldValue);
                        PropertyUtils.setProperty(target, property, oldValue);
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(PropertyEditorMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (operation == EditorMonitorOperation.doRedo) {
                try {
                    if (!redoStack.empty()) {
                        Object actualValue = PropertyUtils.getProperty(target, property);
                        this.undoStack.push(actualValue);
                        Object nextValue = this.redoStack.pop();
                        this.ignoreUpdate(nextValue);
                        PropertyUtils.setProperty(target, property, nextValue);
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(PropertyEditorMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (operation == EditorMonitorOperation.clear) {
                this.undoStack.clear();
                this.redoStack.clear();
            }
            if (operation == EditorMonitorOperation.desactiva) {
                this.activo = false;
            }
        } else {
            if (operation == EditorMonitorOperation.activa) {
                this.activo = true;
            }
        }
        if (operation == EditorMonitorOperation.getValue) {
            try {
                return PropertyUtils.getProperty(target, property);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                Logger.getLogger(PropertyEditorMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void doUndo() {
        doOperation(null, EditorMonitorOperation.doUndo);
    }
    
    public void doRedo() {
        doOperation(null, EditorMonitorOperation.doRedo);
    }
    
    public void clear() {
        doOperation(null, EditorMonitorOperation.clear);
    }
    
    public void setActivo(boolean activo) {
        if (activo) {
            doOperation(null, EditorMonitorOperation.activa);
        } else {
            doOperation(null, EditorMonitorOperation.desactiva);
        }
    }

    @Override
    public void updateModel(Object origen, String property, Object value) {
        if (ignoreList.contains(value)) {
            ignoreList.remove(value);
        } else {
            this.doOperation(value, EditorMonitorOperation.addUndo);
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignoreList.add(value);
    }

    @Override
    public Object getModelValue() {
        return doOperation(null, EditorMonitorOperation.getValue);
    }

    @Override
    public void bindListener(Object target, String property) {
        this.target = target;
        this.property = property;
    }
}
