/**
 * Copyright 2012-2013 Nekorp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.servicio.CostosCalculator;
import prototipo.servicio.EditorMonitor;
import prototipo.servicio.Metadata;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.EdicionServicioMetadata;
import prototipo.view.model.ServicioVB;
import prototipo.view.model.cliente.ClienteVB;
import prototipo.view.model.cliente.DomicilioFiscalVB;
import prototipo.view.model.costo.RegistroCostoVB;

/**
 *
 *
 */
@Component
@Aspect
public class EditorMonitorImp implements EditorMonitor {

    private static final Logger LOGGER = Logger.getLogger(EditorMonitorImp.class);
    private LinkedList<EditorLog> logEdiciones;
    private LinkedList<EditorLog> redoLog;
    private int maxUndo = 1000;
    /**
     * no importa que este vacia la lista ya no hay undo :<
     */
    private boolean sinRetorno = false;
    /**
     * no importa que este vacia la lista ya no hay undo en el cliente
     */
    private boolean sinRetornoCliente = false;
    /**
     * para informar a quien le interese si tiene cambios el modelo.
     */
    @Autowired
    private EdicionServicioMetadata model;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private CostosCalculator calculator;
    @Autowired
    private ProxyUtil proxyUtil;

    private EditorMonitorImp() {
        logEdiciones = new LinkedList<>();
        redoLog = new LinkedList<>();
    }

