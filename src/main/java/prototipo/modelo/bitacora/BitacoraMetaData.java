/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marisa
 */
@Component
public class BitacoraMetaData {
    private String fechaEntrada;
    private String fechaSalidaAuto;
    private String tiempoEstadia;

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalidaAuto() {
        return fechaSalidaAuto;
    }

    public void setFechaSalidaAuto(String fechaSalidaAuto) {
        this.fechaSalidaAuto = fechaSalidaAuto;
    }

    public String getTiempoEstadia() {
        return tiempoEstadia;
    }

    public void setTiempoEstadia(String tiempoEstadia) {
        this.tiempoEstadia = tiempoEstadia;
    }
    
}
