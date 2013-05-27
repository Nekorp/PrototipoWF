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

package org.nekorp.workflow.desktop.control.imp;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nekorp.workflow.desktop.control.AuthorityController;
import org.nekorp.workflow.desktop.view.model.security.PermisosAutoView;
import org.nekorp.workflow.desktop.view.model.security.PermisosBitacoraView;
import org.nekorp.workflow.desktop.view.model.security.PermisosClienteView;
import org.nekorp.workflow.desktop.view.model.security.PermisosCostoView;
import org.nekorp.workflow.desktop.view.model.security.PermisosInventarioDamageView;
import org.nekorp.workflow.desktop.view.model.servicio.EdicionServicioMetadata;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@Aspect
public class AuthorityControllerImp implements AuthorityController {

    @Autowired
    @Qualifier("permisosClienteView")
    private PermisosClienteView permisosCliente;
    @Autowired
    @Qualifier("permisosAutoView")
    private PermisosAutoView permisosAuto;
    @Autowired
    private PermisosBitacoraView permisosBitacora;
    @Autowired
    private PermisosCostoView permisosCosto;
    @Autowired
    private PermisosInventarioDamageView permisosInventarioDamage;
    @Autowired
    @Qualifier(value = "servicio")
    private ServicioVB servicioVB;
    @Autowired
    private EdicionServicioMetadata metadataServicio;
    
    @Pointcut("execution(* org.nekorp.workflow.desktop.control.WorkflowApp.cargaServicio(..)) "
        + " || execution(* org.nekorp.workflow.desktop.control.WorkflowApp.guardaServicio(..))")  
    public void applySecurityDirectivePointCut() {
    }
    
    @AfterReturning("applySecurityDirectivePointCut()")
    @Override
    public void applySecurityDirective() {
        //creo que quieren este
        //boolean puedeEditar = !(servicioVB.getStatus().equals("Cancelado") || servicioVB.getStatus().equals("Terminado"));
        //pero pidieron esto
        boolean puedeEditar = !(servicioVB.getStatus().equals("Terminado"));
        permisosBitacora.setModificarEventos(puedeEditar);
        permisosCosto.setPuedeEditarCostos(puedeEditar);
        permisosInventarioDamage.setPuedeEditar(puedeEditar);
        permisosCliente.setPuedeEditar(puedeEditar);
        permisosAuto.setPuedeEditar(puedeEditar);
        
        metadataServicio.setServicioCargado(true);
        if (puedeEditar) {
            metadataServicio.setEditado(true);//mentiras!!!!
        } else {
            metadataServicio.setEditado(false);
        }
    }
}
