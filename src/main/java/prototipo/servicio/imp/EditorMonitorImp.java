/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.servicio.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.Servicio;
import prototipo.modelo.ServicioMetadata;
import prototipo.modelo.cliente.Cliente;
import prototipo.modelo.cliente.DomicilioFiscal;
import prototipo.servicio.EditorMonitor;
import prototipo.servicio.Metadata;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;

/**
 *
 * @author Marisa
 */
@Component
@Aspect
public class EditorMonitorImp implements EditorMonitor {
    private static final Logger LOGGER = Logger.getLogger(EditorMonitorImp.class);
    
    private LinkedList<EditorLog> logEdiciones;
    
    /**
     * para informar a quien le interese si tiene cambios el modelo.
     */
    @Autowired
    private ServicioMetadata model;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    
    private EditorMonitorImp() {
        logEdiciones = new LinkedList<>();
    }
    
    @Pointcut("execution(* prototipo.modelo..set*(..))")  
    public void modelChange() {
    }
    @Around("modelChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        Object target = pjp.getTarget();
        if (!(target instanceof Metadata)) {
            String methodName = pjp.getSignature().getName();
            String propertyName = StringUtils.substringAfter(methodName, "set");
            String primeraLetra = propertyName.charAt(0) + "";
            propertyName = primeraLetra.toLowerCase() + StringUtils.substringAfter(propertyName, primeraLetra);
            Object oldValue = PropertyUtils.getProperty(target, propertyName);
            if (!oldValue.equals(pjp.getArgs()[0])) {
                EditorLog log = new EditorLog();
                log.setTarget(target);
                log.setProperty(propertyName);
                log.setOldValue(oldValue);
                this.logEdiciones.addFirst(log);
                this.model.setEditado(true);
                this.model.setClienteEditado(tieneEdicionCliente());
                EditorMonitorImp.LOGGER.info("se agrego undo target:"+target+" property:"+ propertyName + " old:" + oldValue + " new:" + pjp.getArgs()[0]);
            }
        }
        pjp.proceed();
    }

    @Override
    public void undo() {
        try {
            EditorLog log = getFirstValid();
            if (log == null) {
                EditorMonitorImp.LOGGER.info("se solicita undo pero no hay nada valido para undo");
                return;
            }
            //paso las reglas asi que se quitara del queue para procesarse
            this.logEdiciones.remove(log);
            PropertyUtils.setProperty(log.getTarget(), log.getProperty(), log.getOldValue());
            //en teoria lo anterior deberia ser suficiente para que se actualize la vista,
            //pero existe el problema de que en el objeto log
            //no se tiene el proxy al objeto, asi que no se activan los aspectos con los updates
            //una solucion era conseguir los proxys a los objetos pero es mas simple lanzar el processModelUpdate
            //del binding manadager
            bindingManager.processModelUpdate(log.getTarget(), log.getProperty(), log.getOldValue());
            if (this.logEdiciones.isEmpty()) {
                this.model.setEditado(false);
            }
            this.model.setClienteEditado(tieneEdicionCliente());
            EditorMonitorImp.LOGGER.info("se proceso undo target:"+log.getTarget()+" property:"+ log.getProperty() + " old:" + log.getOldValue());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            EditorMonitorImp.LOGGER.error("No se logro hacer undo", ex);
        }
    }

    @Override
    public void clear() {
        this.logEdiciones = new LinkedList<>();
        this.model.setEditado(false);
        this.model.setClienteEditado(false);
    }
    
    @Override
    public void clear(Object target) {
        Object obj = target;
        if(AopUtils.isAopProxy(target)){
            try {
                Advised advised = (Advised) target;
                obj = advised.getTargetSource().getTarget();
            } catch (Exception ex) {
                EditorMonitorImp.LOGGER.error("No se logro recuperar el proxy", ex);
                return;//no hace nada !!!
            }
        }
        LinkedList<EditorLog> borrar = new LinkedList<>();
        for (EditorLog log: this.logEdiciones) {
            if (log.getTarget() == obj) {
                borrar.add(log);
            }
        }
        this.logEdiciones.removeAll(borrar);
        if (this.logEdiciones.isEmpty()) {
            this.model.setEditado(false);
        }
        this.model.setClienteEditado(tieneEdicionCliente());
    }

    @Override
    public boolean hasChange() {
        return !this.logEdiciones.isEmpty();
    }
    
    private EditorLog getFirstValid() {
        for (EditorLog log: this.logEdiciones) {
            if (this.isValid(log)) {
                return log;
            }
        }
        return null;
    }
    
    private boolean isValid(EditorLog log) {
        //algunas reglas 
        //por ejemplo no se puede deshacer el cambio de id de cliente o servicio, por que ya no seria el mismo cliente o servicio.
        //estos cambios se mantienen en la cola por que asi se mantiene activado el estatus de modificado y ciertamente esta modificado
        //solo no se pueden deshacer estos cambios
        if (log.getTarget() instanceof Servicio && log.getProperty().compareTo("id") == 0) {
            //se ignora la peticion por ser el id del servicio
            return false;
        }
        if (log.getTarget() instanceof Servicio && log.getProperty().compareTo("idCliente") == 0) {
            //se ignora la peticion por ser el id del cliente dentro del servicio
            return false;
        }
        if (log.getTarget() instanceof Cliente && log.getProperty().compareTo("id") == 0) {
            //se ignora la peticion por ser el id del cliente
            return false;
        }
        return true;
    }
    
    private boolean tieneEdicionCliente() {
        for (EditorLog log: this.logEdiciones) {
            if (log.getTarget() instanceof Cliente || log.getTarget() instanceof DomicilioFiscal) {
                return true;
            }
        }
        return false;
    }
}
