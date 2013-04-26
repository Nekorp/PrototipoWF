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
package org.nekorp.workflow.desktop.servicio.imp;

import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.nekorp.workflow.desktop.modelo.alerta.AlertaServicio;
import org.nekorp.workflow.desktop.servicio.ServicioAlertaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

/**
 * TODO
 * En algun momento de la vida esto seria mejor hacerlo en el server para no exponer los usuarios y cuentas
 */
@Service("servicioAlertaEmail")
public class ServicioAlertaEmailImp implements ServicioAlertaEmail<AlertaServicio> {

    @Autowired
    private ServicioAlertaEmailTemplate template;
    private String contenidoRaw = "Para el Cliente <cliente>\n\n"
        + "Se requiere el seguimiento al automóvil <tipo>, <marca> con placas <placas> con Kilometraje <kilometraje>,"
        + " para el servicio <servicio> que se requiere para el kilometraje <kilometrajeServicio>.";
    
    
    @Override
    public void enviarAlerta(List<AlertaServicio> alertas) {
        try {
            for (AlertaServicio x: alertas) {
                Message message = new MimeMessage(template.buildSession());
                message.setFrom(new InternetAddress(template.getMailSender()));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(template.getMailRecipient()));
                message.setSubject("Alerta");
                ST contenido = new ST(contenidoRaw);
                contenido.add("cliente", x.getNombreCliente());
                contenido.add("tipo", x.getTipoAuto());
                contenido.add("marca", x.getMarcaAuto());
                contenido.add("placas", x.getPlacasAuto());
                contenido.add("kilometraje", x.getKilometrajeAuto());
                contenido.add("servicio", x.getDescripcionServicio());
                contenido.add("kilometrajeServicio", x.getKilometrajeServicio());
                message.setText(contenido.render());
                Transport.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
