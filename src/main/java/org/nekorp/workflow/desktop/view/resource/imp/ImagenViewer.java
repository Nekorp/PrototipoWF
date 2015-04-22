/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import org.nekorp.workflow.desktop.servicio.imp.ImageServiceImp;

/**
 *
 * @author Nekorp
 */
public class ImagenViewer extends javax.swing.JPanel {

    private BufferedImage originalImage;

    public ImagenViewer() {
        initComponents();
    }
    /**
     * Creates new form ImagenViewer
     * @param image
     */
    public ImagenViewer(BufferedImage image) {
        initComponents();
        this.originalImage = image;
    }
    
    public void setImage(BufferedImage image) {
        originalImage = image;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.originalImage != null ) {
            //g.drawImage(originalImage, 0, 0, null);
            Graphics2D g2 = (Graphics2D)g;
            Dimension dImage = new Dimension(this.originalImage.getWidth(), this.originalImage.getHeight());
            Dimension myDimension = this.getSize();
            if (dImage.width > myDimension.width || dImage.height > myDimension.height) {
                Dimension newDimension = ImageServiceImp.getScaledDimension(dImage, this.getSize());
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                //originalImage.createGraphics();
                int origenX = (int)((myDimension.getWidth() - newDimension.getWidth()) / 2);
                int origenY = (int)((myDimension.getHeight() - newDimension.getHeight()) / 2);
                g2.drawImage(originalImage, origenX, origenY, (int)newDimension.getWidth(), (int)newDimension.getHeight(), null);
            } else {
                int origenX = (int)((myDimension.getWidth() - dImage.getWidth()) / 2);
                int origenY = (int)((myDimension.getHeight() - dImage.getHeight()) / 2);
                g2.drawImage(originalImage, origenX, origenY, null);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
