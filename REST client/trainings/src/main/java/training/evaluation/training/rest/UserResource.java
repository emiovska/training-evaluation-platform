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

    @PostMapping("/register")
    public User signUp(@RequestBody User user) {
        System.out.println("Rest register");
        return userServices.register(user);
    }


    @GetMapping("/")
    public List<User> getAll() {
        return userServices.getAllUsers();
    }

    @GetMapping("/byUsername/{username}")
    public User getByUsername(@PathVariable String username) {
        System.out.println("By username "+username);
        return userServices.getByUsername(username);
    }
}
