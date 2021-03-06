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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nekorp.workflow.desktop.data.access.AutoDAO;
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
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;

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
    @Autowired
    private AutoDAO autoDAO;
    @Override
    public void load(List<ServicioIndex> origen, List<ServicioIndexVB> destino) {
        ServicioIndexVB nuevo;
        Map<Long, Customer> clienteMap = new HashMap<>();
        for (Customer x: customerDao.consultaTodos()) {
            clienteMap.put(x.getId(), x);
        }
        Map<String, ServicioIndexAutoData> autoMap = new HashMap<>();
        for(ServicioIndexAutoData x: autoDAO.getIndiceAutos()) {
            autoMap.put(x.getNumeroSerie(), x);
        }
        for (ServicioIndex x: origen) {
            //TODO quitar este new cuando se requieran pojos proxeados.
            nuevo = new ServicioIndexVB();
            nuevo.setStatus(x.getStatus());
            nuevo.setDescripcion(x.getDescripcion());
            nuevo.setFechaRecepcion(x.getFechaInicio());
            nuevo.setIdCliente(x.getIdCliente().toString());
            nuevo.setIdServicio(x.getId());
            
            //de momento todos los clientes tienen nombre de organizacion
            OrganizationName name = (OrganizationName)clienteMap.get(x.getIdCliente()).getName();
            nuevo.setNombreCliente(name.getName());
            
            ServicioIndexAutoData autoIndex = autoMap.get(x.getIdAuto());
            nuevo.setNumeroSerieAuto(autoIndex.getNumeroSerie());
            nuevo.setPlacasAuto(autoIndex.getPlacas());
            nuevo.setTipo(autoIndex.getTipo());
            
            CostoMetadata costoMetadata = new CostoMetadata();
            costoMetadata.setTotalServicio(MonedaVB.valueOf(x.getCostoTotal().getValue()));
            CobranzaMetadata cobranzaMetadata = new CobranzaMetadata();
            DatosCobranzaVB datosCobranzaVB = new DatosCobranzaVB();
            datosCobranzaBridge.load(x.getCobranza(), datosCobranzaVB);
            cobranzaMetadaCalculator.calculaMetaData(x.getStatus(),cobranzaMetadata, costoMetadata, datosCobranzaVB);
            nuevo.setCobranzaMetadata(cobranzaMetadata);
            destino.add(nuevo);
        }
    }

    @Override
    public void unload(List<ServicioIndexVB> origen, List<ServicioIndex> destino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
