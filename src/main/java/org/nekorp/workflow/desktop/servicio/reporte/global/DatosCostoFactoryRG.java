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

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.data.access.CostoDAO;
import org.nekorp.workflow.desktop.modelo.costo.RegistroCosto;
import org.nekorp.workflow.desktop.modelo.reporte.global.DatosCostoRG;
import org.nekorp.workflow.desktop.modelo.servicio.Servicio;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DatosCostoFactoryRG implements DataFactoryRG<DatosCostoRG> {

    @Autowired
    private CostoDAO costoDAO;
    private MonedaVB iva = MonedaVB.valueOf("0.16");
    @Override
    public DatosCostoRG build(Servicio data) {
        DatosCostoRG r = new DatosCostoRG();
        List<RegistroCosto> costo = costoDAO.cargar(data.getId());
        r.setManoDeObra(concatenerManoDeObra(costo));
        r.setCostoManoDeObra(sumarCostoManoDeObra(costo));
        r.setCostoRefacciones(sumarCostoRefacciones(costo));
        r.setIvaCosto(sumarIvaCosto(costo));
        r.setManoDeObraFacturado(sumarManoDeObraFacturado(costo));
        r.setRefaccionesFacturado(sumarRefaccionesFacturado(costo));
        return r;
    }

    private String concatenerManoDeObra(List<RegistroCosto> costo) {
        String r = "";
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Mano de Obra")) {
                if (!StringUtils.isEmpty(r)) {
                    r = r + ", ";
                }
                r = r + x.getConcepto();
            }
        }
        return r;
    }
    
    private double sumarCostoManoDeObra(List<RegistroCosto> costo) {
        MonedaVB total = new MonedaVB();
        MonedaVB precioUnitario;
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Mano de Obra")) {
                precioUnitario = MonedaVB.valueOf(x.getPrecioUnitario().getValue());
                total = total.suma(precioUnitario.multiplica(x.getCantidad()));
            }
        }
        return total.doubleValue();
    }
    
    private double sumarCostoRefacciones(List<RegistroCosto> costo) {
        MonedaVB total = new MonedaVB();
        MonedaVB precioUnitario;
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Refacciones")) {
                precioUnitario = MonedaVB.valueOf(x.getPrecioUnitario().getValue());
                total = total.suma(precioUnitario.multiplica(x.getCantidad()));
            }
        }
        return total.doubleValue();
    }
    
    private double sumarIvaCosto(List<RegistroCosto> costo) {
        MonedaVB total = new MonedaVB();
        MonedaVB precioUnitario;
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Refacciones") || StringUtils.equals(x.getSubtipo(), "Mano de Obra")) {
                if (x.isPrecioUnitarioConIVA()) {
                    precioUnitario = MonedaVB.valueOf(x.getPrecioUnitario().getValue());
                    total = total.suma(precioUnitario.multiplica(x.getCantidad()).multiplica(iva));
                }
            }
        }
        return total.doubleValue();
    }
    
    private double sumarManoDeObraFacturado(List<RegistroCosto> costo) {
        MonedaVB total = new MonedaVB();
        MonedaVB precioCliente;
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Mano de Obra")) {
                precioCliente = MonedaVB.valueOf(x.getPrecioCliente().getValue());
                total = total.suma(precioCliente.multiplica(x.getCantidad()));
            }
        }
        return total.doubleValue();
    }
    
    private double sumarRefaccionesFacturado(List<RegistroCosto> costo) {
        MonedaVB total = new MonedaVB();
        MonedaVB precioCliente;
        for (RegistroCosto x: costo) {
            if (StringUtils.equals(x.getSubtipo(), "Refacciones")) {
                precioCliente = MonedaVB.valueOf(x.getPrecioCliente().getValue());
                total = total.suma(precioCliente.multiplica(x.getCantidad()));
            }
        }
        return total.doubleValue();
    }
}
