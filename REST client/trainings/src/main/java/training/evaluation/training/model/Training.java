package training.evaluation.training.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trainings")
public class Training {
    @Id
    private String id;
    private String name;
    private String level;
    private String description;

    private Binary imageFile;

    private String trainer;
    private List<String> skills;

    public Training() {
    }

    public Training(String id, String name, String level, String description, Binary imageFile, String trainer, List<String> skills) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptipon) {
        this.description = descriptipon;
    }

    public Binary getImageFile() {
        return imageFile;
    }

    public void setImageFile(Binary imageFile) {
        this.imageFile = imageFile;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Level: " + level + ", Description: " + description;
    }
}
