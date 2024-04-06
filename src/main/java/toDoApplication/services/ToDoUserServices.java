package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.data.models.User;
import toDoApplication.data.repository.UserRepository;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.exception.UserNotFoundException;
import toDoApplication.utils.Mappers;
import java.util.List;
import static toDoApplication.data.models.TaskStatus.NOT_COMPLETED;
import static toDoApplication.utils.Mappers.mapToTask;

@Service
@AllArgsConstructor
public class ToDoUserServices implements UserService{
    private UserRepository userRepository;
    private TasksServices tasksServices;
    public void save(User user){
        userRepository.save(user);
    }
    public long count(){
        return userRepository.count();
    }
    public void register(RegisterRequest request){
        User user = Mappers.mapRequestToUser(request);
        save(user);
    }
    public void deleteUserByUsername(DetailsRequest detailsRequest){
        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equalsIgnoreCase(detailsRequest.getUsername()) &&
            user.getPassword().equalsIgnoreCase(detailsRequest.getPassword())){
                userRepository.delete(user);
                return;
            }
        }
        throw new UserNotFoundException();
    }
    public long countAllTasks(){
        return tasksServices.count();
    }
    public void createTask(TaskRequest taskRequest){
        Task task = mapToTask(taskRequest);
        task.setStatus(NOT_COMPLETED);
        tasksServices.create(task);
    }
    public long countTasks(String username){
        return tasksServices.countUserTasks(username);
    }

}
