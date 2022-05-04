package bll;

import be.Credential;
import be.user.User;
import bll.util.BCrypt;
import dal.interfaces.IDatabaseFacade;
import javafx.scene.control.Alert;

public class CredentialManager {

    private final IDatabaseFacade databaseFacade;
    private final String salt = BCrypt.gensalt(10);
    private Credential credential;

    public CredentialManager(IDatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public User loginCredential(String userName, String userPassword){
        if(checkCredential(userName, userPassword)){
            return databaseFacade.getPersonById(credential.getId());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setContentText("Username or Password is wrong");
            alert.showAndWait();
        }
        return null;
    }

    private boolean checkCredential(String userName, String userPassword){
        credential = databaseFacade.checkCredential(userName);
        return credential != null && BCrypt.checkpw(userPassword, credential.getPassword());
    }

    public String newHashPassword(String password){
        return BCrypt.hashpw(password, this.salt);
    }
}
