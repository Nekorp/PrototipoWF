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

package prototipo.servicio.binding;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototipo.servicio.validacion.ValidacionBeanFactory;
import prototipo.servicio.validacion.imp.CampoObligatorioValidacion;
import prototipo.view.binding.Bindable;
import prototipo.view.binding.BindingManager;
import prototipo.view.model.ServicioVB;
import prototipo.view.model.cliente.ClienteVB;
import prototipo.view.model.cliente.ValidacionCliente;

/**
 *
 */
@Service
@Aspect
public class ValidationManager {

    @Autowired
    private ServicioVB viewServicioModel;
    @Autowired
    private ClienteVB viewClienteModel;
    @Autowired
    private ValidacionCliente validacionCliente;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    @Autowired
    private ValidacionBeanFactory factory;
    
    @Pointcut("execution(* prototipo.control.WorkflowApp.startApliacion(..))")
    public void inicioAplicacion() {
    }
    
    @AfterReturning("inicioAplicacion()")
    public void setUpValidacionesIniciales() {
        setUpClientValidation();
    }
    
    public void setUpClientValidation() {
        ValidacionBindable vld = new ValidacionBindable();
        vld.setTarget(validacionCliente);
        vld.setValidationResult("nombreOk");
        CampoObligatorioValidacion regVld = new CampoObligatorioValidacion();
        regVld.setFailMessage("El campo es obligatorio");
        regVld.setFactory(factory);
        vld.setValidador(regVld);
        bindingManager.registerBind(viewClienteModel, "nombre", vld);
    }
}
