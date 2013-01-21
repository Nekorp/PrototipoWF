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

package prototipo.view.resource.imp;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.apache.commons.lang.StringUtils;

/**
 *
 */
public class DocumentSizeValidator extends DocumentFilter {
    private int maxSize;

    public DocumentSizeValidator(int param) {
        maxSize = param;
    }

    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        int sizeActual = fb.getDocument().getLength();
        int espacio = maxSize - sizeActual;
        String dato = str;
        if (espacio <= 0) {
            return;
        }
        if (dato.length() > espacio) {
            dato = StringUtils.substring(str, 0, espacio);
        }
        super.insertString(fb, offs, dato, a);
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        int sizeActual = fb.getDocument().getLength() - length;//ya sin el espacio que se remplaza
        int espacio = maxSize - sizeActual;
        String dato = str;
        if (espacio <= 0) {
            return;
        }
        if (dato.length() > espacio) {
            dato = StringUtils.substring(str, 0, espacio);
        }
        super.replace(fb, offs, length, dato, a);
    }
}
