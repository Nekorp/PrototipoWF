package prototipo.control.imp;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import prototipo.control.WorkflowApp;

@Component
public class WorkflowAppPrototipo implements WorkflowApp {
	private static final Logger LOGGER = Logger.getLogger(WorkflowAppPrototipo.class);
	
	@Override
	public void startApliacion() {
		WorkflowAppPrototipo.LOGGER.info("iniciando aplicacion");
	}
}
