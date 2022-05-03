package bll;

import be.Credential;
import be.Person;
import be.user.UserType;
import bll.util.BCrypt;
import dal.interfaces.IDatabaseFacade;

public class CredentialManager {

    private final IDatabaseFacade databaseFacade;
    private final String salt = BCrypt.gensalt(10);
    private Credential credential;

    public CredentialManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public Person loginCredential(String userName, String userPassword){
        if(checkCredential(userName, userPassword)){
            return databaseFacade.getPersonById(credential.getId());
        }else return null;
    }

    private boolean checkCredential(String userName, String userPassword){
        credential = databaseFacade.checkCredential(userName);
        return credential != null && BCrypt.checkpw(userPassword, credential.getPassword());
    }

    public String newHashPassword(String password){
        return BCrypt.hashpw(password, this.salt);
    }
}