package toDoApplication.services;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
@Service
public interface UserService{
    void save(User user);
    long count();
    void register(RegisterRequest request);
    void deleteUserByUsername(DetailsRequest detailsRequest);
    long countAllTasks();
    void createTask(TaskRequest taskRequest);
    long countTasks(String username);
    void deleteAll();
}