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

import java.util.List;
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
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 *
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
    
    /**
     * estos pointcut no son suficientes
     * debido a que el editor monitor modifica el modelo directamente
     * este objeto no es informado cuando suceden undo y redo
     * se agrego codigo al editor monitor para subsanar este issue
     */
    @Pointcut("execution(* org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB.set*(..))"
        + " || execution(* org.nekorp.workflow.desktop.view.model.servicio.ServicioVB.setCostos(..))")  
    public void costosChange() {
    }
    @Around("costosChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        this.calculaCosto(viewServicioModel.getCostos(), costosMetadata);
    }
    
    @Override
    public void calculaCosto(List<RegistroCostoVB> costos, CostoMetadata model) {
        CostosCalculatorImp.LOGGER.debug("Recalculando");
        MonedaVB total  = new MonedaVB();
        //los totales del reporte del cliente se calculan dentro del excell
        for (RegistroCostoVB x: costos) {
            //esto se pidio en el issue #3 pero ya no salen las cuentas con la orden de servicio
//            if (!StringUtils.equals("Insumo", x.getSubtipo())) {
//                total = total.suma(x.getSubtotal());
//            }
            if (!(x instanceof RegistroOtrosGastosVB)) {
                total = total.suma(x.getSubtotal());
            }
        }
        model.setTotal(total);
    }
}
