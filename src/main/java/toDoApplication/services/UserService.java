package toDoApplication.services;

import org.springframework.stereotype.Service;
import toDoApplication.data.models.User;
import toDoApplication.dtos.requests.RegisterRequest;

@Service
public interface UserService{

    void save(User user);

    long count();

    void register(RegisterRequest request);
}
