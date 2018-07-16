package training.evaluation.training.service;

import training.evaluation.training.model.User;

import java.util.List;

public interface IUserServices {

    User register(User user);
    List<User> getAllUsers();
}
