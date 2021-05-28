package epam.nazaruk.final_project.web.command.registration_command;

import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.service.exception.AlreadyExistRecordExceptionService;
import epam.nazaruk.final_project.service.exception.ServiceException;
import epam.nazaruk.final_project.service.validation.FieldsValidator;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import epam.nazaruk.final_project.service.AuthorizationsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveUserCommand extends Command {
    Logger log = Logger.getLogger(SaveUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start SaveUserCommand");

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        if (FieldsValidator.registrationValidation(user)){
            try {
                AuthorizationsService.createUser(user);
            }catch (AlreadyExistRecordExceptionService e){
                response.sendRedirect(request.getContextPath()+Path.REDIRECT_REGISTRATION_PAGE_PATH+"&registration_message=user_exist");
                return;
            } catch (ServiceException e) {
                response.sendRedirect(request.getContextPath()+Path.REDIRECT_REGISTRATION_PAGE_PATH+"&registration_message=have_problem");
                return;
            }

            response.sendRedirect(request.getContextPath()+Path.REDIRECT_LOGIN_PAGE_PATH);
        }else{
            response.sendRedirect(request.getContextPath()+Path.REDIRECT_REGISTRATION_PAGE_PATH+"&registration_message=incorrect_data");
        }
    }
}
