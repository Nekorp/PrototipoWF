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
package prototipo.view.binding.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.servicio.imp.ProxyUtil;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 *
 */
@Component
@Aspect
public class BindingManagerImp implements BindingManager<Bindable> {
    private static final Logger LOGGER = Logger.getLogger(BindingManagerImp.class);
    @Autowired
    private ProxyUtil proxyUtil;
    private Map<Object,Map<String,List<Bindable>>> bindings;
    private PropertyUtilsBean propertyUtils;
    
    
    public BindingManagerImp() {
        bindings = new HashMap<>();
        propertyUtils = new PropertyUtilsBean();
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
        try {
            //recupera el objeto del proxy
            Object obj = proxyUtil.getTarget(target);
            //configura en sentido modelo -> vista
            Map<String,List<Bindable>> objectBindings = this.bindings.get(obj);
            if (objectBindings == null) {
                objectBindings = new HashMap<>();
                this.bindings.put(obj, objectBindings);
            }
            List<Bindable> bnds = objectBindings.get(property);
            if (bnds == null) {
                bnds = new LinkedList<>();
                objectBindings.put(property, bnds);
            }
            bnds.add(component);
            //configura en sentido vista -> modelo
            component.bindListener(target, property);
            //actualiza la vista con el valor del modelo
            component.updateModel(obj, property, propertyUtils.getProperty(target, property));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            BindingManagerImp.LOGGER.error("Eror en binding", ex);
        }
    }
    
    @Override
    public void removeBind(Object target, String property, Bindable component) {
        //recupera el objeto del proxy
        Object obj = proxyUtil.getTarget(target);
        Map<String,List<Bindable>> objectBindings = this.bindings.get(obj);
        if (objectBindings != null) {
            List<Bindable> bnds = objectBindings.get(property);
            if (bnds != null) {
                bnds.remove(component);
                if (bnds.isEmpty()) {
                    objectBindings.remove(property);
                    if (objectBindings.isEmpty()) {
                        this.bindings.remove(obj);
                    }
                }
            }
        }
    }
    
    @Override
    public void clearObjectBindings(Object target, Bindable component) {
        //recupera el objeto del proxy
        Object obj = proxyUtil.getTarget(target);
        Map<String,List<Bindable>> objectBindings = this.bindings.get(obj);
        LinkedList<String> propiedades = new LinkedList<>();
        if (objectBindings != null) {
            for(String key: objectBindings.keySet()) {
                List<Bindable> bnds = objectBindings.get(key);
                if (bnds != null && bnds.contains(component)) {
                    propiedades.add(key);
                }
            }
        }
        for (String x: propiedades) {
            this.removeBind(target, x, component);
        }
    }
    
    @Override
    public void clearBindings(Bindable component) {
        LinkedList<Object> targets = new LinkedList<>();
        for (Object key: this.bindings.keySet()) {
            targets.add(bindings.get(key));
        }
        for (Object obj: targets) {
            this.clearObjectBindings(obj, component);
        }
    }

    
    @Override
    public void processModelUpdate(Object origen, String property, Object value){
        Map<String,List<Bindable>> objectBindings = this.bindings.get(origen);
        if (objectBindings != null) {
            List<Bindable> lista = objectBindings.get(property);
            if (lista != null) {
                for (Bindable bind: lista) {
                    //TODO encontrar la forma de determinar la fuente del cambio
                    //asi se podria cancelar la actualizacion
                    //if (source != bind) {
                        BindingManagerImp.LOGGER.debug("evento origen:"+origen+" property:"+property+" valor:" + value + " target:" + bind);
                        bind.updateModel(origen, property, value);
                    //}
                }
            }
        }
    }
}
