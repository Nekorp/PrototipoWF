/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marisa
 */
@Component
public class DateConverter implements Converter {
    @Value("#{appConfig['app.view.defaultDateFormat']}")
    private String format;

    @Override
    public Object convert(Class type, Object o) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(this.format);
            if (type == String.class) {
                if (o == null) {
                    return "";
                } else {
                    return df.format(o);
                }
            } else {
                if (o == null || o.toString().length() == 0) {
                    return null;
                }
                return df.parse(o.toString());
            }
        } catch (ParseException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
