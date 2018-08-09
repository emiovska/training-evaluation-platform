package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.TrainingRequest;

public interface TrainingRequestRepository extends MongoRepository<TrainingRequest, String> {
}
