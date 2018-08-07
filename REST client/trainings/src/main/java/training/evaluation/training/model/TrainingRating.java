package training.evaluation.training.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainingRating")
public class TrainingRating {

    @Id
    @Getter private String id;
    @Getter @Setter private Id trainingId;
    @Getter @Setter private Id userId;
    @Getter @Setter private int rating;  //should be between 0-5
    @Getter @Setter private boolean cup;

    public TrainingRating() { }

    public TrainingRating(String id, Id trainingId, Id userId, int rating, boolean cup) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.rating = rating;
        this.cup = cup;
    }
}
