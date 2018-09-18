package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.*;
import training.evaluation.training.repository.*;
import training.evaluation.training.service.ITrainingServices;

import java.util.*;

import static training.evaluation.training.model.constants.Levels.*;
import static training.evaluation.training.model.constants.Roles.*;
import static training.evaluation.training.model.constants.Status.*;

@org.springframework.stereotype.Service
public class TrainingServicesImpl implements ITrainingServices {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingRequestRepository trainingRequestRepository;

    @Autowired
    private TrainingRatingRepository trainingRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommonServices commonServices;


    public ResponseEntity<Training> createTraining(Training training) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        String username = commonServices.getUsernameFromLoggedUser(CommonServices.token);
        if (role.equals(ADMIN) || role.equals(TRAINER)) {
            training.setTrainer(username);
            trainingRepository.save(training);
            return new ResponseEntity<>(training, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    public ResponseEntity<List<Training>> getAllTrainings() {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        if (role.equals(ADMIN) || role.equals(TRAINER)) {
            return new ResponseEntity<>(trainingRepository.findAll(), HttpStatus.OK);
        } else if (role.equals(USER)) {
            return getAllTrainingsByUserLevel();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<String> deleteTraining(String id) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        String username = commonServices.getUsernameFromLoggedUser(CommonServices.token);
        Optional<Training> training = trainingRepository.findById(id);

        if (role.equals(ADMIN) || (role.equals(TRAINER) && training.get().getTrainer().equals(username))) {

            if (training.isPresent()) {
                trainingRepository.delete(training.get());
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
        Optional<Training> trainingData = trainingRepository.findById(id);
        String trainer;

        if ((role.equals(ADMIN)) || (role.equals(TRAINER) && trainingData.get().getTrainer().equals(username))) {
            if (trainingData.isPresent()) {
                Training tr = trainingData.get();
                tr.setName(training.getName());
                tr.setLevel(training.getLevel());
                tr.setDescription(training.getDescription());
                tr.setTrainer(training.getTrainer());
                tr.setSkills(training.getSkills());
                if (training.getTrainer() != null) {
                    trainer = training.getTrainer();
                } else {
                    trainer = username;
                }

                tr.setTrainer(trainer);
                return new ResponseEntity<>(trainingRepository.save(tr), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    public ResponseEntity<List<Training>> findByNameStartingWith(String name) {
        return new ResponseEntity<>(trainingRepository.findByNameStartingWith(name.toUpperCase()), HttpStatus.OK);
    }

    public ResponseEntity<List<Training>> filterByLevel(String level) {
        return new ResponseEntity<>(trainingRepository.findByLevel(level), HttpStatus.OK);
    }

    public ResponseEntity<List<Training>> findByNameStartingWithAndLevel(String name, String level) {
        return new ResponseEntity<>(trainingRepository.findByNameStartingWithAndLevel(name, level), HttpStatus.OK);
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
        listOfTraining = trainingRepository.findByLevel(level);

        if (level.equals(SE)) {
            listOfTraining.addAll(trainingRepository.findByLevel(JSE));

        } else if (level.equals(SSE)) {
            listOfTraining.addAll(trainingRepository.findByLevel(SE));
            listOfTraining.addAll(trainingRepository.findByLevel(JSE));

        } else if (level.equals(TL)) {
            listOfTraining.addAll(trainingRepository.findByLevel(SSE));
            listOfTraining.addAll(trainingRepository.findByLevel(SE));
            listOfTraining.addAll(trainingRepository.findByLevel(JSE));

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
            else {
                //return message if not status is not approved
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(trainingRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TrainingRequest.TrainingRequestResponse>> getAllTrainingRequests() {
        List<TrainingRequest.TrainingRequestResponse> trainingRequestResponses = commonServices.getTrainingRequestResponse(trainingRequestRepository.findAll());
        return new ResponseEntity<>(trainingRequestResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TrainingRequest.TrainingRequestResponse>> getAllApprovedTrainingRequests() {
        System.out.println("getAllApprovedTrainingRequests");

        System.out.println("getTrainingRequest");
        for (TrainingRequest tr : trainingRequestRepository.findByStatus(APPROVED) )
            System.out.println(tr.getStatus() + "         ");

        System.out.println("getTrainingRequestResponse");
        for (TrainingRequest.TrainingRequestResponse tr : commonServices.getTrainingRequestResponse(trainingRequestRepository.findByStatus(APPROVED) ))
        System.out.println(tr.getStatus() + "         ");
        List<TrainingRequest.TrainingRequestResponse> trainingRequestResponses = commonServices.getTrainingRequestResponse(trainingRequestRepository.findByStatus(APPROVED));
        return new ResponseEntity<>(trainingRequestResponses, HttpStatus.OK);
    }

    public ResponseEntity<TrainingRequest.TrainingRequestResponse> getByTrainingRequestId(String id) {
        TrainingRequest request=trainingRequestRepository.findById(id).get();
        TrainingRequest.TrainingRequestResponse response = commonServices.getResponseFromTrainingRequest(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Iterable<TrainingRating.TrainingRatingResponse>> getAllTrainingRatings() {
        List<TrainingRating.TrainingRatingResponse> trainingRatingResponses = commonServices.getTrainingRatingResponse(trainingRatingRepository.findAll());
        return new ResponseEntity<>(trainingRatingResponses,HttpStatus.OK);
    }

    public ResponseEntity<Iterable<TrainingRating.TrainingRatingResponse>> getAllTrainingRatingsByUserId(String userId) {
        List<TrainingRating.TrainingRatingResponse> trainingRatingResponses = commonServices.getTrainingRatingResponse(trainingRatingRepository.findByUserId(userId));
        return new ResponseEntity<>(trainingRatingResponses,HttpStatus.OK);
    }

    public ResponseEntity<TrainingRating> rateTraining(String id, int rating) {
        String role = commonServices.getRoleFromLoggedUser(CommonServices.token);
        if (role.equals(TRAINER)) {

            Optional<TrainingRating> trainingRating = trainingRatingRepository.findById(id);
            if (trainingRating.isPresent()) {
                trainingRating.get().setRating(rating);
                if (rating >= 4)
                    trainingRating.get().setCup(true);
                trainingRatingRepository.save(trainingRating.get());
                return new ResponseEntity<>(trainingRating.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<String> rate(String trainingRatingId) {
        Optional<TrainingRating> trainingRating = trainingRatingRepository.findById(trainingRatingId);
        if (trainingRating.isPresent()) {
            trainingRating.get().setDone(true);
            trainingRatingRepository.save(trainingRating.get());
            String trainingId = trainingRating.get().getTrainingId();
            Training training = trainingRepository.findById(trainingId).get();
            List<String> skills = training.getSkills();
            String userId = trainingRating.get().getUserId();
            User user = userRepository.findById(userId).get();
            user.setSkills(skills);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
