package prototipo.view.control.imp;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prototipo.view.AppMainWindow;
import prototipo.view.control.LookAndFeelManager;
import prototipo.view.control.WindowManager;

@Aspect
@Component
public class MainWindowManager implements WindowManager {

    @Autowired
    private LookAndFeelManager lookAndFeelManager;

    @Pointcut("execution(* prototipo.control.WorkflowApp.startApliacion(..))")
    public void startAplicacion() {
    }

    @AfterReturning("startAplicacion()")
    public void iniciaMainWindow() {
        lookAndFeelManager.setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMainWindow().setVisible(true);
            }
        });
    }

}
