package todoApplication.cotroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import toDoApplication.ToDoMain;
import toDoApplication.controller.ToDoController;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.dtos.response.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes= ToDoMain.class)
public class ToDoControllerTest{
    @Autowired
    private ToDoController controller;
    @Test
    void register_testUserIsRegistered(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("name");
        request.setPassword("password");
//        ResponseEntity<RegisterResponse> response = new ResponseEntity<>();
//        assertEquals(response, controller.register(request));
    }

}
