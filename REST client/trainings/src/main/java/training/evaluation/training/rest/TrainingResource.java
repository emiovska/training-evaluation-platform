package training.evaluation.training.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import training.evaluation.training.repository.TrainingRepository;
import training.evaluation.training.model.Training;

import javax.validation.Valid;

@RestController
public class TrainingResource {

    @Autowired
    private TrainingRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Training createTraining(@Valid @RequestBody Training training) {
        repository.save(training);
        return training;
    }

    @RequestMapping(value="/trainings", method=RequestMethod.GET)
    public Iterable<Training> trainings() {
        return repository.findAll();
    }
}
