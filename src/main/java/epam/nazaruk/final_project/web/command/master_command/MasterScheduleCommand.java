package epam.nazaruk.final_project.web.command.master_command;

import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.web.Path;
import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.db.dao.MasterRecordsDAO;
import epam.nazaruk.final_project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;


public class MasterScheduleCommand  extends Command {
    Logger logger = Logger.getLogger(MasterScheduleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.trace("Start MasterScheduleCommand");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        MasterRecordsDAO masterRecordsDAO = new MasterRecordsDAO();

        List<String> workDays = masterRecordsDAO.selectWorkDays(user.getName(),user.getSurname());
        logger.trace("Date of master's work "+ workDays.toString());

        Map<String,List<MasterRecordsBean>> schedule = new TreeMap<>();
        for (String day:workDays){
            DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.parse(day));
            day = day + " "+  dayOfWeek.getDisplayName(TextStyle.FULL, new Locale((String) session.getAttribute("locale"))).toUpperCase();
            schedule.put(day,masterRecordsDAO.findRecordsByMasterAndDay(user.getName(),user.getSurname(),day));
        }
        logger.trace("Schedule: "+ schedule.toString());

        request.setAttribute("schedule",schedule);

        return Path.MASTER_PAGE_PATH;
    }
}
