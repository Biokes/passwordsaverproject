package toDoApplication.utils;

import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;

public class Mappers{
    public static User mapRequestToUser(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
}
