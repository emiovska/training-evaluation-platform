package training.evaluation.training.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.TrainingRating;
import training.evaluation.training.model.TrainingRequest;

import java.util.List;


public interface ITrainingServices {

    Training createTraining(Training training);
    Iterable<Training> getAllTrainings();
    ResponseEntity<String> deleteTraining(String id);
    ResponseEntity<Training> updateTraining(String id, Training training);
    List<Training> findByNameStartingWith(String name);
    List<Training> filterByLevel(String level);
    List<Training> findByNameStartingWithAndLevel(String name, String level);
    Training setTrainingPicture(MultipartFile multipart, String trainingName);
    String getTrainingPicture(String trainingName);
    List<Training> getAllTrainingsByUserLevel(String level);

    //training request
    TrainingRequest createTrainingRequest(TrainingRequest trainingRequest);
    TrainingRequest approveTrainingRequest(String id);
    TrainingRequest cancelTrainingRequest(String id);
    TrainingRequest completeTrainingRequest(String id);

    //training rating
    Iterable<TrainingRating> getAllTrainingRatings();
    Iterable<TrainingRating> getAllTrainingRatingsByUserId(String userId);
    TrainingRating rateTraining(String id, int rating);

}
