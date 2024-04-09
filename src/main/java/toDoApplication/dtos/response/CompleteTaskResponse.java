package toDoApplication.dtos.response;

import lombok.Data;
import toDoApplication.data.models.TaskStatus;

import java.time.LocalDateTime;

@Data
public class CompleteTaskResponse{
    private String username;
    private String taskName;
    private TaskStatus status;
    private LocalDateTime duration;
}
