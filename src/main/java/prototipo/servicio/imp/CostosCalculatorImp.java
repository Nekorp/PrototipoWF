/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.modelo.costo.CostoMetadata;
import prototipo.modelo.costo.RegistroCosto;
import prototipo.modelo.currency.Moneda;
import prototipo.servicio.CostosCalculator;

/**
 *
 * @author Marisa
 */
@Component
@Aspect
public class CostosCalculatorImp implements CostosCalculator {
    private static final Logger LOGGER = Logger.getLogger(CostosCalculatorImp.class);
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    private Servicio viewServicioModel;
    
    /**
     * estos pointcut no son suficientes
     * debido a que el editor monitor modifica el modelo directamente
     * este objeto no es informado cuando suceden undo y redo
     * se agrego codigo al editor monitor para subsanar este issue
     */
    @Pointcut("execution(* prototipo.modelo.costo.RegistroCosto.set*(..)) || execution(* prototipo.modelo.Servicio.setCostos(..))")  
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
        Moneda total  = new Moneda();
        for (RegistroCosto x: viewServicioModel.getCostos()) {
            total = total.suma(x.getSubtotal());
        }
        this.costosMetadata.setTotal(total);
    }
}
