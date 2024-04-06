package toDoApplication.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
public class Task{
    @Id
    private String id;
    private String taskName;
    private String taskUser;
    private Date duedate;
    private TaskStatus status;
}
