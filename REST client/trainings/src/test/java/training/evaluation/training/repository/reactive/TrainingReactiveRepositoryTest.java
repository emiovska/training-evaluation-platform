package training.evaluation.training.repository.reactive;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import training.evaluation.training.model.Training;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrainingReactiveRepositoryTest {

    @Autowired
    TrainingReactiveRepository trainingReactiveRepository;

    @Before
    public void setUp()throws Exception{
        trainingReactiveRepository.deleteAll().block();
    }

    @Test
    public void testTrainingSave() throws Exception{
        Training training = new Training();
        training.setName("Spring 5 Reactive Training");
        trainingReactiveRepository.save(training);

        Long count = trainingReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

}