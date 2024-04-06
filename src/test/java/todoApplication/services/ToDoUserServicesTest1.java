package todoApplication.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.exception.InvalidUsernameException;
import toDoApplication.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes= ToDoMain.class)
class ToDoUserServicesTest{
@Autowired
    private UserService userService;
@Test
    void createUser_testUserIsCreated(){
        RegisterRequest request = new RegisterRequest();
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
        assertThrows(InvalidUsernameException.class, ()->userService.deleteUserByUsername(detailsRequest));
    }
}