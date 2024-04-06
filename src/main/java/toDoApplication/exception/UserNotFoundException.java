package toDoApplication.exception;

public class UserNotFoundException extends ToDoManagerExceptions{
    public UserNotFoundException(){
        super("invalid details provided");
    }
}
