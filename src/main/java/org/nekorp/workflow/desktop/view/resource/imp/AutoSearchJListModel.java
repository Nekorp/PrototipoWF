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

package org.nekorp.workflow.desktop.view.resource.imp;

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import technology.tikal.taller.automotriz.model.index.servicio.ServicioIndexAutoData;


/**
 * @author Nekorp
 */
public class AutoSearchJListModel extends AbstractListModel {

    private List<ServicioIndexAutoData> datos;
    
    public AutoSearchJListModel() {
        datos = new LinkedList<>();
    }
    
    @Override
    public int getSize() {
        return datos.size();
    }
    
    @Override
    public Object getElementAt(int i) {
        return datos.get(i).getNumeroSerie();
    }
    
    public void updateData(List<ServicioIndexAutoData> data) {
        this.datos = data;
        this.fireContentsChanged(this, 0, datos.size());
    }
    
    public ServicioIndexAutoData getAutoAt(int i) {
        return datos.get(i);
    }
}
