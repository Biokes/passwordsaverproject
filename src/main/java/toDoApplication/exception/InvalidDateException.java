package toDoApplication.exception;

public class InvalidDateException extends ToDoManagerExceptions{
    public InvalidDateException(){
        super("Invalid date provided.\nDate should be in dd/mm/yyyy format.");
    }
}
