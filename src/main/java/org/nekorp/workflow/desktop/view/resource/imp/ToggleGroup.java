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
package org.nekorp.workflow.desktop.view.resource.imp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nekorp
 */
public class ToggleGroup {
    private final List<JLabelToggleButton> buttons;
    public ToggleGroup() {
        buttons = new ArrayList<>();
    }
    public void addToGroup(JLabelToggleButton e) {
        this.buttons.add(e);
    }
    
    public void notifySelect(JLabelToggleButton e) {
        for (JLabelToggleButton x: buttons) {
            if (x != e) {
                x.setSelected(false);
            }
        }
    }
}
