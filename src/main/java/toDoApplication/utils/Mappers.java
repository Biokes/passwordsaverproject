package toDoApplication.utils;

import org.bson.codecs.jsr310.LocalDateCodec;
import org.springframework.format.datetime.DateFormatter;
import toDoApplication.data.models.Task;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.exception.ElapsedDateException;
import toDoApplication.exception.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
            task.setDuedate(validateDate(request.getDueDate( )));
            if(Validator.isElapsed(task.getDuedate()))
                throw new ElapsedDateException();
        }catch(ParseException  error){
            throw new InvalidDateException();
        }
        return task;
    }

}
