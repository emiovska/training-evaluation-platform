package training.evaluation.training.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import training.evaluation.training.model.Training;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrainingRepositoryTest {
    @Autowired
    TrainingRepository trainingRepository;

    @Before
    public void setUp()throws Exception{
        trainingRepository.deleteAll();
    }

    @Test
    public void testTrainingSave() throws Exception{
        Training training = new Training();
        training.setName("Spring 5 Reactive Training");
        trainingRepository.save(training);

        Long count = trainingRepository.count();
        assertEquals(Long.valueOf(1L), count);
    }
}