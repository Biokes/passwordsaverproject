package toDoApplication.services;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.CompleteRequest;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
@Service
public interface UserService{
    void save(User user);
    long count();
    void register(RegisterRequest request);
    void deleteUser(DetailsRequest detailsRequest);
    void createTask(TaskRequest taskRequest);
    long countTasks(String username);
    void deleteAll();
    void completeTask(CompleteRequest completeRequest);
    boolean isTaskCompleted(CompleteRequest completeRequest);

}