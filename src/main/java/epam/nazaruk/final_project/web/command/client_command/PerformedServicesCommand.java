package epam.nazaruk.final_project.web.command.client_command;

import epam.nazaruk.final_project.db.Status;
import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.db.dao.MasterRecordsDAO;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PerformedServicesCommand  extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        int userId = user.getId();

        List<MasterRecordsBean> masterRecordsBeanList = new MasterRecordsDAO().selectRecordsByUser(userId, Status.PAID.ordinal()+1);
        request.setAttribute("performedRecords",masterRecordsBeanList);

        return Path.PERFORMED_SERVICES_PATH;
    }
}
