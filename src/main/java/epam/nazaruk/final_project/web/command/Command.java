package epam.nazaruk.final_project.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public  abstract class Command {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
