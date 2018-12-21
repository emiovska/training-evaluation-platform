package training.evaluation.training.service.reactive;

import reactor.core.publisher.Flux;
import training.evaluation.training.model.Training;

public interface TrainingReactiveService {
    Flux<Training> listAllTrainings();
}
