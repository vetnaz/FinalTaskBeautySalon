package epam.nazaruk.final_project.service.validation;

import epam.nazaruk.final_project.db.entity.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;

public class FieldsValidator {
    private static final String LOGIN_PATTERN = "^[0-9a-zA-Z-+_]{3,25}$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{6,18}$";
    private static final String EMAIL_PATTERN = "^(?=.{3,30}$)[^\\s]+@[^\\s]+\\.[^\\s]+$";
    private static final String NAME_PATTERN = "^[^\\s$/()]+$";
    private static final String SURNAME_PATTERN = "^[^\\s$/()]+$";

    public static boolean validatePassword(String password){
        return password != null && password.matches(PASSWORD_PATTERN);
    }

    public static boolean validateLogin(String login){
        return login != null && login.matches(LOGIN_PATTERN);
    }

    public static boolean registrationValidation(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String name = user.getName();
        String surname = user.getSurname();

        return (email == null || email.length() == 0 || email.matches(EMAIL_PATTERN))
                && login != null && login.matches(LOGIN_PATTERN)
                && password != null && password.matches(PASSWORD_PATTERN)
                && name !=null && name.matches(NAME_PATTERN)
                && surname!= null && surname.matches(SURNAME_PATTERN);
    }

    public static boolean recordValidation(String chosenMaster, String chosenProcedure, String date, String timeslot) {
        boolean dateCheck = false;
        if (date!=null&&LocalDate.parse(date).compareTo(LocalDate.now())>=0){
            dateCheck = true;
            DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.parse(date));
            int day = dayOfWeek.getValue();
            if(day==7||day==6){
                return false;
            }
        }

        boolean checkTimeslot = false;
        if (timeslot!=null&&LocalTime.now().compareTo(LocalTime.parse(timeslot.replaceAll("-[0-9:]*","")))>-1){
            checkTimeslot = true;
        }

        return     chosenMaster!=null
                && chosenProcedure != null
                &&checkTimeslot
                &&dateCheck;
    }
}