    @Pointcut("execution(* prototipo.view.model..set*(..))")
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
                redoLog = new LinkedList<>();
                this.addUndo(log);
            }
        }
        pjp.proceed();
    }

    @Override
    public void undo() {
        try {
            EditorLog log = getFirstValid();
            if (log == null) {
                EditorMonitorImp.LOGGER.debug("se solicita undo pero no hay nada valido para undo");
                return;
            }
            //paso las reglas asi que se quitara del queue para procesarse
            this.logEdiciones.remove(log);
            EditorLog redo = new EditorLog();
            redo.setTarget(log.getTarget());
            redo.setProperty(log.getProperty());
            redo.setOldValue(PropertyUtils.getProperty(redo.getTarget(), redo.getProperty()));
            this.redoLog.addFirst(redo);
            PropertyUtils.setProperty(log.getTarget(), log.getProperty(), log.getOldValue());
            //en teoria lo anterior deberia ser suficiente para que se actualize la vista,
            //pero existe el problema de que en el objeto log
            //no se tiene el proxy al objeto, asi que no se activan los aspectos con los updates
            //una solucion era conseguir los proxys a los objetos pero es mas simple lanzar el processModelUpdate
            //del binding manadager
            //se complica cuando otras cosas ademas del update manager observan el modelo
            //asi como este objeto no es notificado
            notificaCalculator(log);
            bindingManager.processModelUpdate(log.getTarget(), log.getProperty(), log.getOldValue());
            if (this.logEdiciones.isEmpty()) {
                this.model.setEditado(this.sinRetorno);
            }
            this.model.setClienteEditado(tieneEdicionCliente() || this.sinRetornoCliente);
            EditorMonitorImp.LOGGER.debug("se proceso undo target:" + log.getTarget() + " property:" + log.getProperty() + " old:" + log.getOldValue());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            EditorMonitorImp.LOGGER.error("No se logro hacer undo", ex);
        } finally {
            this.model.setTieneRedo(this.hasRedo());
            this.model.setTieneUndo(this.hasUndo());
        }
    }

    @Override
    public void redo() {
        try {
            if (this.redoLog.isEmpty()) {
                return;
            }
            EditorLog log = this.redoLog.removeFirst();
            EditorLog undo = new EditorLog();
            undo.setTarget(log.getTarget());
            undo.setProperty(log.getProperty());
            undo.setOldValue(PropertyUtils.getProperty(undo.getTarget(), undo.getProperty()));
            this.addUndo(undo);
            PropertyUtils.setProperty(log.getTarget(), log.getProperty(), log.getOldValue());
            notificaCalculator(log);
            bindingManager.processModelUpdate(log.getTarget(), log.getProperty(), log.getOldValue());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            EditorMonitorImp.LOGGER.error("No se logro hacer undo", ex);
        } finally {
            this.model.setTieneRedo(this.hasRedo());
            this.model.setTieneUndo(this.hasUndo());
        }
    }

    @Override
    public void clear() {
        this.logEdiciones = new LinkedList<>();
        this.sinRetorno = false;
        this.sinRetornoCliente = false;
        this.model.setEditado(false);
        this.model.setClienteEditado(false);
        this.model.setTieneRedo(this.hasRedo());
        this.model.setTieneUndo(this.hasUndo());
    }

    @Override
    public void clear(Object target) {
        Object obj = proxyUtil.getTarget(target);
        LinkedList<EditorLog> borrar = new LinkedList<>();
        for (EditorLog log : this.logEdiciones) {
            if (log.getTarget() == obj) {
                borrar.add(log);
            }
        }
        this.logEdiciones.removeAll(borrar);
        if (this.logEdiciones.isEmpty()) {
            this.model.setEditado(this.sinRetorno);
        }
        this.sinRetornoCliente = false;
        this.model.setClienteEditado(tieneEdicionCliente());
        this.model.setTieneRedo(this.hasRedo());
        this.model.setTieneUndo(this.hasUndo());
    }

    @Override
    public boolean hasChange() {
        return (!this.logEdiciones.isEmpty() || this.sinRetorno || this.sinRetornoCliente);
    }

    @Override
    public void addUndo(EditorLog log) {
        if (this.isValid(log)) {
            this.logEdiciones.addFirst(log);
            if (this.logEdiciones.size() >= this.maxUndo) {
                EditorLog old = this.logEdiciones.removeLast();
                if (this.esEdicionCliente(old)) {
                    this.sinRetornoCliente = true;
                }
                this.sinRetorno = true;
            }
            EditorMonitorImp.LOGGER.debug("se agrego undo target:" + log.getTarget() + " property:" + log.getProperty() + " old:" + log.getOldValue());
        } else {
            if (this.esEdicionCliente(log)) {
                this.sinRetornoCliente = true;
            }
            this.sinRetorno = true;
        }
        this.model.setEditado(true);
        this.model.setClienteEditado(tieneEdicionCliente() || this.sinRetornoCliente);
        this.model.setTieneRedo(this.hasRedo());
        this.model.setTieneUndo(this.hasUndo());
    }

    private EditorLog getFirstValid() {
        for (EditorLog log : this.logEdiciones) {
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
        if (log.getTarget() instanceof ServicioVB && log.getProperty().compareTo("id") == 0) {
            //se ignora la peticion por ser el id del servicio
            return false;
        }
        if (log.getTarget() instanceof ServicioVB && log.getProperty().compareTo("idCliente") == 0) {
            //se ignora la peticion por ser el id del cliente dentro del servicio
            return false;
        }
        if (log.getTarget() instanceof ClienteVB && log.getProperty().compareTo("id") == 0) {
            //se ignora la peticion por ser el id del cliente
            return false;
        }
        if (log.getTarget() instanceof RegistroCostoVB && log.getProperty().compareTo("tipo") == 0) {
            //se ignora la peticion de cambiar el tipo del registro (en la pantalla no se edita)
            return false;
        }
        return true;
    }

    private boolean tieneEdicionCliente() {
        for (EditorLog log : this.logEdiciones) {
            if (log.getTarget() instanceof ClienteVB || log.getTarget() instanceof DomicilioFiscalVB) {
                return true;
            }
        }
        return false;
    }

    private boolean esEdicionCliente(EditorLog log) {
        if (log.getTarget() instanceof ClienteVB || log.getTarget() instanceof DomicilioFiscalVB) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUndo() {
        return !this.logEdiciones.isEmpty();
    }

    @Override
    public boolean hasRedo() {
        return !this.redoLog.isEmpty();
    }

    /**
     * este metodo realmente es un parte al no tener los proxys para hacer los
     * undo y redo, hay otros componentes observando el modelo pero no son
     * notificados por que no se tiene referencia al proxy
     *
     * @param log el objeto que se modifico
     */
    private void notificaCalculator(EditorLog log) {
        if (log.getTarget() instanceof ServicioVB && log.getProperty().equals("costos")) {
            this.calculator.recalcula();
        }
        if (log.getTarget() instanceof RegistroCostoVB) {
            this.calculator.recalcula();
        }
    }
}
