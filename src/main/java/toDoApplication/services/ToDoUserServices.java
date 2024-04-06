package toDoApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toDoApplication.data.repository.UserRepository;

@Service
@AllArgsConstructor
public class ToDoUserServices implements UserService{

    private UserRepository userRepository;
}
