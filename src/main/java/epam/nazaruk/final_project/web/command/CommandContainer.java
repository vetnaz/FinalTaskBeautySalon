package epam.nazaruk.final_project.web.command;

import epam.nazaruk.final_project.web.command.admin_command.AdminPageCommand;
import epam.nazaruk.final_project.web.command.admin_command.CancelRecordCommand;
import epam.nazaruk.final_project.web.command.admin_command.ChangeTimeslotCommand;
import epam.nazaruk.final_project.web.command.admin_command.UpdateRecordStatusCommand;
import epam.nazaruk.final_project.web.command.authentification_command.LoginCommand;
import epam.nazaruk.final_project.web.command.authentification_command.LogoutCommand;
import epam.nazaruk.final_project.web.command.client_command.*;
import epam.nazaruk.final_project.web.command.home_command.MainPageCommand;
import epam.nazaruk.final_project.web.command.master_command.MasterScheduleCommand;
import epam.nazaruk.final_project.web.command.master_command.ServiceDoneCommand;
import epam.nazaruk.final_project.web.command.registration_command.RegistrationCommand;
import epam.nazaruk.final_project.web.command.registration_command.SaveUserCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
	
	private static final Logger log = Logger.getLogger(CommandContainer.class);
	
	private static final Map<String, Command> commands = new TreeMap<>();
	
	static {
		commands.put("login", new LoginCommand());
		commands.put("createRecordPage", new RecordPageCommand());
		commands.put("logout",new LogoutCommand());
		commands.put("main",new MainPageCommand());
		commands.put("createOrder",new CreateRecordCommand());
		commands.put("masterSchedule",new MasterScheduleCommand());
		commands.put("updateRecords",new UpdateRecordStatusCommand());
		commands.put("admin",new AdminPageCommand());
		commands.put("language",new AppLocalizationsCommand());
		commands.put("cancelRecord",new CancelRecordCommand());
		commands.put("changeTimeslot",new ChangeTimeslotCommand());
		commands.put("registration",new RegistrationCommand());
		commands.put("saveUser",new SaveUserCommand());
		commands.put("performedServices",new PerformedServicesCommand());
		commands.put("commentPage",new CommentPageCommand());
		commands.put("saveFeedback",new SaveFeedbackCommand());
		commands.put("confirmedServices",new ConfirmedRecordsPageCommand());
		commands.put("serviceDone",new ServiceDoneCommand());
	}


	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}