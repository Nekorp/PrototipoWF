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

package org.nekorp.workflow.desktop.servicio.reporte.global;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.data.access.CustomerDAO;
import org.nekorp.workflow.desktop.modelo.reporte.global.RenglonRG;
import org.nekorp.workflow.desktop.rest.util.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import technology.tikal.taller.automotriz.model.servicio.Servicio;
import org.springframework.stereotype.Component;
import technology.tikal.customers.model.ClienteMx;
import technology.tikal.customers.model.Customer;
import technology.tikal.customers.model.address.MexicoAddress;
import technology.tikal.customers.model.name.OrganizationName;
import technology.tikal.customers.model.phone.MexicoPhoneNumber;

/**
 * @author Nekorp
 */
@Component
public class RenglonFactoryRG implements DataFactoryRG<RenglonRG> {

    @Autowired
    @Qualifier("taller-RestTemplateFactory")
    private RestTemplateFactory factory;
    @Autowired
    private CustomerDAO customerDao;
    
    @Override
    public RenglonRG build(Servicio data) {
        Map<String, Object> map = new HashMap<>();
        map.put("idServicio", data.getId());
        RenglonRG r = factory.getTemplate().getForObject(factory.getRootUlr() + "/reportes/global/renglones/servicio/{idServicio}", RenglonRG.class, map);
        fillClienteData(r, customerDao.cargar(data.getIdCliente()));
        return r;
    }
    
    private void fillClienteData(RenglonRG r, Customer customer) {
        if (customer != null) {
            if (customer.getName() instanceof OrganizationName){
                OrganizationName n = (OrganizationName) customer.getName();
                r.getDatosCliente().setNombre(n.getName());
            }
            if (customer instanceof ClienteMx) {
                ClienteMx mx = (ClienteMx) customer;
                if (mx.getDomicilioFiscal() instanceof MexicoAddress) {
                    MexicoAddress dir = (MexicoAddress) mx.getDomicilioFiscal();
                    r.getDatosCliente().setCiudad(StringUtils.defaultIfEmpty(dir.getCiudad(), ""));
                    r.getDatosCliente().setColonia(StringUtils.defaultIfEmpty(dir.getColonia(), ""));
                    r.getDatosCliente().setDireccion(StringUtils.defaultIfEmpty(dir.getCalle(), ""));
                }
            }
            if (customer.getPrimaryContact() != null) {
                if (customer.getPrimaryContact().getName() instanceof OrganizationName) {
                    OrganizationName n = (OrganizationName) customer.getPrimaryContact().getName();
                    r.getDatosCliente().setContacto(n.getName());
                }
                if (customer.getPrimaryContact().getPhoneNumber() != null 
                        && customer.getPrimaryContact().getPhoneNumber().length > 0
                        && customer.getPrimaryContact().getPhoneNumber()[0] instanceof MexicoPhoneNumber) {
                    MexicoPhoneNumber telefono = (MexicoPhoneNumber) customer.getPrimaryContact().getPhoneNumber()[0];
                    r.getDatosCliente().setTelefono(StringUtils.defaultIfEmpty(telefono.getTelefono(), ""));
                }
            }
        }
    }
}
