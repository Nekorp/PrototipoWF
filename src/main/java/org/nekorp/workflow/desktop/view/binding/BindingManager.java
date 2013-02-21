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

/**
 *
 *
 */
public interface BindingManager<T> {
    /**
     * registra un nuevo binding
     * @param target el objeto del modelo al que se quiere hacer binding.
     * @param property la propiedad del objeto al que se quiere ligar.
     * @param component el componente de la vista que se quiere bindear. 
     */
    void registerBind(Object target, String property, T component);
    
    /**
     * quita un binding
     * @param target el objeto del modelo al que se quiere hacer binding.
     * @param property la propiedad del objeto al que se quiere ligar.
     * @param component el componente de la vista que se quiere bindear. 
     */
    void removeBind(Object target, String property, T component);
    
    /**
     * elimina todos los binding asociados entre el componente y el target
     * @param target el objeto con el que se tienen los bindings
     * @param component el componente que quiere liberar los bindings
     */
    void clearObjectBindings(Object target, T component);
    
    /**
     * elimina todos los binding que tenga el componente
     * @param component el componente que quiere liberar los bindings
     */
    void clearBindings(T component);
    
    /**
     * procesa una actualizacion en el modelo.
     * @param origen el objeto que fue modificado
     * @param property el nombre de la propiedad
     * @param value el primer parametro del metodo
     */
    void processModelUpdate(Object origen, String property, Object value);
}
