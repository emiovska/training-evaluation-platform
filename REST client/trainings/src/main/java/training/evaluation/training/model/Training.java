package training.evaluation.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainings")
public class Training {
    @Id
    private String id;
    private String name;
    private String level;
    private String description;

    public Training() {}

    public Training(String name, String level, String description) {
        this.name = name;
        this.level = level;
        this.description = description;
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

    @Override
    public String toString() {
        return "Name: " + name + ", Level: " + level + ", Description: " + description;
    }
}
