package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.Training;

import java.util.List;

public interface TrainingRepository  extends MongoRepository<Training, String> {
    Training findByName(String name);
    List<Training> findByLevel(String level);
    Training findByDescription(String description);
}
