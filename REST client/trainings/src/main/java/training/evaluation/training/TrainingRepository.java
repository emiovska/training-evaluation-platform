package training.evaluation.training;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrainingRepository  extends MongoRepository<Training, String> {
    Training findByName(String name);
    List<Training> findByLevel(String level);
}
