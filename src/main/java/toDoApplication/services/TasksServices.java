package toDoApplication.services;

import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;

import java.util.List;

@Service
public interface TasksServices{
    void create(Task task);
    long countUserTasks(String username);
    void deleteAll();
    List<Task> findAll();
    void save(Task task);
}
