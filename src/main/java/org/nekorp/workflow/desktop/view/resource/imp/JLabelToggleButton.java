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

import java.awt.Color;
import org.nekorp.workflow.desktop.view.icon.FlatButtonIcon;
import org.nekorp.workflow.desktop.view.resource.skin.ButtonStyle;

/**
 *
 * @author Nekorp
 */
public abstract class JLabelToggleButton extends JLabelButton {
    
    private ToggleGroup toggleGroup;
    private Color selectedColor;
    private Color selectedBackgorundColor;
    private boolean selected;
    
    public JLabelToggleButton() {
        super();
    }
    
    public JLabelToggleButton(FlatButtonIcon buttonIcon) {
        super(buttonIcon);
    }
    
    @Override
    protected void mouseClickedEvent(java.awt.event.MouseEvent evt) {
        if (javax.swing.SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
            this.setSelected(true);
            this.actionPerform(evt);
        }
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
        this.toggleGroup.addToGroup(this);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getSelectedBackgorundColor() {
        return selectedBackgorundColor;
    }

    public void setSelectedBackgorundColor(Color selectedBackgorundColor) {
        this.selectedBackgorundColor = selectedBackgorundColor;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (toggleGroup != null && this.selected) {
            this.toggleGroup.notifySelect(this);
        }
        chooseColor();
    }
        
    @Override
    public void chooseColor() {
        if (selected) {
            this.setForeground(selectedColor);
            if (this.getButtonIcon() != null) {
                this.getButtonIcon().setColor(selectedColor);
            }
            if (this.getBackgroundPanel() != null) {
                this.getBackgroundPanel().setBackground(this.selectedBackgorundColor);
            }
            this.repaint();
        } else{
            super.chooseColor();
        }
    }

    @Override
    public void setStyle(ButtonStyle style) {
        super.setStyle(style);
        this.selectedColor = style.getSelectedColor();
        this.selectedBackgorundColor = style.getSelectedBackgorundColor();
    }
}
