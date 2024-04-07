package todoApplication.cotroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.controller.ToDoController;
import toDoApplication.dtos.requests.RegisterRequest;

@SpringBootTest(classes= ToDoMain.class)
public class ToDoControllerTest{
    @Autowired
    private ToDoController controller;
    @Test
    void register_testUserIsRegistered(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("name");
        request.setPassword("password");
        controller.register(request);

    }

}
