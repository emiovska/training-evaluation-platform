package training.evaluation.training.service.reactive.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import training.evaluation.training.model.Training;
import training.evaluation.training.service.reactive.TrainingReactiveService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrainingReactiveServiceImplTest {

    @Autowired
    private TrainingReactiveService trainingReactiveService;

    @Before
    public void setUp()throws Exception{
        System.out.println("Set up");
    }

    @Test
    public void testTrainingSave() throws Exception{
        Training training = new Training();
        training.setName("Spring 5 Reactive Training");
        System.out.println(trainingReactiveService.listAllTrainings());
    }

}