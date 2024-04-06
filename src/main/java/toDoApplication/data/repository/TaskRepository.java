package toDoApplication.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import toDoApplication.data.models.Task;

public interface TaskRepository extends MongoRepository<Task, String>{
}
