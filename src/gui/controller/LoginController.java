package gui.controller;

import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        ISceneLoader<DashboardController> dashboardScene =  new DashboardScene();
        dashboardScene.loadNewScene(this.primaryStage);
        DashboardController dashboardSceneController = dashboardScene.getController();
    }
}
