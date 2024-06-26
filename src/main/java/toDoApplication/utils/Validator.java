package toDoApplication.utils;

import toDoApplication.dtos.requests.CompleteTaskRequest;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.CreateTaskRequest;
import toDoApplication.exception.IncompleteDetailsException;
import toDoApplication.exception.InvalidDateException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Validator{
    public static void validate(String details){
        if(details == null || details.isBlank() ){
            throw new IncompleteDetailsException();
        }
        details = details.strip();
    }
    public static void validateRegisterRequest(RegisterRequest request){

        validate(request.getPassword());
        validate(request.getUsername());
    }
    public static LocalDate validateDate(String date) throws ParseException{
        if(date.length()!= 10)
            throw new InvalidDateException();
        return LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static boolean isElapsed(LocalDate date){
        return date.isBefore(LocalDate.now());
    }
    public static void validateDetailsRequest(DetailsRequest detailsRequest){
        validate(detailsRequest.getUsername( ));
        validate(detailsRequest.getPassword( ));
    }
    public static void validateCompleteRequest(CompleteTaskRequest completeTaskRequest){
        validate(completeTaskRequest.getUsername( ));
        validate(completeTaskRequest.getTaskName( ));
    }
    public static void validateTaskRequest(CreateTaskRequest createTaskRequest){
        validate(createTaskRequest.getTaskName( ));
        validate(createTaskRequest.getUsername( ));
    }
}
