package training.evaluation.training.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import training.evaluation.training.model.TrainingRating;
import training.evaluation.training.service.ITrainingServices;

@CrossOrigin(origins = {"${origins}"})
@RestController
@RequestMapping(value = "/trainingRating")
@Api(description = "Resource to expose all available training rating endpoints", tags = {"TrainingRating"})
public class TrainingRatingResource {

    @Autowired
    ITrainingServices services;

    @GetMapping("/all")
    @ApiOperation(value = "Get all training ratngs ", notes = "Return list of training ratings")
    public Iterable<TrainingRating> getAllTrainingRatings() {
        return services.getAllTrainingRatings();
    }
}
