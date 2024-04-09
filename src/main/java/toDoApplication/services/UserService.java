package toDoApplication.services;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.CompleteTaskRequest;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.dtos.response.CompleteTaskResponse;
import toDoApplication.dtos.response.ViewTaskResponse;

public interface UserService{
    void save(User user);
    long count();
    void register(RegisterRequest request);
    void deleteUser(DetailsRequest detailsRequest);
    void createTask(TaskRequest taskRequest);
    long countTasks(String username);
    void deleteAll();
    CompleteTaskResponse completeTask(CompleteTaskRequest completeTaskRequest);
    boolean isTaskCompleted(CompleteTaskRequest completeTaskRequest);
    ViewTaskResponse viewAllTasks(DetailsRequest request);
}