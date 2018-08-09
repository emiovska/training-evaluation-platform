package training.evaluation.training.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;

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
    Training getTrainingPicture(String trainingName);
}
