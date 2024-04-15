package toDoApplication.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest{
    private String taskName;
    private LocalDateTime startrDate;
    private String username;
    private String dueDate;

}
