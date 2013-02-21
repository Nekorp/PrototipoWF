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
package org.nekorp.workflow.desktop.view.resource.imp;

import org.nekorp.workflow.desktop.view.AppMainWindow;
import org.nekorp.workflow.desktop.view.resource.LookAndFeelManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LookAndFeelManagerImp implements LookAndFeelManager {

    /**
     * el identificador del look and feel.
     */
    @Value("#{appConfig['app.view.lookAndFeel']}")
    private String lookAndFeelValue;

    @Override
    public void setLookAndFeel() {
        try {
            javax.swing.UIManager.setLookAndFeel(lookAndFeelValue);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void setLookAndFeelValue(String value) {
        this.lookAndFeelValue = value;
    }
}
