package epam.nazaruk.final_project.web.command.admin_command;

import epam.nazaruk.final_project.service.AdminService;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPageCommand extends Command {
    Logger log = Logger.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start AdminPageCommand");

        int recordPerPage = 10;
        int currentPage;
        if (request.getParameter("currentPage")!=null){
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }else {
            currentPage = 1;
        }
        int numberOfPages;

        int numberOfRows = AdminService.getNumberOfRows();
        numberOfPages = numberOfRows / recordPerPage;
        if (numberOfRows%recordPerPage>0 && numberOfRows>recordPerPage){
            numberOfPages++;
        }

        request.setAttribute("currentPage",currentPage);
        request.setAttribute("recordPerPage",recordPerPage);
        request.setAttribute("numberOfPages",numberOfPages);

        List<MasterRecordsBean> masterRecordsBeans = AdminService.findMasterRecords(currentPage,recordPerPage);
        log.trace(masterRecordsBeans.toString());

        request.setAttribute("allRecords",masterRecordsBeans);

        return Path.ADMIN_PAGE_PATH;
    }
}
