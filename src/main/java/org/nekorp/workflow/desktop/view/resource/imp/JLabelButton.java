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
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.nekorp.workflow.desktop.view.icon.FlatButtonIcon;
import org.nekorp.workflow.desktop.view.resource.skin.ButtonStyle;

/**
 *
 * @author Nekorp
 */
public abstract class JLabelButton extends JLabel {
    private FlatButtonIcon buttonIcon;
    private Color baseColor;
    private Color hoverColor;
    private JPanel backgroundPanel;
    private Color backgroundColor;
    private Color backgroundHoverColor;
    private boolean mouseOver;
    private boolean active;
    
    public JLabelButton() {
        setListener();
        active = true;
    }
    
    public JLabelButton(FlatButtonIcon buttonIcon) {
        this.buttonIcon = buttonIcon;
        super.setIcon(buttonIcon);
        setListener();
        active = true;
    }
    
    private void setListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (active) {
                    mouseClickedEvent(evt);
                }
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnteredEvent(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExitedEvent(evt);
            }
        });
    }
    
    protected void mouseClickedEvent(java.awt.event.MouseEvent evt) {
        if (javax.swing.SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
            this.actionPerform(evt);
        }
    }
    
    protected abstract void actionPerform(java.awt.event.MouseEvent evt);
    
    protected void mouseEnteredEvent(java.awt.event.MouseEvent evt) {
        mouseOver = true;
        chooseColor();
    }
    
    protected void mouseExitedEvent(java.awt.event.MouseEvent evt) {
        mouseOver = false;
        chooseColor();
    }

    public FlatButtonIcon getButtonIcon() {
        return buttonIcon;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }
    
    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public void setBackgroundPanel(JPanel backgroundPanel) {
        this.backgroundPanel = backgroundPanel;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundHoverColor() {
        return backgroundHoverColor;
    }

    public void setBackgroundHoverColor(Color backgroundHoverColor) {
        this.backgroundHoverColor = backgroundHoverColor;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        this.active = enabled;
    }
    
    public void chooseColor() {
        if (active) {
            if (mouseOver) {
                this.setForeground(hoverColor);
                //this.setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
                if (buttonIcon != null) {
                    this.buttonIcon.setColor(hoverColor);
                }
                if (this.backgroundPanel != null) {
                    this.backgroundPanel.setBackground(this.backgroundHoverColor);
                }
            } else {
                this.setForeground(baseColor);
                //this.setFont(new Font(this.getFont().getName(), 0, this.getFont().getSize()));
                if (buttonIcon != null) {
                    this.buttonIcon.setColor(baseColor);
                }
                if (this.backgroundPanel != null) {
                    this.backgroundPanel.setBackground(this.backgroundColor);
                }
            }
        } else {
            this.setForeground(baseColor);
            if (buttonIcon != null) {
                this.buttonIcon.setColor(baseColor);
            }
            if (this.backgroundPanel != null) {
                this.backgroundPanel.setBackground(this.backgroundColor);
            }
        }
        this.repaint();
    }
    
    public void setStyle(ButtonStyle style) {
        this.baseColor = style.getBaseColor();
        this.hoverColor = style.getHoverColor();
        this.backgroundColor = style.getBackgroundColor();
        this.backgroundHoverColor = style.getHoverBackgroundColor();
        this.chooseColor();
    }
}
