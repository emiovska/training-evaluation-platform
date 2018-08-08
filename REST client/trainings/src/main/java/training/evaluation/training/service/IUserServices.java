package training.evaluation.training.service;

import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.User;

import java.util.List;

public interface IUserServices {

    User register(User user);
    List<User> getAllUsers();
    User getByUsername(String username);
    User setProfilePicture(MultipartFile multipart,String username);
    User getProfilePicture(String username);

}