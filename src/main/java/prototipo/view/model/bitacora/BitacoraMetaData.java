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
package prototipo.view.model.bitacora;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;

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
