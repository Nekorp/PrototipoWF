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
package org.nekorp.workflow.desktop.view.resource.skin;

import java.awt.Color;

/**
 *
 * @author Nekorp
 */
public class SkinInfo {
    private ButtonStyle mainButton;
    private ButtonStyle mainTab;
    
    public SkinInfo() {
        mainButton = new ButtonStyle();
        //mainButton.setBaseColor(new Color(102,102,102)); //#666666
        mainButton.setBaseColor(Color.decode("#A8A8AC"));
        //mainButton.setHoverColor(Color.WHITE);
        mainButton.setHoverColor(Color.decode("#FFFFFF"));
        
        mainTab = new ButtonStyle();
        mainTab.setBaseColor(Color.decode("#606062"));
        mainTab.setHoverColor(Color.decode("#000000"));
        mainTab.setSelectedColor(Color.decode("#000000"));
    }

    public ButtonStyle getMainButton() {
        return mainButton;
    }

    public void setMainButton(ButtonStyle mainButton) {
        this.mainButton = mainButton;
    }

    public ButtonStyle getMainTab() {
        return mainTab;
    }

    public void setMainTab(ButtonStyle mainTab) {
        this.mainTab = mainTab;
    }
}
