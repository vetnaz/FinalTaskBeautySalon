package epam.nazaruk.final_project;

import epam.nazaruk.final_project.service.mail.MailSender;

import javax.mail.MessagingException;

public class MailExample {
    public static void main(String[] args) {
        try {
            MailSender.sendEmail("tommynazaruk@gmail.com","Vitalii Nazaruk");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
