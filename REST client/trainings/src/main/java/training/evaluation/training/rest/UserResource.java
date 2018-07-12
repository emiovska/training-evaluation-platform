package training.evaluation.training.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import training.evaluation.training.model.User;
import training.evaluation.training.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    IUserServices services;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User registerUser(@Valid @RequestBody User user) {
        return services.register(user);
    }

}
