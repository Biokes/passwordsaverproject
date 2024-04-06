package todoApplication.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.data.models.User;
import toDoApplication.data.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ToDoMain.class )
class ToDoUserServicesTest{
@Autowired
private UserRepository userRepository;
    @Test
    void saveUser_testUserIsSaved(){
        User user = new User();
        assertEquals(0,userRepository.count());
        userRepository.save(user);
        assertEquals(1,userRepository.count());
    }
    @AfterEach
    void wipe(){
        userRepository.deleteAll();
    }
}