package training.evaluation.training.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
public class User {

    @Id
    @Getter private String id;
    @Indexed(unique=true)
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String firstname;
    @Getter @Setter private String lastname;
    @Getter @Setter private String type;                            //ADMIN, TRAINER, USER
    @Getter @Setter private String level;                           // JSE, SE, SSE, TL
    @Getter @Setter private List<String> skills;
    @Getter @Setter private List<String> trainingRatings;
    @Getter @Setter private Binary picture;

    public User() {}

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
