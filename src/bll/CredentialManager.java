package bll;

import be.Person;
import be.user.UserType;
import bll.util.BCrypt;

public class CredentialManager {

    private String salt = BCrypt.gensalt(10);

    public CredentialManager() {
    }

    public Person loginCredential(String userName, String userPassword){
        if(checkCredential(userName, userPassword)){
            Person person = new Person("Tobias", "Rasmussen");
            person.setUserType(UserType.STUDENT);
            return person;
        }else return null;

    }

    private boolean checkCredential(String userName, String userPassword){
        boolean correctLogin = false;
        String databasePassword = "";
        correctLogin = BCrypt.checkpw(userPassword, databasePassword);
        return correctLogin;
    }

    public String newHashPassword(String password){
        return BCrypt.hashpw(password, this.salt);
    }
}
