/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.binding.imp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 * @author Marisa
 */
@Component
@Aspect
public class BindingManagerImp implements BindingManager<Bindable> {
    private static final Logger LOGGER = Logger.getLogger(BindingManagerImp.class);
    
    private Map<String,Map<String,List<Bindable>>> bindings;
    
    public BindingManagerImp() {
        bindings = new HashMap<>();
    }
    @Pointcut("execution(* prototipo.modelo..set*(..))")  
    public void modelChange() {
    }
    @Around("modelChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        //BindingManagerImp.LOGGER.debug("evento:"+pjp.getTarget());
        pjp.proceed();
        String methodName = pjp.getSignature().getName();
        String propertyName = StringUtils.substringAfter(methodName, "set");
        String primeraLetra = propertyName.charAt(0) + "";
        propertyName = primeraLetra.toLowerCase() + StringUtils.substringAfter(propertyName, primeraLetra);
        //TODO to weak code :<
        processModelUpdate(pjp.getTarget(),propertyName, pjp.getArgs()[0]);
    }
    
    @Override
    public void registerBind(Object target, String property, Bindable component) {
        //configura en sentido modelo -> vista
        Map<String,List<Bindable>> objectBindings = this.bindings.get(target.toString());
        if (objectBindings == null) {
            objectBindings = new HashMap<>();
            this.bindings.put(target.toString(), objectBindings);
        }
        List<Bindable> bnds = objectBindings.get(property);
        if (bnds == null) {
            bnds = new LinkedList<>();
            objectBindings.put(property, bnds);
        }
        bnds.add(component);
        //configura en sentido vista -> modelo
        component.bindListener(target, property);
    }

    /**
     * procesa una actualizacion en el modelo.
     * @param origen el objeto que fue modificado
     * @param property el nombre de la propiedad
     * @param value el primer parametro del metodo
     */
    private void processModelUpdate(Object origen, String property, Object value){
        Map<String,List<Bindable>> objectBindings = this.bindings.get(origen.toString());
        if (objectBindings != null) {
            List<Bindable> lista = objectBindings.get(property);
            if (lista != null) {
                for (Bindable bind: lista) {
                    //TODO encontrar la forma de determinar la fuente del cambio
                    //asi se podria cancelar la actualizacion
                    //if (source != bind) {
                        BindingManagerImp.LOGGER.debug("evento origen:"+origen+" property:"+property+" valor:" + value + " target:" + bind);
                        bind.updateModel(value);
                    //}
                }
            }
        }
    }
}
