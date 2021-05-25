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

public class ConfirmedRecordsPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<MasterRecordsBean> masterRecordsBeanList;

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        MasterRecordsDAO masterRecordsDAO = new MasterRecordsDAO();
        masterRecordsBeanList = masterRecordsDAO.selectConfirmedRecordsByUSer(user.getId(), Status.CONFIRMED.ordinal()+1);

        request.setAttribute("confirmedRecords",masterRecordsBeanList);

        return Path.CONFIRMED_PAGE_PATH;
    }
}
