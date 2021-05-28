package epam.nazaruk.final_project.web.command.admin_command;

import epam.nazaruk.final_project.db.dao.ServiceRecordDAO;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTimeslotCommand extends Command {
    Logger log = Logger.getLogger(ChangeTimeslotCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start ChangeTimeslotCommand");

        int recordId = Integer.parseInt(request.getParameter("recordId"));
        log.trace("Record id :"+recordId);
        String timeslot = request.getParameter("timeslot");
        log.trace("New timeslot :"+timeslot);
        new ServiceRecordDAO().updateTimeslotById(recordId,timeslot);

        response.sendRedirect(request.getContextPath()+Path.REDIRECT_ADMIN_PAGE_COMMAND);
    }
}
