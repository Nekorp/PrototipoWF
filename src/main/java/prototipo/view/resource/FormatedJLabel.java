/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JLabel;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Marisa
 */
public class FormatedJLabel extends JLabel {
    
    private Converter formatter;
    
    public FormatedJLabel(Converter formatter) {
        this.formatter = formatter;
    }
    
    public Object getValue() {
        return this.formatter.convert(Object.class, this.getText());
    }
    
     public void setValue(Object value) {
        this.setText((String)this.formatter.convert(String.class, value));
    }
}
