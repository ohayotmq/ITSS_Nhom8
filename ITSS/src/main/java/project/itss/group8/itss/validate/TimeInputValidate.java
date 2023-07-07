package project.itss.group8.itss.validate;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TimeInputValidate {
    public boolean checkMonthInput (String month) {
        System.out.println(month);
        if (month.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13) {
            System.out.println("Valid Month String: " + month);
            return true;
        }
        else {
            System.out.println("Invalid Month String: " + month);
            return false;
        }
    }

    public static boolean checkInputError(String time) {
        if (checkTimeInput(time))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkTimeInput(String timeToCheck) {
        System.out.println(timeToCheck);
        try {
            LocalTime.parse(timeToCheck);
            System.out.println("Valid time string: " + timeToCheck);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time string: " + timeToCheck);
            return false;
        }
    }
}
