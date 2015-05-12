/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

package org.nekorp.workflow.desktop.view.resource.damage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 * @author Nekorp
 */
public class IndicadorBarraGraphicsView extends JPanel {

    private int widthBar;
    private int heightBar;
    private int strokeSize = 2;
    private double porcentaje;
    
    @Override
    public void paint(Graphics g) {
        int widthOutRect = widthBar - strokeSize - (strokeSize/2);
        int heightOutRect = heightBar - strokeSize - (strokeSize/2);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape;
        g2.setRenderingHints(rh);
        shape = new RoundRectangle2D.Double(strokeSize/2, strokeSize/2, widthOutRect, heightOutRect, heightOutRect/2, heightOutRect/2);
        BasicStroke strokeLinea = new BasicStroke(strokeSize, 0, 0, 4);
        g2.setStroke(strokeLinea);
        GradientPaint gpOut = new GradientPaint(0, 0, new Color(219,219,219), 0, heightOutRect, Color.WHITE, false);
        g2.setPaint(gpOut);
        g2.fill(shape);
        g2.setPaint(Color.BLACK);
        g2.draw(shape);
        
        int widthInnerRect = (int)(widthBar * porcentaje) - (strokeSize * 3) + 1;
        int heightInnerRect = heightBar - (strokeSize * 3) + 1;
        
        shape = new RoundRectangle2D.Double(strokeSize + strokeSize/2 - 1, strokeSize + strokeSize/2 - 1,
            widthInnerRect, heightInnerRect, heightInnerRect/2, heightInnerRect/2);
        GradientPaint gpInner = new GradientPaint(0, 0, Color.WHITE, 0, heightInnerRect, new Color(38, 75, 140), false);
        g2.setPaint(gpInner);
        g2.fill(shape);
        g2.setPaint(new Color(38, 75, 140));
        g2.draw(shape);
        
        shape = new RoundRectangle2D.Double(strokeSize/2, strokeSize/2, widthOutRect, heightOutRect, heightOutRect/2, heightOutRect/2);
        g2.setStroke(strokeLinea);
        g2.setPaint(Color.BLACK);
        g2.draw(shape);
    }

    public void setWidthBar(int widthBar) {
        this.widthBar = widthBar;
    }

    public void setHeightBar(int heightBar) {
        this.heightBar = heightBar;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
