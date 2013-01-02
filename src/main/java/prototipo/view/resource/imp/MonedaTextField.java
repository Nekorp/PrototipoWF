/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.resource.imp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import org.apache.commons.lang.StringUtils;

/**
 *
 * text field para que solo capturen monedas
 */
public class MonedaTextField extends JTextField {
    public MonedaTextField() {
        super();
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                char car = e.getKeyChar();
                if (car != '.') {
                    if((car<'0' || car>'9')) {
                        e.consume();
                    }
                } else {
                    if (StringUtils.contains(getText(), '.')) {
                        e.consume();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
