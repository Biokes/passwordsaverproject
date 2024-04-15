package toDoApplication.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Tasks")
public class Task{
    @Id
    private String id;
    private String taskName;
    private String taskUser;
    private LocalDateTime startDate;
    private TaskStatus status;
    private LocalDateTime taskDuration;

}
