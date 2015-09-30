/**
 *   Copyright 2012-2015 TIKAL-TECHNOLOGY
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.servicio.CostosCalculator;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroOtrosGastosVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.servicio.GrupoCostoVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @author Nekorp
 */
@Component
@Aspect
public class CostosCalculatorImp implements CostosCalculator {
    private static final Logger LOGGER = Logger.getLogger(CostosCalculatorImp.class);
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    
    @Pointcut("execution(* org.nekorp.workflow.desktop.view.model.servicio.ServicioVB.setCostos(..))")  
    public void listaCostosChange() {
    }
    @Pointcut("execution(* org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB.set*(..))")  
    public void costoChange() {
    }
    @Around("listaCostosChange()")
    public void updateList(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        this.calculaCosto(viewServicioModel.getCostos(), costosMetadata);
    }
    
    @Around("costoChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        this.calculaCosto(viewServicioModel.getCostos(), costosMetadata);
    }
    
    @Override
    public void calculaCosto(List<RegistroCostoVB> costos, CostoMetadata model) {
        CostosCalculatorImp.LOGGER.debug("Recalculando");
        MonedaVB total  = new MonedaVB();
        MonedaVB iva  = new MonedaVB();
        MonedaVB totalSinOtro  = new MonedaVB();
        //los totales del reporte del cliente se calculan dentro del excell
        for (RegistroCostoVB x: costos) {
            //se calculan dos totales diferente como se pidio en el issue #47
            total = total.suma(x.getSubtotal());
            if (!(x instanceof RegistroOtrosGastosVB)) {
                totalSinOtro = totalSinOtro.suma(x.getSubtotal());
            }
            iva = iva.suma(x.getIvaSubtotal());
        }
        model.setSubtotal(total);
        model.setIva(iva);
        model.setTotalServicio(total.suma(iva));
        model.setTotalSinOtros(totalSinOtro);
        //calculo por grupos
        Map<GrupoCostoVB, CalculoPorGrupo> calculoData = new HashMap<>();
        for (RegistroCostoVB x: costos) {
            CalculoPorGrupo data = calculoData.get(x.getGrupo());
            if (data == null) {
                data = new CalculoPorGrupo();
                calculoData.put(x.getGrupo(), data);
            }
            data.setSubtotal(data.getSubtotal().suma(x.getSubtotal()));
            data.setIva(data.getIva().suma(x.getIvaSubtotal()));
        }
        for (GrupoCostoVB x: calculoData.keySet()) {
            x.getMetadata().setSubtotal(calculoData.get(x).getSubtotal());
            x.getMetadata().setIva(calculoData.get(x).getIva());
            x.getMetadata().setTotal(calculoData.get(x).getSubtotal().suma(calculoData.get(x).getIva()));
        }
    }
    
    class CalculoPorGrupo {
        private MonedaVB subtotal;
        private MonedaVB iva;

        public CalculoPorGrupo() {
            subtotal = new MonedaVB();
            iva = new MonedaVB();
        }
        public MonedaVB getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(MonedaVB subtotal) {
            this.subtotal = subtotal;
        }

        public MonedaVB getIva() {
            return iva;
        }

        public void setIva(MonedaVB iva) {
            this.iva = iva;
        }
        
    }
}
