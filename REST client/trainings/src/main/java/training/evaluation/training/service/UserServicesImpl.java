package training.evaluation.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.UserRepository;

@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository repository;

    @Override
    public User register(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

}
