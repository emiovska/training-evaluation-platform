package training.evaluation.training.service.reactive.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import training.evaluation.training.model.Training;
import training.evaluation.training.repository.reactive.TrainingReactiveRepository;
import training.evaluation.training.service.reactive.TrainingReactiveService;

@Service
public class TrainingReactiveServiceImpl implements TrainingReactiveService {
    @Autowired
    TrainingReactiveRepository trainingReactiveRepository;

    @Override
    public Flux<Training> listAllTrainings() {
        return trainingReactiveRepository.findAll();
    }
}
