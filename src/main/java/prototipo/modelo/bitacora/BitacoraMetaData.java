/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.bitacora;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

/**
 *
 * @author Marisa
 */
@Component
public class BitacoraMetaData implements Metadata {
    private String fechaEntrada;
    private String fechaSalidaAuto;
    private String tiempoEstadia;
    
    
    public BitacoraMetaData() {
        fechaEntrada = "";
        fechaSalidaAuto = "";
        tiempoEstadia = "";
    }

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
