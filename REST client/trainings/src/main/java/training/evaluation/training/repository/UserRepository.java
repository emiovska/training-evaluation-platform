package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
