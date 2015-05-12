/**
 *   Copyright 2015 TIKAL-TECHNOLOGY
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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.nekorp.workflow.desktop.servicio.CobranzaMetadataCalculator;
import org.nekorp.workflow.desktop.view.binding.Bindable;
import org.nekorp.workflow.desktop.view.binding.BindingManager;
import org.nekorp.workflow.desktop.view.binding.ReadOnlyBinding;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaMetadata;
import org.nekorp.workflow.desktop.view.model.cobranza.CobranzaWarningLevel;
import org.nekorp.workflow.desktop.view.model.cobranza.DatosCobranzaVB;
import org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB;
import org.nekorp.workflow.desktop.view.model.costo.CostoMetadata;
import org.nekorp.workflow.desktop.view.model.currency.MonedaVB;
import org.nekorp.workflow.desktop.view.model.servicio.ServicioVB;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nekorp
 */
@Component
@Aspect
public class CobranzaMetadataCalculatorImp implements CobranzaMetadataCalculator, InitializingBean {
    @Value("#{appConfig['app.service.cobranza.dias.warn']}")
    private int diasWarn;
    @Value("#{appConfig['app.service.cobranza.dias.urgent']}")
    private int diasUrgent;
    @Autowired
    private CostoMetadata costosMetadata;
    @Autowired
    private CobranzaMetadata cobranzaMetadata;
    @Autowired
    @Qualifier(value="servicio")
    private ServicioVB viewServicioModel;
    @Autowired
    private BindingManager<Bindable> bindingManager;
    
    /**
     * estos pointcut no son suficientes
     * debido a que el editor monitor modifica el modelo directamente
     * este objeto no es informado cuando suceden undo y redo
     * se agrego codigo al editor monitor para subsanar este issue
     */
    @Pointcut("execution(* org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB.setMonto(..)) || "
            + "execution(* org.nekorp.workflow.desktop.view.model.cobranza.PagoCobranzaVB.setFecha(..))")  
    public void pagoMontoChange() {
    }
    @Around("pagoMontoChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        this.calculaMetaData(viewServicioModel.getStatus(), cobranzaMetadata, costosMetadata, viewServicioModel.getCobranza());
    }
    
    public void setBindings() {
        Bindable update = new ReadOnlyBinding() {
            @Override
            public void notifyUpdate(Object origen, String property, Object value) {
                calculaMetaData(viewServicioModel.getStatus(), cobranzaMetadata, costosMetadata, viewServicioModel.getCobranza());
            }
        };
        this.bindingManager.registerBind(costosMetadata, "total", update);
        this.bindingManager.registerBind(viewServicioModel.getCobranza(), "pagos", update);
        this.bindingManager.registerBind(viewServicioModel.getCobranza(), "inicio", update);
    }
    
    @Override
    public void calculaMetaData(String status, CobranzaMetadata cobranzaMetadata, CostoMetadata costoMetadata, DatosCobranzaVB cobranza) {
        MonedaVB acuenta = new MonedaVB();
        for(PagoCobranzaVB pago : cobranza.getPagos()) {
            acuenta = acuenta.suma(pago.getMonto());
        }
        cobranzaMetadata.setAcuenta(acuenta);
        MonedaVB total = costoMetadata.getTotal();
        cobranzaMetadata.setTotalServicio(total);
        MonedaVB saldo = total.resta(acuenta);
        if (status.compareTo("Terminado") != 0) {
            cobranzaMetadata.setSaldo(saldo);
            if (saldo.doubleValue() <= 0) {
                cobranzaMetadata.getStatus().setStatusCobranza("");
            } else {
                cobranzaMetadata.getStatus().setStatusCobranza("Adeudo");
            }
        } else {
            cobranzaMetadata.setSaldo(new MonedaVB());
            cobranzaMetadata.getStatus().setStatusCobranza("");
        }
        calculaWanrLevel(status, saldo, cobranzaMetadata, cobranza);
    }
    
    private void calculaWanrLevel(String status, MonedaVB saldo, CobranzaMetadata cobranzaMetadata, DatosCobranzaVB cobranza) {
        CobranzaWarningLevel warningLevel = CobranzaWarningLevel.info;
        if (saldo.doubleValue() <= 0 || status.compareTo("Terminado") == 0) {
            cobranzaMetadata.getStatus().setWarningLevel(warningLevel);
            cobranzaMetadata.setDiasUltimoPago(0);
            return;
        }
        DateTime ultimoPago = new DateTime(cobranza.getInicio());
        for(PagoCobranzaVB pago : cobranza.getPagos()) {
            DateTime fechaPago = new DateTime(pago.getFecha());
            if (ultimoPago.isBefore(fechaPago)) {
                ultimoPago = fechaPago;
            }
        }
        if (ultimoPago.plusDays(diasWarn).isBeforeNow()) {
            warningLevel = CobranzaWarningLevel.warn;
        }
        if (ultimoPago.plusDays(diasUrgent).isBeforeNow()) {
            warningLevel = CobranzaWarningLevel.urgent;
        }
        int dias = Days.daysBetween(ultimoPago.toLocalDate(), DateTime.now().toLocalDate()).getDays();
        cobranzaMetadata.setDiasUltimoPago(dias);
        cobranzaMetadata.getStatus().setWarningLevel(warningLevel);
    }
    
    public void updateCosto(MonedaVB costo) {
        this.calculaMetaData(viewServicioModel.getStatus(), cobranzaMetadata, costosMetadata, viewServicioModel.getCobranza());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setBindings();
        //this.calculaMetaData();
    }
}
