package toDoApplication.exception;

public class UsernameTakenException extends ToDoManagerExceptions{

    public UsernameTakenException(String message){
        super("\""+message + "\" already taken. pls choose another one");
    }
}
