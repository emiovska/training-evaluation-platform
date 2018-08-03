package training.evaluation.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainingRating")
public class TrainingRating {

    @Id
    private String id;
    private Id trainingId;
    private Id userId;
    private int rating;  //should be between 0-5
    private boolean cup;

    public TrainingRating() { }

    public TrainingRating(String id, Id trainingId, Id userId, int rating, boolean cup) {
        this.id = id;
        this.trainingId = trainingId;
        this.userId = userId;
        this.rating = rating;
        this.cup = cup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Id getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Id trainingId) {
        this.trainingId = trainingId;
    }

    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isCup() {
        return cup;
    }

    public void setCup(boolean cup) {
        this.cup = cup;
    }
}
