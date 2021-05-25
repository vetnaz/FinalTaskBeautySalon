package epam.nazaruk.final_project.web.command.registration_command;

import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {
    Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.trace("Start RegistrationCommand");

        return Path.REGISTRATION_PAGE_PATH;
    }
}
