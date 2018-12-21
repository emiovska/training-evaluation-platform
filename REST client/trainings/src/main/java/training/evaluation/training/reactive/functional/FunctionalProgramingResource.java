package training.evaluation.training.reactive.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class FunctionalProgramingResource {

    @Autowired
    public TrainingHandler functionalProgramingHandler;

    @Bean
    public RouterFunction<ServerResponse> showTraining() {
        return route(GET("/reactive-training/{id}"), req -> ok().body(
            functionalProgramingHandler.showTraining(req.pathVariable("id")), ServerResponse.class));
    }
}
