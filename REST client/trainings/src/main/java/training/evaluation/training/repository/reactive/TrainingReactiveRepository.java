package training.evaluation.training.repository.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import training.evaluation.training.model.Training;

@Component
public interface TrainingReactiveRepository extends ReactiveMongoRepository<Training, String> {

}
