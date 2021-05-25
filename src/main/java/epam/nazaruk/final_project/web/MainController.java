package epam.nazaruk.final_project.web;

import epam.nazaruk.final_project.web.command.Command;
import epam.nazaruk.final_project.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class MainController extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("Get method");
        command(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("Post method");
        doGet(req,resp);
    }

    private void command(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.trace("Start MainController");
        HttpSession session = req.getSession();
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", "en");
        }

        log.trace("Command :"+req.getParameter("command"));
        Command command = CommandContainer.get(req.getParameter("command"));

        String forward = command.execute(req,resp);
        log.trace("Forward :" + forward);

        req.getRequestDispatcher(forward).forward(req,resp);

        String reqQuery =  req.getQueryString();
        if (reqQuery!=null){
            String previousRequest = "/controller?" + reqQuery;
            log.trace("Request query: " + req.getQueryString());
            req.getSession().setAttribute("previous_request",previousRequest);
        }

    }
}
