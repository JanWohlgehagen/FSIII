package gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanlægningController implements Initializable {

    private DashboardController dashboardController;


    @FXML
    private TextArea txtAreaPlanlægning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {

            //txtAreaPlanlægning.setText(dashboardController.);

        });
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void handelVidere(ActionEvent actionEvent) {

    }
}
