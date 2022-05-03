package gui.controller;

import be.Case;
import be.Person;

import gui.util.ISceneLoader;
import gui.util.PlanlægningScene;
import gui.util.SagsoplysningScene;
import gui.util.BestillingsScene;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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

    public void handleButtonSagsoplysning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<SagsoplysningController> sagsoplysningsScene =  new SagsoplysningScene();
        sagsoplysningsScene.loadNewScene(new Stage());
        SagsoplysningController sagsoplysningController = sagsoplysningsScene.getController();
        sagsoplysningController.setSagsoplysningsController(sagsoplysningController);
    }

    public void handleButtonBestilling(ActionEvent actionEvent) throws IOException {
        ISceneLoader<BestillingsViewController> bestillingsScene =  new BestillingsScene();
        bestillingsScene.loadNewScene(new Stage());
        BestillingsViewController bestillingsViewController = bestillingsScene.getController();
        bestillingsViewController.setBestillingsViewController(bestillingsViewController);
        bestillingsViewController.setCurrentCase(currentCase);
    }

    public void handleButtonPlanlægning(ActionEvent actionEvent) throws IOException {
        ISceneLoader<PlanlægningController> planlægningScene = new PlanlægningScene();
        planlægningScene.loadNewScene(new Stage());
        PlanlægningController planlægningController = planlægningScene.getController();
        planlægningController.setDashboardController(dashboardController);
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
