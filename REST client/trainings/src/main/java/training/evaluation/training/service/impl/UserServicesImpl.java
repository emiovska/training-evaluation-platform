package training.evaluation.training.service.impl;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.service.IUserServices;

import java.util.List;


@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

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
        User user = userRepository.findByUsername(username);
        try {
            Binary binaryPic = new Binary(BsonBinarySubType.BINARY, multipart.getBytes());
            user.setPicture(binaryPic);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
