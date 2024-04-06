package toDoApplication.utils;

import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.exception.IncompleteDetailsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Validator{
    public static void validate(String details){
        if(details == null || details.isBlank()){
            throw new IncompleteDetailsException();
        }
    }
    public static void validateRegisterRequest(RegisterRequest request){
        validate(request.getPassword().strip());
        validate(request.getUsername().strip());
    }
    public static LocalDate validateDate(String date) throws ParseException{
        return LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static boolean isElapsed(LocalDate date){
        return date.isBefore(LocalDate.now());
    }
}
