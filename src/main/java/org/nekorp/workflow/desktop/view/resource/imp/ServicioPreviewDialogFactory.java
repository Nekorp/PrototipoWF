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

import java.awt.Frame;
import javax.swing.JDialog;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.quick.ServicioPreview;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("servicioPreviewDialogFactory")
public class ServicioPreviewDialogFactory implements DialogFactory {

    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    private ServicioPreview servicioPreview;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    
    @Override
    public JDialog createDialog(Frame frame, boolean modal) {
        JDialog dialog = new JDialog(mainFrame, true);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setTitle("Consulta Servicio " + " Número: " + viewServicioModel.getId());
        servicioPreview.setParent(dialog);
        servicioPreview.iniciaVista();
        servicioPreview.setEditableStatus(false);
        dialog.add(servicioPreview);
        dialog.validate();
        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        return dialog;
    }

}
