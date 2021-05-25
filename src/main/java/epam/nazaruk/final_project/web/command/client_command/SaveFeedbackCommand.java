package epam.nazaruk.final_project.web.command.client_command;

import epam.nazaruk.final_project.db.dao.FeedbackDAO;
import epam.nazaruk.final_project.db.dao.MasterRecordsDAO;
import epam.nazaruk.final_project.db.entity.Feedback;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class SaveFeedbackCommand extends Command {
    Logger log = Logger.getLogger(SaveFeedbackCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String comment = request.getParameter("comment");
        int recordId = Integer.parseInt(request.getParameter("recordId"));

        Feedback feedback = new Feedback();
        feedback.setComment(comment);
        feedback.setFeedbackDate(LocalDate.now().toString());
        feedback.setServiceRecordId(recordId);

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedbackDAO.insertIntoFeedback(feedback);

        request.setAttribute("commentMessage","succeed");
        return Path.PERFORMED_SERVICES_PATH;
    }
}
