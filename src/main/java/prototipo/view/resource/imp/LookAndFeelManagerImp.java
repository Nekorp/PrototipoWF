package prototipo.view.resource.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import prototipo.view.AppMainWindow;
import prototipo.view.resource.LookAndFeelManager;

@Component
public class LookAndFeelManagerImp implements LookAndFeelManager {

    /**
     * el identificador del look and feel.
     */
    @Value("#{appConfig['app.view.lookAndFeel']}")
    private String lookAndFeelValue;

    @Override
    public void setLookAndFeel() {
        try {
            javax.swing.UIManager.setLookAndFeel(lookAndFeelValue);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppMainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMainWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void setLookAndFeelValue(String value) {
        this.lookAndFeelValue = value;
    }
}
