package training.evaluation.training.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.util.List;

@Document(collection = "users")
public class User {


    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    private List<String> skills;
    private List<TrainingRating> trainingRatings;
    private String type;                            //ADMIN, TRAINER, USER
    private String level;                           // JSE, SE, SSE, TL
    private Binary picture;

    public User() {
    }

    public User(ObjectId id, String username, String password, String firstname, String lastname, List<String> skills, List<TrainingRating> trainingRatings, String type, String level, Binary picture) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.skills = skills;
        this.trainingRatings = trainingRatings;
        this.type = type;
        this.level = level;
        this.picture = picture;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<TrainingRating> getTrainingRatings() {
        return trainingRatings;
    }

    public void setTrainingRatings(List<TrainingRating> trainingRatings) {
        this.trainingRatings = trainingRatings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Binary getPicture() {
        return picture;
    }
    public void setLevel(Binary picture) {
        this.picture = picture;
    }
}
