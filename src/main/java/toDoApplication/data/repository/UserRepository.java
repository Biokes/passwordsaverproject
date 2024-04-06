package toDoApplication.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import toDoApplication.data.models.User;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findByUsername(String username);
}
