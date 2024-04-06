package toDoApplication.exception;

public class ElapsedDateException extends ToDoManagerExceptions{
    public ElapsedDateException(){
        super("Date has elapsed");
    }
}
