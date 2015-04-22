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

package org.nekorp.workflow.desktop.servicio.bridge;

import java.util.List;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.servicio.CobranzaMetadataCalculator;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.cobranza.DatosCobranzaVB;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioIndexVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.customers.model.Customer;
import technology.tikal.customers.model.name.OrganizationName;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndex;

/**
 * @author Nekorp
 */
@Service
public class ServicioIndexBridge implements ModelBridge<List<ServicioIndex>, List<ServicioIndexVB>> {

    @Autowired
    private CobranzaMetadataCalculator cobranzaMetadaCalculator;
    @Autowired
    private DatosCobranzaBridge datosCobranzaBridge;
    @Autowired
    private CustomerDAO customerDao;
    @Override
    public void load(List<ServicioIndex> origen, List<ServicioIndexVB> destino) {
        ServicioIndexVB nuevo;
        for (ServicioIndex x: origen) {
            //TODO quitar este new cuando se requieran pojos proxeados.
            nuevo = new ServicioIndexVB();
            nuevo.setStatus(x.getStatus());
            nuevo.setDescripcion(x.getDescripcion());
            nuevo.setFechaRecepcion(x.getFechaInicio());
            nuevo.setIdCliente(x.getClienteData().getId().toString());
            nuevo.setIdServicio(x.getId());
            Customer customer = customerDao.cargar(x.getClienteData().getId());
            OrganizationName name = (OrganizationName) customer.getName();
            nuevo.setNombreCliente(name.getName());
            nuevo.setNumeroSerieAuto(x.getAutoData().getNumeroSerie());
            nuevo.setPlacasAuto(x.getAutoData().getPlacas());
            nuevo.setTipo(x.getAutoData().getTipo());
            
            CostoMetadata costoMetadata = new CostoMetadata();
            costoMetadata.setTotal(MonedaVB.valueOf(x.getCostoTotal().getValue()));
            CobranzaMetadata cobranzaMetadata = new CobranzaMetadata();
            DatosCobranzaVB datosCobranzaVB = new DatosCobranzaVB();
            datosCobranzaBridge.load(x.getCobranza(), datosCobranzaVB);
            cobranzaMetadaCalculator.calculaMetaData(x.getStatus(),cobranzaMetadata, costoMetadata, datosCobranzaVB);
            nuevo.setCobranzaWarningLevel(cobranzaMetadata.getWarningLevel());
            nuevo.setSaldo(cobranzaMetadata.getSaldo());
            nuevo.setDiasUltimoPago(cobranzaMetadata.getDiasUltimoPago());
            nuevo.setStatusCobranza(cobranzaMetadata.getStatusCobranza());
            
            destino.add(nuevo);
        }
    }

    @Override
    public void unload(List<ServicioIndexVB> origen, List<ServicioIndex> destino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
