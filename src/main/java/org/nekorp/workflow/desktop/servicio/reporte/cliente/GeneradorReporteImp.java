/**
 *   Copyright 2012-2013 Nekorp
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

package org.nekorp.workflow.desktop.servicio.reporte.cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.ReporteCliente;
import org.nekorp.workflow.desktop.servicio.reporte.GeneradorReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class GeneradorReporteImp implements GeneradorReporte {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(GeneradorReporteImp.class);
    @Autowired
    private DatosReporteClienteFactory datosReporteClienteFactory;
    @Autowired
    private EncabezadoReporteCliente encabezadoReporteCliente;
    @Autowired
    private DatosAutoReporteCliente datosAutoReporteCliente;
    @Autowired
    private CostoMecanicaReporteCliente costoMecanicaReporteCliente;
    @Autowired
    private CostoHojalateriaReporteCliente costoHojalateriaReporteCliente;
    @Autowired
    private TotalServicioReporteCliente totalServicioReporteCliente;
    @Autowired
    private BitacoraReporteCliente bitacoraReporteCliente;
    @Override
    public void generaReporte(File destination) {
        FileOutputStream fileOut = null;
        ReporteCliente datos = datosReporteClienteFactory.getData();
        try {
            GeneradorReporteImp.LOGGER.info("file:" + destination);
            //Workbook wb = WorkbookFactory.create(template);
            XSSFWorkbook wb = new XSSFWorkbook();
            int maxColumn = 0;
            //CreationHelper createHelper = wb.getCreationHelper();
            //se crea la primera hoja
            Sheet sheet = wb.createSheet("Hoja1");
            BordeSeccion borde = new BordeSeccion();
            ContextoSeccion contexto = new ContextoSeccion();
            contexto.setWb(wb);
            contexto.setSheet(sheet);
            //encabezado
            borde.setLeftColumn(1);
            borde.setUpperRow(1);
            BordeSeccion bordeEncabezado = encabezadoReporteCliente.generar(borde, contexto, datos);
            if (bordeEncabezado.getRightColumn() > maxColumn) {
                maxColumn = bordeEncabezado.getRightColumn();
            }
            //datos auto
            borde.setLeftColumn(1);
            borde.setUpperRow(bordeEncabezado.getLowerRow() + 2);
            BordeSeccion bordeAuto = datosAutoReporteCliente.generar(borde, contexto, datos);
            if (bordeAuto.getRightColumn() > maxColumn) {
                maxColumn = bordeAuto.getRightColumn();
            }
            //mecanica
            borde.setLeftColumn(1);
            borde.setUpperRow(bordeAuto.getLowerRow() + 2);
            BordeSeccion bordeMecanica = costoMecanicaReporteCliente.generar(borde, contexto, datos);
            if (bordeMecanica.getRightColumn() > maxColumn) {
                maxColumn = bordeMecanica.getRightColumn();
            }
            //hojalateria
            borde.setLeftColumn(bordeMecanica.getRightColumn() + 1);
            BordeSeccion bordeHojalateria = costoHojalateriaReporteCliente.generar(borde, contexto, datos);
            if (bordeHojalateria.getRightColumn() > maxColumn) {
                maxColumn = bordeHojalateria.getRightColumn();
            }
            //total servicio
            if (bordeMecanica.getLowerRow() < bordeHojalateria.getLowerRow()) {
                borde.setUpperRow(bordeHojalateria.getLowerRow() + 2);
            } else {
                borde.setUpperRow(bordeMecanica.getLowerRow() + 2);
            }
            borde.setLeftColumn(1);
            BordeSeccion bordeTotalServicio = totalServicioReporteCliente.generar(borde, contexto, datos);
            if (bordeTotalServicio.getRightColumn() > maxColumn) {
                maxColumn = bordeTotalServicio.getRightColumn();
            }
            //bitacora
            borde.setLeftColumn(1);
            borde.setUpperRow(bordeTotalServicio.getLowerRow() + 3);
            BordeSeccion bordeBitacora = bitacoraReporteCliente.generar(borde, contexto, datos);
            if (bordeBitacora.getRightColumn() > maxColumn) {
                maxColumn = bordeBitacora.getRightColumn();
            }
            for (int i = 1; i <= maxColumn; i++) {
                sheet.autoSizeColumn(i);
            }
            fileOut = new FileOutputStream(destination);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            GeneradorReporteImp.LOGGER.error("error al generar reporte", ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException | NullPointerException ex) {
                GeneradorReporteImp.LOGGER.error("error al cerrar archivo de reporte", ex);
            }
        }
    }
}
