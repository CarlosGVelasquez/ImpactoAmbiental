package infraestructura.notificaciones.implementaciones;

import infraestructura.notificaciones.NotificacionException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME =  "mimogrupo4dds@gmail.com";
    private static final String PASSWORD = "kbxlungydkonlwmc"; //Clave de aplicación generada en GMAIL
    private static final String EMAIL_SUBJECT = "Recomendaciones sobre tu Huella de Carbono";

    private static final String URL = "www.google.com";
    private static final String EMAIL_TEXT = "Hola! Te enviamos las siguientes recomendaciones para reducir tu huella de carbono: "+ URL;


    public void enviarEmail (String mailDestino){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(USERNAME));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailDestino));

            msg.setSubject(EMAIL_SUBJECT);

            msg.setText(EMAIL_TEXT);

            Transport.send(msg);

        } catch (MessagingException e) {
            throw new NotificacionException("Ocurrió un error al querer enviar el mail. Motivo:" + e.getMessage());
        }
    }



}