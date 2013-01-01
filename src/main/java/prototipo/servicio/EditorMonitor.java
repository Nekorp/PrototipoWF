/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio;

/**
 *
 * monitorea ediciones al modelo,
 * guarda un historial de modificaciones y
 * en teoria podria servir para... undo y activar el guardar.
 */
public interface EditorMonitor {
    /**
     * pide que se deshaga el ultimo cambio que se tenga en la cola
     * esto puede o no funcionar dependiendo de si existen cambios permitidos
     * para deshacer
     */
    void undo();
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
}
