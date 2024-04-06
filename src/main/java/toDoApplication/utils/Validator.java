package toDoApplication.utils;

import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.exception.IncompleteDetailsException;

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
}
