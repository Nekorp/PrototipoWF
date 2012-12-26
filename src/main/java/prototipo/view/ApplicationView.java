/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view;

import javax.swing.JPanel;

/**
 *
 * @author Marisa
 */
public abstract class ApplicationView extends JPanel {
    
    public abstract void iniciaVista();
    
    public abstract void setEditableStatus(boolean value);
}
