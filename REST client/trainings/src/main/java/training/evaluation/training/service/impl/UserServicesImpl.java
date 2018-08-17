package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.service.IUserServices;

import java.util.List;


@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommonServices functions;

    public ResponseEntity<User> register(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> getByUsername(String username) {
        return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
    }

    public ResponseEntity<User> setProfilePicture(MultipartFile multipart, String username) {
        return new ResponseEntity<>(functions.uploadUserPicture(multipart,username), HttpStatus.OK);
    }

    public ResponseEntity<String> getProfilePicture(String username) {
        return new ResponseEntity<>(functions.retrieveUserPicture(username), HttpStatus.OK);

    }

}
