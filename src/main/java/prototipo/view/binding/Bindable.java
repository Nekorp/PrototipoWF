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

/**
 *
 *
 */
public interface Bindable {

    /**
     * notifica al objeto ligado que el modelo se actualizo.
     * @param value el nuevo valor del modelo.
     */
    void updateModel(Object origen, String property, Object value);
    
    /**
     * indica al objeto que ignore el update al valor enviado
     * esto es un fix temporal para el problema del update ciclico
     * @param value el update a ignorar, en teoria fue un update creado
     * dentro del mismo componente.
     */
    void ignoreUpdate(Object value);
    
    /**
     * consulta el valor actual del modelo que representa el objeto.
     * @return el valor del objeto representado.
     */
    Object getModelValue();
    
    /**
     * solicita que se registre un listener para sus actualizaciones y que estas
     * modifiquen el objeto target en su propiedad property.
     * @param target el objeto a ser actualizado durante las actualizaciones.
     * @param property la propiedad del objeto a actualizar.
     */
    void bindListener(Object target, String property);
}
