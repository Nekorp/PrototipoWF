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

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ServicioAlertaEmailTemplate {

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
    @Value("#{appConfig['app.mail.smtp.factory.fallback']}")
    private String fallBack;
    @Value("#{appConfig['app.mail.smtp.starttls']}")
    private String starttls;
    @Value("#{appConfig['app.mail.smtp.debug']}")
    private String debug;
    
    public Session buildSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.socketFactory.class", smtpFactoryClass);
        props.put("mail.smtp.socketFactory.fallback", fallBack);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.debug", debug);

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
            }
        });
        return session;
    }

    public String getMailSender() {
        return mailSender;
    }

    public String getMailRecipient() {
        return mailRecipient;
    }
}
