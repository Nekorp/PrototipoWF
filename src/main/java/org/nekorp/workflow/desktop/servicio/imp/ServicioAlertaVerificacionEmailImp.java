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
import org.nekorp.workflow.desktop.modelo.alerta.AlertaVerificacion;
import org.nekorp.workflow.desktop.servicio.ServicioAlertaEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

/**
 * TODO
 * En algun momento de la vida esto seria mejor hacerlo en el server para no exponer los usuarios y cuentas
 */
@Service("servicioAlertaVerificacionEmail")
public class ServicioAlertaVerificacionEmailImp implements ServicioAlertaEmail<AlertaVerificacion> {

    private String contenidoRaw = "Verificar el estatus de la verificación del auto con placas <placas>, en el periodo <periodo>.";
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
    public void enviarAlerta(List<AlertaVerificacion> alertas) {
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
            for (AlertaVerificacion x: alertas) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mailSender));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mailRecipient));
                message.setSubject("Alerta de Verificación");
                ST contenido = new ST(contenidoRaw);
                contenido.add("placas", x.getPlacas());
                contenido.add("periodo", x.getPeriodo());
                message.setText(contenido.render());
                Transport.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
