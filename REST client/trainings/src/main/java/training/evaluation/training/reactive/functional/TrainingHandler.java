package training.evaluation.training.reactive.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import training.evaluation.training.model.Training;
import training.evaluation.training.repository.reactive.TrainingReactiveRepository;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Configuration
public class TrainingHandler {
    @Autowired
    TrainingReactiveRepository trainingReactiveRepository;

    public Mono<ServerResponse> showTraining(String id) {
        System.out.println("ID "+ id);
        Mono<Training> monoTraining =  this.trainingReactiveRepository.findById(id);

        return this.trainingReactiveRepository.findById(id).flatMap(training -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(training)))
                .switchIfEmpty(Mono.defer(() -> ServerResponse.notFound().build()));

    }

}
