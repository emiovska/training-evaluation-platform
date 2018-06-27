package training.evaluation.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WidgetController {

    @Autowired
    private TrainingRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Training createTraining(@Valid @RequestBody Training training) {
        repository.save(training);
        return training;
    }
}
