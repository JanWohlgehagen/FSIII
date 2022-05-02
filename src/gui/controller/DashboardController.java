package gui.controller;

import be.Person;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {







    private DashboardController dashboardController;
    private Person loginPerson;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonSagsåbning(ActionEvent actionEvent) {
    }

    public void handleButtonOpfølgning(ActionEvent actionEvent) {
    }

    public void handleButtonSagsoplysning(ActionEvent actionEvent) {
    }

    public void handleButtonBestilling(ActionEvent actionEvent) {
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
