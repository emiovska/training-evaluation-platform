package training.evaluation.training.service;

import training.evaluation.training.model.Users;


public interface IUserServices {

    Users register(Users user);
    Iterable<Users> getAllUsers();
}
