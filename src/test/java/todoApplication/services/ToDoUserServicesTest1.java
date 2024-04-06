package todoApplication.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
}