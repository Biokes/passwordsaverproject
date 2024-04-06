package todoApplication.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.requests.TaskRequest;
import toDoApplication.exception.IncompleteDetailsException;
import toDoApplication.exception.InvalidUsernameException;
import toDoApplication.exception.UserNotFoundException;
import toDoApplication.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes= ToDoMain.class)
public class ToDoUserServicesTest{
    @BeforeEach
    void wipe(){
        userService.deleteAll();
    }
    @Test
    void createUser_testUserIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        assertEquals(1, userService.count());
    }
    @Test
    void deleteUserByUsername_testUserIsDeleted(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        DetailsRequest detailsRequest = new DetailsRequest();
        detailsRequest.setUsername("username");
        detailsRequest.setPassword("password");
        userService.deleteUserByUsername(detailsRequest);
        assertEquals(0, userService.count());
    }
    @Test
    void deleteUserWithWrongDetails_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        DetailsRequest detailsRequest = new DetailsRequest();
        detailsRequest.setUsername("user1");
        detailsRequest.setPassword("password");
        assertThrows(UserNotFoundException.class, ()->userService.deleteUserByUsername(detailsRequest));
    }
    @Test
    void createToDo_testToDoIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTaskName("task name");
        taskRequest.setDueDate("12/12/2024");
        userService.createTask(taskRequest);
        assertEquals(1,userService.countTasks(taskRequest.getUsername()));
    }
    @Test
    void registerWithInvaliDetails_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("");
        assertThrows(IncompleteDetailsException.class,()->userService.register(request));
        assertEquals(0, userService.count());
    }
    @Test
    void test(){}
    @Autowired
    private UserService userService;

}