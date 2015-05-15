/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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
package org.nekorp.workflow.desktop.view.resource.busqueda;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import org.apache.commons.lang.StringUtils;
import technology.tikal.string.util.StringNormalizer;

/**
 *
 * @author Nekorp
 */
public class BusquedaAutoRowFilter extends RowFilter {

    private List<Matcher> matchers;
    private boolean acceptAll;
    
    public BusquedaAutoRowFilter(String filtro) {
        if (filtro == null) {
            throw new IllegalArgumentException("El filtro no debe ser nulo");
        }
        if (filtro.length() < 1) {
            acceptAll = true;
        }
        String[] filtros = StringUtils.split(filtro, ",");
        matchers = new ArrayList<>();
        for (String x: filtros) {
            String nuevoFiltro = x;
            boolean quote = false;
            if (x.startsWith("\"") && x.endsWith("\"") && x.length() > 2) {
                nuevoFiltro = x.substring(1, x.length()-1);
                quote = true;
            }
            if (quote) {
                matchers.add(Pattern.compile("^" + StringNormalizer.normalize(nuevoFiltro) + "$").matcher(""));
            } else {
                if (x.endsWith(" ") && StringUtils.isNotEmpty(x)) {
                    matchers.add(Pattern.compile(".*" + StringNormalizer.normalize(nuevoFiltro) + " .*").matcher(""));
                } else {
                    matchers.add(Pattern.compile(".*" + StringNormalizer.normalize(nuevoFiltro) + ".*").matcher(""));
                }
            }
        }
    }
    
    @Override
    public boolean include(Entry entry) {
        if (acceptAll) {
            return true;
        }
        for (Matcher x: matchers) {
            boolean match = false;
            //acomodarlos en el orden que se espera que hagan match o los menos costosos primero
            if (!match) {
                String id = entry.getStringValue(0);
                match = x.reset(id).find();
            }
            if (!match) {
                String placas = entry.getStringValue(1).toLowerCase();
                match = x.reset(placas).find();
            }
            if (!match) {
                String tipo = StringNormalizer.normalize(entry.getStringValue(2));
                match = x.reset(tipo).find();
            }
            if (!match) {
                return false;
            }
        }
        return true;
    }
}
