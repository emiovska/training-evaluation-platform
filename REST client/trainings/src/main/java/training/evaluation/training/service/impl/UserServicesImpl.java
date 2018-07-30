package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import training.evaluation.training.model.Users;
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
    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
