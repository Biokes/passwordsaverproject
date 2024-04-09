package toDoApplication.services;

import toDoApplication.data.models.Task;
import toDoApplication.dtos.requests.CompleteTaskRequest;

import java.util.List;


public interface TasksServices{
    void create(Task task);
    long countUserTasks(String username);
    void deleteAll();
    List<Task> findAll();
    void save(Task task);
    Task findTask(CompleteTaskRequest completeTaskRequest);
    List<Task> findUserTasks(String username);
    void deleteUserTasks(String username);
}
