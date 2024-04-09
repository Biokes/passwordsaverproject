package toDoApplication.services;

import org.springframework.http.server.ServerHttpAsyncRequestControl;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.Task;
import toDoApplication.dtos.requests.CompleteRequest;

import java.util.List;


public interface TasksServices{
    void create(Task task);
    long countUserTasks(String username);
    void deleteAll();
    List<Task> findAll();
    void save(Task task);
    Task findTask(CompleteRequest completeRequest);
    List<Task> findUserTasks(String username);
    void deleteUserTasks(String username);
}
