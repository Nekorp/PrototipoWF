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

package org.nekorp.workflow.desktop.servicio.binding;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.servicio.validacion.PoliticaValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.ValidacionBeanFactory;
import org.nekorp.workflow.desktop.servicio.validacion.imp.CampoObligatorioValidacion;
import org.nekorp.workflow.desktop.servicio.validacion.imp.HibernateValidatorDelegate;
import org.nekorp.workflow.desktop.servicio.validacion.imp.ValidacionBeanFactoryImp;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionCliente;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionDatosAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;


/**
 * @author Nekorp
 */
@Service
@Aspect
public class ValidationManager {

    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    @Qualifier(value="w-servicio")
    private ServicioVB viewServicioModelWizard;
    @Autowired
    @Qualifier(value="p-servicio")
    private ServicioVB viewServicioModelWizardProgrmacion;
    @Autowired
    @Qualifier(value="validacionCliente")
    private ValidacionCliente validacionCliente;
    @Autowired
    @Qualifier(value="w-validacionCliente")
    private ValidacionCliente validacionClienteWizard;
     @Autowired
    @Qualifier(value="p-validacionCliente")
    private ValidacionCliente validacionClienteWizardProgramacion;
    @Autowired
    @Qualifier(value="validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAuto;
    @Autowired
    @Qualifier(value="w-validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAutoWizard;
    @Autowired
    @Qualifier(value="p-validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAutoWizardProgramacion;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private AbstractMessageSource messageSource;
    
    private ValidacionBeanFactory factory;
    private ValidacionBeanFactory factoryLaxa;
    private ValidatorFactory hibernateFactory; 
    
    public ValidationManager() {
        factory = new ValidacionBeanFactoryImp(PoliticaValidacion.TODOVALIDO);
        factoryLaxa = new ValidacionBeanFactoryImp(PoliticaValidacion.NADAINCORRECTO);
        hibernateFactory = Validation.buildDefaultValidatorFactory();
    }
    
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.startApliacion(..))")
    public void inicioAplicacion() {
    }
    
    @AfterReturning("inicioAplicacion()")
    public void setUpValidacionesIniciales() {
        setUpClientValidation(viewServicioModel, validacionCliente);
        setUpDatosAutoValidation(viewServicioModel, validacionDatosAuto);
        setUpClientValidation(viewServicioModelWizard, validacionClienteWizard);
        setUpDatosAutoValidation(viewServicioModelWizard, validacionDatosAutoWizard);
        setUpClientValidation(viewServicioModelWizardProgrmacion, validacionClienteWizardProgramacion);
        setUpDatosAutoValidation(viewServicioModelWizardProgrmacion, validacionDatosAutoWizardProgramacion);
    }
    
    public void setUpClientValidation(ServicioVB servicio, ValidacionCliente objVal) {
        this.createHibernateDelegate(servicio.getCliente(), "nombre", objVal, "nombreOk");
        this.createHibernateDelegate(servicio.getCliente(), "rfc", objVal, "rfcOk");
        this.createHibernateDelegate(servicio.getCliente().getDomicilio(), "calle", objVal, "calleOk");
        this.createHibernateDelegate(servicio.getCliente().getDomicilio(), "numInterior", objVal, "numInteriorOk");
        this.createHibernateDelegate(servicio.getCliente().getDomicilio(), "codigoPostal", objVal, "codigoPostalOk");
        this.createHibernateDelegate(servicio.getCliente().getDomicilio(), "colonia", objVal, "coloniaOk");
        this.createHibernateDelegate(servicio.getCliente().getDomicilio(), "ciudad", objVal, "ciudadOk");
        this.createHibernateDelegate(servicio.getCliente(), "contacto", objVal, "contactoOk");
        this.createHibernateDelegate(servicio.getCliente(), "email", objVal, "emailOk");
        this.createHibernateDelegate(servicio.getCliente().getTelefonoUno(), "valor", objVal, "telefonoUnoOk");
        this.createHibernateDelegate(servicio.getCliente().getTelefonoDos(), "valor", objVal, "telefonoDosOk");
        this.createHibernateDelegate(servicio.getCliente().getTelefonoTres(), "valor", objVal, "telefonoTresOk");
    }
    
    private void createHibernateDelegate(Object origin, String originProperty, Object target, String targetProperty) {
        ValidacionBindable vld = new ValidacionBindable();
        vld.setTarget(target);
        vld.setValidationResult(targetProperty);
        
        HibernateValidatorDelegate hibVal = new HibernateValidatorDelegate(hibernateFactory.getValidator());
        hibVal.setFactory(factory);
        hibVal.setMessageSource(messageSource);
        hibVal.setPropertyPath(originProperty);
        
        vld.setValidador(hibVal);
        bindingManager.registerBind(origin, originProperty, vld);
    }
    
    public void setUpDatosAutoValidation(ServicioVB servicio, ValidacionDatosAuto objVal) {
        this.createCampoObligatorioBinding(servicio.getAuto(), "marca", objVal, "marca", "El campo marca es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "tipo", objVal, "tipo", "El campo tipo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "version", objVal, "version", "El campo version es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "numeroSerie", objVal, "numeroSerie", "El campo serie es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "modelo", objVal, "modelo", "El campo modelo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "color", objVal, "color", "El campo color es obligatorio");
        this.createCampoObligatorioBinding(servicio.getAuto(), "placas", objVal, "placas", "El campo placas es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "kilometraje", objVal, "kilometraje", "El campo kilometraje es obligatorio");
        this.createCampoObligatorioBinding(servicio, "descripcion", objVal, "descripcionServicio", "El campo descripcion es obligatorio");
    }
    
    private void createCampoObligatorioBinding(Object origin, String originProperty, Object target, String targetProperty, String message) {
        ValidacionBindable vld = new ValidacionBindable();
        vld.setTarget(target);
        vld.setValidationResult(targetProperty);
        CampoObligatorioValidacion regVld = new CampoObligatorioValidacion();
        regVld.setFailMessage(message);
        regVld.setFactory(factory);
        vld.setValidador(regVld);
        bindingManager.registerBind(origin, originProperty, vld);
    }
}
