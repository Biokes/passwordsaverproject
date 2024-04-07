package toDoApplication.utils;

import toDoApplication.data.models.Task;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.exception.ElapsedDateException;
import toDoApplication.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static toDoApplication.utils.Validator.isElapsed;
import static toDoApplication.utils.Validator.validateDate;

public class Mappers{
    public static User mapRequestToUser(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
    public static Task mapToTask(TaskRequest request){
        Task task = new Task();
        task.setTaskUser(request.getUsername());
        task.setTaskName(request.getTaskName());
        try{
            request.setDueDate(request.getDueDate( ).strip().replaceAll("\\D", "/"));
            task.setDuedate(LocalDate.parse(validateDate(request.getDueDate( ))
                                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        }catch(Exception error){
            throw new InvalidDateException();
        }
        if(isElapsed(task.getDuedate()))
            throw new ElapsedDateException();
        return task;
    }

}
