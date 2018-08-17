package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.TrainingRating;

public interface TrainingRatingRepository extends MongoRepository<TrainingRating, String> {
}
