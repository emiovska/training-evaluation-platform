package training.evaluation.training.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import training.evaluation.training.model.User;
import training.evaluation.training.service.IUserServices;
import training.evaluation.training.service.impl.UserServicesImpl;

import java.util.List;

@CrossOrigin(origins = {"${origins}"})
@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private IUserServices userServices;

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {
        return userServices.register(user);
    }

    @GetMapping("/allusers")
    public List<User> getAll() {
        return userServices.getAllUsers();
    }

    @GetMapping("/byUsername/{username}")
    public User getByUsername(@PathVariable String username) {
        return userServices.getByUsername(username);
    }
}
