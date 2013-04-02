/**
 *   Copyright 2013 Nekorp
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

package org.nekorp.workflow.desktop.servicio.reporte.orden.servicio;

import java.io.File;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.nekorp.workflow.desktop.control.MensajesControl;
import org.nekorp.workflow.desktop.modelo.reporte.orden.servicio.ParametrosReporteOS;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("GeneradorOrdenServicio")
public class GeneradorOrdenServicio implements GeneradorReporte<ParametrosReporteOS> {
    private static final Logger LOGGER = Logger.getLogger(GeneradorOrdenServicio.class);
    @Autowired
    private MensajesControl mensajesControl;
    @Autowired
    private OrdenServicioDataFactory ordenServicioDataFactory;
    @Override
    public void generaReporte(ParametrosReporteOS param) {
        try {
            File jasperFile = new File("report2.jasper");
            String fileName = jasperFile.getCanonicalPath();
            String outFileName = param.getDestination().getCanonicalPath();
            HashMap hm = new HashMap();
            hm.put("detalleCosto", MockOrdenServicioDataFactory.getDetalleCosto());
            JasperPrint print = JasperFillManager.fillReport(
                fileName,
                hm,
                new JRBeanCollectionDataSource(ordenServicioDataFactory.getDatosOS(param)));
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();
        } catch (JRException e) {
            GeneradorOrdenServicio.LOGGER.error("error al generar pdf", e);
            mensajesControl.reportaError("Error al generar PDF/n" + e.getMessage());
        } catch (Exception e){
            GeneradorOrdenServicio.LOGGER.error("error al generar pdf", e);
            mensajesControl.reportaError("Error al generar PDF/n" + e.getMessage());
        }
    }
}
