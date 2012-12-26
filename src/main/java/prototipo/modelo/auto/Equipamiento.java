/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.modelo.auto;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Marisa
 */
public class Equipamiento {
    private String transmision;
    private String elevadores;
    private String bolsasDeAire;
    private boolean aireAcondicionado;
    private List<String> equipoAdicional;

    public Equipamiento() {
        transmision = "";
        elevadores = "";
        bolsasDeAire = "";
        aireAcondicionado = false;
        equipoAdicional = new LinkedList<>();
    }
    
    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getElevadores() {
        return elevadores;
    }

    public void setElevadores(String elevadores) {
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
        return equipoAdicional;
    }

    public void setEquipoAdicional(List<String> equipoAdicional) {
        this.equipoAdicional = equipoAdicional;
    }

}
