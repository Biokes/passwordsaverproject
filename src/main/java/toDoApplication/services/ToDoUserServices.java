package toDoApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toDoApplication.data.repository.UserRepository;

@Service
public class ToDoUserServices implements UserService{
    @Autowired
    private UserRepository userRepository;
}
