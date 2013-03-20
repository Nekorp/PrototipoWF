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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.nekorp.workflow.desktop.modelo.servicio.AlertaServicio;
import org.nekorp.workflow.desktop.servicio.ServicioAlertaEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

/**
 * TODO
 * En algun momento de la vida esto seria mejor hacerlo en el server para no exponer los usuarios y cuentas
 */
@Service
public class ServicioAlertaEmailImp implements ServicioAlertaEmail {

    private String contenidoRaw = "Para el Cliente <cliente>\n\n"
        + "Se requiere el seguimiento al automóvil <tipo>, <marca> con placas <placas> con Kilometraje <kilometraje>,"
        + " para el servicio <servicio> que se requiere para el kilometraje <kilometrajeServicio>.";
    @Value("#{appConfig['app.mail.smtp.host']}")
    private String smtpHost;
    @Value("#{appConfig['app.mail.smtp.port']}")
    private String smtpPort;
    @Value("#{appConfig['app.mail.smtp.factory.class']}")
    private String smtpFactoryClass;
    @Value("#{appConfig['app.mail.smtp.auth']}")
    private String smtpAuth;
    @Value("#{appConfig['app.mail.smtp.user']}")
    private String user;
    @Value("#{appConfig['app.mail.smtp.password']}")
    private String password;
    @Value("#{appConfig['app.mail.alerta.sender']}")
    private String mailSender;
    @Value("#{appConfig['app.mail.alerta.recipient']}")
    private String mailRecipient;
    
    @Override
    public void enviarAlerta(List<AlertaServicio> alertas) {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.socketFactory.class", smtpFactoryClass);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
            }
        });
        try {
            for (AlertaServicio x: alertas) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mailSender));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mailRecipient));
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
