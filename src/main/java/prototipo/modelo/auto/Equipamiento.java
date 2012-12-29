/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.auto;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Marisa
 */
@Component
public class Equipamiento {
    private TipoTransmision transmision;
    private TipoElevador elevadores;
    private String bolsasDeAire;
    private boolean aireAcondicionado;
    private List<String> equipoAdicional;

    public Equipamiento() {
        transmision = TipoTransmision.ESTANDAR;
        elevadores = TipoElevador.MANUAL;
        bolsasDeAire = "";
        aireAcondicionado = false;
        equipoAdicional = new LinkedList<>();
    }
    
    public TipoTransmision getTransmision() {
        return transmision;
    }

    public void setTransmision(TipoTransmision transmision) {
        this.transmision = transmision;
    }

    public TipoElevador getElevadores() {
        return elevadores;
    }

    public void setElevadores(TipoElevador elevadores) {
        this.elevadores = elevadores;
    }

    public String getBolsasDeAire() {
        return bolsasDeAire;
    }

    public void setBolsasDeAire(String bolsasDeAire) {
        this.bolsasDeAire = bolsasDeAire;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public List<String> getEquipoAdicional() {
        LinkedList<String> r = new LinkedList<>();
        for (String dato: this.equipoAdicional) {
            r.add(dato);
        }
        return r;
    }

    public void setEquipoAdicional(List<String> equipoAdicional) {
        this.equipoAdicional = new LinkedList<>();
        for (String dato: equipoAdicional) {
            this.equipoAdicional.add(dato);
        }
    }

}
