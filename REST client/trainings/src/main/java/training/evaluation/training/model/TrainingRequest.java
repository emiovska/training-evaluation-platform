package training.evaluation.training.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainingRequest")
public class TrainingRequest {

    @Id
    @Getter private String Id;
    @Getter @Setter private Id trainingId;
    @Getter @Setter private Id userId;
    @Getter @Setter private boolean isCompleted;
    @Getter @Setter private String status;          // APPROVED, PENDING, CANCELED

    public TrainingRequest() { }

    public TrainingRequest(String id, Id trainingId, Id userId, boolean isCompleted, String status) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.isCompleted = isCompleted;
        this.status = "PENDING";
    }
}
