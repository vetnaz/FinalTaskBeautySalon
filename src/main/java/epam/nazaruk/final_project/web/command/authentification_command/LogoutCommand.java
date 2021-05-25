package epam.nazaruk.final_project.web.command.authentification_command;

import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String locale = null;
        try {
            locale = (String) session.getAttribute("locale");
        }catch (Exception e){
            log.trace("Default locale");
        }
        
        session = request.getSession(false);
        if (session != null)
            session.invalidate();

        if (locale!=null){
            request.getSession().setAttribute("locale",locale);
        }
        
        return Path.REDIRECT_MAIN_PAGE_COMMAND;
    }
}
