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
package org.nekorp.workflow.desktop.view.start;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.resource.LookAndFeelManager;
import org.nekorp.workflow.desktop.view.resource.WindowTask;

/**
 *
 * @author Nekorp
 */
public class AppStartingView extends javax.swing.JFrame {

    private LookAndFeelManager lookAndFeelManager;
    private List<TaskStatusView> tasks;
    
    public void init() {
        tasks = new LinkedList<>();
        lookAndFeelManager.setLookAndFeel();
        this.initComponents();
        this.validate();
        this.pack();
        setLocationRelativeTo(null);
        final WindowTask windowTask = new WindowTask();
        windowTask.setWindow(this);
        java.awt.EventQueue.invokeLater(windowTask);
    }
    
    public void addTask(TaskStatusView task) {
        this.tasks.add(task);
        recalculateMsg();
    }
    
    public synchronized void recalculateMsg() {
        String msg = "";
        for(TaskStatusView x: this.tasks) {
            if (StringUtils.isEmpty(msg)) {
                msg = x.toString();
            } else {
                msg = msg + "\n" + x.toString();
            }
        }
        this.mensajes.setText(msg);
    }
    

    public void setLookAndFeelManager(LookAndFeelManager lookAndFeelManager) {
        this.lookAndFeelManager = lookAndFeelManager;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("System Start");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("El Sistema esta iniciando");

        jScrollPane1.setBorder(null);

        mensajes.setEditable(false);
        mensajes.setBackground(new java.awt.Color(0, 0, 0));
        mensajes.setColumns(20);
        mensajes.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        mensajes.setForeground(new java.awt.Color(255, 255, 255));
        mensajes.setRows(5);
        mensajes.setBorder(null);
        jScrollPane1.setViewportView(mensajes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mensajes;
    // End of variables declaration//GEN-END:variables
}