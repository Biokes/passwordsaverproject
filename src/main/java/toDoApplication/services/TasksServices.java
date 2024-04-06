package toDoApplication.services;

import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;

@Service
public interface TasksServices{
    long count();
    void create(Task task);
    long countUserTasks(String username);
}
