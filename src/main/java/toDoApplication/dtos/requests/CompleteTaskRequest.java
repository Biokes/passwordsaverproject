package toDoApplication.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompleteTaskRequest{
    private String username;
    private String taskName;
    private LocalDateTime startDateTime;
}
