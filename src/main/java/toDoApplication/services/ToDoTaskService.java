package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.data.repository.TaskRepository;
import toDoApplication.exception.TaskDoesNotExistException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoTaskService implements TasksServices{
    public void create(Task task){
        taskRepository.save(task);
    }
    public long countUserTasks(String username){
        return taskRepository.findByTaskUser(username).size();
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
    public Task findTaskBy(String taskName){
       Optional<Task> taskGotten = taskRepository.findByTaskName(taskName);
       if( taskGotten.isEmpty() ) throw new TaskDoesNotExistException();
       return (Task)taskGotten.get();
    }
    public void save(Task task){
        taskRepository.save(task);
    }

    private TaskRepository taskRepository;
}
