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
package prototipo.view;

import java.awt.image.BufferedImage;
import prototipo.view.resource.ThumbViewListener;
import prototipo.view.resource.imp.ImagenViewer;

/**
 *
 * 
 */
public class ThumbnailView extends javax.swing.JPanel {

    private ThumbViewListener listener;
    /**
     * Creates new form ThumbnailView
     */
    public ThumbnailView(BufferedImage img, ThumbViewListener obs) {
        initComponents();
        this.content.add(new ImagenViewer(img));
        this.listener = obs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        borrar = new javax.swing.JMenuItem();
        content = new javax.swing.JPanel();

        borrar.setText("borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(borrar);

        setMaximumSize(new java.awt.Dimension(225, 150));
        setPreferredSize(new java.awt.Dimension(225, 150));

        content.setMaximumSize(new java.awt.Dimension(225, 150));
        content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                contentMouseReleased(evt);
            }
        });
        content.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void contentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentMouseReleased
        if(evt.isPopupTrigger()) {
            this.jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            this.listener.selectEvent(this);
        }
    }//GEN-LAST:event_contentMouseReleased

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        this.listener.deleteEvent(this);
    }//GEN-LAST:event_borrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem borrar;
    private javax.swing.JPanel content;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}