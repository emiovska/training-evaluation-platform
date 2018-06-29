package training.evaluation.training.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training.evaluation.training.repository.TrainingRepository;
import training.evaluation.training.model.Training;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
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
    public ResponseEntity<String> delete(@PathVariable String id) {
        Optional<Training> training = repository.findById(id);
        if(training.isPresent()) {
            repository.delete(training.get());
            return new ResponseEntity<>("Training deleted", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/training/{id}")
    public ResponseEntity<Training> update(@PathVariable String id, @RequestBody Training training) {
        Optional<Training> trainingData = repository.findById(id);

        if(trainingData.isPresent())
        {
            Training tr = trainingData.get();
            tr.setName(training.getName());
            tr.setLevel(training.getLevel());
            tr.setDescription(training.getDescription());
            return new ResponseEntity<>(repository.save(tr), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}