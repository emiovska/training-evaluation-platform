package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.*;
import training.evaluation.training.repository.*;
import training.evaluation.training.service.ITrainingServices;

import java.util.List;
import java.util.Optional;

import static training.evaluation.training.model.constants.Levels.*;
import static training.evaluation.training.model.constants.Roles.*;
import static training.evaluation.training.model.constants.Status.*;

@org.springframework.stereotype.Service
public class TrainingServicesImpl implements ITrainingServices {

    @Autowired
    private TrainingRepository repository;

    @Autowired
    private TrainingRequestRepository trainingRequestRepository;

    @Autowired
    private TrainingRatingRepository trainingRatingRepository;

    @Autowired
    CommonServices commonServices;


    public ResponseEntity<Training> createTraining(Training training) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        String username = commonServices.getUsernameFromLoggedUser(CommonServices.token);
        if (role.equals(TRAINER)) {
            training.setTrainer(username);
            repository.save(training);
            return new ResponseEntity<>(training, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    public ResponseEntity<List<Training>> getAllTrainings() {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        if (role.equals(ADMIN) || role.equals(TRAINER)) {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } else if (role.equals(USER)) {
            return getAllTrainingsByUserLevel();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<String> deleteTraining(String id) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        String username = commonServices.getUsernameFromLoggedUser(CommonServices.token);
        Optional<Training> training = repository.findById(id);

        if (role.equals(ADMIN) || (role.equals(TRAINER) && training.get().getTrainer().equals(username))) {

            if (training.isPresent()) {
                repository.delete(training.get());
                return new ResponseEntity<>("Training deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Training> updateTraining(String id, Training training) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        String username = commonServices.getUsernameFromLoggedUser(CommonServices.token);
        Optional<Training> trainingData = repository.findById(id);
        String trainer;

        if ((role.equals(ADMIN)) || (role.equals(TRAINER) && trainingData.get().getTrainer().equals(username))) {
            if (trainingData.isPresent()) {
                Training tr = trainingData.get();
                tr.setName(training.getName());
                tr.setLevel(training.getLevel());
                tr.setDescription(training.getDescription());

                if (training.getTrainer() != null) {
                    trainer = training.getTrainer();
                } else {
                    trainer = username;
                }

                tr.setTrainer(trainer);
                return new ResponseEntity<>(repository.save(tr), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    public ResponseEntity<List<Training>> findByNameStartingWith(String name) {
        return new ResponseEntity<>(repository.findByNameStartingWith(name), HttpStatus.OK);
    }

    public ResponseEntity<List<Training>> filterByLevel(String level) {
        return new ResponseEntity<>(repository.findByLevel(level), HttpStatus.OK);
    }

    public ResponseEntity<List<Training>> findByNameStartingWithAndLevel(String name, String level) {
        return new ResponseEntity<>(repository.findByNameStartingWithAndLevel(name, level), HttpStatus.OK);
    }

    public ResponseEntity<Training> setTrainingPicture(MultipartFile multipart, String trainingName) {
        return new ResponseEntity<>(commonServices.uploadTrainingPicture(multipart, trainingName), HttpStatus.OK);
    }

    public ResponseEntity<String> getTrainingPicture(String trainingName) {
        return new ResponseEntity<>(commonServices.retrieveTrainingPicture(trainingName), HttpStatus.OK);
    }

    public ResponseEntity<List<Training>> getAllTrainingsByUserLevel() {
        List<Training> listOfTraining = null;
        User user = commonServices.getUserFromToken(CommonServices.token);
        String level = user.getLevel();

        //add to list lower levels
        listOfTraining = repository.findByLevel(level);

        if (level.equals(SE)) {
            listOfTraining.addAll(repository.findByLevel(JSE));

        } else if (level.equals(SSE)) {
            listOfTraining.addAll(repository.findByLevel(SE));
            listOfTraining.addAll(repository.findByLevel(JSE));

        } else if (level.equals(TL)) {
            listOfTraining.addAll(repository.findByLevel(SSE));
            listOfTraining.addAll(repository.findByLevel(SE));
            listOfTraining.addAll(repository.findByLevel(JSE));

        }

        return new ResponseEntity<>(listOfTraining, HttpStatus.OK);
    }


    //training request
    public ResponseEntity<TrainingRequest> createTrainingRequest(TrainingRequest trainingRequest) {
        trainingRequest.setStatus(PENDING);
        trainingRequest.setCompleted(false);
        trainingRequestRepository.save(trainingRequest);
        return new ResponseEntity<>(trainingRequest, HttpStatus.OK);
    }

    public ResponseEntity<TrainingRequest> approveTrainingRequest(String id) {
        return changeStatus(id, APPROVED);
    }

    public ResponseEntity<TrainingRequest> cancelTrainingRequest(String id) {
        return changeStatus(id, CANCELED);
    }

    public ResponseEntity<TrainingRequest> changeStatus(String id, String status) {
        Optional<TrainingRequest> trainingRequest = trainingRequestRepository.findById(id);
        if (trainingRequest.isPresent()) {
            trainingRequest.get().setStatus(status);
            trainingRequestRepository.save(trainingRequest.get());
            return new ResponseEntity<>(trainingRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<TrainingRequest> completeTrainingRequest(String id) {
        Optional<TrainingRequest> trainingRequest = trainingRequestRepository.findById(id);
        if (trainingRequest.isPresent()) {
            if (trainingRequest.get().getStatus().equals(APPROVED)) {
                trainingRequest.get().setCompleted(true);
                trainingRequestRepository.save(trainingRequest.get());

                TrainingRating trainingRating = new TrainingRating(trainingRequest.get().getTrainingId(), trainingRequest.get().getUserId(), 0, false);
                trainingRatingRepository.save(trainingRating);
            }
            return new ResponseEntity<>(trainingRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<TrainingRating>> getAllTrainingRatings() {
        return new ResponseEntity<>(trainingRatingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<TrainingRating>> getAllTrainingRatingsByUserId(String userId) {
        return new ResponseEntity<>(trainingRatingRepository.findByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<TrainingRating> rateTraining(String id, int rating) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        if (role.equals(TRAINER)) {

            Optional<TrainingRating> trainingRating = trainingRatingRepository.findById(id);
            if (trainingRating.isPresent()) {
                trainingRating.get().setRating(rating);
                if (rating >= 4)
                    trainingRating.get().setCup(true);
                return new ResponseEntity<>(trainingRating.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
