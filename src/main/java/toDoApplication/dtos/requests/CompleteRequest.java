package toDoApplication.dtos.requests;

import lombok.Data;

@Data
public class CompleteRequest{
    private String username;
    private String taskName;
}
