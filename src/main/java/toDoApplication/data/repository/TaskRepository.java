package toDoApplication.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import toDoApplication.data.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByTaskUser(String username);

    Optional<Task> findByTaskName(String taskName);
}
