/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource;

import javax.swing.JFrame;

/**
 *
 * @author Marisa
 */
public class WindowTask implements Runnable {

    private JFrame window;
    
    @Override
    public void run() {
        this.window.setVisible(true);
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }    
}
