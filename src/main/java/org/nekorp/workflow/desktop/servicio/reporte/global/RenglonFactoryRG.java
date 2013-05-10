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

package org.nekorp.workflow.desktop.servicio.reporte.global;

import java.util.Date;
import org.joda.time.DateTime;
import org.nekorp.workflow.desktop.modelo.reporte.global.RenglonRG;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class RenglonFactoryRG implements DataFactoryRG<RenglonRG> {

    @Autowired
    private DatosAutoFactoryRG factoryAuto;
    @Autowired
    private DatosBitacoraFactoryRG factoryBitacora;
    @Autowired
    private DatosClienteFactoryRG factoryCliente;
    @Autowired
    private DatosCostoFactoryRG factoryCosto;
    @Autowired
    private DatosServicioFactoryRG factoryServicio;
    
    @Override
    public RenglonRG build(Servicio data) {
        RenglonRG r = new RenglonRG();
        r.setDatosAuto(factoryAuto.build(data));
        r.setDatosBitacora(factoryBitacora.build(data));
        r.setDatosCliente(factoryCliente.build(data));
        r.setDatosCosto(factoryCosto.build(data));
        r.setDatosServicio(factoryServicio.build(data));
        Date entradaAutoRaw = r.getDatosBitacora().getFechaIngresoAuto();
        if (entradaAutoRaw != null) {
            DateTime entradaAuto = new DateTime(entradaAutoRaw);
            entradaAuto = new DateTime(entradaAuto.getYear(), entradaAuto.getMonthOfYear(), entradaAuto.getDayOfMonth(),
                entradaAuto.hourOfDay().getMinimumValue(), entradaAuto.minuteOfHour().getMinimumValue(), 
                entradaAuto.secondOfMinute().getMinimumValue(), entradaAuto.millisOfSecond().getMinimumValue(),
                entradaAuto.getZone());
            DateTime iniServ = new DateTime(data.getMetadata().getFechaInicio());
            iniServ = new DateTime(iniServ.getYear(), iniServ.getMonthOfYear(), iniServ.getDayOfMonth(),
                iniServ.hourOfDay().getMinimumValue(), iniServ.minuteOfHour().getMinimumValue(), 
                iniServ.secondOfMinute().getMinimumValue(), iniServ.millisOfSecond().getMinimumValue(),
                iniServ.getZone());
            if (iniServ.isBefore(entradaAuto)) {
                r.getDatosServicio().setProgramado("X");
            }
        }   
        return r;
    }

}
