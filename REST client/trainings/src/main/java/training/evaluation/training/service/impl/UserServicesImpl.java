package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.service.IUserServices;

import java.util.List;


@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
