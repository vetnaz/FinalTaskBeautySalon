package epam.nazaruk.final_project.web.command.client_command;

import epam.nazaruk.final_project.db.dao.ServiceDAO;
import epam.nazaruk.final_project.db.dao.ServiceMasterDAO;
import epam.nazaruk.final_project.db.entity.Master;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordPageCommand extends Command {
    private static final Logger log = Logger.getLogger(RecordPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("Start RecordPageCommand");

        List<String> services = new ServiceDAO().findServicesTitle();
        log.trace("List of services "+services.toString());

        Map<String,List<Master>> serviceMasters = new HashMap<>();
        for (String service:services){
            serviceMasters.put(service,new ServiceMasterDAO().findMastersByService(service));
        }
        log.trace("Service and master: "+serviceMasters.toString());

        request.setAttribute("serviceMasters",serviceMasters);
        log.trace("Set attribute serviceMasters");

        request.setAttribute("currentDate",LocalDate.now().toString());

        return Path.CREATE_RECORD_PATH;
    }


}
