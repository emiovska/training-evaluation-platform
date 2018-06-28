package training.evaluation.training.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import training.evaluation.training.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository  extends CrudRepository<Training,String>, MongoRepository<Training, String> {
    Training findByName(String name);
    List<Training> findByLevel(String level);
}
