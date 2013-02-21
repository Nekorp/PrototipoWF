/**
 *   Copyright 2013 Nekorp
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

package org.nekorp.workflow.desktop.view.resource.imp;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import org.apache.commons.lang.StringUtils;

/**
 * limita el tamanio de una caja de texto ademas de pasarlo a mayusculas
 */
public class DocumentSizeValidatorMayusculas extends DocumentSizeValidator {

    public DocumentSizeValidatorMayusculas(int param) {
        super(param);
    }

    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(fb, offs, StringUtils.upperCase(str), a);
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        super.replace(fb, offs, length, StringUtils.upperCase(str), a);
    }
}
