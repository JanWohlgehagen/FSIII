package gui.controller;

import be.Case;
import be.Person;
import gui.util.BestillingsScene;
import gui.util.DashboardScene;
import gui.util.ISceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {







    private DashboardController dashboardController;
    private Person loginPerson;
    private Case currentCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonSagsåbning(ActionEvent actionEvent) {
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) {
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) {
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        ISceneLoader<BestillingsViewController> bestillingsScene =  new BestillingsScene();
        bestillingsScene.loadNewScene(new Stage());
        BestillingsViewController bestillingsViewController = bestillingsScene.getController();
        bestillingsViewController.setBestillingsViewController(bestillingsViewController);
        bestillingsViewController.setCurrentCase(currentCase);
    }

    public void handleButtonPlanlægning(ActionEvent actionEvent) {
    }

    public void handleButtonLevering(ActionEvent actionEvent) {
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void setloginPerson(Person person){
        this.loginPerson = person;
    }
}
