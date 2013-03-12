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
import org.nekorp.workflow.desktop.control.HistorialServiciosController;
import org.nekorp.workflow.desktop.view.HistorialServicioView;
import org.nekorp.workflow.desktop.view.resource.DialogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class HistorialServicioDialogFactory implements DialogFactory {

    @Autowired
    private HistorialServicioView historialView;
    @Autowired
    private javax.swing.JFrame mainFrame;
    @Autowired
    private HistorialServiciosController historialController;
    @Override
    public JDialog createDialog(Frame frame, boolean modal) {
        HistorialServiciosJTableModel model = new HistorialServiciosJTableModel();
        model.setDatos(historialController.getHistorial());
        JDialog dialog = new JDialog(mainFrame, true);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setTitle("Historial Servicio");
        historialView.setParent(dialog);
        historialView.iniciaVista();
        historialView.setHistoricoModel(model);
        dialog.add(historialView);
        dialog.validate();
        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        return dialog;
    }

}
