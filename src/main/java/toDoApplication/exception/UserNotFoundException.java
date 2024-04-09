package toDoApplication.exception;

public class UserNotFoundException extends ToDoManagerExceptions{
    public UserNotFoundException(){
        super("username provided  not found");
    }
}
