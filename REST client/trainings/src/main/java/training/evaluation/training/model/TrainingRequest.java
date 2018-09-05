package training.evaluation.training.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static training.evaluation.training.model.constants.Status.PENDING;

@Document(collection = "trainingRequest")
public class TrainingRequest {

    @Id
    @Getter private String id;
    @Getter @Setter private String trainingId;
    @Getter @Setter private String userId;
    @Getter @Setter private boolean isCompleted;
    @Getter @Setter private String status=PENDING;          // APPROVED, PENDING, CANCELED

    public TrainingRequest() { }

    public TrainingRequest(String trainingId, String userId, boolean isCompleted, String status) {
        this.trainingId = trainingId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TrainingRequest{" +
                "Id='" + id + '\'' +
                ", trainingId='" + trainingId + '\'' +
                ", userId='" + userId + '\'' +
                ", isCompleted=" + isCompleted +
                ", status='" + status + '\'' +
                '}';
    }
}
