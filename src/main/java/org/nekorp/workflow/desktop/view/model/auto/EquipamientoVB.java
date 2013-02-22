/**
 *   Copyright 2012-2013 Nekorp
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
package org.nekorp.workflow.desktop.view.model.auto;

import java.util.LinkedList;
import java.util.List;

public class EquipamientoVB {
    private TipoTransmisionVB transmision;
    private TipoElevadorVB elevadores;
    private String bolsasDeAire;
    private boolean aireAcondicionado;
    private List<String> equipoAdicional;

    public EquipamientoVB() {
        transmision = TipoTransmisionVB.estandar;
        elevadores = TipoElevadorVB.manuales;
        bolsasDeAire = "";
        aireAcondicionado = false;
        equipoAdicional = new LinkedList<>();
    }
    
    public TipoTransmisionVB getTransmision() {
        return transmision;
    }

    public void setTransmision(TipoTransmisionVB transmision) {
        this.transmision = transmision;
    }

    public TipoElevadorVB getElevadores() {
        return elevadores;
    }

    public void setElevadores(TipoElevadorVB elevadores) {
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
