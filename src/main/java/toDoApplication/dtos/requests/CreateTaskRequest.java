package toDoApplication.dtos.requests;

import lombok.Data;

@Data
public class CreateTaskRequest{
    private String taskName;
    private String dueDate;
    private String username;

}
