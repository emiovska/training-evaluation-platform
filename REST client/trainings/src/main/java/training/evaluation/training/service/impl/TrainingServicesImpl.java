package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.*;
import training.evaluation.training.repository.*;
import training.evaluation.training.security.JwtTokenUtil;
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
    private UserRepository userRepository;

    @Autowired
    private TrainingRequestRepository trainingRequestRepository;

    @Autowired
    private TrainingRatingRepository trainingRatingRepository;


    @Autowired
    CommonRepository functions;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @Override
    public List<Training> getAllTrainingsByUserLevel(String authorizationValue) {
        List<Training> listOfTraining = null;
        if (authorizationValue != null || authorizationValue.startsWith("Bearer ")) {

            String token = authorizationValue.substring(14);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username);
            String level = user.getLevel();

            //add to list lower levels
            listOfTraining = repository.findByLevel(level);
            if (level.equals(Levels.JSE)) {
                return listOfTraining;

            } else if (level.equals(Levels.SE)) {
                listOfTraining.addAll(repository.findByLevel(Levels.JSE));

            } else if (level.equals(Levels.SSE)) {
                listOfTraining.addAll(repository.findByLevel(Levels.SE));
                listOfTraining.addAll(repository.findByLevel(Levels.JSE));

            } else if (level.equals(Levels.TL)) {
                listOfTraining.addAll(repository.findByLevel(Levels.SSE));
                listOfTraining.addAll(repository.findByLevel(Levels.SE));
                listOfTraining.addAll(repository.findByLevel(Levels.JSE));

            }


        }
        return listOfTraining;
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
        if (trainingRequest.getStatus().equals(APPROVED)) {
            trainingRequest.setCompleted(true);
            trainingRequestRepository.save(trainingRequest);

            TrainingRating trainingRating = new TrainingRating(trainingRequest.getTrainingId(), trainingRequest.getUserId(), 0, false);
            trainingRatingRepository.save(trainingRating);
        }
        return trainingRequest;
    }

    @Override
    public Iterable<TrainingRating> getAllTrainingRatings() {
        return trainingRatingRepository.findAll();
    }

    @Override
    public Iterable<TrainingRating> getAllTrainingRatingsByUserId(String userId) {
        return trainingRatingRepository.findByUserId(userId);
    }

    @Override
    public TrainingRating rateTraining(String id, int rating) {
        TrainingRating trainingRating = trainingRatingRepository.findById(id).get();
        trainingRating.setRating(rating);
        if (rating >= 4)
            trainingRating.setCup(true);
        return trainingRating;
    }

}
