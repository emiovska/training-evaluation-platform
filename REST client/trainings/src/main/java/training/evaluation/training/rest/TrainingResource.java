package training.evaluation.training.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(method=RequestMethod.DELETE, value="/training/{id}")
    public String delete(@PathVariable String id) {
        Training training = repository.findById(id).get();
        repository.delete(training);

        return "Product deleted";
    }
}
