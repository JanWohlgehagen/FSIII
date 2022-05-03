package gui.controller;

import be.Person;
import be.user.UserType;
import bll.CredentialManager;
import bll.ManagerFacade;
import gui.model.CredentialModel;
import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private Stage primaryStage;

    private CredentialModel credentialModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            credentialModel = new CredentialModel(new ManagerFacade());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        Person person = credentialModel.checkCredential(txtUsername.getText(), txtPassword.getText());
        if(person != null){
            switch (person.getUserType()){
                case STUDENT -> {
                    ISceneLoader<DashboardController> dashboardScene =  new DashboardScene();
                    dashboardScene.loadNewScene(this.primaryStage);
                    DashboardController dashboardSceneController = dashboardScene.getController();
                    dashboardSceneController.setDashboardController(dashboardSceneController);
                    dashboardSceneController.setloginPerson(person);
                }
                case TEACHER -> {
                    //TODO Implement this case
                    throw new UnsupportedOperationException();
                }
                case ADMIN -> {
                    //TODO Implement this case
                    throw new UnsupportedOperationException();
                }
                default ->  throw new UnsupportedOperationException();
            }
        }

    }

}
