package repo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.repository.UserRepository;

@SpringBootTest
public class UserTest{
    @Autowired
    private UserRepository userRepository;
    @Test
    public void createAndSaveUser_testUserIsCreatedAndSaved(){

    }
}
