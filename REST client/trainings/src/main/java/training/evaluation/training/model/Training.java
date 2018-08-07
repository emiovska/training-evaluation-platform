package training.evaluation.training.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trainings")
public class Training {
    @Id
    @Getter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String level;
    @Getter @Setter private String description;
    @Getter @Setter private Binary imageFile;
    @Getter @Setter private String trainer;
    @Getter @Setter private List<String> skills;

    public Training() {}

    public Training(String name, String level, String description, Binary imageFile, String trainer, List<String> skills) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.imageFile = imageFile;
        this.trainer = trainer;
        this.skills = skills;
    }

    public Training(String name, String level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Level: " + level + ", Description: " + description;
    }
}
