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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.servicio.CostosCalculator;
import org.nekorp.workflow.desktop.view.model.ServicioVB;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroHojalateriaPinturaVB;
import org.nekorp.workflow.desktop.view.model.costo.RegistroMecanicaVB;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ServicioVB viewServicioModel;
    
    /**
     * estos pointcut no son suficientes
     * debido a que el editor monitor modifica el modelo directamente
     * este objeto no es informado cuando suceden undo y redo
     * se agrego codigo al editor monitor para subsanar este issue
     */
    @Pointcut("execution(* org.nekorp.workflow.desktop.view.model.costo.RegistroCostoVB.set*(..))"
        + " || execution(* org.nekorp.workflow.desktop.view.model.ServicioVB.setCostos(..))")  
    public void costosChange() {
    }
    @Around("costosChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        this.recalcula();
    }
    
    @Override
    public void recalcula() {
        CostosCalculatorImp.LOGGER.debug("Recalculando");
        MonedaVB total  = new MonedaVB();
        MonedaVB totalMecanicaManoDeObra = new MonedaVB();
        MonedaVB totalMecanicaRefacciones = new MonedaVB();
        MonedaVB totalHojalateriaManoDeObra = new MonedaVB();
        MonedaVB totalHojalateriaInsumos = new MonedaVB();
        for (RegistroCostoVB x: viewServicioModel.getCostos()) {
            total = total.suma(x.getSubtotal());
            if (x instanceof RegistroHojalateriaPinturaVB) {
                if (StringUtils.equals(x.getSubtipo(),"Mano de Obra")) {
                    totalHojalateriaManoDeObra = totalHojalateriaManoDeObra.suma(x.getSubtotal());
                }
                if (StringUtils.equals(x.getSubtipo(),"Insumo")) {
                    totalHojalateriaInsumos = totalHojalateriaInsumos.suma(x.getSubtotal());
                }
            }
            if (x instanceof RegistroMecanicaVB) {
                if (StringUtils.equals(x.getSubtipo(),"Mano de Obra")) {
                    totalMecanicaManoDeObra = totalMecanicaManoDeObra.suma(x.getSubtotal());
                }
                if (StringUtils.equals(x.getSubtipo(),"Refacciones")) {
                    totalMecanicaRefacciones = totalMecanicaRefacciones.suma(x.getSubtotal());
                }
            }
        }
        this.costosMetadata.setTotal(total);
        this.costosMetadata.setTotalHojalateriaInsumos(totalHojalateriaInsumos);
        this.costosMetadata.setTotalHojalateriaManoDeObra(totalHojalateriaManoDeObra);
        this.costosMetadata.setTotalMecanicaManoDeObra(totalMecanicaManoDeObra);
        this.costosMetadata.setTotalMecanicaRefacciones(totalMecanicaRefacciones);
    }
}
