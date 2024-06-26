package toDoApplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toDoApplication.dtos.requests.CompleteTaskRequest;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.CreateTaskRequest;
import toDoApplication.dtos.response.ViewTaskResponse;
import toDoApplication.exception.ToDoManagerExceptions;
import toDoApplication.services.UserService;

import static org.springframework.http.HttpStatus.*;
@RestController
@RequestMapping("/ToDo")
public class ToDoController{
    @Autowired
    private UserService userService;
    @PostMapping("/Register")
    public ResponseEntity<?> createAccount(@RequestBody RegisterRequest request){
        try{
            userService.register(request);
            return new ResponseEntity<>("Registered successfully", CREATED);
        }
        catch( ToDoManagerExceptions error){
            return new ResponseEntity<>(error.getMessage(),BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@RequestBody DetailsRequest request){
        try{
            userService.deleteUser(request);
            return new ResponseEntity<>("Deleted successfully.", OK);
        }
        catch(ToDoManagerExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @PatchMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request){
        try{
            userService.createTask(request);
            return new ResponseEntity<>("Task created successfully", OK);
        }
        catch(ToDoManagerExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @PostMapping("/mark-task-Done")
    public ResponseEntity<?> markTaskAsDone(@RequestBody CompleteTaskRequest request){
        try{
            userService.completeTask(request);
            return new ResponseEntity<>("successfully marked", OK);
        }
        catch(ToDoManagerExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @GetMapping("view-all-task")
    public ResponseEntity<?> viewAllTasks(@RequestBody DetailsRequest request){
        try{
            ViewTaskResponse response = userService.viewAllTasks(request);
            return new ResponseEntity<>(response.getBody(), OK);
        }
        catch(ToDoManagerExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
}
