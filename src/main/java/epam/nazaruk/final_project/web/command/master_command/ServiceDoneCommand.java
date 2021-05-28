package epam.nazaruk.final_project.web.command.master_command;

import epam.nazaruk.final_project.db.Status;
import epam.nazaruk.final_project.db.dao.ServiceRecordDAO;
import epam.nazaruk.final_project.db.dao.UserDAO;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.service.mail.MailSender;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServiceDoneCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String serviceDoneId = request.getParameter("orderDone");

        new ServiceRecordDAO().updateStatusOfRecords(Integer.parseInt(serviceDoneId), Status.DONE.ordinal()+1);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByOrder(Integer.parseInt(serviceDoneId));

        String name = user.getName() + " " + user.getSurname();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(()->{
            try {
                MailSender.sendEmail(user.getEmail(),name);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        },1, TimeUnit.DAYS);


        response.sendRedirect(request.getContextPath()+Path.REDIRECT_MASTER_PAGE_COMMAND);
    }
}
