package toDoApplication.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.services.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/ToDo/")
public class ToDoController{
    private UserService userService;
    @RequestMapping("/Register")
    public void register(RegisterRequest request){

    }
}
