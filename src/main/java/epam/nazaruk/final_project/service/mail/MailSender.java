package epam.nazaruk.final_project.service.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void sendEmail(String recipient,String recipientName) throws MessagingException {
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.host","smtp.gmail.com");
        properties.setProperty("mail.smtp.port","587");

        String myAccountEmail = "yourbestbeautysalon@gmail.com";
        String myPassword = "nazaruk2909";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,myPassword);
            }
        });

        Message message = prepareMessage(session,myAccountEmail,recipient,recipientName);

        assert message != null;
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String recipientName) {
        Message message = new MimeMessage(session);

        String html = "<h3>Dear " + recipientName + "\n" +
                " please leave your feedback about our service.\n" +
                " It helps us become better<h3>\n" +
                "http://localhost:8080/beautysalon/controller?command=performedServices";

        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setSubject("Beauty salon, leave feedback");
            message.setContent(html, "text/html; charset=utf-8");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
