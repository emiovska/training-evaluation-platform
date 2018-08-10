package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.CommonRepository;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.service.IUserServices;

import java.util.List;


@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommonRepository functions;

    @Override
    public User register(User user) {
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

    @Override
    public User setProfilePicture(MultipartFile multipart, String username) {
        return functions.uploadUserPicture(multipart,username);
    }

    @Override
    public String getProfilePicture(String username) {
        return functions.retrieveUserPicture(username);

    }

}
