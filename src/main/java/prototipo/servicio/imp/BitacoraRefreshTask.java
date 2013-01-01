/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

/**
 *
 * @author Marisa
 */
public class BitacoraRefreshTask implements Runnable {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraRefreshTask.class);
    private BitacoraAnalyzerImp analyzer;

    public BitacoraRefreshTask(BitacoraAnalyzerImp analyzer) {
        this.analyzer = analyzer;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.currentThread().sleep(1000l);
            } catch (InterruptedException ex) {
                BitacoraRefreshTask.LOGGER.error(ex);
            }
            analyzer.updateModel(this,null, null);
        }
    }
    
}
