package toDoApplication.exception;

public class InvalidUsernameException extends ToDoManagerExceptions{
    public InvalidUsernameException(String username){
        super(username + " not found.");
    }
}
