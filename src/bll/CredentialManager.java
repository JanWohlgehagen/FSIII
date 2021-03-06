package bll;

import be.Credential;
import be.User;
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

    /**
     * loginCredential checks if Username and Password are correct, using the checkCredential method and then logs you in if they are.
     * If incorrect, the method returns a window with an alert that tells you either Username or Password is wrong.
     * @param userName the input form the user
     * @param userPassword the input form the user
     * @return a user if is a correct username and password.
     */
    protected User loginCredential(String userName, String userPassword) {
        if (checkCredential(userName, userPassword)) {
            return databaseFacade.getUserById(credential.getUserId());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setContentText("Username or Password is wrong");
            alert.showAndWait();
        }
        return null;
    }

    /**
     * Creates a new user in the system, no matter what type of user it is.
     * @param credential
     */
    protected void createNewLoginUser(Credential credential) {
        credential.setPassword(newHashPassword(credential.getPassword()));
        databaseFacade.createNewLoginUser(credential);
    }

    protected void editLoginUser(Credential credential) {
        if(!credential.getPassword().isEmpty()){
            credential.setPassword(newHashPassword(credential.getPassword()));
            databaseFacade.updatePassword(credential);
        }
        databaseFacade.updateLoginName(credential);
    }


    private boolean checkCredential(String userName, String userPassword) {
        credential = databaseFacade.checkCredential(userName);
        return credential != null && BCrypt.checkpw(userPassword, credential.getPassword());
    }

    private String newHashPassword(String password) {
        return BCrypt.hashpw(password, this.salt);
    }
}
