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
package org.nekorp.workflow.desktop.view.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 *
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
