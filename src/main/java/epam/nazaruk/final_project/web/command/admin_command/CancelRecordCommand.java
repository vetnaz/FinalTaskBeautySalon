package epam.nazaruk.final_project.web.command.admin_command;

import epam.nazaruk.final_project.db.dao.ServiceRecordDAO;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelRecordCommand extends Command {
    Logger log = Logger.getLogger(CancelRecordCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start CancelRecordCommand");

        int id = Integer.parseInt(request.getParameter("cancelId"));
        new ServiceRecordDAO().deleteRecordById(id);

        return Path.REDIRECT_ADMIN_PAGE_COMMAND;
    }
}
