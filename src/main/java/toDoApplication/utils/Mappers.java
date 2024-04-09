package toDoApplication.utils;

import toDoApplication.data.models.Task;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.CreateTaskRequest;
import toDoApplication.dtos.response.CompleteTaskResponse;

import static toDoApplication.utils.Validator.*;

public class Mappers{
    public static User mapRequestToUser(RegisterRequest request){
        validateRegisterRequest(request);
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
    public static Task mapToTask(CreateTaskRequest request){
        Task task = new Task();
        task.setTaskUser(request.getUsername());
        task.setTaskName(request.getTaskName());
        return task;
    }

    public static CompleteTaskResponse mapCompleteTask(Task task){
        CompleteTaskResponse response = new CompleteTaskResponse();
        response.setUsername(task.getTaskUser());
        response.setDuration(task.getStartDate());
        response.setStatus(task.getStatus());
        response.setTaskName(task.getTaskName());
        return response;
    }
}
