package epam.nazaruk.final_project.web.command.client_command;

import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.service.RecordService;
import epam.nazaruk.final_project.service.exception.AlreadyExistRecordExceptionService;
import epam.nazaruk.final_project.service.validation.FieldsValidator;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateRecordCommand extends Command {
    Logger log = Logger.getLogger(CreateRecordCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start CreateRecordCommand");

        String chosenProcedure = request.getParameter("chosenProcedure");
        log.trace("Chosen procedure "+ chosenProcedure);
        String chosenMaster = request.getParameter(chosenProcedure);
        log.trace("Chosen master "+ chosenMaster);

        String date = request.getParameter("date");
        String timeslot = request.getParameter("timeslot");

        if (FieldsValidator.recordValidation(chosenMaster,chosenProcedure,date,timeslot)){
            log.trace("Chosen date "+ date);
            log.trace("Chosen timeslot "+ timeslot);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            log.trace("User : " + user.toString());

            try {
                RecordService.recordCreation(chosenProcedure, chosenMaster, date, timeslot, user);
            } catch (AlreadyExistRecordExceptionService alreadyExistRecordExceptionService) {
                request.setAttribute("createRecordMessage","taken");
                return Path.REDIRECT_CREATE_RECORD_PAGE_COMMAND;
            }

            request.setAttribute("createRecordMessage","successful");
            return Path.REDIRECT_CREATE_RECORD_PAGE_COMMAND;
        }else {
            request.setAttribute("createRecordMessage","incorrect");
            return Path.REDIRECT_CREATE_RECORD_PAGE_COMMAND;
        }
    }

}
