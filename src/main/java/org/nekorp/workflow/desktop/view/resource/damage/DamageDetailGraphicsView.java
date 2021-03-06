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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;


/**
 * @author Nekorp
 */
public class DamageDetailGraphicsView extends JPanel {

    public static final int SuperiorDerecha = 0;
    public static final int SuperiorIzquierda = 1;
    public static final int InferiorDerecha = 2;
    public static final int InferiorIzquierda = 3;
    private static final int DEFAULT_FONT_SIZE = 18;
    
    private Point posicion;
    private Point contexto;
    private String caracteristica = "";
    private String categoria = "";
    
    private int innerCircleSize = 22;
    private int outerCircleSize = 30;
    
    private int lineUnoSizeX = 25;
    private int lineUnoSizey = 110;
    //private int lineDosSize = 120;
    
    private int fontSize = DamageDetailGraphicsView.DEFAULT_FONT_SIZE;
    private int margenTexto = 0;
    
    private int orientacion;
    
    private Color colorLinea = new Color(0x347bed);
    private int strokeSize = 2;
    private BasicStroke strokeLinea; 
    private Shape clickArea;
    
    
    public DamageDetailGraphicsView() {
        this.setOpaque(false);
        strokeLinea = new BasicStroke(strokeSize, 0, 0, 4);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        Shape shape;
        
        //lineas
        int x_ini;
        int y_ini;
        int x_fin = 0;
        int y_fin = 0;
        
        //texto 
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int texto_x = 0;
        int texto_y = 0;
        int texto_height = metrics.getHeight();
        int texto_lenght = metrics.stringWidth(caracteristica);
        
        x_ini = posicion.x + contexto.x;
        y_ini = posicion.y + contexto.y;
        
        switch(orientacion) {
                case SuperiorDerecha:
                    x_fin = x_ini + lineUnoSizeX;
                    y_fin = y_ini - lineUnoSizey + texto_height;
                    break;
                case SuperiorIzquierda:
                    x_fin = x_ini - lineUnoSizeX;
                    y_fin = y_ini - lineUnoSizey + texto_height;
                    break;
                case InferiorDerecha:
                    x_fin = x_ini + lineUnoSizeX;
                    y_fin = y_ini + lineUnoSizey;
                    break;
                case InferiorIzquierda:
                    x_fin = x_ini - lineUnoSizeX;
                    y_fin = y_ini + lineUnoSizey;
                    break;
        }
        g2.setPaint(colorLinea);
        g2.setStroke(strokeLinea);
        g2.draw(new Line2D.Double(x_ini, y_ini, x_fin, y_fin));
        
        x_ini = x_fin;
        y_ini = y_fin;
        
        int lineDosSize = texto_lenght + 6;
        switch(orientacion) {
                case SuperiorDerecha:
                    x_fin = x_ini + lineDosSize;
                    y_fin = y_ini;
                    texto_x = x_ini + ((lineDosSize - texto_lenght) / 2);
                    texto_y = y_fin - texto_height + (fontSize - margenTexto);
                    break;
                case SuperiorIzquierda:
                    x_fin = x_ini - lineDosSize;
                    y_fin = y_ini;
                    texto_x = x_fin + ((lineDosSize - texto_lenght) / 2);
                    texto_y = y_fin - texto_height + (fontSize - margenTexto);
                    break;
                case InferiorDerecha:
                    x_fin = x_ini + lineDosSize;
                    y_fin = y_ini;
                    texto_x = x_ini + ((lineDosSize - texto_lenght) / 2);
                    texto_y = y_fin - texto_height + (fontSize - margenTexto);
                    break;
                case InferiorIzquierda:
                    x_fin = x_ini - lineDosSize;
                    y_fin = y_ini;
                    texto_x = x_fin + ((lineDosSize - texto_lenght) / 2);
                    texto_y = y_fin - texto_height + (fontSize - margenTexto);
                    break;
        }
        g2.setStroke(strokeLinea);
        g2.setPaint(colorLinea);
        g2.draw(new Line2D.Double(x_ini, y_ini, x_fin, y_fin));
        
        
        g2.setColor(Color.BLACK);
        g2.drawString(caracteristica, texto_x, texto_y);
        g2.drawString(categoria, texto_x, texto_y + texto_height + 1);
        //circulos
        int x_pos;
        int y_pos;
        
        x_pos = posicion.x + contexto.x - (outerCircleSize/2);
        y_pos = posicion.y + contexto.y - (outerCircleSize/2);
        shape = new Ellipse2D.Double(x_pos, y_pos, outerCircleSize, outerCircleSize);
        g2.setPaint(new Color(0xFFAAAA));
        g2.fill(shape);
        g2.draw(shape);
        clickArea = shape;

        x_pos = posicion.x + contexto.x - (innerCircleSize/2);
        y_pos = posicion.y + contexto.y - (innerCircleSize/2);
        
        shape = new Ellipse2D.Double(x_pos, y_pos, innerCircleSize , innerCircleSize);
        g2.setPaint(Color.RED);
        g2.fill(shape);
        g2.draw(shape);
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public Point getContexto() {
        return contexto;
    }

    public void setContexto(Point contexto) {
        this.contexto = contexto;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }

    public void setCaracteristica(String caracteristica) {
        if(caracteristica.compareTo("Daño estructural") == 0){
            this.caracteristica = "Estructural";
        } else {
            this.caracteristica = caracteristica;
        }
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setMargenTexto(int margenTexto) {
        this.margenTexto = margenTexto;
    }
    
    public boolean shapeContains(Point posicion) {
        return clickArea.contains(posicion);
    }
}
