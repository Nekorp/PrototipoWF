/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.view.model.imp;

import javax.swing.JComponent;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prototipo.modelo.aop.ServicioAop;
import prototipo.servicio.BitacoraAnalyzer;
import prototipo.view.model.Bindable;
import prototipo.view.model.BindingManager;
import prototipo.view.model.ServicioViewModel;

/**
 *
 * @author Marisa
 */
@Component("modelBinder")
@Aspect
public class ServicioViewModelImpl implements ServicioViewModel {
    private static final Logger LOGGER = Logger.getLogger(ServicioViewModelImpl.class);
    @Autowired
    private ServicioAop modelo;
    @Autowired
    private BindingManager<Bindable> bindings;
    @Autowired
    private BitacoraAnalyzer bitacoraAnalyzer;
    public ServicioViewModelImpl() {
    }
//    @Override
//    public void loadData(Servicio data) {
//        //BeanUtils.copyProperties(data, this);
//    }
    @Pointcut("execution(* prototipo.modelo.aop.*.set*(..))")  
    public void modelChange() {
    }
    @Around("modelChange()")
    public void updateProperty(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        //TODO to weak code :<
        bindings.updateModelBindings(pjp.getSignature().getName(), pjp.getArgs()[0]);
    }
//
//    @Override
//    public Servicio getServicio() {
//        return this.data;
//    }
//
//    @Override
//    public String getFechaEntrada() {
//        Date fechaEntrada = bitacoraAnalyzer.getFechaEnradaAuto(this.data);
//        if (fechaEntrada != null) {
//            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
//            return df.format(fechaEntrada);
//        } else {
//            return "";
//        }
//    }
//
//    @Override
//    public String getFechaSalida() {
//        Date fechaSalida = bitacoraAnalyzer.getFechaSalidaAuto(this.data);
//        if (fechaSalida != null) {
//            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
//            return df.format(fechaSalida);
//        } else {
//            return "";
//        }
//    }
//
//    @Override
//    public String getTiempoEstadia() {
//        return this.bitacoraAnalyzer.getTiempoEstadia(this.data);
//    }
//
//    @Override
//    public Servicio getData() {
//        Servicio r = new Servicio();
//        BeanUtils.copyProperties(this, r);
//        return r;
//    }
    

    @Override
    public void bind(String property, JComponent component) {
        if (component instanceof Bindable) {
            bindings.registerBind(property, (Bindable)component);
        } else {
            ServicioViewModelImpl.LOGGER.error(
                "se trato de registrar un componente que no es bindable" + component);
            throw new IllegalArgumentException("el componente no es bindable");
        }
    }
    
}
