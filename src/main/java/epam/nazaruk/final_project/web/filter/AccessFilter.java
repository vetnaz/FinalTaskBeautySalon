package epam.nazaruk.final_project.web.filter;

import epam.nazaruk.final_project.db.Role;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.web.Path;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AccessFilter implements Filter {
    Logger log = Logger.getLogger(AccessFilter.class);

    private static final List<String> adminCommands = new ArrayList<>();
    private static final List<String> masterCommand = new ArrayList<>();
    private static final List<String> userCommands = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) {
        log.trace("Start AccessFilter");

        adminCommands.add("admin");
        adminCommands.add("updateRecords");
        adminCommands.add("cancelRecord");
        adminCommands.add("changeTimeslot");

        masterCommand.add("masterSchedule");
        masterCommand.add("updateRecords");

        userCommands.add("createRecordPage");
        userCommands.add("createOrder");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter("command");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();

        if (command != null) {
            log.trace("Command not null");
            User user = (User) session.getAttribute("user");

            if (adminCommands.contains(command) && user != null && Role.getRole(user).getName().equals("admin")
                    || userCommands.contains(command) && user != null && Role.getRole(user).getName().equals("client")
                    || masterCommand.contains(command) && user != null && Role.getRole(user).getName().equals("master")) {

                log.trace("User has a permission");

                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else if(adminCommands.contains(command)||userCommands.contains(command)||masterCommand.contains(command)) {
                log.trace("User hasn't got  a permission");

                httpRequest.setAttribute("access_message", "is_not_allowed");

                httpRequest.getRequestDispatcher(Path.REDIRECT_MAIN_PAGE_COMMAND).forward(servletRequest, servletResponse);
                return;
            }
        }
        log.trace("Command go ahead");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
