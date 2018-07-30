package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.Users;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    UsersfindByUsername(String username);

    @Override
    List<User> findAll();
}
