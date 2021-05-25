package epam.nazaruk.final_project.web.command.home_command;

import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import epam.nazaruk.final_project.db.bean.ServiceMasterBean;
import epam.nazaruk.final_project.db.dao.MasterDAO;
import epam.nazaruk.final_project.db.dao.ServiceDAO;
import epam.nazaruk.final_project.db.dao.ServiceMasterDAO;
import epam.nazaruk.final_project.db.entity.Master;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageCommand extends Command {
    Logger log = Logger.getLogger(MainPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start MainPageCommand");

        List<ServiceMasterBean> servicesList = new ServiceMasterDAO().findServicesAndMasterSortByName();
        List<Master> masters = new MasterDAO().findAllMasters();
        List<String> services = new ServiceDAO().findServicesTitle();

        String sortValue = request.getParameter("filter");
        String[] mastersCheck = request.getParameterValues("masters");
        String[] servicesCheck = request.getParameterValues("services");

        if (mastersCheck!=null){
            servicesList = servicesList.stream().filter(x->{
                for (String s:mastersCheck){
                    if (s.equals(x.getMasterName()+" "+x.getMasterSurname())){
                        return true;
                    }
                }
                return false;
            }).collect(Collectors.toList());
        }

        if (servicesCheck!=null){
            servicesList = servicesList.stream().filter(x->{
                for (String s:servicesCheck){
                    if (s.equals(x.getTitle())){
                        return true;
                    }
                }
                return false;
            }).collect(Collectors.toList());
        }

        if(sortValue!=null) {
            if (sortValue.equals("name")) {
                servicesList.sort(Comparator.comparing(ServiceMasterBean::getMasterName));
                request.setAttribute("name","checked");
            }

            if (sortValue.equals("rating")) {
                servicesList.sort((x1,x2)->x2.getRating()-x1.getRating());
                request.setAttribute("rating","checked");
            }
        }

        request.setAttribute("masters",masters);
        request.setAttribute("servicesName",services);
        request.setAttribute("services", servicesList);

        return Path.MAIN_PAGE_PATH;
    }
}
