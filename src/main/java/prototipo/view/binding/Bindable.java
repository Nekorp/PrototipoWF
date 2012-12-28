/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

/**
 *
 * @author Marisa
 */
public interface Bindable {

    /**
     * notifica al objeto ligado que el modelo se actualizo.
     * @param value el nuevo valor del modelo.
     */
    void updateModel(Object value);
    
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
    Object getValue();
    
    /**
     * solicita que se registre un listener para sus actualizaciones y que estas
     * modifiquen el objeto target en su propiedad property.
     * @param target el objeto a ser actualizado durante las actualizaciones.
     * @param property la propiedad del objeto a actualizar.
     */
    void bindListener(Object target, String property);
}
