package training.evaluation.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainingRequest")
public class TrainingRequest {

    @Id
    private String Id;
    private Id trainingId;
    private Id userId;
    private boolean isCompleted;
    private String status;          // APPROVED, PENDING, CANCELED

    public TrainingRequest() { }

    public TrainingRequest(String id, Id trainingId, Id userId, boolean isCompleted, String status) {
        Id = id;
        this.trainingId = trainingId;
        this.userId = userId;
        this.isCompleted = isCompleted;
        this.status = "PENDING";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
            this.status = status;
    }
}
