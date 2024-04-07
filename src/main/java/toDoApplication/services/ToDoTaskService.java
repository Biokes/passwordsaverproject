package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.data.repository.TaskRepository;
import toDoApplication.dtos.requests.CompleteRequest;
import toDoApplication.exception.TaskDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
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
    public List<Task> findAll(){
        return taskRepository.findAll();
    }
    public void save(Task task){
        taskRepository.save(task);
    }
    public Task findTask(CompleteRequest completeRequest){
        for(Task task : findAll()){
            if(task.getTaskUser().equalsIgnoreCase(completeRequest.getUsername())
                       && task.getTaskName().equalsIgnoreCase(completeRequest.getTaskName()))
                return task;
        }
        throw new TaskDoesNotExistException();
    }
    public List<Task> findUserTasks(String username){
        List<Task> taskList = new ArrayList<>();
        for(Task task : findAll()){
            if(task.getTaskUser().equalsIgnoreCase(username))
                taskList.add(task);
        }
        return taskList;
    }

    private TaskRepository taskRepository;
}
