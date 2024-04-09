package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.data.repository.TaskRepository;
import toDoApplication.dtos.requests.CompleteTaskRequest;
import toDoApplication.exception.TaskDoesNotExistException;

import java.util.ArrayList;
import java.util.List;

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
    public Task findTask(CompleteTaskRequest completeTaskRequest){
        for(Task task : findAll()){
            if(task.getTaskUser().equalsIgnoreCase(completeTaskRequest.getUsername())
                       && task.getTaskName().equalsIgnoreCase(completeTaskRequest.getTaskName()))
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
    public void deleteUserTasks(String username){
        taskRepository.deleteAll(taskRepository.findByTaskUser(username));
    }

    @Autowired
    private TaskRepository taskRepository;
}
