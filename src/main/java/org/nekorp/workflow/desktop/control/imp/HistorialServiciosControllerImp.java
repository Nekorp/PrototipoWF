/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

package org.nekorp.workflow.desktop.control.imp;

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.control.HistorialServiciosController;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.data.access.ServicioDAO;
import org.nekorp.workflow.desktop.servicio.CostosCalculator;
import org.nekorp.workflow.desktop.servicio.bridge.CostoBridge;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.historial.RegistoHistoriaServicioVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndex;
import technology.tikal.taller.automotriz.model.servicio.costo.RegistroCosto;

/**
 * @author Nekorp
 */
@Service
public class HistorialServiciosControllerImp implements HistorialServiciosController {

    @Autowired
    private CostosCalculator costoCalculator;
    @Autowired
    private CostoDAO costoDAO;
    @Autowired
    private CostoBridge costoBridge;
    @Autowired
    private ServicioDAO servicioDAO;
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    
    @Override
    public List<RegistoHistoriaServicioVB> getHistorial() {
        List<RegistoHistoriaServicioVB> respuesta = new LinkedList<>();
        List<ServicioIndex> serviciosRelacionados = servicioDAO.getIndiceServiciosMismoAuto(servicioVB.getAuto().getNumeroSerie());
        for (ServicioIndex x: serviciosRelacionados) {
            respuesta.add(generaRegistro(x));
        }
        return respuesta;
    }

    private RegistoHistoriaServicioVB generaRegistro(ServicioIndex index) {
        RegistoHistoriaServicioVB respuesta = new RegistoHistoriaServicioVB();
        respuesta.setNumeroServicio(index.getId().toString());
        respuesta.setFechaInicio(index.getFechaInicio());
        respuesta.setDescripcionServicio(index.getDescripcion());
        CostoMetadata costoMD = new CostoMetadata();
        List<RegistroCostoVB> costosVB = new LinkedList<>();
        List<RegistroCosto> costo = costoDAO.cargar(index.getId());
        costoBridge.load(costo, costosVB);
        costoCalculator.calculaCosto(costosVB, costoMD);
        respuesta.setCosto(costoMD.getSubtotal());
        return respuesta;
    }
}
