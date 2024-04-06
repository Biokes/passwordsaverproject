package toDoApplication.exception;

public class PastDateException  extends ToDoManagerExceptions{
    public PastDateException(){
        super("Date has elapsed");
    }
}
