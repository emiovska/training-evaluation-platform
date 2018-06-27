package training.evaluation.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingApplication implements CommandLineRunner {


    @Autowired
    private TrainingRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplication.class, args);
    }

    @Override
    public void run(String... args){

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Training("Training 1", "SE"));
        repository.save(new Training("TR2", "SSE"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Training customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByName('Training 1'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("Training 1"));

        System.out.println("Customers found with findByLevel('SSE'):");
        System.out.println("--------------------------------");
        for (Training training : repository.findByLevel("SSE")) {
            System.out.println(training);
        }

    }
}
