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

package org.nekorp.workflow.desktop.servicio.binding;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.servicio.validacion.ValidacionBeanFactory;
import org.nekorp.workflow.desktop.servicio.validacion.imp.CampoObligatorioValidacion;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionCliente;
import org.nekorp.workflow.desktop.view.model.validacion.ValidacionDatosAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 *
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
    @Qualifier(value="validacionCliente")
    private ValidacionCliente validacionCliente;
    @Autowired
    @Qualifier(value="w-validacionCliente")
    private ValidacionCliente validacionClienteWizard;
    @Autowired
    @Qualifier(value="validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAuto;
    @Autowired
    @Qualifier(value="w-validacionDatosAuto")
    private ValidacionDatosAuto validacionDatosAutoWizard;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ValidacionBeanFactory factory;
    
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.startApliacion(..))")
    public void inicioAplicacion() {
    }
    
    @AfterReturning("inicioAplicacion()")
    public void setUpValidacionesIniciales() {
        setUpClientValidation(viewServicioModel, validacionCliente);
        setUpDatosAutoValidation(viewServicioModel, validacionDatosAuto);
        setUpClientValidation(viewServicioModelWizard, validacionClienteWizard);
        setUpDatosAutoValidation(viewServicioModelWizard, validacionDatosAutoWizard);
    }
    
    public void setUpClientValidation(ServicioVB servicio, ValidacionCliente objVal) {
        this.createCampoObligatorioBinding(servicio.getCliente(), "nombre", objVal, "nombreOk", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getCliente().getDomicilio(), "calle", objVal, "calleOk", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getCliente().getDomicilio(), "numInterior", objVal, "numInteriorOk", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getCliente().getDomicilio(), "colonia", objVal, "coloniaOk", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getCliente().getDomicilio(), "ciudad", objVal, "ciudadOk", "El campo es obligatorio");
    }
    
    public void setUpDatosAutoValidation(ServicioVB servicio, ValidacionDatosAuto objVal) {
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "marca", objVal, "marca", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "tipo", objVal, "tipo", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "version", objVal, "version", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "numeroSerie", objVal, "numeroSerie", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "modelo", objVal, "modelo", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "color", objVal, "color", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "placas", objVal, "placas", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio.getDatosAuto(), "kilometraje", objVal, "kilometraje", "El campo es obligatorio");
        this.createCampoObligatorioBinding(servicio, "descripcion", objVal, "descripcionServicio", "El campo es obligatorio");
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
