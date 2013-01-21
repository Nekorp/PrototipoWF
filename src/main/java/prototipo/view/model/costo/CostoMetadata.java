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
package prototipo.view.model.costo;

import org.springframework.stereotype.Component;
import prototipo.servicio.Metadata;
import prototipo.view.model.currency.MonedaVB;


@Component
public class CostoMetadata implements Metadata {
    private MonedaVB total;
    private MonedaVB totalMecanicaManoDeObra;
    private MonedaVB totalMecanicaRefacciones;
    private MonedaVB totalHojalateriaManoDeObra;
    private MonedaVB totalHojalateriaInsumos;
    
    public CostoMetadata() {
        this.total = new MonedaVB();
        totalMecanicaManoDeObra = new MonedaVB();
        totalMecanicaRefacciones = new MonedaVB();
        totalHojalateriaManoDeObra = new MonedaVB();
        totalHojalateriaInsumos = new MonedaVB();
    }

    public MonedaVB getTotal() {
        return total;
    }

    public void setTotal(MonedaVB total) {
        this.total = total;
    }

    public MonedaVB getTotalMecanicaManoDeObra() {
        return totalMecanicaManoDeObra;
    }

    public void setTotalMecanicaManoDeObra(MonedaVB totalMecanicaManoDeObra) {
        this.totalMecanicaManoDeObra = totalMecanicaManoDeObra;
    }

    public MonedaVB getTotalMecanicaRefacciones() {
        return totalMecanicaRefacciones;
    }

    public void setTotalMecanicaRefacciones(MonedaVB totalMecanicaRefacciones) {
        this.totalMecanicaRefacciones = totalMecanicaRefacciones;
    }

    public MonedaVB getTotalHojalateriaManoDeObra() {
        return totalHojalateriaManoDeObra;
    }

    public void setTotalHojalateriaManoDeObra(MonedaVB totalHojalateriaManoDeObra) {
        this.totalHojalateriaManoDeObra = totalHojalateriaManoDeObra;
    }

    public MonedaVB getTotalHojalateriaInsumos() {
        return totalHojalateriaInsumos;
    }

    public void setTotalHojalateriaInsumos(MonedaVB totalHojalateriaInsumos) {
        this.totalHojalateriaInsumos = totalHojalateriaInsumos;
    }
}
