package gui.controller;

import be.Borger;
import be.Case;
import gui.model.CaseModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OpfoelgningController implements Initializable {

    @FXML
    private TextArea txtAreaOpfoelgning;
    @FXML
    private CheckBox checkBoxCloseCase;
    @FXML
    private CheckBox checkBoxContinueCase;
    @FXML
    private CheckBox checkBoxChangeCase;
    @FXML
    private CheckBox checkBoxRevisitationCase;

    private Case selectCase;
    private Borger selectCitizen;
    private DashboardController dashboardController;
    private CaseModel caseModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            selectCase = dashboardController.getSelectedCase();
            selectCitizen = dashboardController.getSelectedCitizen();

            txtAreaOpfoelgning.setText(selectCase.getOpfoelgningstagProperty().get());
            setOpfoelgningTag();
        });

    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }

    public void setCaseModel(CaseModel caseModel) {
        this.caseModel = caseModel;
    }

    private void setOpfoelgningTag(){
        String opfoelgningstag = selectCase.getOpfoelgningstagProperty().get();
        switch (opfoelgningstag){
            case "Afsluttes" -> {
                checkBoxCloseCase.setSelected(true);
            }
            case "Fortsættes" -> {
                checkBoxContinueCase.setSelected(true);
            }
            case "Ændres" -> {
                checkBoxChangeCase.setSelected(true);
            }
            case "Revisitation" -> {
                checkBoxRevisitationCase.setSelected(true);
            }
        }
    }

    public void handleCBClose(ActionEvent actionEvent) {
        selectCase.getOpfoelgningstagProperty().set("Afsluttes");
        checkBoxRevisitationCase.setSelected(false);
        checkBoxChangeCase.setSelected(false);
        checkBoxContinueCase.setSelected(false);
    }

    public void handleCBContinue(ActionEvent actionEvent) {
        selectCase.getOpfoelgningstagProperty().set("Fortsættes");
        checkBoxCloseCase.setSelected(false);
        checkBoxRevisitationCase.setSelected(false);
        checkBoxChangeCase.setSelected(false);
    }

    public void handleCBChange(ActionEvent actionEvent) {
        selectCase.getOpfoelgningstagProperty().set("Ændres");
        checkBoxRevisitationCase.setSelected(false);
        checkBoxContinueCase.setSelected(false);
        checkBoxCloseCase.setSelected(false);
    }

    public void handleCBRevisitation(ActionEvent actionEvent) {
        selectCase.getOpfoelgningstagProperty().set("Revisitation");
        checkBoxChangeCase.setSelected(false);
        checkBoxContinueCase.setSelected(false);
        checkBoxCloseCase.setSelected(false);
    }

    public void handleMouseGemOgLukScene(MouseEvent mouseEvent) {
    }

    public void handleMouseGemOgNæsteScene(MouseEvent mouseEvent) {
        caseModel.updateCaseOnCitizen(selectCitizen.getIDProperty().get(), selectCase);
    }
}
