package toDoApplication.utils;

import org.bson.codecs.jsr310.LocalDateCodec;
import org.springframework.format.datetime.DateFormatter;
import toDoApplication.data.models.Task;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.exception.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
            task.setDuedate(validateDate(request.getDueDate( )));
        }catch(ParseException  error){
            throw new InvalidDateException();}
        return task;
    }
    private static Date validateDate(String date) throws ParseException{
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
