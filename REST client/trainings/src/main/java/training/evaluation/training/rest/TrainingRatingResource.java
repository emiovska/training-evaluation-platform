package training.evaluation.training.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @GetMapping("/TrainingRatingsBy/{userId}")
    @ApiOperation(value = "Find training ratings by userId", notes = "Find training ratings by user id. Return list of training ratings of the searched userId. User must be previously logged in (Baerer Authorization with JWT token needed).")
        public Iterable<TrainingRating> getAllTrainingRatingsByUserId(@ApiParam(value = "User id of the user that we search for as a path variable.", required = true) @PathVariable String userId) {
         return services.getAllTrainingRatingsByUserId(userId);
    }

    @PostMapping("/rateTraining/{id}/{rating}")
    @ApiOperation(value = "Rate training", notes = "Find training ratings by id and set rate value. User must be previously logged in (Baerer Authorization with JWT token needed).")
    public TrainingRating rateTraining(@ApiParam(value = "Training Ratings id of the object that we need to update rating", required = true) @PathVariable String id,@ApiParam(value = "Rating value that we need to set", required = true) @PathVariable int rating) {
        return services.rateTraining(id,rating);
    }
}
