package todoApplication.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.dtos.requests.*;
import toDoApplication.dtos.response.ViewTaskResponse;
import toDoApplication.exception.*;
import toDoApplication.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

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
        userService.deleteUser(detailsRequest);
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
        assertThrows(UserNotFoundException.class, ()->userService.deleteUser(detailsRequest));
        detailsRequest.setUsername("username");
        detailsRequest.setPassword("password101,.");
        assertEquals(1, userService.count());
        detailsRequest.setUsername("username");
        assertThrows(UserNotFoundException.class, ()->userService.deleteUser(detailsRequest));
        assertEquals(1, userService.count());
        detailsRequest.setPassword("password");
        userService.deleteUser(detailsRequest);
        assertEquals(0, userService.count());
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
    void registerWithInvalidDetails_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("");
        assertThrows(IncompleteDetailsException.class,()->userService.register(request));
        assertEquals(0, userService.count());
    }
    @Test
    void createTaskWithElapsedDateAndInvalidDate_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTaskName("task name");
        taskRequest.setDueDate("12/12/202");
        assertThrows(InvalidDateException.class, ()->userService.createTask(taskRequest));
        assertEquals(0,userService.countTasks(taskRequest.getUsername()));
        taskRequest.setDueDate("12/12/2022");
        assertThrows(ElapsedDateException.class, ()->userService.createTask(taskRequest));
        taskRequest.setDueDate("12/12/2024");
        userService.createTask(taskRequest);
        assertEquals(1, userService.countTasks(taskRequest.getUsername()));
    }
    @Test
    void completeTask_testTaskIsCompleted(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTaskName("fishing");
        taskRequest.setDueDate("12/12/2024");
        userService.createTask(taskRequest);
        assertEquals(1, userService.countTasks(taskRequest.getUsername()));
        CompleteRequest completeRequest = new CompleteRequest();
        completeRequest.setUsername("username");
        completeRequest.setTaskName("fishing12");
        assertThrows(TaskDoesNotExistException.class,()->userService.completeTask(completeRequest));
        completeRequest.setUsername("username12");
        completeRequest.setTaskName("fishing");
        assertThrows(UserNotFoundException.class,()->userService.completeTask(completeRequest));
        completeRequest.setUsername("username");
        completeRequest.setTaskName("fishing");
        userService.completeTask(completeRequest);
        assertTrue(userService.isTaskCompleted(completeRequest));
    }
    @Test
    void viewAllTask_testAllTAskIsViewed(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTaskName("fishing");
        taskRequest.setDueDate("12/12/2024");
        userService.createTask(taskRequest);
        DetailsRequest detailsRequest = new DetailsRequest();
        detailsRequest.setUsername("username");
        detailsRequest.setPassword("password");
        ViewTaskResponse expected = userService.viewAllTasks(detailsRequest);
        assertEquals(String.format("Task Name : %s\nDue Date : %s\nStatus : %s\n",
                        "fishing","12/12/2024","NOT_COMPLETED"),
                expected.getBody());
        CompleteRequest completeRequest = new CompleteRequest();
        completeRequest.setUsername("username");
        completeRequest.setTaskName("fishing");
        userService.completeTask(completeRequest);
        expected = userService.viewAllTasks(detailsRequest);
        assertEquals(String.format("Task Name : %s\nDue Date : %s\nStatus : %s\n",
                        "fishing","12/12/2024","COMPLETED"),
                expected.getBody());

    }
    @Test
    void validateInput_testInputsAreValidated(){
        RegisterRequest  request = new RegisterRequest();
        request.setUsername("user101");
        request.setPassword("");
        assertThrows(IncompleteDetailsException.class,()->userService.register(request));
        request.setPassword("password");
        userService.register(request);
        assertThrows(UsernameTakenException.class,()->userService.register(request));
        DetailsRequest details = new DetailsRequest();
        assertThrows(IncompleteDetailsException.class,()->userService.deleteUser(details));
        details.setPassword("password");
        assertThrows(IncompleteDetailsException.class,()->userService.deleteUser(details));
        details.setUsername("user101");
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUsername("username");
        taskRequest.setTaskName("fishing");
        taskRequest.setDueDate("12/12/2024");
        assertThrows(UserNotFoundException.class,()->userService.createTask(taskRequest));
        taskRequest.setUsername("user101");
        userService.createTask(taskRequest);
        assertEquals(1, userService.countTasks("user101"));
        assertThrows(UserNotFoundException.class,()->userService.countTasks("user10"));
        userService.deleteUser(details);
        assertThrows(UserNotFoundException.class,()->userService.countTasks("user101"));
    }
    @Autowired
    private UserService userService;

}