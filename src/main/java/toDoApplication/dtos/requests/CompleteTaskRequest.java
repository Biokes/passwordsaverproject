package toDoApplication.dtos.requests;

import lombok.Data;

@Data
public class CompleteTaskRequest{
    private String username;
    private String taskName;
}
