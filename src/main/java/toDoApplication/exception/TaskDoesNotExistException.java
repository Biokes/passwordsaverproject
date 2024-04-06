package toDoApplication.exception;

import toDoApplication.services.ToDoTaskService;

public class TaskDoesNotExistException extends ToDoManagerExceptions{
    public TaskDoesNotExistException(){
        super("Task with provided name does not exist");
    }
}
