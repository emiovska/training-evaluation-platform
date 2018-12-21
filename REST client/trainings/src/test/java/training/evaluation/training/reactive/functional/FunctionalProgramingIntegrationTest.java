package training.evaluation.training.reactive.functional;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import training.evaluation.training.TrainingApplication;
import training.evaluation.training.model.Training;
import training.evaluation.training.repository.reactive.TrainingReactiveRepository;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TrainingApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalProgramingIntegrationTest {

    @Autowired
    private FunctionalProgramingResource functionalProgramingResource;

    @MockBean
    private TrainingReactiveRepository trainingReactiveRepository;

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenCorrectEmployee() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(functionalProgramingResource.showTraining())
                .build();

        Training training = new Training("Functional Programming", "JE");

        given(trainingReactiveRepository.findById("1")).willReturn(Mono.just(training));

         client.get()
                .uri("/reactive-training/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Training.class)
                .isEqualTo(training);
    }

}