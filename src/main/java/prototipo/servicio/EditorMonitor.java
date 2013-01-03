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
package prototipo.servicio;

import prototipo.servicio.imp.EditorLog;

/**
 *
 * monitorea ediciones al modelo,
 * guarda un historial de modificaciones y
 * en teoria podria servir para... undo y activar el guardar.
 */
public interface EditorMonitor {
    
    void addUndo(EditorLog log);
    /**
     * pide que se deshaga el ultimo cambio que se tenga en la cola
     * esto puede o no funcionar dependiendo de si existen cambios permitidos
     * para deshacer
     */
    void undo();
    /**
     * el undo del undo
     */
    void redo();
    /**
     * limpia la lista de cambios por deshacer completamente
     * sin importar nada =p
     */
    void clear();
    /**
     * limpia la lista pero de los cambios con respecto a un objeto del modelo
     * pero toma en cuenta reglas como los id's
     * @param target el objeto a quitar del historial de cambios 
     */
    void clear(Object target);
    /**
     * @return true si existen cambios a deshacer (validos o no)
     */
    boolean hasChange();
    
    /**
     * @return true si tiene undos en el stack
     */
    boolean hasUndo();
    
    /**
     * @return true si tiene redo en el stack
     */
    boolean hasRedo();
}
