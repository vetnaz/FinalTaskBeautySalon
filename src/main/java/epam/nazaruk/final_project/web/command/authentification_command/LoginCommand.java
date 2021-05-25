package epam.nazaruk.final_project.web.command.authentification_command;

import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.db.Role;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.service.AuthorizationsService;
import epam.nazaruk.final_project.service.exception.EntityNotFoundExceptionService;
import epam.nazaruk.final_project.service.exception.ServiceException;
import epam.nazaruk.final_project.service.validation.FieldsValidator;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.trace("LoginCommand start");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        log.trace("Login: " +login);

        String password = request.getParameter("password");
        log.trace("Password: " +password);

        if (FieldsValidator.validateLogin(login)&&FieldsValidator.validatePassword(password)){
            User user;
            try {
                user = AuthorizationsService.getUser(login,password);
            }catch (EntityNotFoundExceptionService e) {
                log.trace("No such user");
                request.setAttribute("valid_message","no_such_user");
                return Path.LOGIN_PAGE_PATH;
            } catch (ServiceException e) {
                log.trace("Wrong password");
                request.setAttribute("valid_message","wrong_password");
                return Path.LOGIN_PAGE_PATH;
            }

            Role userRole = Role.getRole(user);
            session.setAttribute("user", user);
            log.trace("Set attribute user");
            session.setAttribute("userRole", userRole);
            log.trace("Set attribute userRole");

            return Path.REDIRECT_MAIN_PAGE_COMMAND;
        }

        return Path.LOGIN_PAGE_PATH;
    }

}
