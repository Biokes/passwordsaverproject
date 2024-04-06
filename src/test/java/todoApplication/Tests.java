package todoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoApplication.services.UserService;

@SpringBootTest
public class Tests{
    @Autowired
    private UserService userRepository;

}
