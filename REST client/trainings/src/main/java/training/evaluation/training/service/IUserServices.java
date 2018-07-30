package training.evaluation.training.service;

import training.evaluation.training.model.Users;

import java.util.List;

public interface IUserServices {

    Users register(Users user);

    List<Users> getAllUsers();

    Users getByUsername(String username);
}
