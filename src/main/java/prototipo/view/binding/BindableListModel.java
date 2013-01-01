/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import java.util.LinkedList;
import javax.swing.AbstractListModel;
import prototipo.view.binding.listener.BindingListDataListener;

/**
 *
 * @author Marisa
 */
public class BindableListModel<T> extends AbstractListModel<T> implements Bindable {
    private BindingListDataListener listener;
    private LinkedList<Object> ignore;
    private LinkedList<T> datos;
    
    public BindableListModel() {
        this.datos = new LinkedList<>();
        ignore = new LinkedList<>();
    }
    
    @Override
    public void updateModel(Object origen, String property, Object value) {
        if(!ignore.remove(value)){
            this.removeListDataListener(listener);
            LinkedList<T> param = (LinkedList<T>) value;
            int index = datos.size() -1;
            if (index < 0) {
                index = 0;
            }
            this.datos = new LinkedList<>();
            for (T dato: param) {
                this.datos.add(dato);
            }
            this.fireIntervalAdded(value, 0, index);
            this.addListDataListener(listener);
        }
    }

    @Override
    public void ignoreUpdate(Object value) {
        ignore.add(value);
    }

    @Override
    public Object getModelValue() {
        return this.datos;
    }

    @Override
    public void bindListener(Object target, String property) {
       listener = new BindingListDataListener(target, property, this);
       this.addListDataListener(listener);
    }

    @Override
    public int getSize() {
        return datos.size();
    }

    @Override
    public T getElementAt(int index) {
        return datos.get(index);
    }
    
    public void addElement(T element){
        this.datos.add(element);
        this.fireIntervalAdded(this, this.datos.size()-1, this.datos.size()-1);
    }
    
    public void removeElement(T element) {
        int index = this.datos.indexOf(element);
        if(this.datos.remove(element)){
            this.fireIntervalRemoved(this, index, index);
        }
    }
}
