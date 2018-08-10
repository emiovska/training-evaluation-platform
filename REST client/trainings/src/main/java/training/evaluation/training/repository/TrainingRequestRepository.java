package training.evaluation.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import training.evaluation.training.model.TrainingRequest;

import java.util.Optional;


public interface TrainingRequestRepository extends MongoRepository<TrainingRequest, String> {
    //TrainingRequest findAllById(String id);

    Optional<TrainingRequest> findById(String id);


}
