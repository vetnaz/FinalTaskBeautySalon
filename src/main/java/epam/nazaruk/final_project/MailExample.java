package epam.nazaruk.final_project;

import epam.nazaruk.final_project.service.mail.MailSender;

import javax.mail.MessagingException;

public class MailExample {
    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            MailExample mailExample = new MailExample();
            mailExample.hello();
        });
        thread.start();

    }

    void hello(){
        synchronized (new Object()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
