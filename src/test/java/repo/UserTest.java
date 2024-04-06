package repo;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.ToDoMain;
import toDoApplication.data.models.User;
import toDoApplication.data.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest{
    @Autowired
    private UserRepository userRepository;
    @Test
    public void createAndSaveUser_testUserIsCreatedAndSaved(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1,userRepository.count());
    }
}
