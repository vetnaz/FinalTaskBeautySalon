package epam.nazaruk.final_project.web.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppLocalizationsCommand  extends Command{
    Logger log = Logger.getLogger(AppLocalizationsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start AppLocalizationsCommand");

        HttpSession session = request.getSession();

        String locale = request.getParameter("locale");
        log.trace("Locale: "+ locale);

        session.setAttribute("locale",locale);

        return session.getAttribute("previous_request").toString();
    }
}
