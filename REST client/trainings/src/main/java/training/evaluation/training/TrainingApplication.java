package training.evaluation.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import training.evaluation.training.repository.TrainingRepository;

@SpringBootApplication
public class TrainingApplication {


    @Autowired
    private TrainingRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }

    /*@Override
    public void run(String... args){
    }*/
}
