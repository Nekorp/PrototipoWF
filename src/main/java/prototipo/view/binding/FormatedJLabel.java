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
    public Object getValue() {
        return this.formatter.convert(Object.class, super.getValue());
    }
    
    @Override
    public void updateModel(Object value) {
        super.updateModel(this.formatter.convert(String.class, value));
    }
}
