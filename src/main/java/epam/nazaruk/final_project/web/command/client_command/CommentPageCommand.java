package epam.nazaruk.final_project.web.command.client_command;

import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.db.dao.MasterRecordsDAO;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentPageCommand extends Command {
    Logger log = Logger.getLogger(CommentPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int recordId = Integer.parseInt(request.getParameter("recordId"));

        MasterRecordsBean masterRecordsBean = new MasterRecordsDAO().selectRecordById(recordId);
        request.setAttribute("record",masterRecordsBean);

        return Path.COMMENT_PAGE_PATH;
    }
}
