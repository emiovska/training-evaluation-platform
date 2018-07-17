package training.evaluation.training.rest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import training.evaluation.training.model.Users;
import training.evaluation.training.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    IUserServices services;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Users registerUser(@Valid @RequestBody Users user) {
        return services.register(user);
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public @ResponseBody Iterable<Users> getAllUsers() {
        System.out.println("all users");
        return services.getAllUsers();
    }



}
