package epam.nazaruk.final_project.web.command.admin_command;

import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.db.Status;
import epam.nazaruk.final_project.db.dao.ServiceRecordDAO;
import epam.nazaruk.final_project.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRecordStatusCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String recordIdPaid = request.getParameter("orderPaid");
        String recordConfirmed = request.getParameter("orderConfirmed");

        if (recordIdPaid!=null){
            new ServiceRecordDAO().updateStatusOfRecords(Integer.parseInt(recordIdPaid), Status.PAID.ordinal()+1);
            response.sendRedirect(request.getContextPath()+Path.REDIRECT_ADMIN_PAGE_COMMAND);
            return;
        }
        if (recordConfirmed!=null){
            new ServiceRecordDAO().updateStatusOfRecords(Integer.parseInt(recordConfirmed), Status.CONFIRMED.ordinal()+1);
            response.sendRedirect(request.getContextPath()+Path.REDIRECT_ADMIN_PAGE_COMMAND);
            return;
        }

        response.sendRedirect(request.getContextPath()+Path.REDIRECT_ADMIN_PAGE_COMMAND);
    }
}
