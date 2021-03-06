package gui.controller;

import be.User;
import bll.ManagerFacade;
import dal.DatabaseFacade;
import gui.model.UserModel;
import gui.util.AdminDashboardScene;
import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private GridPane parentPaneGridPane;

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userModel = new UserModel(new ManagerFacade(new DatabaseFacade()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        User user = userModel.checkCredential(txtUsername.getText(), txtPassword.getText());
        if (user != null) {
            if (user.getUserType().equals(User.UserType.STUDENT) || user.getUserType().equals(User.UserType.TEACHER)) {
                ISceneLoader<DashboardController> dashboardScene = new DashboardScene();
                dashboardScene.loadNewScene((Stage) parentPaneGridPane.getScene().getWindow());
                DashboardController dashboardSceneController = dashboardScene.getController();
                dashboardSceneController.setDashboardController(dashboardSceneController);
                dashboardSceneController.setLoginPerson(user);

            } else if (user.getUserType().equals(User.UserType.ADMIN)) {
                ISceneLoader<AdminDashboardController> adminDashboardScene = new AdminDashboardScene();
                adminDashboardScene.loadNewScene((Stage) parentPaneGridPane.getScene().getWindow());

            } else throw new UnsupportedOperationException();
        }

    }


}
