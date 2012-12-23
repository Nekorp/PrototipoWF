package prototipo.control;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	private static Logger LOGGER = Logger.getLogger(MainClass.class);

	public static void main(String arg[]) {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
			ctx.getBean(WorkflowApp.class).startApliacion();
		} catch (Exception e) {
			MainClass.LOGGER.error("No se logro inicializar la aplicacion", e);
			System.exit(1);
		}
	}
}
