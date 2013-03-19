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

package org.nekorp.workflow.desktop.servicio.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nekorp.workflow.desktop.servicio.GeneradorReporte;
import org.nekorp.workflow.desktop.view.model.bitacora.BitacoraMetaData;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoEntregaVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoGeneralVB;
import org.nekorp.workflow.desktop.view.model.bitacora.EventoVB;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class GeneradorReporteImp implements GeneradorReporte {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(GeneradorReporteImp.class);
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private BitacoraMetaData bitacoraMetaData;
    @Autowired
    private CostoMetadata costosMetadata;
    @Override
    public void generaReporte(File destination) {
        FileOutputStream fileOut = null;
        try {
            GeneradorReporteImp.LOGGER.info("file:" + destination);
            //carga template
            InputStream template = new FileInputStream("Template.xlsx");
            Workbook wb = WorkbookFactory.create(template);
            CreationHelper createHelper = wb.getCreationHelper();
            //se toma la primera hoja
            Sheet sheet = wb.getSheetAt(0);
            
            //escribir los datos del primer renglon
            Row row = sheet.getRow((short)1);
            row.createCell(2).setCellValue(viewServicioModel.getId());
            row.createCell(4).setCellValue(bitacoraMetaData.getTiempoEstadia());
            //datos del segundo renglon
            row = sheet.getRow((short)2);
            row.createCell(2).setCellValue(viewServicioModel.getCliente().getNombre());
            //tercer renglon
            row = sheet.getRow((short)3);
            row.createCell(2).setCellValue(viewServicioModel.getDescripcion());
            //datos del auto
            row = sheet.getRow((short)7);
            row.getCell(1).setCellValue(viewServicioModel.getAuto().getMarca());
            row.getCell(2).setCellValue(viewServicioModel.getAuto().getTipo());
            row.getCell(3).setCellValue(viewServicioModel.getAuto().getVersion());
            row.getCell(4).setCellValue(viewServicioModel.getAuto().getNumeroSerie());
            row.getCell(5).setCellValue(viewServicioModel.getAuto().getModelo());
            row.getCell(6).setCellValue(viewServicioModel.getAuto().getColor());
            row.getCell(7).setCellValue(viewServicioModel.getAuto().getPlacas());
            row.getCell(8).setCellValue(viewServicioModel.getDatosAuto().getKilometraje());
            
            //Escribir costos
            row = sheet.getRow((short)11);
            row.getCell(1).setCellValue(costosMetadata.getTotalMecanicaManoDeObra().doubleValue());
            row.getCell(2).setCellValue(costosMetadata.getTotalMecanicaRefacciones().doubleValue());
            row.getCell(3).setCellValue(costosMetadata.getTotalHojalateriaManoDeObra().doubleValue());
            row.getCell(4).setCellValue(costosMetadata.getTotalHojalateriaInsumos().doubleValue());
            
            //Escribir la bitacora
            List<EventoVB> eventos = viewServicioModel.getBitacora().getEventos();
            int rowActual = 14;
            for (EventoVB x: eventos) {
                row = sheet.createRow((short)rowActual);
                if (x instanceof EventoEntregaVB) {
                    EventoEntregaVB eventoEntrega = (EventoEntregaVB) x;
                    row.createCell(1).setCellValue(eventoEntrega.getNombreEvento());
                    row.createCell(2).setCellValue(eventoEntrega.getResponsable());
                    //fecha con estilo
                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(
                        createHelper.createDataFormat().getFormat("yy/m/d h:mm:ss"));
                    Cell cell = row.createCell(3);
                    cell.setCellValue(eventoEntrega.getFecha());
                    cell.setCellStyle(cellStyle);
                }
                if (x instanceof EventoGeneralVB) {
                    EventoGeneralVB eventoGeneral = (EventoGeneralVB) x;
                    row.createCell(1).setCellValue("Otro");
                    row.createCell(2).setCellValue(eventoGeneral.getDetalle());
                    //fecha con estilo
                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(
                        createHelper.createDataFormat().getFormat("yy/m/d h:mm:ss"));
                    Cell cell = row.createCell(3);
                    cell.setCellValue(eventoGeneral.getFechaEvento());
                    cell.setCellStyle(cellStyle);
                    
                    row.createCell(4).setCellValue(eventoGeneral.getEtiquetas());
                }
                rowActual = rowActual +1;
            }
//            row.createCell(2).setCellValue(
//                 createHelper.createRichTextString("This is a string"));
//            row.createCell(3).setCellValue(true);
            
            
            fileOut = new FileOutputStream(destination);
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException | InvalidFormatException ex) {
            GeneradorReporteImp.LOGGER.error("error al generar reporte", ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                GeneradorReporteImp.LOGGER.error("error al cerrar archivo de reporte", ex);
            }
        }
    }

    
}
