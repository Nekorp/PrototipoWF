/**
 *   Copyright 2013 Nekorp
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

package org.nekorp.workflow.desktop.servicio.reporte;

import java.util.LinkedList;
import java.util.List;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.AutoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.EventoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.RegistroCostoRC;
import org.nekorp.workflow.desktop.modelo.reporte.cliente.ReporteCliente;

/**
 *
 */
public class ReporteClienteDataSource {

    public static List<ReporteCliente> getData() {
        ReporteCliente dato = new ReporteCliente();
        dato.setNumeroDeServicio("4564");
        dato.setNombreDelCliente("El Cliente");
        dato.setDescripcionServicio("Se le tuneo la nave");
        dato.setTiempoReparacion("20 dias");
        AutoRC auto = new AutoRC();
        auto.setMarca("Ford");
        auto.setTipo("Fiesta");
        auto.setVersion("LE");
        auto.setSerie("4545645dsdsds");
        auto.setModelo("2002");
        auto.setColor("Rojo");
        auto.setPlacas("454-dds");
        auto.setKilometraje("4545");
        dato.setAuto(auto);
        List<RegistroCostoRC> mecanica = new LinkedList<>();
        RegistroCostoRC registro = new RegistroCostoRC();
        registro.setTipo("Refacciones");
        registro.setDescripcion("refa 1");
        registro.setCosto("$21.00");
        mecanica.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Mano de Obra");
        registro.setDescripcion("trabajo 1");
        registro.setCosto("$21.00");
        mecanica.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Mano de Obra");
        registro.setDescripcion("trabajo 3");
        registro.setCosto("$6.00");
        mecanica.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Refacciones");
        registro.setDescripcion("refa 5");
        registro.setCosto("$700.00");
        mecanica.add(registro);
        dato.setRegistroMecanica(mecanica);
        
        List<RegistroCostoRC> hojalateria = new LinkedList<>();
        registro = new RegistroCostoRC();
        registro.setTipo("Mano de Obra");
        registro.setDescripcion("trabajo 2");
        registro.setCosto("$56.00");
        hojalateria.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Refacciones");
        registro.setDescripcion("refa2");
        registro.setCosto("$77.00");
        hojalateria.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Mano de Obra");
        registro.setDescripcion("coso 3");
        registro.setCosto("$2,332.00");
        hojalateria.add(registro);
        
        registro = new RegistroCostoRC();
        registro.setTipo("Mano de Obra");
        registro.setDescripcion("coso 4343");
        registro.setCosto("$4,545.00");
        hojalateria.add(registro);
        dato.setRegistroHojalateriaPintura(hojalateria);
        
        dato.setTotalMecanica("$748.00");
        dato.setTotalHojalateriaPintura("$7,010.00");
        dato.setTotalServicio("$7,758.00");
        
        List<EventoRC> eventos = new LinkedList<>();
        EventoRC evento;
        evento = new EventoRC();
        evento.setNombreEvento("Entrada de auto");
        evento.setDetalle("Nombre de quien entrego");
        evento.setFecha("Fecha de entrega");
        evento.setEtiqueta("Etiqueta");
        eventos.add(evento);
        
        evento = new EventoRC();
        evento.setNombreEvento("Otro");
        evento.setDetalle("Descripción");
        evento.setFecha("Fecha");
        evento.setEtiqueta("Etiqueta");
        eventos.add(evento);
        
        evento = new EventoRC();
        evento.setNombreEvento("Entrada de auto");
        evento.setDetalle("Nombre de quien entrego");
        evento.setFecha("Fecha de entrega");
        evento.setEtiqueta("Etiqueta");
        eventos.add(evento);
        
        evento = new EventoRC();
        evento.setNombreEvento("Otro");
        evento.setDetalle("Descripción");
        evento.setFecha("Fecha");
        evento.setEtiqueta("Etiqueta");
        eventos.add(evento);
        
        dato.setBitacora(eventos);
        
        List<ReporteCliente> r = new LinkedList<>();
        r.add(dato);
        return r;
    }
}
