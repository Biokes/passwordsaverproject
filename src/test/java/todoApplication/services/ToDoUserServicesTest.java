package todoApplication.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.dtos.requests.*;
import toDoApplication.dtos.response.CompleteTaskResponse;
import toDoApplication.dtos.response.ViewTaskResponse;
import toDoApplication.exception.*;
import toDoApplication.services.UserService;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static toDoApplication.data.models.TaskStatus.COMPLETED;

@SpringBootTest(classes = ToDoMain.class)
public class ToDoUserServicesTest{

    @Autowired
  private UserService userService;

    @BeforeEach
    public void setUp(){
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
        CreateTaskRequest createTaskRequest= new CreateTaskRequest();
        createTaskRequest.setUsername("username");
        createTaskRequest.setTaskName("task name");
        createTaskRequest.setDueDate("12/12/2024");
        userService.createTask(createTaskRequest);
        assertEquals(1,userService.countTasks(createTaskRequest.getUsername()));
    }
    @Test
    void registerWithInvalidDetails_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("");
        assertThrows(IncompleteDetailsException.class,()->userService.register(request));
        assertEquals(0, userService.count());
    }
//    @Test
//    void createTaskWithElapsedDateAndInvalidDate_testExceptionIsThrown(){
//        RegisterRequest request = new RegisterRequest();
//        request.setUsername("username");
//        request.setPassword("password");
//        userService.register(request);
//        CreateTaskRequest createTaskRequest= new CreateTaskRequest();
//        createTaskRequest.setUsername("username");
//        createTaskRequest.setTaskName("task name");
//        createTaskRequest.setDueDate("12/12/202");
//        assertThrows(InvalidDateException.class, ()->userService.createTask(createTaskRequest));
//        assertEquals(0,userService.countTasks(createTaskRequest.getUsername()));
//        createTaskRequest.setDueDate("12/12/2022");
//        assertThrows(ElapsedDateException.class, ()->userService.createTask(createTaskRequest));
//        createTaskRequest.setDueDate("12/12/2024");
//        userService.createTask(createTaskRequest);
//        assertEquals(1, userService.countTasks(createTaskRequest.getUsername()));
//    }
    @Test
    void completeTask_testTaskIsCompleted(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        CreateTaskRequest createTaskRequest= new CreateTaskRequest();
        createTaskRequest.setUsername("username");
        createTaskRequest.setTaskName("fishing");
        createTaskRequest.setDueDate("12/12/2024");
        userService.createTask(createTaskRequest);
        assertEquals(1, userService.countTasks(createTaskRequest.getUsername()));
        CompleteTaskRequest completeTaskRequest= new CompleteTaskRequest();
        completeTaskRequest.setUsername("username");
        completeTaskRequest.setTaskName("fishing12");
        assertThrows(TaskDoesNotExistException.class,()->userService.completeTask(completeTaskRequest));
        completeTaskRequest.setUsername("username12");
        completeTaskRequest.setTaskName("fishing");
        assertThrows(UserNotFoundException.class,()->userService.completeTask(completeTaskRequest));
        completeTaskRequest.setUsername("username");
        completeTaskRequest.setTaskName("fishing");
        userService.completeTask(completeTaskRequest);
        assertTrue(userService.isTaskCompleted(completeTaskRequest));
    }
    @Test
    void viewAllTask_testAllTaskIsViewed(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setPassword("password");
        userService.register(request);
        CreateTaskRequest createTaskRequest= new CreateTaskRequest();
        createTaskRequest.setUsername("username");
        createTaskRequest.setTaskName("fishing");
        createTaskRequest.setDueDate("12/12/2024");
        userService.createTask(createTaskRequest);
        DetailsRequest detailsRequest = new DetailsRequest();
        detailsRequest.setUsername("username");
        detailsRequest.setPassword("password");
        ViewTaskResponse expected = userService.viewAllTasks(detailsRequest);
        assertEquals(String.format("Task Name : %s\nDue Date : %s\nStatus : %s\n",
                        "fishing","12/12/2024","NOT_COMPLETED"),
                expected.getBody());
        CompleteTaskRequest completeTaskRequest= new CompleteTaskRequest();
        completeTaskRequest.setUsername("username");
        completeTaskRequest.setTaskName("fishing");
        userService.completeTask(completeTaskRequest);
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
        CreateTaskRequest createTaskRequest= new CreateTaskRequest();
        createTaskRequest.setUsername("username");
        createTaskRequest.setTaskName("fishing");
        createTaskRequest.setDueDate("12/12/2024");
        assertThrows(UserNotFoundException.class,()->userService.createTask(createTaskRequest));
        createTaskRequest.setUsername("user101");
        userService.createTask(createTaskRequest);
        assertEquals(1, userService.countTasks("user101"));
        assertThrows(UserNotFoundException.class,()->userService.countTasks("user10"));
        userService.deleteUser(details);
        assertThrows(UserNotFoundException.class,()->userService.countTasks("user101"));
    }
    @Test
    void completeTask_testTimeFrameIsRetured() throws InterruptedException{
        RegisterRequest request = new RegisterRequest();
        request.setUsername("abbey");
        request.setPassword("pasword");
        userService.register(request);
        CreateTaskRequest createTask = new CreateTaskRequest();
        createTask.setUsername("abbey");
        createTask.setTaskName("learn archery");
        userService.createTask(createTask);
        assertEquals(1, userService.countTasks("abbey"));
        CompleteTaskRequest completeTaskRequest = new CompleteTaskRequest();
        completeTaskRequest.setUsername("abbey");
        completeTaskRequest.setTaskName("learn archery");
        Thread.sleep(10000);
        CompleteTaskResponse response = userService.completeTask(completeTaskRequest);
        assertEquals("abbey", response.getUsername());
        assertEquals("learn archery", response.getTaskName());
        assertEquals(COMPLETED, response.getStatus());
        assertEquals("10 seconds",response .getDuration());
        assertEquals("23/12/2023", response.getDueDate().toString());

    }

}