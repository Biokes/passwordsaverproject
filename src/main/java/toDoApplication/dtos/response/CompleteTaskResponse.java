package toDoApplication.dtos.response;

import lombok.Data;
import toDoApplication.data.models.TaskStatus;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class CompleteTaskResponse{
    private String username;
    private String taskName;
    private TaskStatus status;
    private Duration duration;
    private LocalDate dueDate;
}
