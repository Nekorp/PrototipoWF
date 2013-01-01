/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding;

import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Marisa
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
    public void updateModel(Object origen, Object value) {
        super.updateModel(origen, this.formatter.convert(String.class, value));
    }
}
