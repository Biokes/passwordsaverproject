package toDoApplication.exception;

public class IncompleteDetailsException extends ToDoManagerExceptions{
    public IncompleteDetailsException(){
        super("Input fields cannot be empty");
    }
}
