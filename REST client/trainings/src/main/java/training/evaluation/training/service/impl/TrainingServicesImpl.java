package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import training.evaluation.training.model.Training;
import training.evaluation.training.repository.TrainingRepository;
import training.evaluation.training.service.ITrainingServices;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class TrainingServicesImpl  implements ITrainingServices {

    @Autowired
    private TrainingRepository repository;

    public Training createTraining(Training training) {
        repository.save(training);
        return training;
    }

    public Iterable<Training> getAllTrainings() {
        return repository.findAll();
    }

    public ResponseEntity<String> deleteTraining(String id) {
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

    public ResponseEntity<Training> updateTraining(String id, Training training) {
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


    public List<Training> findByNameStartingWith(String name) {
        return repository.findByNameStartingWith(name);
    }


    public List<Training> filterByLevel(String level) {
        return repository.findByLevel(level);
    }


    public List<Training> findByNameStartingWithAndLevel(String name, String level){
        return repository.findByNameStartingWithAndLevel(name,level);
    }
}
