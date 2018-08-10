package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.TrainingRating;
import training.evaluation.training.model.TrainingRequest;
import training.evaluation.training.repository.CommonRepository;
import training.evaluation.training.repository.TrainingRatingRepository;
import training.evaluation.training.repository.TrainingRepository;
import training.evaluation.training.repository.TrainingRequestRepository;
import training.evaluation.training.service.ITrainingServices;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class TrainingServicesImpl implements ITrainingServices {
    public static final String PENDING = "PENDING";
    public static final String APPROVED = "APPROVED";
    public static final String CANCELED = "CANCELED";

    @Autowired
    private TrainingRepository repository;

    @Autowired
    private TrainingRequestRepository trainingRequestRepository;

    @Autowired
    private TrainingRatingRepository trainingRatingRepository;


    @Autowired
    CommonRepository functions;

    public Training createTraining(Training training) {
        repository.save(training);
        return training;
    }

    public Iterable<Training> getAllTrainings() {
        return repository.findAll();
    }

    public ResponseEntity<String> deleteTraining(String id) {
        Optional<Training> training = repository.findById(id);
        if (training.isPresent()) {
            repository.delete(training.get());
            return new ResponseEntity<>("Training deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Training> updateTraining(String id, Training training) {
        Optional<Training> trainingData = repository.findById(id);

        if (trainingData.isPresent()) {
            Training tr = trainingData.get();
            tr.setName(training.getName());
            tr.setLevel(training.getLevel());
            tr.setDescription(training.getDescription());
            return new ResponseEntity<>(repository.save(tr), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public List<Training> findByNameStartingWith(String name) {
        return repository.findByNameStartingWith(name);
    }


    public List<Training> filterByLevel(String level) {
        return repository.findByLevel(level);
    }


    public List<Training> findByNameStartingWithAndLevel(String name, String level) {
        return repository.findByNameStartingWithAndLevel(name, level);
    }

    @Override
    public Training setTrainingPicture(MultipartFile multipart, String trainingName) {
        return functions.uploadTrainingPicture(multipart, trainingName);
    }

    @Override
    public String getTrainingPicture(String trainingName) {
        return functions.retrieveTrainingPicture(trainingName);
    }


    //training request
    @Override
    public TrainingRequest createTrainingRequest(TrainingRequest trainingRequest) {
        trainingRequest.setStatus(PENDING);
        trainingRequest.setCompleted(false);
        trainingRequestRepository.save(trainingRequest);
        return trainingRequest;
    }

    @Override
    public TrainingRequest approveTrainingRequest(String id) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).get();
        trainingRequest.setStatus(APPROVED);
        trainingRequestRepository.save(trainingRequest);
        return trainingRequest;
    }

    @Override
    public TrainingRequest cancelTrainingRequest(String id) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).get();
        trainingRequest.setStatus(CANCELED);
        trainingRequestRepository.save(trainingRequest);
        return trainingRequest;
    }

    @Override
    public TrainingRequest completeTrainingRequest(String id) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).get();
        if (trainingRequest.getStatus().equals(APPROVED))
        {
            trainingRequest.setCompleted(true);
            trainingRequestRepository.save(trainingRequest);

            TrainingRating trainingRating = new TrainingRating(trainingRequest.getTrainingId(), trainingRequest.getUserId(), 0, false);
            trainingRatingRepository.save(trainingRating);
        }
        return trainingRequest;
    }

}
