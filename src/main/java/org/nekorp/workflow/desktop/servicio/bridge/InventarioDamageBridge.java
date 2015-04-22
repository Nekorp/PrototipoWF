/**
 *   Copyright 2013-2015 TIKAL-TECHNOLOGY
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

package org.nekorp.workflow.desktop.servicio.bridge;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.nekorp.workflow.desktop.view.model.inventario.damage.DamageDetailsVB;
import org.nekorp.workflow.desktop.view.model.inventario.damage.InventarioDamageVB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tikal.taller.automotriz.model.servicio.auto.damage.DamageDetail;

/**
 * @author Nekorp
 */
@Service
public class InventarioDamageBridge implements ModelBridge<List<DamageDetail>, InventarioDamageVB> {

    private static final String derecha = "derecha";
    private static final String izquierda = "izquierda";
    private static final String frontal = "frontal";
    private static final String trasera = "trasera";
    
    @Autowired
    private DamageDetailBridge damageDetailBridge;
    
    @Override
    public void load(List<DamageDetail> origen, InventarioDamageVB destino) {
        List<DamageDetailsVB> listDerecha = new LinkedList<>();
        List<DamageDetailsVB> listIzquierda = new LinkedList<>();
        List<DamageDetailsVB> listFrontal = new LinkedList<>();
        List<DamageDetailsVB> listTrasera = new LinkedList<>();
        DamageDetailsVB nuevo;
        for (DamageDetail x: origen) {
            nuevo = new DamageDetailsVB();// no se necestia proxy por el momento.
            damageDetailBridge.load(x, nuevo);
            if (StringUtils.equals(x.getEsquema(), derecha)) {
                listDerecha.add(nuevo);
            }
            if (StringUtils.equals(x.getEsquema(), izquierda)) {
                listIzquierda.add(nuevo);
            }
            if (StringUtils.equals(x.getEsquema(), frontal)) {
                listFrontal.add(nuevo);
            }
            if (StringUtils.equals(x.getEsquema(), trasera)) {
                listTrasera.add(nuevo);
            }
        }
        destino.setDerecha(listDerecha);
        destino.setIzquierda(listIzquierda);
        destino.setFrontal(listFrontal);
        destino.setTrasera(listTrasera);
    }

    @Override
    public void unload(InventarioDamageVB origen, List<DamageDetail> destino) {
        List<DamageDetailsVB> listDerecha = origen.getDerecha();
        doUnload(listDerecha, destino, derecha);
        List<DamageDetailsVB> listIzquierda = origen.getIzquierda();
        doUnload(listIzquierda, destino, izquierda);
        List<DamageDetailsVB> listFrontal = origen.getFrontal();
        doUnload(listFrontal, destino, frontal);
        List<DamageDetailsVB> listTrasera = origen.getTrasera();
        doUnload(listTrasera, destino, trasera);
    }
    
    private void doUnload(List<DamageDetailsVB> origen, List<DamageDetail> destino, String esquema) {
        DamageDetail nuevo;
        for (DamageDetailsVB x: origen) {
            nuevo = new DamageDetail();
            damageDetailBridge.unload(x, nuevo);
            nuevo.setEsquema(esquema);
            destino.add(nuevo);
        }
    }
}
