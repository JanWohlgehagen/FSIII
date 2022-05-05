package gui.controller;

import be.Borger;
import be.Case;
import gui.model.CaseModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanlaegningController implements Initializable {

    private DashboardController dashboardController;
    private CaseModel caseModel;
    private Case selectCase;
    private Borger selectCitizen;


    @FXML
    private TextArea txtAreaPlanlaegning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectCase = dashboardController.getSelectedCase();
            selectCitizen = dashboardController.getSelectedCitizen();

            txtAreaPlanlaegning.setText(selectCase.getPlanProperty().get());

        });
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void setCaseModel(CaseModel caseModel){
        this.caseModel = caseModel;
    }
    public void handelVidere(ActionEvent actionEvent) {
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
    }
}
