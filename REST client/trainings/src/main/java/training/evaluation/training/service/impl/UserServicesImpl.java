package training.evaluation.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.UserRepository;
import training.evaluation.training.service.IUserServices;

import static training.evaluation.training.model.constants.Roles.*;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class UserServicesImpl implements IUserServices {
    private String loggedUsername = CommonServices.loggedUsername;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommonServices commonServices;

    public ResponseEntity<User> register(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<User> update(String id, User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User usr = userData.get();
            usr.setUsername(user.getUsername());
            usr.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(usr), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listOfUsers = null;

        //get rights for role
        if (loggedUsername.equals(ADMIN)) {
            listOfUsers = userRepository.findAll();
        }
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    public ResponseEntity<User> getByUsername(String username) {
        return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
    }

    public ResponseEntity<User> setProfilePicture(MultipartFile multipart, String username) {
        return new ResponseEntity<>(commonServices.uploadUserPicture(multipart, username), HttpStatus.OK);
    }

    public ResponseEntity<String> getProfilePicture(String username) {
        return new ResponseEntity<>(commonServices.retrieveUserPicture(username), HttpStatus.OK);

    }

}
