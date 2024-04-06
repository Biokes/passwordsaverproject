package toDoApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toDoApplication.data.models.User;
import toDoApplication.data.repository.UserRepository;
import toDoApplication.dtos.requests.DetailsRequest;
import toDoApplication.dtos.requests.RegisterRequest;
import toDoApplication.exception.UserNotFoundException;
import toDoApplication.utils.Mappers;

import java.util.List;

@Service
public class ToDoUserServices implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public long count(){
        return 0;
    }

    @Override
    public void register(RegisterRequest request){
        User user = Mappers.mapRequestToUser(request);
        save(user);
    }

    @Override
    public void deleteUserByUsername(DetailsRequest detailsRequest){
        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equalsIgnoreCase(detailsRequest.getUsername()) &&
            user.getPassword().equalsIgnoreCase(detailsRequest.getPassword())){
                userRepository.delete(user);
                return;
            }
        }
        throw new UserNotFoundException();
    }
}
