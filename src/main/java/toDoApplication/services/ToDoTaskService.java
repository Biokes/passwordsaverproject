package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.data.repository.TaskRepository;

@Service
@AllArgsConstructor
public class ToDoTaskService implements TasksServices{
private TaskRepository taskRepository;
    public long count(){
        return taskRepository.count();
    }
    public void create(Task task){
        taskRepository.save(task);
    }
    public long countUserTasks(String username){
        return taskRepository.findByTaskUser(username).size();
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
}
