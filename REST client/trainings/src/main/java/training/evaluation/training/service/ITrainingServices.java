package training.evaluation.training.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.TrainingRating;
import training.evaluation.training.model.TrainingRequest;

import java.util.List;


public interface ITrainingServices {

    ResponseEntity<Training> createTraining(Training training);
    ResponseEntity<List<Training>> getAllTrainings();
    ResponseEntity<String> deleteTraining(String id);
    ResponseEntity<Training> updateTraining(String id, Training training);
    ResponseEntity<List<Training>> findByNameStartingWith(String name);
    ResponseEntity<List<Training>> filterByLevel(String level);
    ResponseEntity<List<Training>> findByNameStartingWithAndLevel(String name, String level);
    ResponseEntity<Training> setTrainingPicture(MultipartFile multipart, String trainingName);
    ResponseEntity<String> getTrainingPicture(String trainingName);
    ResponseEntity<List<Training>> getAllTrainingsByUserLevel();

    //training request
    ResponseEntity<TrainingRequest> createTrainingRequest(TrainingRequest trainingRequest);
    ResponseEntity<TrainingRequest> approveTrainingRequest(String id);
    ResponseEntity<TrainingRequest> cancelTrainingRequest(String id);
    ResponseEntity<TrainingRequest> completeTrainingRequest(String id);
    ResponseEntity<List<TrainingRequest>> getAllTrainingRequests();

    //training rating
    ResponseEntity<Iterable<TrainingRating>> getAllTrainingRatings();
    ResponseEntity<Iterable<TrainingRating>> getAllTrainingRatingsByUserId(String userId);
    ResponseEntity<TrainingRating> rateTraining(String id, int rating);
    ResponseEntity<String> rate(String trainingRatingId);

}
