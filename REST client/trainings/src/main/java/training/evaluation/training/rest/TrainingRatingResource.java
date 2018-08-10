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
}
