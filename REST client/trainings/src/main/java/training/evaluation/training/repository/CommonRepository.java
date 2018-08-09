package training.evaluation.training.repository;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Repository
public class CommonRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    public User uploadUserPicture(MultipartFile multipart, String identificator) {
        User userRecord = userRepository.findByUsername(identificator);
        userRecord.setPicture(multipartToBinary(multipart));
        userRepository.save(userRecord);
        return userRecord;
    }

    public Training uploadTrainingPicture(MultipartFile multipart, String identificator) {
        Training trainingRecord = trainingRepository.findByName(identificator).get(0);
        trainingRecord.setPicture(multipartToBinary(multipart));
        trainingRepository.save(trainingRecord);
        return trainingRecord;
    }

    public Binary multipartToBinary(MultipartFile multipart) {
        try {
            Binary binaryPic = new Binary(BsonBinarySubType.BINARY, multipart.getBytes());
            return binaryPic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User retrieveUserPicture(String username) {
        User user = userRepository.findByUsername(username);
        Binary document = user.getPicture();
        commonRetrieve("user", document, username);
        return user;
    }


    public Training retrieveTrainingPicture(String trainingName) {
        Training training = trainingRepository.findByName(trainingName).get(0);
        Binary document = training.getPicture();
        commonRetrieve("training", document, trainingName);
        return training;
    }

    public void commonRetrieve(String type, Binary document, String name) {
        File folder = new File("temp");
        if (document != null) {
            FileOutputStream fileOuputStream = null;
            try {
                createIfNotExist(folder);
                if (type.equals("user")) {
                    folder = new File("temp//user");
                    if (!createIfNotExist(folder)) {
                        if (folder.isDirectory()) {
                            for (File f : folder.listFiles()) {
                                f.delete();
                            }
                        }
                    }
                } else if (type.equals("training")) {
                    folder = new File("temp//training");
                    createIfNotExist(folder);
                }
                fileOuputStream = new FileOutputStream(folder.getPath() + "//" + name + "_picture.jpg");
                fileOuputStream.write(document.getData());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileOuputStream != null) {
                    try {
                        fileOuputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean createIfNotExist(File folder) {
        if (!folder.exists()) {
            folder.mkdir();
            return true;
        }
        return false;
    }
}
