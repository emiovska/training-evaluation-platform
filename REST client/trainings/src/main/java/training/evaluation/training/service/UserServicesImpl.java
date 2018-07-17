package training.evaluation.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.model.Users;

@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository repository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        return user;
    }

    @Override
    public Iterable<Users> getAllUsers() {
        return repository.findAll();
    }

}
