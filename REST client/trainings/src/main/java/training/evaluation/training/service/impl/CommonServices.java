package training.evaluation.training.service.impl;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import training.evaluation.training.model.Training;
import training.evaluation.training.model.User;
import training.evaluation.training.repository.TrainingRepository;
import training.evaluation.training.repository.UserRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Repository
public class CommonServices {

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

    public String retrieveUserPicture(String username) {
        User user = userRepository.findByUsername(username);
        Binary document = user.getPicture();
        return commonRetrieve("user", document, username);
    }


    public String retrieveTrainingPicture(String trainingName) {
        Training training = trainingRepository.findByName(trainingName).get(0);
        Binary document = training.getPicture();
        return  commonRetrieve("training", document, trainingName);
    }

    public String commonRetrieve(String type, Binary document, String name) {
        String path = "C://Users//"+System.getProperty("user.name")+"//Downloads"+"//temp";
        File folder = new File(path);
        if (document != null) {
            FileOutputStream fileOuputStream = null;
            try {
                createIfNotExist(folder);
                if (type.equals("user")) {
                    folder = new File(path+"//user");
                    if (!createIfNotExist(folder)) {
                        if (folder.isDirectory()) {
                            for (File f : folder.listFiles()) {
                                f.delete();
                            }
                        }
                    }
                } else if (type.equals("training")) {
                    folder = new File(path + "//training");
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

        return path;
    }

    public boolean createIfNotExist(File folder) {
        if (!folder.exists()) {
            folder.mkdir();
            return true;
        }
        return false;
    }

    public String getType(User user){
        return user.getType();
    }
}
