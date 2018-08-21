package training.evaluation.training.model.constants;

public class Roles {
    public static final String ADMIN = "ADMIN";        //read/update/delete users/trainings
    public static final String USER = "USER";          //read trainings from its level,update self profile
    public static final String TRAINER = "TRAINER";    //read users/trainings, create new, update/delete training created by him, update self profile, grade users who listened training
}
